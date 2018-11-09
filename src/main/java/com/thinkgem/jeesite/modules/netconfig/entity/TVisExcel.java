package com.thinkgem.jeesite.modules.netconfig.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class TVisExcel extends DataEntity<TVisExcel> {
	private static final long serialVersionUID = -4784909926758624036L;
	private String name;
	private String type;
	private String templatetype;
	public String getTemplatetype() {
		return templatetype;
	}
	public void setTemplatetype(String templatetype) {
		this.templatetype = templatetype;
	}
	private int sort;
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
