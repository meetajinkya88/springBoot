package com.alacriti.poc.multipledbperf.vo;

public class ReadRecordResponse 
{
	
	protected long noOfParallelThreads;
	protected double noOfReadsPerSecond;
	protected long totalReadCount;
	protected String responseMsg;
	protected long totalTimeTakenInSec;
	protected long failedReadCount;
	
	
	
	
	public ReadRecordResponse(String responseMsg) {
		super();
		this.responseMsg = responseMsg;
	}
	public ReadRecordResponse(long noOfParallelThreads, double noOfReadsPerSecond, long totalReadCount,
			long totalTimeTakenInSec, long failedReadCount,String responseMsg) {
		super();
		this.noOfParallelThreads = noOfParallelThreads;
		this.noOfReadsPerSecond = noOfReadsPerSecond;
		this.totalReadCount = totalReadCount;
		this.responseMsg = responseMsg;
		this.totalTimeTakenInSec = totalTimeTakenInSec;
		this.failedReadCount = failedReadCount;
	}
	public long getNoOfParallelThreads() {
		return noOfParallelThreads;
	}
	public void setNoOfParallelThreads(long noOfParallelThreads) {
		this.noOfParallelThreads = noOfParallelThreads;
	}
	public double getNoOfReadsPerSecond() {
		return noOfReadsPerSecond;
	}
	public void setNoOfReadsPerSecond(double noOfReadsPerSecond) {
		this.noOfReadsPerSecond = noOfReadsPerSecond;
	}
	public long getTotalReadCount() {
		return totalReadCount;
	}
	public void setTotalReadCount(long totalReadCount) {
		this.totalReadCount = totalReadCount;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	public long getTotalTimeTakenInSec() {
		return totalTimeTakenInSec;
	}
	public void setTotalTimeTakenInSec(long totalTimeTakenInSec) {
		this.totalTimeTakenInSec = totalTimeTakenInSec;
	}
	public long getFailedReadCount() {
		return failedReadCount;
	}
	public void setFailedReadCount(long failedReadCount) {
		this.failedReadCount = failedReadCount;
	}

	
	
	

}
