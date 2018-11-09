/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单用户信令追踪SAEGW池组抓包Entity
 * @author zhuguangrui
 * @version 2017-06-16
 */
public class TCapEpgPath extends DataEntity<TCapEpgPath> {
	
	private static final long serialVersionUID = 1L;
	private String pid;		// pid
	private String poolids;		// poolids
	private String poolNames;		// poolnames
	private String msisdn;		// msisdn
	private String pcapPath;		// pcappath
	private String pcapName;		// pcapname
	private String startTime;		// starttime
	private String endTime;		// endtime
	
	public TCapEpgPath() {
		super();
	}

	public TCapEpgPath(String id){
		super(id);
	}

	@Length(min=0, max=5, message="pid长度必须介于 0 和 5 之间")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Length(min=0, max=100, message="poolids长度必须介于 0 和 100 之间")
	public String getPoolids() {
		return poolids;
	}

	public void setPoolids(String poolids) {
		this.poolids = poolids;
	}
	
	@Length(min=0, max=500, message="poolnames长度必须介于 0 和 500 之间")
	public String getPoolNames() {
		return poolNames;
	}

	public void setPoolNames(String poolNames) {
		this.poolNames = poolNames;
	}
	
	@Length(min=0, max=20, message="msisdn长度必须介于 0 和 20 之间")
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	@Length(min=0, max=100, message="pcappath长度必须介于 0 和 100 之间")
	public String getPcapPath() {
		return pcapPath;
	}

	public void setPcapPath(String pcapPath) {
		this.pcapPath = pcapPath;
	}
	
	@Length(min=0, max=50, message="pcapname长度必须介于 0 和 50 之间")
	public String getPcapName() {
		return pcapName;
	}

	public void setPcapName(String pcapName) {
		this.pcapName = pcapName;
	}
	
	@Length(min=0, max=20, message="starttime长度必须介于 0 和 20 之间")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Length(min=0, max=20, message="endtime长度必须介于 0 和 20 之间")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}