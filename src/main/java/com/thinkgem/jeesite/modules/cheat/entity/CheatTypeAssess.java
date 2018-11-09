package com.thinkgem.jeesite.modules.cheat.entity;

import java.util.Map;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class CheatTypeAssess extends DataEntity<CheatTypeAssess>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String recordtime;
	private String timeH;
	private String timeD;
	private String timeM;
	private String imsi;
	private String msisdn;
	private String ratType;
	private String freeTotal;
	private String total;
	private String percent;
	private String cheatCase;
	private String cheatCaseChinese;
	private String proxyServerIp;
	private long cheatFlow;
	private int counts;
	private String proxyIp;
	
	private String netid;
	private String startTime;
	private String endTime;
	private String timeGranularity;
	
	private Map<String,String> innerCheatMap;
	
	
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	
	public String getTimeH() {
		return timeH;
	}
	public void setTimeH(String timeH) {
		this.timeH = timeH;
	}
	public String getTimeD() {
		return timeD;
	}
	public void setTimeD(String timeD) {
		this.timeD = timeD;
	}
	public String getTimeM() {
		return timeM;
	}
	public void setTimeM(String timeM) {
		this.timeM = timeM;
	}
	public String getImsi() {
		return imsi;
	}
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getRatType() {
		return ratType;
	}
	public void setRatType(String ratType) {
		this.ratType = ratType;
	}
	public String getFreeTotal() {
		return freeTotal;
	}
	public void setFreeTotal(String freeTotal) {
		this.freeTotal = freeTotal;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getCheatCase() {
		return cheatCase;
	}
	public void setCheatCase(String cheatCase) {
		this.cheatCase = cheatCase;
	}
	public String getProxyServerIp() {
		return proxyServerIp;
	}
	public void setProxyServerIp(String proxyServerIp) {
		this.proxyServerIp = proxyServerIp;
	}
	public String getCheatCaseChinese() {
		return cheatCaseChinese;
	}
	public void setCheatCaseChinese(String cheatCaseChinese) {
		this.cheatCaseChinese = cheatCaseChinese;
	}
	public long getCheatFlow() {
		return cheatFlow;
	}
	public void setCheatFlow(long cheatFlow) {
		this.cheatFlow = cheatFlow;
	}
	public int getCounts() {
		return counts;
	}
	public void setCounts(int counts) {
		this.counts = counts;
	}
	public String getProxyIp() {
		return proxyIp;
	}
	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}
	public Map<String, String> getInnerCheatMap() {
		return innerCheatMap;
	}
	public void setInnerCheatMap(Map<String, String> innerCheatMap) {
		this.innerCheatMap = innerCheatMap;
	}
	public String getNetid() {
		return netid;
	}
	public void setNetid(String netid) {
		this.netid = netid;
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
	public String getTimeGranularity() {
		return timeGranularity;
	}
	public void setTimeGranularity(String timeGranularity) {
		this.timeGranularity = timeGranularity;
	}
	
	

}
