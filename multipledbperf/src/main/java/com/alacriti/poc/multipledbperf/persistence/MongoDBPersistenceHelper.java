package com.alacriti.poc.multipledbperf.persistence;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alacriti.poc.multipledbperf.context.ExecutionContext;
import com.alacriti.poc.multipledbperf.dao.mongodb.MongoDAO;
import com.alacriti.poc.multipledbperf.util.JSONUtil;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedMetaData;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;

@Component("MongoDBPersistenceHelper")
@Scope("prototype")
public class MongoDBPersistenceHelper extends MySQLPersistenceTypeHelper
{
	protected Logger log = LoggerFactory.getLogger(MongoDBPersistenceHelper.class);

	@Autowired 
	protected MongoDAO dao;
	protected long enrichmentTime;
	
	public MongoDBPersistenceHelper()
	{
		super();
	}
	
	@Override
	public void persist(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws Exception
	{
		log.info("*********************************************************");
		long enrichmentstartTime = System.currentTimeMillis();
		doEnrichment(persistenceContext,recordList);
		enrichmentTime += System.currentTimeMillis() - enrichmentstartTime;
		log.info("Enrichment Time till now:" + enrichmentTime/1000);

		log.info("Persisting into db with recordSize:" + recordList.size());
	
		persistIntoDB(persistenceContext,recordList);
	}

	protected void doEnrichment(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws Exception
	{
		super.doEnrichment(persistenceContext, recordList);
		long feedId = 0;
		long lastInstertedFeedId = 0;
		
		if(persistenceContext.getLastInstertedFeedId() <=0)
			 lastInstertedFeedId = dao.getLastInsertedFeedId();
		else
			lastInstertedFeedId = persistenceContext.getLastInstertedFeedId();
		
		for (CustomerFeedVO customerFeedVO : recordList) 
			customerFeedVO.setFeedId(++lastInstertedFeedId );

		log.info("Last Used Feed Record Id:" + (lastInstertedFeedId ));
		
		persistenceContext.setLastInstertedFeedId(lastInstertedFeedId);

	}
	protected void persistIntoDB(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws Exception 
	{
		long startTime = System.currentTimeMillis();
//		for(CustomerFeedVO feedVO: recordList)
			dao.addCustomers(recordList);
		
		persistenceContext.setTimeTakenInDBOperations(new AtomicLong(persistenceContext.getTimeTakenInDBOperations().get() + (System.currentTimeMillis()- startTime)/1000));

	}
	
	@Override
	public CustomerFeedMetaData getCustomerFeedMetaData() 
	{
		CustomerFeedMetaData customerFeedMetaData = new CustomerFeedMetaData();
		//customerFeedMetaData.setCount(count);
		customerFeedMetaData.setMinFeedId(dao.getFirstInsertedFeedId());
		customerFeedMetaData.setMaxFeedId(dao.getLastInsertedFeedId());
		return customerFeedMetaData;
	}

	protected CustomerFeedVO getCustomerFeedRecord(long feedId) 
	{
		return 	dao.getCustomerById(feedId);
	}
	
	@Override
	public void read(ExecutionContext executionContext, CustomerFeedMetaData metaData) throws Exception
	{

		long feedId = getRandomFeedId(metaData);
		CustomerFeedVO custFeedVO = getCustomerFeedRecord(feedId);

		custFeedVO = JSONUtil.convertJsonToObject(this.compressor.deCompress(custFeedVO.getJsonText()));
		
		log.info("MongoDB:Read Cust FeedVO for acct no:" + custFeedVO.getCustomerAccountNumber());

	}

}
