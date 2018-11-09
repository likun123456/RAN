/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 拓扑设备管理Entity
 * @author yh
 * @version 2018-03-08
 */
public class TEquipment extends DataEntity<TEquipment> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 设备名称
	private String type;		// 设备类型
	private String ip;		// ip
	private String username;		// username
	private String passwd;		// passwd
	
	public TEquipment() {
		super();
	}

	public TEquipment(String id){
		super(id);
	}

	@Length(min=1, max=100, message="设备名称长度必须介于 1 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=255, message="设备类型长度必须介于 1 和 255 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	
	@Length(min=0, max=100, message="passwd长度必须介于 0 和 100 之间")
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
}