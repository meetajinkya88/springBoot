package com.alacriti.poc.multipledbperf.persistence;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alacriti.poc.multipledbperf.compress.ICompressor;
import com.alacriti.poc.multipledbperf.compress.factory.CompressorFactory;
import com.alacriti.poc.multipledbperf.constants.Constants;
import com.alacriti.poc.multipledbperf.context.ExecutionContext;
import com.alacriti.poc.multipledbperf.datasource.S3Manager;
import com.alacriti.poc.multipledbperf.util.ExceptionUtil;
import com.alacriti.poc.multipledbperf.util.JSONUtil;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;
import com.amazonaws.AmazonClientException;


public abstract class BasePersistenceHelper implements IPersistenceTypeHelper
{
		
	protected Logger log = LoggerFactory.getLogger(BasePersistenceHelper.class);

	protected ICompressor compressor;

	protected void persistIntoS3(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws IOException, AmazonClientException, InterruptedException
	{
		long startTime = System.currentTimeMillis();

		S3Manager.persistIntoS3(persistenceContext,recordList);

		persistenceContext.setTimeTakenInS3Operations(persistenceContext.getTimeTakenInS3Operations()+ (System.currentTimeMillis()- startTime)/1000);

	}
	protected void getRemoteJsonFileFromS3(CustomerFeedVO custFeedVO) throws Exception 
	{
		String jsonText = S3Manager.getCustFeedJsonText(custFeedVO.getExternalStoragePath());
		CustomerFeedVO custFeedVO1 = JSONUtil.convertJsonToObject(this.compressor.deCompress(jsonText));
		//	log.info("Json for acct no:" + custFeedVO.getUniqueCustomerId() + " is : " + jsonText);
	}
	public void update(ExecutionContext executionContext, long startIndex, long endIndex)throws Exception
	{
		
	}
	
	
}
