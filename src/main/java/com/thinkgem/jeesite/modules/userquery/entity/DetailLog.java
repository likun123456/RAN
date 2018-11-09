package com.thinkgem.jeesite.modules.userquery.entity;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class DetailLog extends DataEntity<DetailLog>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String logType;
	private List<String> mmeLogList;
	private List<String> epgLogList;
	private List<String> pcrfLogList;
	
	public String getLogType() {
		return logType;
	}
	public void setLogType(String logType) {
		this.logType = logType;
	}
	public List<String> getMmeLogList() {
		return mmeLogList;
	}
	public void setMmeLogList(List<String> mmeLogList) {
		this.mmeLogList = mmeLogList;
	}
	public List<String> getEpgLogList() {
		return epgLogList;
	}
	public void setEpgLogList(List<String> epgLogList) {
		this.epgLogList = epgLogList;
	}
	public List<String> getPcrfLogList() {
		return pcrfLogList;
	}
	public void setPcrfLogList(List<String> pcrfLogList) {
		this.pcrfLogList = pcrfLogList;
	}
	
	
	
	

}
