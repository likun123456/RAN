/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 机房管理Entity
 * @author yh
 * @version 2018-03-08
 */
public class TComputerroom extends DataEntity<TComputerroom> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	
	public TComputerroom() {
		super();
	}

	public TComputerroom(String id){
		super(id);
	}

	@Length(min=0, max=300, message="name长度必须介于 0 和 300 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}