package com.alacriti.poc.multipledbperf.amf.loader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.alacriti.poc.multipledbperf.amf.parser.EnhancedStdAMFLinearParser;
import com.alacriti.poc.multipledbperf.constants.BatchConstants;
import com.alacriti.poc.multipledbperf.util.CollectionUtil;
import com.alacriti.poc.multipledbperf.util.ExceptionUtil;
import com.alacriti.poc.multipledbperf.util.StringUtils;
import com.alacriti.poc.multipledbperf.vo.CDFFieldDataTypeVO;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;
import com.alacriti.poc.multipledbperf.vo.PartnerCDFCustomFieldVO;



/**
 * This class will load AMF records including CDF fields
 *
 * @author ajinkyab
 *
 */
@Component
public class EnhancedAccountMasterLoadingHandler 
{
	private static Logger log = LoggerFactory.getLogger(EnhancedAccountMasterLoadingHandler.class);
	protected BufferedReader bufferedReader = null;
	protected InputStream inputStream = null;
	public static String FILE_CREATION_DATE_FORMAT = "yyyyMMddHHmmssSSS";
	public static String DETAILED_RECORD_DATE_FORMAT = "yyyyMMdd";

	protected long totalFailedRecords = 0;
	protected long totalDetailedRecords = 0;
	protected long totalBatches = 0;
	public static int MAX_FILE_HEADER_RECORD_SIZE = 140;
	public static int MAX_BATCH_HEADER_RECORD_SIZE = 140;
	public static int MAX_DETAIL_RECORD_SIZE = 750;
	public static int MAX_CDF_FIELD_COUNT_LENGTH = 3;

	public static int MAX_CDF_RECORD_SIZE = 70;
	public static int MAX_BATCH_TRAILER_RECORD_SIZE = 12;
	public static int MAX_FILE_TRAILER_RECORD_SIZE = 22;
	public static final int FAILED = 3;



	protected static int PRODUCT_ID_ORBIPAY = 2;
	public static final String STANDARD_AMF_DETAIL_RECORD_TYPE = "1";
	public static final String ENHANCED_AMF_DETAIL_RECORD_TYPE = "21";
	protected long lineNo = 0;
	protected long totalBatchDetailedRecordCount = 0;
	protected EnhancedStdAMFLinearParser parser = null;
	protected ArrayList<CustomerFeedVO> recordList = new ArrayList<CustomerFeedVO>();
	public static final int LOADED = 1;
	private static final String FEED_TABLE_NAME = null;

	public EnhancedAccountMasterLoadingHandler()
	{
		parser = getParser();
	}


	protected boolean isDetailRecord(String line)
	{
		return ENHANCED_AMF_DETAIL_RECORD_TYPE.equalsIgnoreCase(line.substring(0, 2));
	}
	public void connectToInboundAMFFile(String file) throws FileNotFoundException
	{
		setInputStream( new FileInputStream(file));
		setBufferedReader(new BufferedReader(new InputStreamReader(getInputStream())));

	}
	public void close() throws Exception
	{
		try
		{
			if (isValidResource(getBufferedReader()))
				ExceptionUtil.closeAndIgnoreBadPaddingException(getBufferedReader());

		}
		catch (Exception e)
		{
			log.error(
					"Exception occured at closeFile() finally block . " + ExceptionUtil.getStackTrace(e));
			throw new Exception("Exception occured at closeFile() finally block . ");

		}
		try
		{
			if (isValidResource(getInputStream()))
				getInputStream().close();
		}
		catch (Exception e)
		{
			log.error(
					"Exception occured at closeFile() finally block . " + ExceptionUtil.getStackTrace(e));
			throw new Exception("Exception occured at closeFile() finally block . ");

		}
	}

	public ArrayList<CustomerFeedVO> getCustomerBatchRecords(AtomicInteger at, int batchSize) throws Exception
	{
		if (log.isDebugEnabled())
			log.debug("loadCustomerRecords() start");

		try
		{
			String record = null;

			recordList = new ArrayList<CustomerFeedVO>();

			while((record = getRecord()) != null )
			{
				//	if (log.isDebugEnabled())
				//	log.info("At line no : " + (lineNo + 1) + " Received Record: \n" + record);
				String recordType = parser.getRecordType(record);

				if (isFileHeader(recordType))
					lineNo++;
				else if (isBatchHeader(recordType))			
					lineNo++;
				else if (isDetailedRecord(recordType))
				{
					lineNo++;

					totalDetailedRecords++;
					totalBatchDetailedRecordCount++;

					CustomerFeedVO cvo = parser.getCustomerFeedVO(record);

					enrichFields(record,cvo,at);
					if (isCDFFieldsReceived(record, parser))
						parser.setCommonDataFields(record, cvo);

					recordList.add(cvo);

					if (recordList.size() >= batchSize)
						break;

				}
				else if (isBatchTrailer(recordType))
				{
					lineNo++;
					totalBatchDetailedRecordCount = 0;
					totalBatches++;
				}
				else if (isFileTrailer(recordType))
					lineNo++;
				else
				{
					log.error("Error in loading the records since unknown record type found at line no:" + lineNo
							+ " with below line :\n" + record);
					throw new Exception(" Error in loading the records since unknown record type found at ilne no:"
							+ lineNo + " with below line :\n" + record);
				}

			}

			return recordList;


		}
		catch (Exception e)
		{
			log.error("Exception occured at loadCustomerRecords()" + ExceptionUtil.getStackTrace(e));
			throw new Exception("Exception occured at loadCustomerRecords()");

		}
		finally
		{

		}
	}

	private void enrichFields(String entryDetailRecord, CustomerFeedVO cvo, AtomicInteger at) 
	{
		if(cvo == null)
			return;

		cvo.setFileType(1234);
		cvo.setRecordStatus(LOADED);
		cvo.setFileId(at.incrementAndGet());
		cvo.setProductId(2);


		cvo.setMainPartnerKey("pkey");
		cvo.setDetailedRecord(entryDetailRecord);
		cvo.setFeedTableName(FEED_TABLE_NAME);

	}


	protected boolean isCDFFieldsReceived(String record, EnhancedStdAMFLinearParser parser)
	{
		return record.length() >= (MAX_DETAIL_RECORD_SIZE + MAX_CDF_FIELD_COUNT_LENGTH);

	}


	protected boolean isDetailedRecord(String recordType)
	{
		return StringUtils.areEqual(recordType, BatchConstants.RECORD_TYPE_DETAIL);
	}

	protected boolean isFileTrailer(String recordType)
	{
		return StringUtils.areEqual(recordType, BatchConstants.RECORD_TYPE_FILE_TRAILER);
	}

	protected boolean isBatchTrailer(String recordType)
	{
		return StringUtils.areEqual(recordType, BatchConstants.RECORD_TYPE_BATCH_TRAILER);
	}

	protected boolean isBatchHeader(String recordType)
	{
		return StringUtils.areEqual(recordType, BatchConstants.RECORD_TYPE_BATCH_HEADER);
	}

	protected boolean isFileHeader(String recordType)
	{
		return StringUtils.areEqual(recordType, BatchConstants.RECORD_TYPE_FILE_HEADER);
	}

	public String getRecord() throws IOException
	{
		return getBufferedReader().readLine();
	}
	public boolean hasNoMoreRecords() 
	{
		return  CollectionUtil.isEmpty(this.recordList);
	}

	protected boolean isValidResource(Object object)
	{
		if (object == null)
		{
			return false;
		}
		return true;
	}

	protected EnhancedStdAMFLinearParser getParser()
	{
		return new EnhancedStdAMFLinearParser();
	}

	protected BufferedReader getBufferedReader()
	{
		return this.bufferedReader;
	}

	protected void setBufferedReader(BufferedReader bufferedReader)
	{
		this.bufferedReader = bufferedReader;
	}

	protected InputStream getInputStream()
	{
		return inputStream;
	}

	protected void setInputStream(InputStream inputStream)
	{
		this.inputStream = inputStream;
	}

}
