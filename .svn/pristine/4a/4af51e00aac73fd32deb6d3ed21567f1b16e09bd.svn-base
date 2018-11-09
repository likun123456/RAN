/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 免费业务代码
 * @author zhuguangrui
 * @version 2017-08-22
 */
public class TFreeRatingGroup extends DataEntity<TFreeRatingGroup> {
	
	private static final long serialVersionUID = 1L;
	private String ratingGroup;		// ratinggroup
	private String name;		// name
	
	public TFreeRatingGroup() {
		super();
	}

	public TFreeRatingGroup(String id){
		super(id);
	}

	@Length(min=0, max=20, message="ratinggroup长度必须介于 0 和 20 之间")
	public String getRatingGroup() {
		return ratingGroup;
	}

	public void setRatingGroup(String ratingGroup) {
		this.ratingGroup = ratingGroup;
	}
	
	@Length(min=0, max=50, message="name长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}