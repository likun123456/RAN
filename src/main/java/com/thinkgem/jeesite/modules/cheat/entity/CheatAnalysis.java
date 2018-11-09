package com.thinkgem.jeesite.modules.cheat.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class CheatAnalysis extends DataEntity<CheatAnalysis>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long freeTotal;
	private long total;
	private double percent;
	private String ratType;
	private String msisdn;
	private String netid;
	private String imsi;
	private String cheatCase;
	private String ratingGroup;
	
	private String startTime;
	private String endTime;
	private int freePrecentThreshold;
	private int freeThreshold;
	
	
	
	public long getFreeTotal() {
		return freeTotal;
	}
	public void setFreeTotal(long freeTotal) {
		this.freeTotal = freeTotal;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	public String getRatType() {
		return ratType;
	}
	public void setRatType(String ratType) {
		this.ratType = ratType;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getNetid() {
		return netid;
	}
	public void setNetid(String netid) {
		this.netid = netid;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getCheatCase() {
		return cheatCase;
	}
	public void setCheatCase(String cheatCase) {
		this.cheatCase = cheatCase;
	}
	public String getRatingGroup() {
		return ratingGroup;
	}
	public void setRatingGroup(String ratingGroup) {
		this.ratingGroup = ratingGroup;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getFreePrecentThreshold() {
		return freePrecentThreshold;
	}
	public void setFreePrecentThreshold(int freePrecentThreshold) {
		this.freePrecentThreshold = freePrecentThreshold;
	}
	public int getFreeThreshold() {
		return freeThreshold;
	}
	public void setFreeThreshold(int freeThreshold) {
		this.freeThreshold = freeThreshold;
	}
	

}
