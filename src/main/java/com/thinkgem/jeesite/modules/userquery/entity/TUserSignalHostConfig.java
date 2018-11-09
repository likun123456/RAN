package com.thinkgem.jeesite.modules.userquery.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 信令追踪host配置Entity
 * @author zhuguangrui
 * @version 2017-07-07
 */
public class TUserSignalHostConfig extends DataEntity<TUserSignalHostConfig>  {

	private static final long serialVersionUID = 1L;
	
	private String ip;
	
	private String name;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
