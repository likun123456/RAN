/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.collectset.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 采集器配置Entity
 * @author yanghai
 * @version 2017-05-24
 */
public class TCollectordeploy extends DataEntity<TCollectordeploy> {
	
	private static final long serialVersionUID = 1L;
	private String collectorname;		// collectorname
	private String ip;		// ip
	private String username;		// username
	private String password;		// password
	private int coredataebmlog;
	private int coredatasub;
	private int coreebmlog;    //按池组汇总网元ebm数据 目前提供地图打点使用数据
	
	
	public int getCoreebmlog() {
		return coreebmlog;
	}

	public void setCoreebmlog(int coreebmlog) {
		this.coreebmlog = coreebmlog;
	}

	public TCollectordeploy() {
		super();
	}

	public TCollectordeploy(String id){
		super(id);
	}

	@Length(min=0, max=100, message="collectorname长度必须介于 0 和 100 之间")
	public String getCollectorname() {
		return collectorname;
	}

	public void setCollectorname(String collectorname) {
		this.collectorname = collectorname;
	}
	
	@Length(min=0, max=100, message="ip长度必须介于 0 和 100 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Length(min=0, max=50, message="username长度必须介于 0 和 50 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Length(min=0, max=50, message="password长度必须介于 0 和 50 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCoredataebmlog() {
		return coredataebmlog;
	}

	public void setCoredataebmlog(int coredataebmlog) {
		this.coredataebmlog = coredataebmlog;
	}

	public int getCoredatasub() {
		return coredatasub;
	}

	public void setCoredatasub(int coredatasub) {
		this.coredatasub = coredatasub;
	}

	
}