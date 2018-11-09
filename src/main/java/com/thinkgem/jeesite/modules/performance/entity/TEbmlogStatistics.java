/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.entity;

import java.math.BigInteger;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 异常原因多维分析(ebmlog)Entity
 * @author 王晶石
 * @version 2017-06-07
 */
public class TEbmlogStatistics extends DataEntity<TEbmlogStatistics> {
	
	private static final long serialVersionUID = 1L;
	private String netid;		    // netid
	private String datetime;		// datetime
	private String causecode;		// causecode
	private String subcausecode;	// subcausecode
	private String cc;		        // cc描述
	private String scc;		        // scc描述
	private String ccPlusScc;		// cc_plus_scc
	private String kName;		    // k_name
	private String kKey;		    // k_key
	private String kValue;		    // k_value
	private String eventType;       // 事件类型
	private String fname;           // 网元名称
	private BigInteger failures;    // 失败数
	private String action;          // 建议操作
	private String domain;
	private String startTime;
	private String endTime;
	private BigInteger reject;
	private BigInteger abort;
	private BigInteger ignore;
	private String eventResult;
	
	public TEbmlogStatistics() {
		super();
	}

	public TEbmlogStatistics(String id){
		super(id);
	}

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
	
	@Length(min=0, max=5, message="causecode长度必须介于 0 和 5 之间")
	public String getCausecode() {
		return causecode;
	}

	public void setCausecode(String causecode) {
		this.causecode = causecode;
	}
	
	@Length(min=0, max=5, message="subcausecode长度必须介于 0 和 5 之间")
	public String getSubcausecode() {
		return subcausecode;
	}

	public void setSubcausecode(String subcausecode) {
		this.subcausecode = subcausecode;
	}
	
	@Length(min=0, max=100, message="cc长度必须介于 0 和 100 之间")
	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}
	
	@Length(min=0, max=100, message="scc长度必须介于 0 和 100 之间")
	public String getScc() {
		return scc;
	}

	public void setScc(String scc) {
		this.scc = scc;
	}
	
	@Length(min=0, max=10, message="cc_plus_scc长度必须介于 0 和 10 之间")
	public String getCcPlusScc() {
		return ccPlusScc;
	}

	public void setCcPlusScc(String ccPlusScc) {
		this.ccPlusScc = ccPlusScc;
	}
	
	@Length(min=0, max=70, message="k_name长度必须介于 0 和 70 之间")
	public String getKName() {
		return kName;
	}

	public void setKName(String kName) {
		this.kName = kName;
	}
	
	@Length(min=0, max=50, message="k_key长度必须介于 0 和 50 之间")
	public String getKKey() {
		return kKey;
	}

	public void setKKey(String kKey) {
		this.kKey = kKey;
	}
	
	@Length(min=0, max=10, message="k_value长度必须介于 0 和 10 之间")
	public String getKValue() {
		return kValue;
	}

	public void setKValue(String kValue) {
		this.kValue = kValue;
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

	public BigInteger getFailures() {
		return failures;
	}

	public void setFailures(BigInteger failures) {
		this.failures = failures;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
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

	public BigInteger getReject() {
		return reject;
	}

	public void setReject(BigInteger reject) {
		this.reject = reject;
	}

	public BigInteger getAbort() {
		return abort;
	}

	public void setAbort(BigInteger abort) {
		this.abort = abort;
	}

	public BigInteger getIgnore() {
		return ignore;
	}

	public void setIgnore(BigInteger ignore) {
		this.ignore = ignore;
	}

	public String getEventResult() {
		return eventResult;
	}

	public void setEventResult(String eventResult) {
		this.eventResult = eventResult;
	}
	
	
	
}