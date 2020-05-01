package com.alacriti.poc.multipledbperf.persistence;


import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.alacriti.poc.multipledbperf.compress.ICompressor;
import com.alacriti.poc.multipledbperf.constants.Constants;
import com.alacriti.poc.multipledbperf.context.ExecutionContext;
import com.alacriti.poc.multipledbperf.util.ExceptionUtil;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;

@Component("PosgresS3PersistenceTypeHelper")
@Scope("prototype")
public class PosgresS3PersistenceTypeHelper extends MySQLS3PersistenceTypeHelper 
{
	protected Logger log = LoggerFactory.getLogger(PosgresS3PersistenceTypeHelper.class);


	public PosgresS3PersistenceTypeHelper()
	{
		super();
	}
	public PosgresS3PersistenceTypeHelper(String  compressorType) 
	{
		super(compressorType);
	}

	protected void persistIntoDB(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws Exception 
	{
		long startTime = System.currentTimeMillis();
		dao.addCustomer(recordList, Constants.PERSISTENCE_TYPE_POSGRES);
		persistenceContext.setTimeTakenInDBOperations(new AtomicLong(persistenceContext.getTimeTakenInDBOperations().get() + (System.currentTimeMillis()- startTime)/1000));
	}

}
