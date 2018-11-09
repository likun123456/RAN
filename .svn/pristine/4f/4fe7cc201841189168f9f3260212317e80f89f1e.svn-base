/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 业务编码中文解释Entity
 * @author zhuguangrui
 * @version 2017-07-07
 */
public class TAllRatinggroupConfig extends DataEntity<TAllRatinggroupConfig> {
	
	private static final long serialVersionUID = 1L;
	private String ratinggroup;		// ratinggroup
	private String name;		// name
	private String type;		// type
	
	public TAllRatinggroupConfig() {
		super();
	}

	public TAllRatinggroupConfig(String id){
		super(id);
	}

	@Length(min=0, max=20, message="ratinggroup长度必须介于 0 和 20 之间")
	public String getRatinggroup() {
		return ratinggroup;
	}

	public void setRatinggroup(String ratinggroup) {
		this.ratinggroup = ratinggroup;
	}
	
	@Length(min=0, max=50, message="name长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=2, message="type长度必须介于 0 和 2 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}