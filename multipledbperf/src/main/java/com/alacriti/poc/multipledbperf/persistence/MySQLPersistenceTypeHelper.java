package com.alacriti.poc.multipledbperf.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alacriti.poc.multipledbperf.constants.Constants;
import com.alacriti.poc.multipledbperf.context.ExecutionContext;
import com.alacriti.poc.multipledbperf.util.JSONUtil;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedMetaData;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;

@Component("MySQLPersistenceTypeHelper")
@Scope("prototype")
public class MySQLPersistenceTypeHelper extends MySQLS3PersistenceTypeHelper 
{

	protected Logger log = LoggerFactory.getLogger(MySQLPersistenceTypeHelper.class);

	public MySQLPersistenceTypeHelper()
	{
		super();
	}
	public MySQLPersistenceTypeHelper(String  compressorType) 
	{

		super(compressorType);
	}

	@Override
	public void persist(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws Exception
	{
		doEnrichment(persistenceContext,recordList);

		log.info("\n Persisting into db with recordSize:" + recordList.size());

		persistIntoDB(persistenceContext,recordList);

	}
	protected void doEnrichment(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws Exception
	{
		JSONUtil.convertObjectToJson(this.compressor,recordList);
	}

	@Override
	public void read(ExecutionContext executionContext, CustomerFeedMetaData metaData) throws Exception 
	{
		long feedId = getRandomFeedId(metaData);
		CustomerFeedVO custFeedVO = getCustomerFeedRecord(feedId);

		custFeedVO = JSONUtil.convertJsonToObject(this.compressor.deCompress(custFeedVO.getJsonText()));

		//	log.info("Read Cust FeedVO for acct no:" + custFeedVO.getCustomerAccountNumber());

	}

	public void update(ExecutionContext executionContext,long startIndex, long endIndex)throws Exception
	{

		List<String> accountBatch = null;

	
		log.info("For thread: " + Thread.currentThread().getName() + ", StartIndex: " + startIndex + ", EndIndex: " + endIndex);
		accountBatch = getAccounts(startIndex,endIndex);

		long start = System.currentTimeMillis();
		updateCustomer(accountBatch);

		executionContext.setTimeTakenInDBOperations(new AtomicLong(executionContext.getTimeTakenInDBOperations().get() +(System.currentTimeMillis() - start)/1000));

	}
	protected void updateCustomer(List<String> accountBatch) 
	{
		dao.updateCustomer(accountBatch, Constants.PERSISTENCE_TYPE_MYSQL);
	}
	private List<String> getAccounts(int batchSize, List<String> accounts) 
	{
		List<String> accountBatch = new ArrayList<>();
		new Random()
		.ints(0, accounts.size())
		.limit(batchSize)
		.forEach(p->accountBatch.add(accounts.get(p)));

		return accountBatch;
	}

	private List<String> getAccounts(long startIndex,long endIndex) 
	{
		return dao.getAccounts(startIndex,endIndex);
	}
}
