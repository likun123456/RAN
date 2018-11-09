package com.thinkgem.jeesite.modules.performance.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * EBMLOG事件英文缩写-中文对照Entity
 * @author 王晶石
 * @version 2017-05-31
 */
public class TEbmEvent extends DataEntity<TEbmEvent>{
	
	private static final long serialVersionUID = 1L;
	private String eventname;
	private String eventfullname;
	
	public String getEventname() {
		return eventname;
	}
	public void setEventname(String eventname) {
		this.eventname = eventname;
	}
	public String getEventfullname() {
		return eventfullname;
	}
	public void setEventfullname(String eventfullname) {
		this.eventfullname = eventfullname;
	}
	
	

}
