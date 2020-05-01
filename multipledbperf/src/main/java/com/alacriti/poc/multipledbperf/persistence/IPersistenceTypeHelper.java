package com.alacriti.poc.multipledbperf.persistence;

import java.util.ArrayList;
import java.util.Map;

import com.alacriti.poc.multipledbperf.context.ExecutionContext;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedMetaData;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;

public interface IPersistenceTypeHelper
{

	public void persist(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws Exception;

	public CustomerFeedMetaData getCustomerFeedMetaData() throws Exception;

	public void read(ExecutionContext executionContext, CustomerFeedMetaData metaData) throws Exception;
		
	public void update(ExecutionContext executionContext,  long startIndex, long endIndex)throws Exception;

}
