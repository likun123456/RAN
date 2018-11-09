package com.thinkgem.jeesite.modules.netconfig.entity;

import java.io.Serializable;

public class THwStatusSaegw implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String slot;//板卡ID
	private String configuredType;
	private String installedType;//板卡图片
	private String operationalState;
	private String adminState;
	private String color; //指示灯颜色
	private String crash; //板卡Crash记录
	private CardInfoSaegw cardInfo;
	
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public String getConfiguredType() {
		return configuredType;
	}
	public void setConfiguredType(String configuredType) {
		this.configuredType = configuredType;
	}
	public String getInstalledType() {
		return installedType;
	}
	public void setInstalledType(String installedType) {
		this.installedType = installedType;
	}
	public String getOperationalState() {
		return operationalState;
	}
	public void setOperationalState(String operationalState) {
		this.operationalState = operationalState;
	}
	public String getAdminState() {
		return adminState;
	}
	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public CardInfoSaegw getCardInfo() {
		return cardInfo;
	}
	public void setCardInfo(CardInfoSaegw cardInfo) {
		this.cardInfo = cardInfo;
	}
	public String getCrash() {
		return crash;
	}
	public void setCrash(String crash) {
		this.crash = crash;
	}
	
}
