package com.alacriti.poc.multipledbperf.datasource;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alacriti.poc.multipledbperf.context.ExecutionContext;
import com.alacriti.poc.multipledbperf.util.CollectionUtil;
import com.alacriti.poc.multipledbperf.util.EnvironmentUtil;
import com.alacriti.poc.multipledbperf.util.ExceptionUtil;
import com.alacriti.poc.multipledbperf.vo.CustomerFeedVO;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.transfer.MultipleFileUpload;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.TransferManagerBuilder;

public class S3Manager 
{
	protected static Logger log = LoggerFactory.getLogger(S3Manager.class);

	protected static TransferManager getS3TransferManager() {
		return TransferManagerBuilder.standard()
				.withS3Client(getS3Client())
				.build();
	}

	protected static List<File> getFiles(ArrayList<CustomerFeedVO> recordList, String directoryPrefix)
	{
		List<File> files = new ArrayList<>();

		recordList.forEach(item->{
			try
			{
			//	if(isEncryptionRequired)
			//		files.add(Files.write(Paths.get("  "+item.getFileName()), item.getJsonTextRAW(),StandardOpenOption.CREATE).toFile());
			//	else
					files.add(Files.write(Paths.get("  "+item.getFileName()), item.getJsonText().getBytes(),StandardOpenOption.CREATE).toFile());
			} 
			catch (IOException e1) 
			{
				log.error("Exception occured at getFiles" + e1.getMessage());
			}
		});

		return files;
	}

	protected static AmazonS3 getS3Client()
	{

		//BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAS7YEJNCXFGRUDCUC", "9P1tdHzBKefuV0FI23jELe/QbcYWCMjrL+yPD3fG");
		AmazonS3  s3Client = AmazonS3ClientBuilder.standard()
				//	.withCredentials(new AWSStaticCredentialsProvider(awsCreds))
				.withRegion(EnvironmentUtil.getS3RegionName()).withForceGlobalBucketAccessEnabled(true).build();

		//        ListObjectsV2Result result =    s3Client.listObjectsV2(EnvironmentUtil.getS3bucketName());
		//        for (S3ObjectSummary objectSummary : result.getObjectSummaries()) {
		//            log.info("Key: " +  objectSummary.getKey() + " Size: "+ objectSummary.getSize());
		//        }

		return s3Client;
	}

	public static void persistIntoS3(ExecutionContext persistenceContext, ArrayList<CustomerFeedVO> recordList) throws AmazonClientException, InterruptedException 
	{

		TransferManager transferManager = null;
		List<File> files = null;
		try 
		{	
			transferManager = getS3TransferManager();

			files = getFiles(recordList,persistenceContext.getDirectoryPrefix());

			MultipleFileUpload xfer = transferManager.uploadFileList(EnvironmentUtil.getS3bucketName(),
					persistenceContext.getDirectoryPrefix(),new File("."), files);

			xfer.waitForCompletion();

			log.info("Files Upload completed with files uploaded: "  + files.size());
		}
		catch (AmazonServiceException e)
		{
			log.error("AmazonServiceException occured at persistIntoS3" , e);
			ExceptionUtil.getStackTrace(e);
			throw e;		
		}
		finally
		{
			if(transferManager != null)
				transferManager.shutdownNow(false);

			removeFiles(files);
		}
		
	}
	
	private static void removeFiles(List<File> files) 
	{
		if(CollectionUtil.isEmpty(files))
			return;

		files.forEach(item->{
			try
			{
				Files.deleteIfExists(item.toPath());
			} 
			catch (IOException e1) 
			{
				log.error("Exception occured at removeFiles" + e1.getMessage(),e1);
			}
		});


	}

	public static String getCustFeedJsonText(String externalStoragePath)
	{
		 S3Object obj = getS3Client().getObject(EnvironmentUtil.getS3bucketName(),externalStoragePath);
			S3ObjectInputStream s3InputStream = obj.getObjectContent();
			String jsonText = new BufferedReader(new InputStreamReader(s3InputStream)).lines()
					.parallel().collect(Collectors.joining("\n"));

			return jsonText;
	}
}
