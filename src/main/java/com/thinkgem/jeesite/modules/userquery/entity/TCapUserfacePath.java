/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单用户信令追踪用户面抓包Entity
 * @author 王晶石
 * @version 2017-08-04
 */
public class TCapUserfacePath extends DataEntity<TCapUserfacePath> {
	
	private static final long serialVersionUID = 1L;
	private String pid;		// pid
	private String imsi;		// imsi
	private String pcappath;		// pcappath
	private String datetime;		// datetime
	
	public TCapUserfacePath() {
		super();
	}

	public TCapUserfacePath(String id){
		super(id);
	}

	@Length(min=0, max=5, message="pid长度必须介于 0 和 5 之间")
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	@Length(min=0, max=30, message="imsi长度必须介于 0 和 30 之间")
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	
	@Length(min=0, max=200, message="pcappath长度必须介于 0 和 200 之间")
	public String getPcappath() {
		return pcappath;
	}

	public void setPcappath(String pcappath) {
		this.pcappath = pcappath;
	}
	
	@Length(min=0, max=20, message="datetime长度必须介于 0 和 20 之间")
	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
}