/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author wangjingshi
 * @version 2017-05-24
 */
public class TPool extends DataEntity<TPool> {
	
	private static final long serialVersionUID = 1L;
	private String fpoolname;		// f_poolname
	private String ftype;		// f_type
	private String order;
	
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public TPool() {
		super();
	}

	public TPool(String id){
		super(id);
	}

	@Length(min=0, max=50, message="f_poolname长度必须介于 0 和 50 之间")
	public String getFpoolname() {
		return fpoolname;
	}

	public void setFpoolname(String fPoolname) {
		this.fpoolname = fPoolname;
	}
	
	@Length(min=0, max=50, message="f_type长度必须介于 0 和 50 之间")
	public String getFtype() {
		return ftype;
	}

	public void setFtype(String fType) {
		this.ftype = fType;
	}
	
}