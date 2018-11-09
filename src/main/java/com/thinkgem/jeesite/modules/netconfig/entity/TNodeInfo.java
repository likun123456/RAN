package com.thinkgem.jeesite.modules.netconfig.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class TNodeInfo extends DataEntity<TNodeInfo>{
	private static final long serialVersionUID = -1172350224780744771L;
	private int netId;
	private String dateTime;
	private String eciStat;
	private String pdcKpi;
	private String listAlarms;
	private String listParamConf;
	private String healthcheck;
	public int getNetId() {
		return netId;
	}
	public void setNetId(int netId) {
		this.netId = netId;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getEciStat() {
		return eciStat;
	}
	public void setEciStat(String eciStat) {
		this.eciStat = eciStat;
	}
	public String getPdcKpi() {
		return pdcKpi;
	}
	public void setPdcKpi(String pdcKpi) {
		this.pdcKpi = pdcKpi;
	}
	public String getListAlarms() {
		return listAlarms;
	}
	public void setListAlarms(String listAlarms) {
		this.listAlarms = listAlarms;
	}
	public String getListParamConf() {
		return listParamConf;
	}
	public void setListParamConf(String listParamConf) {
		this.listParamConf = listParamConf;
	}
	public String getHealthcheck() {
		return healthcheck;
	}
	public void setHealthcheck(String healthcheck) {
		this.healthcheck = healthcheck;
	}
}
