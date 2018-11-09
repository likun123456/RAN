/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.paramconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 网元参数信息报表Entity
 * @author 王晶石
 * @version 2017-06-16
 */
public class TParamExportedExcel extends DataEntity<TParamExportedExcel> {
	
	private static final long serialVersionUID = 1L;
	private String path;		// path
	private String datetime;		// datetime
	private String startTime;
	private String endTime;
	
	public TParamExportedExcel() {
		super();
	}

	public TParamExportedExcel(String id){
		super(id);
	}

	@Length(min=0, max=50, message="path长度必须介于 0 和 50 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=0, max=20, message="datetime长度必须介于 0 和 20 之间")
	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
	
}