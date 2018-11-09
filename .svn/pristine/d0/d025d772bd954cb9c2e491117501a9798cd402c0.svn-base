/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 抓包参数设置Entity
 * @author 王晶石
 * @version 2017-10-23
 */
public class TConfig extends DataEntity<TConfig> {
	
	private static final long serialVersionUID = 1L;
	private Long freeflow;		// 批量打包免费流量门限(byte)
	private Long freeflowfilter;		// 免费流量门限(byte)
	private String freepercent;		// 免费流量占比门限
	private String checkusercount;		// checkusercount
	private String catchpackageusercount;		// 抓包用户数
	private String currentprogramexecutiontime;		// currentprogramexecutiontime
	private String finishcatchpackagetime;		// finishcatchpackagetime 
	private String netName;
	
	public TConfig() {
		super();
	}

	public TConfig(String id){
		super(id);
	}

	public Long getFreeflow() {
		return freeflow;
	}

	public void setFreeflow(Long freeflow) {
		this.freeflow = freeflow;
	}
	
	public Long getFreeflowfilter() {
		return freeflowfilter;
	}

	public void setFreeflowfilter(Long freeflowfilter) {
		this.freeflowfilter = freeflowfilter;
	}
	
	public String getFreepercent() {
		return freepercent;
	}

	public void setFreepercent(String freepercent) {
		this.freepercent = freepercent;
	}
	
	@Length(min=0, max=5, message="checkusercount长度必须介于 0 和 5 之间")
	public String getCheckusercount() {
		return checkusercount;
	}

	public void setCheckusercount(String checkusercount) {
		this.checkusercount = checkusercount;
	}
	
	@Length(min=0, max=5, message="抓包用户数长度必须介于 0 和 5 之间")
	public String getCatchpackageusercount() {
		return catchpackageusercount;
	}

	public void setCatchpackageusercount(String catchpackageusercount) {
		this.catchpackageusercount = catchpackageusercount;
	}
	
	@Length(min=0, max=20, message="currentprogramexecutiontime长度必须介于 0 和 20 之间")
	public String getCurrentprogramexecutiontime() {
		return currentprogramexecutiontime;
	}

	public void setCurrentprogramexecutiontime(String currentprogramexecutiontime) {
		this.currentprogramexecutiontime = currentprogramexecutiontime;
	}
	
	@Length(min=0, max=20, message="finishcatchpackagetime长度必须介于 0 和 20 之间")
	public String getFinishcatchpackagetime() {
		return finishcatchpackagetime;
	}

	public void setFinishcatchpackagetime(String finishcatchpackagetime) {
		this.finishcatchpackagetime = finishcatchpackagetime;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}
	
	
	
}