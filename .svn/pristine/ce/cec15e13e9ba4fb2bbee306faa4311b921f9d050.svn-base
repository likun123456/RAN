/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.autocheck.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 智能巡检Entity
 * @author zhuguangrui
 * @version 2017-08-04
 */
public class TAutoCheckLog extends DataEntity<TAutoCheckLog> {
	
	private static final long serialVersionUID = 1L;
	private Long netId;		// 网元ID
	private Integer checkItem;		// 巡检项
	private String checkResult;		// 巡检结果
	private String checkTime;		// 巡检时间
	private String checkLog;		// 巡检日志
	private String pastScope;
	private String beginDate;
	private String endDate;
	private String netType;
	private String netName;
	private String itemName;
	private String netid;//网元ID
	
	public TAutoCheckLog() {
		super();
	}

	public TAutoCheckLog(String id){
		super(id);
	}

	public Long getNetId() {
		return netId;
	}

	public void setNetId(Long netId) {
		this.netId = netId;
	}
	
	public Integer getCheckItem() {
		return checkItem;
	}

	public void setCheckItem(Integer checkItem) {
		this.checkItem = checkItem;
	}
	
	@Length(min=0, max=20, message="巡检结果长度必须介于 0 和 20 之间")
	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	
	@Length(min=0, max=20, message="巡检时间长度必须介于 0 和 20 之间")
	public String getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	
	public String getCheckLog() {
		return checkLog;
	}

	public void setCheckLog(String checkLog) {
		this.checkLog = checkLog;
	}

	public String getPastScope() {
		return pastScope;
	}

	public void setPastScope(String pastScope) {
		this.pastScope = pastScope;
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

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}
	
	
}