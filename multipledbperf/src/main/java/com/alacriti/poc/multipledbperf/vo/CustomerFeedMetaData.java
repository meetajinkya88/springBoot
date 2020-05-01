package com.alacriti.poc.multipledbperf.vo;

public class CustomerFeedMetaData 
{
	protected long minFeedId;
	protected long maxFeedId;
	protected long count;
	public long getMinFeedId() {
		return minFeedId;
	}
	public void setMinFeedId(long minFeedId) {
		this.minFeedId = minFeedId;
	}
	public long getMaxFeedId() {
		return maxFeedId;
	}
	public void setMaxFeedId(long maxFeedId) {
		this.maxFeedId = maxFeedId;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	
}
