/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 计费欺诈抓包历史记录Entity
 * @author zhuguangrui
 * @version 2017-09-04
 */
public class CheatHistory extends DataEntity<CheatHistory> {
	
	private static final long serialVersionUID = 1L;
	private String recordTime;		// recordtime
	private String servedImsi;		// servedimsi
	private String servedMsisdn;		// servedmsisdn
	private String ratType;		// rattype
	private String freeTotal;		// freetotal
	private String total;		// total
	private String percent;		// percent
	private Integer timeh;		// timeh
	private Integer status;		// status
	private String cheatCase;		// cheatcase
	private String ip;		// ip
	
	private String netid;
	private String beginDate;
	private String endDate;
	
	private String ratingGroup;
	private String name;
	private String dataUp;
	private String dataDown;
	
	public CheatHistory() {
		super();
	}

	public CheatHistory(String id){
		super(id);
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	
	@Length(min=0, max=20, message="servedimsi长度必须介于 0 和 20 之间")
	public String getServedImsi() {
		return servedImsi;
	}

	public void setServedImsi(String servedImsi) {
		this.servedImsi = servedImsi;
	}
	
	@Length(min=0, max=20, message="servedmsisdn长度必须介于 0 和 20 之间")
	public String getServedMsisdn() {
		return servedMsisdn;
	}

	public void setServedMsisdn(String servedMsisdn) {
		this.servedMsisdn = servedMsisdn;
	}
	
	@Length(min=0, max=50, message="rattype长度必须介于 0 和 50 之间")
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
	
	@NotNull(message="timeh不能为空")
	public Integer getTimeh() {
		return timeh;
	}

	public void setTimeh(Integer timeh) {
		this.timeh = timeh;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=0, max=100, message="cheatcase长度必须介于 0 和 100 之间")
	public String getCheatCase() {
		return cheatCase;
	}

	public void setCheatCase(String cheatCase) {
		this.cheatCase = cheatCase;
	}
	
	@Length(min=0, max=20, message="ip长度必须介于 0 和 20 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRatingGroup() {
		return ratingGroup;
	}

	public void setRatingGroup(String ratingGroup) {
		this.ratingGroup = ratingGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDataUp() {
		return dataUp;
	}

	public void setDataUp(String dataUp) {
		this.dataUp = dataUp;
	}

	public String getDataDown() {
		return dataDown;
	}

	public void setDataDown(String dataDown) {
		this.dataDown = dataDown;
	}
	
}