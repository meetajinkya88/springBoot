package com.alacriti.poc.multipledbperf.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alacriti.poc.multipledbperf.amf.loader.EnhancedAccountMasterLoadingHandler;
import com.alacriti.poc.multipledbperf.compress.ICompressor;
import com.alacriti.poc.multipledbperf.compress.factory.CompressorFactory;
import com.alacriti.poc.multipledbperf.constants.Constants;
import com.alacriti.poc.multipledbperf.crypto.EncryptionUtil;
import com.alacriti.poc.multipledbperf.persistence.IPersistenceTypeHelper;
import com.alacriti.poc.multipledbperf.context.ExecutionContext;
import com.alacriti.poc.multipledbperf.persistence.PersistenceTypeFactory;
import com.alacriti.poc.multipledbperf.util.ExceptionUtil;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedMetaData;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;
import com.alacriti.poc.multipledbperf.vo.PersistRecordResponse;
import com.alacriti.poc.multipledbperf.vo.ReadRecordResponse;


@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class MultpleDBPerfService 
{
	protected Logger log = LoggerFactory.getLogger(MultpleDBPerfService.class);

	@Autowired
	protected PersistenceTypeFactory persistenceTypeFactory;


	protected EnhancedAccountMasterLoadingHandler loader;
	private AtomicInteger at = new AtomicInteger(0);
	protected IPersistenceTypeHelper persistenceHelper = null;
	protected ExecutionContext executionContext = null ;
	protected EncryptionUtil encryptionUtil = null;



	public MultpleDBPerfService() 
	{
	}


	public PersistRecordResponse persistRecord(Map<String,String> paramMap)
	{
		PersistRecordResponse persistRecordResponse = null ;
		log.info("HashCode of current Object is:"+ this.hashCode());
		try
		{
			prePersistRecord(paramMap);
			doPersistRecord(paramMap);
			return postPersistRecord(paramMap);
		}
		catch(Exception e)
		{
			log.error("Exception occured at process" , e);
			ExceptionUtil.getStackTrace(e);
			persistRecordResponse = new PersistRecordResponse(e.getMessage());
		}

		return persistRecordResponse;
	}

	private PersistRecordResponse postPersistRecord(Map<String, String> paramMap)
	{
		return  new PersistRecordResponse(executionContext.getTotalWriteCount(),executionContext.getTotalTimeTakenInWrite(),executionContext.getTimeTakenInS3Operations(),executionContext.getTimeTakenInDBOperations().get(),executionContext.getDirectoryPrefix());
	}

	private void doPersistRecord(Map<String, String> paramMap) throws Exception
	{
		long startTime = System.currentTimeMillis();
		boolean isEncryptionRequired = false;

		try
		{
			String fileName = paramMap.get(Constants.ParamConstants.PARAM_FILE);

			if(!isValidFile(fileName))
			{
				log.error("Invalid File :" + fileName);
				throw new Exception("Invalid File :" + fileName);
			}

			int batchSize  = 5000;

			if(paramMap.get(Constants.ParamConstants.PARAM_BATCH_SIZE) !=null)
				batchSize = Integer.parseInt(paramMap.get(Constants.ParamConstants.PARAM_BATCH_SIZE));

			if(paramMap.get(Constants.ParamConstants.PARAM_BATCH_SIZE) !=null)
				batchSize = Integer.parseInt(paramMap.get(Constants.ParamConstants.PARAM_BATCH_SIZE));

			String encRequired =  paramMap.get(Constants.ParamConstants.PARAM_ENCRYPTION_REQUIRED);

			if(encRequired != null && (encRequired.equalsIgnoreCase("true") || encRequired.equalsIgnoreCase("1")))
				isEncryptionRequired = true;

			if(isEncryptionRequired)
			{
				encryptionUtil = new EncryptionUtil();
				encryptionUtil.init();
			}


			log.info("\n Received Params : \n fileName:" + fileName + " \n Prefix:" + executionContext.getDirectoryPrefix() + " \n batchSize:" + batchSize );
			loader.connectToInboundAMFFile(fileName);

			while(true)
			{
				ArrayList<CustomerFeedVO> recordList = loader.getCustomerBatchRecords(at,batchSize);						

				if(hasNoMoreRecords())
					break;

				if(isEncryptionRequired)
					doBulkEncryption(recordList,encryptionUtil);

				persistenceHelper.persist(executionContext, recordList);

				executionContext.setTotalWriteCount(executionContext.getTotalWriteCount() + recordList.size());	

				log.info("Total Records written: " + executionContext.getTotalWriteCount());

			}

		}
		catch(Exception e)
		{
			log.error("Exception occured at doProcess" , e);
			ExceptionUtil.getStackTrace(e);
			throw e;

		}
		finally
		{
			loader.close();
		}
		executionContext.setTotalTimeTakenInWrite((System.currentTimeMillis() - startTime)/1000);

	}



	private void doBulkEncryption(ArrayList<CustomerFeedVO> recordList, EncryptionUtil encryptionUtil) 
	{
		//		for (CustomerFeedVO cvo : recordList)
		//		{
		//			cvo.setEncFieldNo(CustomerFeedVO.CUSTOMER_NUM);
		//			cvo.setOperation(CustomerFeedVO.ENCRYPTION);
		//		}
		//		encryptionUtil.desEncrypt(recordList);
		//
		//		for (CustomerFeedVO cvo : recordList)
		//		{
		//			cvo.setEncFieldNo(CustomerFeedVO.CUSTOMER_ID_NUM);
		//			cvo.setOperation(CustomerFeedVO.ENCRYPTION);
		//		}
		//		encryptionUtil.desEncrypt(recordList);
		//
		//		for (CustomerFeedVO cvo : recordList)
		//		{
		//			cvo.setEncFieldNo(CustomerFeedVO.UNIQUE_CUST_ID);
		//			cvo.setOperation(CustomerFeedVO.ENCRYPTION);
		//		}
		//		encryptionUtil.desEncrypt(recordList);
		//
		//		for (CustomerFeedVO cvo : recordList)
		//		{
		//			cvo.setEncFieldNo(CustomerFeedVO.JSON_TEXT);
		//			cvo.setOperation(CustomerFeedVO.ENCRYPTION);
		//		}
		//		encryptionUtil.desEncrypt(recordList);
		//

	}

	private boolean hasNoMoreRecords() 
	{
		return loader.hasNoMoreRecords();
	}

	private String getDirectoryPrefix(Map<String, String> paramMap) throws Exception 
	{
		String directoryPrefix =  paramMap.get(Constants.ParamConstants.PARAM_DIR_PREFIX);

		if(directoryPrefix == null || directoryPrefix.length() <=0)
			throw new Exception("Invalid Prefix:" + directoryPrefix);

		return directoryPrefix + "/" + System.currentTimeMillis() + "/";
	}

	private boolean isValidFile(String fileName)
	{
		try
		{
			if(fileName == null)
				return false;

			return new File(fileName).exists();
		}
		catch(Exception e)
		{
			log.error("Exception occured at isValidFile" + e.getMessage());
		}
		return false;
	}



	private void prePersistRecord(Map<String, String> paramMap) throws Exception 
	{
		if(loader == null)
			loader = new EnhancedAccountMasterLoadingHandler();

		executionContext = new ExecutionContext(getDirectoryPrefix(paramMap));		

		setPersistenceHelper(paramMap);	

	}


	private void setPersistenceHelper(Map<String, String> paramMap) throws Exception 
	{
		String persistenceType =  paramMap.get(Constants.ParamConstants.PARAM_PERSISTENCE_TYPE);

		persistenceHelper = persistenceTypeFactory.getPersistenceTypeHelper(persistenceType,paramMap.get(Constants.ParamConstants.PARAM_COMPRESSION_TYPE));
		if(persistenceHelper == null)
		{
			log.error("Persistence Helper is null...can't proceed further..");
			throw new Exception("Persistence Helper is null...can't proceed further..");
		}
		log.info("******* Peristence Helper Used:" + persistenceHelper.getClass().getName() + " ****************");
	}


	public ReadRecordResponse readRecord(Map<String, String> paramMap) 
	{

		ReadRecordResponse readRecordResponse = null ;
		try
		{
			preReadRecord(paramMap);
			doReadRecord(paramMap);
			return postReadRecord(paramMap);
		}
		catch(Exception e)
		{
			log.error("Exception occured at process" , e);
			ExceptionUtil.getStackTrace(e);
			readRecordResponse = new ReadRecordResponse(e.getMessage());
		}

		return readRecordResponse;
	}

	private ReadRecordResponse postReadRecord(Map<String, String> paramMap) 
	{	
		return new ReadRecordResponse(getThreadCount(paramMap),((Double)(executionContext.getReadCount().get() * 1.0/executionContext.getTotalTimeTakenInRead())),executionContext.getReadCount().get(),executionContext.getTotalTimeTakenInRead(),executionContext.getFailedReadCount().get(),null);
	}

	private void doReadRecord(Map<String, String> paramMap) throws Exception 
	{
		long startTime = System.currentTimeMillis();
		int threadCount = 0;
		int runTime = 0;
		ScheduledExecutorService executorService = null;
		ScheduledFuture [] future = null;
		int currRunTime = 0;
		CustomerFeedMetaData metaData = null;
		try
		{

			threadCount = getThreadCount(paramMap);
			runTime = getRunTime(paramMap);

			if(runTime <=0)
			{
				log.error("RunTime is invalid");
				throw new Exception("RunTime is invalid");
			}

			long metaDataStartTime = System.currentTimeMillis();
			metaData = getCustFeedMetaData();
			long metaDataTime = System.currentTimeMillis() - metaDataStartTime;

			log.info("Final Get MetaData Time (in sec)is : " + metaDataTime/1000);

			executorService = getExecutor(threadCount);

			future = scheduleReadTasks(executorService,future,threadCount,metaData);


			while(true)
			{
				if(currRunTime == runTime)
				{
					log.info("Current RunTime equals Expected Run Time.. returning");
					break;
				}

				Thread.sleep(5000);
				currRunTime += 5;

				log.info("ReadCount: " + executionContext.getReadCount() + " CurrentRuntime: " + currRunTime);
			}

			executionContext.setTotalTimeTakenInRead((System.currentTimeMillis()- startTime )/1000);

			for(int i = 0 ; i < future.length ; i++)
				future[i].cancel(false);

			log.info("All Threads cancelled ");
		}
		catch(Exception e)
		{
			log.error("Exception occured at doReadRecord"+ e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally
		{
			executorService.shutdown();
		}
	}

	private CustomerFeedMetaData getCustFeedMetaData() throws Exception 
	{
		return persistenceHelper.getCustomerFeedMetaData();
	}

	private ScheduledExecutorService getExecutor(int threadCount) {
		return Executors.newScheduledThreadPool(threadCount);
	}
	private ExecutorService getUpdateExecutor(int threadCount) {
		return Executors.newFixedThreadPool(threadCount);
	}
	private ScheduledFuture[] scheduleReadTasks(ScheduledExecutorService executorService, ScheduledFuture[] future,
			int threadCount, CustomerFeedMetaData metaData) throws Exception 
	{

		future = new ScheduledFuture[threadCount];

		for(int i=0; i <threadCount ; i++)
			future[i] = executorService.scheduleWithFixedDelay(new ReadRecordThread(metaData),Constants.THREAD_DELAY, Constants.THREAD_DELAY, TimeUnit.MILLISECONDS);

		if(future == null || future.length <=0)
		{
			log.error("No Future Object...returning...");
			throw new Exception("Unkwon Exception...");
		}


		return future;
	}

	private List<Future> updateRecordSubmitTasks(ExecutorService executorService,
			int threadCount, int batchSize, long updateRecordCount) throws Exception 
	{
		List<Future> future = new ArrayList<>();

		for(int i=0; i <threadCount ; )
		{
			long startIndex = i==0?0: (updateRecordCount/threadCount)*i + 1 ;
			long endIndex = startIndex + (updateRecordCount/threadCount) -1 ;

			future.add(i, executorService.submit(new UpdateRecordsThread(batchSize,startIndex,endIndex)));
			i++;
		}

		if(future == null || future.size() <=0)
		{
			log.error("No Future Object...returning...");
			throw new Exception("Unkwon Exception...");
		}


		return future;
	}

	private int getRunTime(Map<String, String> paramMap) 
	{
		String runTime = paramMap.get(Constants.ParamConstants.PARAM_RUN_TIME);	

		log.info("Run time:" + runTime);
		if(runTime == null || !isNumeric(runTime))
		{
			log.error("Invalid Run Time ..");
			return 0;		
		}
		return Integer.parseInt(runTime);
	}

	private int getThreadCount(Map<String, String> paramMap) 
	{
		String threadCount = paramMap.get(Constants.ParamConstants.PARAM_THREAD_COUNT);	

		log.info("Thread Count :" + threadCount);
		if(threadCount != null && isNumeric(threadCount))
			return Integer.parseInt(threadCount);
		return 1;
	}

	private boolean isNumeric(String threadCount)
	{
		try
		{
			return threadCount!= null && Integer.parseInt(threadCount) > 0;
		}
		catch(Exception e)
		{
			log.error("Thread count is not numeric:" + threadCount);
		}
		return false;
	}

	private void preReadRecord(Map<String, String> paramMap) throws Exception
	{
		setPersistenceHelper(paramMap);
		executionContext = new ExecutionContext();		

	}

	class UpdateRecordsThread implements Runnable
	{
		int batchSize = 0;
		long startIndex = 0;
		long maxIndex = 0;

		public UpdateRecordsThread(int batchSize, long startIndex, long maxIndex) 
		{
			this.startIndex = startIndex;
			this.maxIndex = maxIndex;
			this.batchSize = batchSize;

		}
		@Override
		public void run() 
		{
			long endIndex = 0;
			try
			{
				while(endIndex < maxIndex)
				{
					if((maxIndex - startIndex) > batchSize)
					{
						endIndex = startIndex + batchSize;
					}
					else
						endIndex= maxIndex;
					
					persistenceHelper.update(executionContext, startIndex,endIndex);
					
					startIndex = endIndex + 1;
				}
			}
			catch (Exception e) 
			{
				log.error("Exception occured at run" + e.getMessage());
				e.printStackTrace();
			}
		}

	}
	class ReadRecordThread implements Runnable
	{

		CustomerFeedMetaData metaData = null;
		public ReadRecordThread(CustomerFeedMetaData metaData) 
		{
			this.metaData = metaData;
		}

		@Override
		public void run()  
		{
			try 
			{
				persistenceHelper.read(executionContext,metaData);
				executionContext.getReadCount().incrementAndGet();

			}
			catch (Exception e)
			{
				log.error("Exception occured at run()" + e.getMessage(),e);
				executionContext.getFailedReadCount().incrementAndGet();
			}
		}



	}

	public PersistRecordResponse updateRecord(Map<String, String> paramMap)
	{
		PersistRecordResponse persistRecordResponse = null ;
		try
		{
			preUpdateRecord(paramMap);
			doUpdateRecord(paramMap);
			return postUpdateRecord(paramMap);
		}
		catch(Exception e)
		{
			log.error("Exception occured at updateRecord" , e);
			ExceptionUtil.getStackTrace(e);
			persistRecordResponse = new PersistRecordResponse(e.getMessage());
		}

		return persistRecordResponse;
	}


	private PersistRecordResponse postUpdateRecord(Map<String, String> paramMap) 
	{			
		return  new PersistRecordResponse(executionContext.getTotalWriteCount(),executionContext.getTotalTimeTakenInWrite(),executionContext.getTimeTakenInDBOperations().get());
	}


	private void doUpdateRecord(Map<String, String> paramMap) throws Exception
	{

		long startTime = System.currentTimeMillis();
		int threadCount = 0;
		long updateRecordCount = 10000;			
		int batchSize  = 5000;
		ExecutorService executorService = null;
		List<Future> future = null;

		try
		{

			threadCount = getThreadCount(paramMap);

			if(paramMap.get(Constants.ParamConstants.PARAM_BATCH_SIZE) !=null)
				batchSize = Integer.parseInt(paramMap.get(Constants.ParamConstants.PARAM_BATCH_SIZE));

			if(paramMap.get(Constants.ParamConstants.PARAM_UPDATE_RECORD_COUNT) !=null)
				updateRecordCount = Integer.parseInt(paramMap.get(Constants.ParamConstants.PARAM_UPDATE_RECORD_COUNT));


			log.info("Update Records received input params: threadCount:" + threadCount + ", batchSize:" + batchSize
					+ " , Total Records to be updated:"  + updateRecordCount + "");


			executorService = getUpdateExecutor(threadCount);

			future = updateRecordSubmitTasks(executorService,threadCount,batchSize,updateRecordCount);

			while(true)
			{
				boolean allDone = true; 
				for(int i = 0 ; i < future.size() ; i++)
				{
					if(!future.get(i).isDone())
						allDone = false; 
				}

				if(allDone)
					break;

				log.info("Update Record thread sleeping..");
				Thread.sleep(5000);

			}			
		}
		catch(Exception e)
		{
			log.error("Exception occured at doUpdateRecord" , e);
			ExceptionUtil.getStackTrace(e);
			throw e;

		}
		finally
		{
			executionContext.setTotalWriteCount(updateRecordCount);	
			executionContext.setTotalTimeTakenInWrite((System.currentTimeMillis() - startTime)/1000);
		}

	}


	private void preUpdateRecord(Map<String, String> paramMap) throws Exception 
	{
		executionContext = new ExecutionContext();		
		setPersistenceHelper(paramMap);			
	}
}
