/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 信令追踪DIAMETER端口设置Entity
 * @author 王晶石
 * @version 2017-07-31
 */
public class TDiameterConfig extends DataEntity<TDiameterConfig> {
	
	private static final long serialVersionUID = 1L;
	private String diameterPort;		// DIAMETER端口号
	private String diameterType;		// DIAMETER上层协议类型 （TCP/SCTP）
	
	public TDiameterConfig() {
		super();
	}

	public TDiameterConfig(String id){
		super(id);
	}

	@Length(min=0, max=500, message="DIAMETER端口号长度必须介于 0 和 500 之间")
	public String getDiameterPort() {
		return diameterPort;
	}

	public void setDiameterPort(String diameterPort) {
		this.diameterPort = diameterPort;
	}
	
	@Length(min=0, max=10, message="DIAMETER上层协议类型 （TCP/SCTP）长度必须介于 0 和 10 之间")
	public String getDiameterType() {
		return diameterType;
	}

	public void setDiameterType(String diameterType) {
		this.diameterType = diameterType;
	}
	
}