package com.alacriti.poc.multipledbperf.context;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ExecutionContext
{
	protected String directoryPrefix = null;
	protected long totalWriteCount;
	protected long totalTimeTakenInWrite;
	protected long timeTakenInS3Operations;
	protected AtomicLong timeTakenInDBOperations = new AtomicLong(0);
	
	protected AtomicInteger readCount = new AtomicInteger(0);
	protected AtomicInteger failedReadCount = new AtomicInteger(0);
	protected long totalTimeTakenInRead = 0;
	
	protected long lastInstertedFeedId ;
	
//	log.info("Last Inserted Feed Record Id:" + lastInstertedFeedId);
	long feedId = lastInstertedFeedId + 1;


	public ExecutionContext(String directoryPrefix)
	{
		this.directoryPrefix = directoryPrefix;
	}

	public ExecutionContext() {
		// TODO Auto-generated constructor stub
	}

	public String getDirectoryPrefix() {
		return directoryPrefix;
	}

	public void setDirectoryPrefix(String directoryPrefix) {
		this.directoryPrefix = directoryPrefix;
	}

	public long getTotalWriteCount() {
		return totalWriteCount;
	}

	public void setTotalWriteCount(long totalWriteCount) {
		this.totalWriteCount = totalWriteCount;
	}

	public long getTotalTimeTakenInWrite() {
		return totalTimeTakenInWrite;
	}

	public void setTotalTimeTakenInWrite(long totalTimeTakenInWrite) {
		this.totalTimeTakenInWrite = totalTimeTakenInWrite;
	}

	public long getTimeTakenInS3Operations() {
		return timeTakenInS3Operations;
	}

	public void setTimeTakenInS3Operations(long timeTakenInS3Operations) {
		this.timeTakenInS3Operations = timeTakenInS3Operations;
	}

	public AtomicLong getTimeTakenInDBOperations() {
		return timeTakenInDBOperations;
	}

	public void setTimeTakenInDBOperations(AtomicLong timeTakenInDBOperations) {
		this.timeTakenInDBOperations = timeTakenInDBOperations;
	}

	public AtomicInteger getReadCount() {
		return readCount;
	}

	public void setReadCount(AtomicInteger readCount) {
		this.readCount = readCount;
	}

	public AtomicInteger getFailedReadCount() {
		return failedReadCount;
	}

	public void setFailedReadCount(AtomicInteger failedReadCount) {
		this.failedReadCount = failedReadCount;
	}

	public long getTotalTimeTakenInRead() {
		return totalTimeTakenInRead;
	}

	public void setTotalTimeTakenInRead(long totalTimeTakenInRead) {
		this.totalTimeTakenInRead = totalTimeTakenInRead;
	}

	public long getLastInstertedFeedId() {
		return lastInstertedFeedId;
	}

	public void setLastInstertedFeedId(long lastInstertedFeedId) {
		this.lastInstertedFeedId = lastInstertedFeedId;
	}

	public long getFeedId() {
		return feedId;
	}

	public void setFeedId(long feedId) {
		this.feedId = feedId;
	}

	
}
