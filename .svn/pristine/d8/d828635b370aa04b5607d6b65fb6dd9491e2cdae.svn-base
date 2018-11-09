/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单用户信令追踪配置Entity
 * @author zhuguangrui
 * @version 2017-06-16
 */
public class TCapConfig extends DataEntity<TCapConfig> {
	
	private static final long serialVersionUID = 1L;
	private String cpuLimit;		// cpu门限
	private String diskLimit;		// 磁盘使用率门限
	private String checkPeriod;		// 检查周期，单位秒
	
	public TCapConfig() {
		super();
	}

	public TCapConfig(String id){
		super(id);
	}

	@Length(min=0, max=5, message="cpu门限长度必须介于 0 和 5 之间")
	public String getCpuLimit() {
		return cpuLimit;
	}

	public void setCpuLimit(String cpuLimit) {
		this.cpuLimit = cpuLimit;
	}
	
	@Length(min=0, max=5, message="磁盘使用率门限长度必须介于 0 和 5 之间")
	public String getDiskLimit() {
		return diskLimit;
	}

	public void setDiskLimit(String diskLimit) {
		this.diskLimit = diskLimit;
	}
	
	@Length(min=0, max=10, message="检查周期，单位秒长度必须介于 0 和 10 之间")
	public String getCheckPeriod() {
		return checkPeriod;
	}

	public void setCheckPeriod(String checkPeriod) {
		this.checkPeriod = checkPeriod;
	}
	
}