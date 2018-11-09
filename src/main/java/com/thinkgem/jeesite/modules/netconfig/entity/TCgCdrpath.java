/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author wangjingshi
 * @version 2017-05-25
 */
public class TCgCdrpath extends DataEntity<TCgCdrpath> {
	
	private static final long serialVersionUID = 1L;
	private Long cgId;		// cg_id
	private Long netId;		// net_id
	private String path;		// path
	private String netName;
	private String cgName;
	private String address;
	private String userName;
	private String password;
	
	public TCgCdrpath() {
		super();
	}

	public TCgCdrpath(String id){
		super(id);
	}

	public Long getCgId() {
		return cgId;
	}

	public void setCgId(Long cgId) {
		this.cgId = cgId;
	}
	
	public Long getNetId() {
		return netId;
	}

	public void setNetId(Long netId) {
		this.netId = netId;
	}
	
	@Length(min=0, max=200, message="path长度必须介于 0 和 200 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}

	public String getCgName() {
		return cgName;
	}

	public void setCgName(String cgName) {
		this.cgName = cgName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}