package com.alacriti.poc.multipledbperf.vo;

public class PersistRecordResponse
{
	protected long totalRecordCount;
	protected long totalTimeTakenInSec;
	protected long timeTakenInS3OperationsInSec;
	protected long timeTakenInDBOperationsInSec;
	protected String fileLocationInS3;
	protected String responseMessage;
	
	
	public PersistRecordResponse(String responseMessage)
	{
		this.responseMessage = responseMessage;
	}
	
	public PersistRecordResponse(long totalRecordCount, long totalTimeTakenInSec,long timeTakenInDBOperationsInSec)
	{
		super();
		this.totalRecordCount = totalRecordCount;
		this.totalTimeTakenInSec = totalTimeTakenInSec;
		this.timeTakenInDBOperationsInSec = timeTakenInDBOperationsInSec;
		
	}
	public PersistRecordResponse(long totalRecordCount, long totalTimeTakenInSec,
			long timeTakenInS3OperationsInSec, long timeTakenInDBOperationsInSec,String fileLocationInS3) {
		super();
		this.totalRecordCount = totalRecordCount;
		this.totalTimeTakenInSec = totalTimeTakenInSec;
		this.timeTakenInS3OperationsInSec = timeTakenInS3OperationsInSec;
		this.timeTakenInDBOperationsInSec = timeTakenInDBOperationsInSec;
		this.fileLocationInS3 = fileLocationInS3;
	}
	public PersistRecordResponse()
	{
		
	}
	public long getTotalRecordCount() {
		return totalRecordCount;
	}
	public void setTotalRecordCount(long totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public long getTotalTimeTakenInSec() {
		return totalTimeTakenInSec;
	}

	public void setTotalTimeTakenInSec(long totalTimeTakenInSec) {
		this.totalTimeTakenInSec = totalTimeTakenInSec;
	}

	public long getTimeTakenInS3OperationsInSec() {
		return timeTakenInS3OperationsInSec;
	}

	public void setTimeTakenInS3OperationsInSec(long timeTakenInS3OperationsInSec) {
		this.timeTakenInS3OperationsInSec = timeTakenInS3OperationsInSec;
	}

	public long getTimeTakenInDBOperationsInSec() {
		return timeTakenInDBOperationsInSec;
	}

	public void setTimeTakenInDBOperationsInSec(long timeTakenInDBOperationsInSec) {
		this.timeTakenInDBOperationsInSec = timeTakenInDBOperationsInSec;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getFileLocationInS3() {
		return fileLocationInS3;
	}

	public void setFileLocationInS3(String fileLocationInS3) {
		this.fileLocationInS3 = fileLocationInS3;
	}
	
	
	
}
