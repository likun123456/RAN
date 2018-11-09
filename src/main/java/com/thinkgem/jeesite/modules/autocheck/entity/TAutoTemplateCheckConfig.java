package com.thinkgem.jeesite.modules.autocheck.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class TAutoTemplateCheckConfig extends DataEntity<TAutoTemplateCheckConfig> {

	private static final long serialVersionUID = 1L;
	
	private int netType;
	
	private int excelId;
	
	private int moduleId;
	
	private String itemName;
	
	private String collecttime;

	private String ids;
	
	private String names;
	
	public int getNetType() {
		return netType;
	}

	public void setNetType(int netType) {
		this.netType = netType;
	}

	public int getExcelId() {
		return excelId;
	}

	public void setExcelId(int excelId) {
		this.excelId = excelId;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCollecttime() {
		return collecttime;
	}

	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}
	
	
	
}
