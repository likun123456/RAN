package com.thinkgem.jeesite.modules.paramconfig.entity;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoCheckConfig;

public class ParamCollectConfig  extends DataEntity<ParamCollectConfig>  {

	private static final long serialVersionUID = 1L;

	private int type;
	
	private String collecttime;
	
	private int biztype;
	
	private List<TAutoCheckConfig> autoCheckConfig;
	
	public ParamCollectConfig() {
		super();
	}

	public ParamCollectConfig(String id){
		super(id);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCollecttime() {
		return collecttime;
	}

	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}

	public int getBiztype() {
		return biztype;
	}

	public void setBiztype(int biztype) {
		this.biztype = biztype;
	}

	public List<TAutoCheckConfig> getAutoCheckConfig() {
		return autoCheckConfig;
	}

	public void setAutoCheckConfig(List<TAutoCheckConfig> autoCheckConfig) {
		this.autoCheckConfig = autoCheckConfig;
	}
	
	
	
}
