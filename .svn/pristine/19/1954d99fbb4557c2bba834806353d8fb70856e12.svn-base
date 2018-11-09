/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 危险命令状态Entity
 * @author 王晶石
 * @version 2018-05-07
 */
public class TVisDangerStatus extends DataEntity<TVisDangerStatus> {
	
	private static final long serialVersionUID = 1L;
	private String status;		// status
	
	public TVisDangerStatus() {
		super();
	}

	public TVisDangerStatus(String id){
		super(id);
	}

	@Length(min=0, max=5, message="status长度必须介于 0 和 5 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}