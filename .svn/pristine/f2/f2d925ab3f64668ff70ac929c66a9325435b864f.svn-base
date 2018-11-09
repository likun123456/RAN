/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单用户信令追踪PCRF抓包Entity
 * @author zhuguangrui
 * @version 2017-06-16
 */
public class TCapPcrfPath extends DataEntity<TCapPcrfPath> {
	
	private static final long serialVersionUID = 1L;
	private String pid;		// pid
	private String netid;		// netid
	private String netName;
	private String capPath;		// cappath
	private String capName;		// capname
	private String startTime;		// starttime
	private String endTime;		// endtime
	
	public TCapPcrfPath() {
		super();
	}

	public TCapPcrfPath(String id){
		super(id);
	}

	@Length(min=0, max=5, message="pid长度必须介于 0 和 5 之间")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Length(min=0, max=5, message="netid长度必须介于 0 和 5 之间")
	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}
	
	@Length(min=0, max=100, message="cappath长度必须介于 0 和 100 之间")
	public String getCapPath() {
		return capPath;
	}

	public void setCapPath(String capPath) {
		this.capPath = capPath;
	}
	
	@Length(min=0, max=100, message="capname长度必须介于 0 和 100 之间")
	public String getCapName() {
		return capName;
	}

	public void setCapName(String capName) {
		this.capName = capName;
	}
	
	@Length(min=0, max=50, message="starttime长度必须介于 0 和 50 之间")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Length(min=0, max=50, message="endtime长度必须介于 0 和 50 之间")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}
	
}