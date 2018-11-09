package com.thinkgem.jeesite.modules.cheat.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

public class CaptureParamConfig  extends DataEntity<CaptureParamConfig>  {

	private static final long serialVersionUID = 1L;

	private String freeflow;//免费流量
	private String freePercent;//百分比
	private String checkUserCount;//连至SAEGW每次检查用户数量,默认50
	private String catchPackageUserCount;//连至抓包服务器每次抓取用户数量,默认20
	private String freeflowFilter;//批量打包免费流量门限
	
	public CaptureParamConfig() {
		super();
	}

	public CaptureParamConfig(String id){
		super(id);
	}

	public String getFreeflow() {
		return freeflow;
	}

	public void setFreeflow(String freeflow) {
		this.freeflow = freeflow;
	}

	public String getFreePercent() {
		return freePercent;
	}

	public void setFreePercent(String freePercent) {
		this.freePercent = freePercent;
	}

	public String getCheckUserCount() {
		return checkUserCount;
	}

	public void setCheckUserCount(String checkUserCount) {
		this.checkUserCount = checkUserCount;
	}

	public String getCatchPackageUserCount() {
		return catchPackageUserCount;
	}

	public void setCatchPackageUserCount(String catchPackageUserCount) {
		this.catchPackageUserCount = catchPackageUserCount;
	}

	public String getFreeflowFilter() {
		return freeflowFilter;
	}

	public void setFreeflowFilter(String freeflowFilter) {
		this.freeflowFilter = freeflowFilter;
	}
	
	
}
