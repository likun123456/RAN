/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 维度指标查询Entity
 * @author 王晶石
 * @version 2017-05-31
 */
public class TTacSuccessRate extends DataEntity<TTacSuccessRate> {
	
	private static final long serialVersionUID = 1L;
	private String netid;		// netid
	private String datetime;		// datetime
	private String tac;		// ratetype
	private String failureCount;		// failure_count
	private String totalCount;		// total_count
	private String successRate;		// success_rate
	private String eventType;       //事件类型 
	private String fname;
	
	public TTacSuccessRate() {
		super();
	}

	public TTacSuccessRate(String id){
		super(id);
	}

	@Length(min=0, max=20, message="netid长度必须介于 0 和 20 之间")
	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}
	
	@Length(min=0, max=30, message="datetime长度必须介于 0 和 30 之间")
	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	@Length(min=0, max=20, message="ratetype长度必须介于 0 和 20 之间")
	public String getTac() {
		return tac;
	}

	public void setTac(String tac) {
		this.tac = tac;
	}
	
	@Length(min=0, max=20, message="failure_count长度必须介于 0 和 20 之间")
	public String getFailureCount() {
		return failureCount;
	}

	public void setFailureCount(String failureCount) {
		this.failureCount = failureCount;
	}
	
	@Length(min=0, max=20, message="total_count长度必须介于 0 和 20 之间")
	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	
	public String getSuccessRate() {
		return successRate;
	}

	public void setSuccessRate(String successRate) {
		this.successRate = successRate;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	
}