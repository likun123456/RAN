/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 危险指令Entity
 * @author 王晶石
 * @version 2018-05-07
 */
public class TVisDanger extends DataEntity<TVisDanger> {
	
	private static final long serialVersionUID = 1L;
	private String nettype;		// nettype
	private String command;		// command
	
	public TVisDanger() {
		super();
	}

	public TVisDanger(String id){
		super(id);
	}

	@Length(min=0, max=20, message="nettype长度必须介于 0 和 20 之间")
	public String getNettype() {
		return nettype;
	}

	public void setNettype(String nettype) {
		this.nettype = nettype;
	}
	
	@Length(min=0, max=1000, message="command长度必须介于 0 和 1000 之间")
	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
}