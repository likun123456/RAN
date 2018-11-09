/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 计费欺诈日志Entity
 * @author zhuguangrui
 * @version 2017-09-06
 */
public class CheatLog extends DataEntity<CheatLog> {
	
	private static final long serialVersionUID = 1L;
	private String netid;		// netid
	private String date;		// date
	private String dateTime;		// datetime
	private String tempstamp;		// 时间戳
	private String cheatCase;		// 计费欺诈类型
	private String cheatNote;		// 计费欺诈备注
	private String imsi;		// imsi
	private String ip;		// 计费欺诈管控状态
	private String cheatState;		// cheatstate
	
	public CheatLog() {
		super();
	}

	public CheatLog(String id){
		super(id);
	}

	@Length(min=0, max=5, message="netid长度必须介于 0 和 5 之间")
	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}
	
	@Length(min=0, max=10, message="date长度必须介于 0 和 10 之间")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Length(min=0, max=20, message="datetime长度必须介于 0 和 20 之间")
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	@Length(min=0, max=20, message="时间戳长度必须介于 0 和 20 之间")
	public String getTempstamp() {
		return tempstamp;
	}

	public void setTempstamp(String tempstamp) {
		this.tempstamp = tempstamp;
	}
	
	@Length(min=0, max=100, message="计费欺诈类型长度必须介于 0 和 100 之间")
	public String getCheatCase() {
		return cheatCase;
	}

	public void setCheatCase(String cheatCase) {
		this.cheatCase = cheatCase;
	}
	
	@Length(min=0, max=200, message="计费欺诈备注长度必须介于 0 和 200 之间")
	public String getCheatNote() {
		return cheatNote;
	}

	public void setCheatNote(String cheatNote) {
		this.cheatNote = cheatNote;
	}
	
	@Length(min=0, max=20, message="imsi长度必须介于 0 和 20 之间")
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	
	@Length(min=0, max=20, message="计费欺诈管控状态长度必须介于 0 和 20 之间")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Length(min=0, max=2, message="cheatstate长度必须介于 0 和 2 之间")
	public String getCheatState() {
		return cheatState;
	}

	public void setCheatState(String cheatState) {
		this.cheatState = cheatState;
	}
	
}