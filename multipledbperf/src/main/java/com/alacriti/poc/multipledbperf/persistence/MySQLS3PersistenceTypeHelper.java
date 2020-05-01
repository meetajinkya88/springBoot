package com.alacriti.poc.multipledbperf.persistence;


import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.alacriti.poc.multipledbperf.compress.ICompressor;
import com.alacriti.poc.multipledbperf.compress.factory.CompressorFactory;
import com.alacriti.poc.multipledbperf.constants.Constants;
import com.alacriti.poc.multipledbperf.context.ExecutionContext;
import com.alacriti.poc.multipledbperf.dao.CustomerRepository;
import com.alacriti.poc.multipledbperf.util.ExceptionUtil;
import com.alacriti.poc.multipledbperf.util.JSONUtil;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedMetaData;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;


@Component("MySQLS3PersistenceTypeHelper")
@Scope("prototype")
public class MySQLS3PersistenceTypeHelper extends BasePersistenceHelper
{

	protected Logger log = LoggerFactory.getLogger(MySQLS3PersistenceTypeHelper.class);
	
	public MySQLS3PersistenceTypeHelper()
	{
		super();
	}
	
	@Autowired
	protected CustomerRepository dao;

	@Autowired
	protected CompressorFactory compressorFactory;

	
	
	public MySQLS3PersistenceTypeHelper(String compressionType)
	{
		this.compressor = getCompressor(compressionType);
		log.info("Compressor instance in Persistence Helper:" + compressor.getClass().getSimpleName());

	}
	
	public void setCompressor(String compressorType)
	{
		this.compressor = getCompressor(compressorType);
	}
	
	@Override
	public void persist(ExecutionContext executionContext, ArrayList<CustomerFeedVO> recordList) throws Exception 
	{

		doEnrichment(executionContext,recordList);

		log.info("\n Persisting into s3 with recordSize:" + recordList.size());

		persistIntoS3(executionContext,recordList);

		log.info("\n Persisting into db with recordSize:" + recordList.size());

		resetJsonText(recordList);
		persistIntoDB(executionContext,recordList);


	}
	private void resetJsonText(ArrayList<CustomerFeedVO> recordList) 
	{
		recordList.forEach(item->{
			try
			{ 
				item.setJsonText(null);
				item.setJsonTextRAW(null);
			} 
			catch (Exception e1) 
			{
				log.error("Exception occured at resetJsonText" + e1.getMessage());
			}
		});

	}

	protected void doEnrichment(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws Exception
	{
		JSONUtil.convertObjectToJson(this.compressor,recordList);

		setExternalStorageFile(persistenceContext,recordList);

	}



	protected void setExternalStorageFile(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws Exception
	{
		boolean isSuccess = recordList.stream().allMatch(item->{
			try {
				item.setExternalStoragePath(persistenceContext.getDirectoryPrefix());
				//item.setFileName(item.getUniqueCustomerIdRAW().toString() +".json");
				item.setFileName(item.getUniqueCustomerId() +".json");

				item.setAbsoluteFileName(item.getExternalStoragePath() + item.getFileName());
			} catch (Exception e) 
			{
				log.error("Exception occured at doEnrichment " + e);
				return false;
			}

			return true;
		});


	}


	protected void persistIntoDB(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws Exception 
	{
		long startTime = System.currentTimeMillis();
		dao.addCustomer(recordList, Constants.PERSISTENCE_TYPE_MYSQL);
		persistenceContext.setTimeTakenInDBOperations(new AtomicLong(persistenceContext.getTimeTakenInDBOperations().get() + (System.currentTimeMillis()- startTime)/1000));

	}

	@Override
	public CustomerFeedMetaData getCustomerFeedMetaData() 
	{
		return dao.getCustFeedMetaData();
	}

	@Override
	public void read(ExecutionContext executionContext, CustomerFeedMetaData metaData) throws Exception
	{

		long feedId = getRandomFeedId(metaData);
		CustomerFeedVO custFeedVO = getCustomerFeedRecord(feedId);

		getRemoteJsonFileFromS3(custFeedVO);

	}
	protected long getRandomFeedId(CustomerFeedMetaData metaData) 
	{
		return new Random().ints(Integer.parseInt(metaData.getMinFeedId()+""),Integer.parseInt((metaData.getMaxFeedId() + 1) + "")).findFirst().getAsInt();
	}

	protected CustomerFeedVO getCustomerFeedRecord(long feedId) 
	{
		return 	dao.getCustomerFeedRecord(feedId);
	}
	
	protected ICompressor getCompressor(String compressionType)
	{
		ICompressor compressor = null;
		try
		{
			compressor = compressorFactory.getCompressor(compressionType);
			if(compressor == null)
			{
				log.error("compressor  is null...can't proceed further..");
				throw new Exception("compressor is null...can't proceed further..");
			}
			log.info("******* Compressor Used:" + compressor.getClass().getName() + " ****************");
		}
		catch(Exception e)
		{
			log.error("Exception occured at getCompressor:" + ExceptionUtil.getStackTrace(e));
		}
		
		return compressor;
	}
}
