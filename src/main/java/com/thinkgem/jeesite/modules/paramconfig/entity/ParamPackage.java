package com.thinkgem.jeesite.modules.paramconfig.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class ParamPackage extends DataEntity<ParamPackage>  {

	private static final long serialVersionUID = 1L;

	private String packagename;
	
	private String nettype;
	
	private String version;
	
	private String updatetime;
	
	public ParamPackage() {
		super();
	}

	public ParamPackage(String id){
		super(id);
	}

	public String getPackagename() {
		return packagename;
	}

	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}

	public String getNettype() {
		return nettype;
	}

	public void setNettype(String nettype) {
		this.nettype = nettype;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	
}
