/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.autocheck.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 自动巡检配置Entity
 * @author zhuguangrui
 * @version 2017-08-05
 */
public class TAutoCheckConfig extends DataEntity<TAutoCheckConfig> {
	
	private static final long serialVersionUID = 1L;
	private String netType;		// 网元类型
	private String itemName;		// 巡检项
	private String itemLimit;		// 门限
	private String itemParam;		// 命令参数
	
	public TAutoCheckConfig() {
		super();
	}

	public TAutoCheckConfig(String id){
		super(id);
	}

	@Length(min=0, max=11, message="网元类型长度必须介于 0 和 11 之间")
	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}
	
	@Length(min=0, max=50, message="巡检项长度必须介于 0 和 50 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=0, max=5, message="门限长度必须介于 0 和 5 之间")
	public String getItemLimit() {
		return itemLimit;
	}

	public void setItemLimit(String itemLimit) {
		this.itemLimit = itemLimit;
	}
	
	@Length(min=0, max=10, message="命令参数长度必须介于 0 和 10 之间")
	public String getItemParam() {
		return itemParam;
	}

	public void setItemParam(String itemParam) {
		this.itemParam = itemParam;
	}
	
}