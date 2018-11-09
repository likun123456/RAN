package com.thinkgem.jeesite.modules.netconfig.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
/**
 * @author wangjingshi
 * @version 2018-03-23
 */
public class TTpXmlFile extends DataEntity<TCg>{
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String datetime;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	

}
