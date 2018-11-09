/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单用户信令追踪Entity
 * @author zhuguangrui
 * @version 2017-06-15
 */
public class TCapAllPath extends DataEntity<TCapAllPath> {
	
	private static final long serialVersionUID = 1L;
	private String netNames;	// 包括MME池组和PCRF网元的名称
	private String msisdn;		// 86开头手机号
	private String capPath;		// 抓包文件存放路径
	private String capName;		// 抓包文件名称
	private String htmlPath;	// 流程图文件存放路径
	private String startTime;	// 抓包开始的时间
	private String endTime;		// 抓包结束时间
	
	public TCapAllPath() {
		super();
	}

	public TCapAllPath(String id){
		super(id);
	}

	@Length(min=0, max=500, message="包括MME池组和PCRF网元的名称长度必须介于 0 和 500 之间")
	public String getNetNames() {
		return netNames;
	}

	public void setNetNames(String netNames) {
		this.netNames = netNames;
	}
	
	@Length(min=0, max=50, message="86开头手机号长度必须介于 0 和 50 之间")
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	
	@Length(min=0, max=100, message="抓包文件存放路径长度必须介于 0 和 100 之间")
	public String getCapPath() {
		return capPath;
	}

	public void setCapPath(String capPath) {
		this.capPath = capPath;
	}
	
	@Length(min=0, max=100, message="抓包文件名称长度必须介于 0 和 100 之间")
	public String getCapName() {
		return capName;
	}

	public void setCapName(String capName) {
		this.capName = capName;
	}
	
	@Length(min=0, max=100, message="流程图文件存放路径长度必须介于 0 和 100 之间")
	public String getHtmlPath() {
		return htmlPath;
	}

	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}
	
	@Length(min=0, max=50, message="抓包开始的时间长度必须介于 0 和 50 之间")
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	
	@Length(min=0, max=50, message="抓包结束时间长度必须介于 0 和 50 之间")
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}