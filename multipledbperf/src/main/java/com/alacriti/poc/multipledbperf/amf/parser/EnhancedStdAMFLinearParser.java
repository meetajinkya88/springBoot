package com.alacriti.poc.multipledbperf.amf.parser;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;
import com.alacriti.poc.multipledbperf.constants.BatchConstants;
import com.alacriti.poc.multipledbperf.util.ExceptionUtil;
import com.alacriti.poc.multipledbperf.util.StringUtils;


public class EnhancedStdAMFLinearParser
{
    public static String ENHANCED_STD_AMF_RECORD_TYPE_FILE_HEADER = "00";
    public static String ENHANCED_STD_AMF_RECORD_TYPE_BATCH_HEADER = "10";
    public static String ENHANCED_STD_AMF_RECORD_TYPE_DETAIL = "21";
    public static String ENHANCED_STD_AMF_RECORD_TYPE_BATCH_TRAILER = "80";
    public static String ENHANCED_STD_AMF_RECORD_TYPE_FILE_TRAILER = "90";

    public static int MAX_FILE_HEADER_RECORD_SIZE = 140;
    public static int MAX_BATCH_HEADER_RECORD_SIZE = 140;
    public static int MAX_DETAIL_RECORD_SIZE = 750;
    public static int MAX_CDF_RECORD_SIZE = 70;
    public static int MAX_BATCH_TRAILER_RECORD_SIZE = 12;
    public static int MAX_FILE_TRAILER_RECORD_SIZE = 22;
    public static int MAX_CDF_FIELD_COUNT_LENGTH = 3;

    public static int MAX_FILE_DETAIL_RECORD_COUNT_SIZE = 10;

    public static final int LOADED = 1;
    public static final int FAILED = 3;

    public static final int CDF_FIELD_KEY_LENGTH = 6;
    public static final int CDF_FIELD_VALUE_LENGTH = 64;
    public static final String FEED_TABLE_NAME = "ext_cust_feed_tbl";

    private static Logger log = LoggerFactory.getLogger(EnhancedStdAMFLinearParser.class);

    private static AtomicInteger at = new AtomicInteger(0);

    public EnhancedStdAMFLinearParser()
    {
        init();
    }

    public void init()
    {
    	
    }

    
    public String getRecordType(String record) throws Exception
    {
        if (StringUtils.areEqual(record.substring(0, 2), ENHANCED_STD_AMF_RECORD_TYPE_FILE_HEADER))
            return BatchConstants.RECORD_TYPE_FILE_HEADER;

        if (StringUtils.areEqual(record.substring(0, 2), ENHANCED_STD_AMF_RECORD_TYPE_BATCH_HEADER))
            return BatchConstants.RECORD_TYPE_BATCH_HEADER;

        if (StringUtils.areEqual(record.substring(0, 2), ENHANCED_STD_AMF_RECORD_TYPE_DETAIL))
            return BatchConstants.RECORD_TYPE_DETAIL;

        if (StringUtils.areEqual(record.substring(0, 2), ENHANCED_STD_AMF_RECORD_TYPE_BATCH_TRAILER))
            return BatchConstants.RECORD_TYPE_BATCH_TRAILER;

        if (StringUtils.areEqual(record.substring(0, 2), ENHANCED_STD_AMF_RECORD_TYPE_FILE_TRAILER))
            return BatchConstants.RECORD_TYPE_FILE_TRAILER;

        return null;
    }

    public CustomerFeedVO getCustomerFeedVO(String entryDetailRecord) throws Exception
    {
        if (log.isDebugEnabled())
            log.debug("getCustomerFeedVO() start ");

        CustomerFeedVO cvo = new CustomerFeedVO();

        setCustomerDetails(entryDetailRecord, cvo);
        return cvo;

    }

    public void setCommonDataFields(String entryDetailRecord, CustomerFeedVO cvo) throws Exception
    {
        if (log.isDebugEnabled())
            log.debug("setCommonDataFields() start ");

        Map<String, String> commonDataFieldsMap = new ConcurrentHashMap<String, String>();

        String cdfFieldCount = entryDetailRecord.substring(750, 753).trim();

        if (log.isDebugEnabled())
            log.debug("Received cdfFieldCount is : " + cdfFieldCount);

        if (!StringUtils.isEmpty(cdfFieldCount) && !StringUtils.isNumeric(cdfFieldCount))
        {
            log.error("Invalid cdfFieldCount received as : " + cdfFieldCount
                    + " which is not even numeric . Setting default value as -1.");
            cvo.setCdfFieldCount(-1);
        }
        else
            cvo.setCdfFieldCount(Integer.parseInt(cdfFieldCount));

        int startIndex, endIndex, maxIndex = 0;

        startIndex = MAX_DETAIL_RECORD_SIZE + MAX_CDF_FIELD_COUNT_LENGTH;
        maxIndex = getMaxIndexValue(entryDetailRecord);
        endIndex = MAX_DETAIL_RECORD_SIZE + MAX_CDF_FIELD_COUNT_LENGTH + MAX_CDF_RECORD_SIZE;

        while (endIndex <= maxIndex)
        {
            log.debug("Start Index : " + startIndex + " endIndex : " + endIndex + " maxIndex : " + maxIndex);

            String cdfField = entryDetailRecord.substring(startIndex, endIndex);

            populateCommonDataFieldToMap(commonDataFieldsMap, cdfField);

            startIndex = endIndex;
            endIndex = endIndex + MAX_CDF_RECORD_SIZE;
        }

        cvo.setCommonDataFieldsMap(commonDataFieldsMap);

        if (log.isDebugEnabled())
        {
            log.debug("Final CDF Map entries as below : ");
            for (Map.Entry<String, String> entry : commonDataFieldsMap.entrySet())
            {
                String key = entry.getKey();
                String value = entry.getValue();
                log.debug("key : " + key + " & value : " + value);
            }
        }

      
    }

    protected int getMaxIndexValue(String entryDetailRecord)
    {
        return entryDetailRecord.length();
    }

    protected void populateCommonDataFieldToMap(Map<String, String> commonDataFieldMap, String cdfField)
    {
        String cdfFieldKey = cdfField.substring(0, CDF_FIELD_KEY_LENGTH).trim();
        String cdfFieldVal = cdfField.substring(CDF_FIELD_KEY_LENGTH, MAX_CDF_RECORD_SIZE).trim();

        if (log.isDebugEnabled())
        {
            log.debug("Parsed CDF Field is : " + cdfField.trim());
            log.debug("CDF Field Key Received is : " + cdfFieldKey);
            log.debug("CDF Field value Received is : " + cdfFieldVal);
        }
        if (!StringUtils.isEmpty(cdfFieldKey))
            commonDataFieldMap.put(cdfFieldKey.toLowerCase(), cdfFieldVal);

    }

    protected void setCustomerDetails(String entryDetailRecord, CustomerFeedVO cvo) throws Exception

    {
        try
        {
            cvo.setPartnerKey(entryDetailRecord.substring(2, 52).trim());
        
            
          
            cvo.setCustomerAccountNumber(entryDetailRecord.substring(52, 84).trim());

            if(cvo.getCustomerAccountNumber() != null)
            	cvo.setCustomerAccountNumberRAW(cvo.getCustomerAccountNumber().getBytes());
       
            // cvo.setCustomerAccountNumberRAW(entryDetailRecord.substring(52,
            // 84).trim().getBytes());

            cvo.setExtCustomerId(entryDetailRecord.substring(84, 116).trim());
            
            if(cvo.getExtCustomerId() != null)
            	cvo.setExtCustomerIdRAW(cvo.getExtCustomerId().getBytes());
            
            cvo.setOnlineEnrollmentCode(entryDetailRecord.substring(116, 148).trim());
            cvo.setMasterAcctId(entryDetailRecord.substring(148, 180).trim());
            cvo.setIssuerStateCode(entryDetailRecord.substring(180, 182).trim());
            cvo.setHierLevel1Value(entryDetailRecord.substring(182, 198).trim());
            cvo.setHierLevel2Value(entryDetailRecord.substring(198, 214).trim());
            cvo.setHierLevel3Value(entryDetailRecord.substring(214, 230).trim());
            cvo.setFirstName(entryDetailRecord.substring(230, 262).trim());
            cvo.setLastName(entryDetailRecord.substring(262, 294).trim());
            
          
            cvo.setSsn(entryDetailRecord.substring(294, 309).trim());
            
            if(cvo.getSsn() != null)
            	cvo.setSsnRAW(cvo.getSsn().getBytes());
       
            
            cvo.setDateOfBirth(entryDetailRecord.substring(309, 317).trim());
            cvo.setEmailAddr(entryDetailRecord.substring(317, 417).trim());
            cvo.setHomePhone(entryDetailRecord.substring(417, 427).trim());
            cvo.setAddrLine1(entryDetailRecord.substring(427, 477).trim());
            cvo.setAddrLine2(entryDetailRecord.substring(477, 527).trim());
            cvo.setCity(entryDetailRecord.substring(527, 547).trim());
            cvo.setStateCode(entryDetailRecord.substring(547, 549).trim());
            cvo.setCountryCode(entryDetailRecord.substring(549, 552).trim());
            cvo.setZip1(entryDetailRecord.substring(552, 562).trim());

            cvo.setCurrStatementBalStr(entryDetailRecord.substring(562, 577).trim());
            cvo.setCurrentBalanceStr(entryDetailRecord.substring(577, 592).trim());
            cvo.setMinPaymentDueStr(entryDetailRecord.substring(592, 607).trim());
            cvo.setPastAmountDueStr(entryDetailRecord.substring(607, 622).trim());

            cvo.setPaymentDueDate(entryDetailRecord.substring(622, 630).trim());
            cvo.setBillingDate(entryDetailRecord.substring(630, 638).trim());
            cvo.setEligIndicator(entryDetailRecord.substring(638, 639).trim());
            cvo.setDiscountEligInd(entryDetailRecord.substring(639, 640).trim());
            cvo.setCardAccountUsageFlag(entryDetailRecord.substring(640, 641).trim());
            cvo.setBankAccountUsageFlag(entryDetailRecord.substring(641, 642).trim());
            cvo.setAcctEligIndicator(entryDetailRecord.substring(642, 643).trim());
            
            cvo.setUniqueCustomerId(cvo.getCustomerAccountNumber());
            if(cvo.getUniqueCustomerId() != null)
            	cvo.setUniqueCustomerIdRAW(cvo.getUniqueCustomerId().getBytes());
       
            // Acct Type and subtype not passed so harcoding them to 2 and 20 respectively
            cvo.setAcctType(BatchConstants.CUST_LOAN_ACCT_TYPE);
            cvo.setAcctSubType(BatchConstants.CUST_LOAN_ACCT_SUB_TYPE);

        
          
        }
        catch (Exception e)
        {
            log.error("Exception Occured at setCustomerDetails() :" + ExceptionUtil.getStackTrace(e));
            throw e;
        }
    }

}
