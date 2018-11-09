package com.thinkgem.jeesite.modules.netconfig.entity;

import java.io.Serializable;

public class CardInfoSaegw implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cardType;
	private String cpu = "0";
	private String memory = "0";
	private String sgwUserCount = "0";
	private String pgwUserCount = "0";
	private String boardInfo;

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getSgwUserCount() {
		return sgwUserCount;
	}

	public void setSgwUserCount(String sgwUserCount) {
		this.sgwUserCount = sgwUserCount;
	}

	public String getPgwUserCount() {
		return pgwUserCount;
	}

	public void setPgwUserCount(String pgwUserCount) {
		this.pgwUserCount = pgwUserCount;
	}

	public String getBoardInfo() {
		return boardInfo;
	}

	public void setBoardInfo(String boardInfo) {
		this.boardInfo = boardInfo;
	}

}
