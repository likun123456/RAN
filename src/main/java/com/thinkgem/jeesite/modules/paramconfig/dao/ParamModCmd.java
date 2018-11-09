package com.thinkgem.jeesite.modules.paramconfig.dao;

import java.io.Serializable;

import com.thinkgem.jeesite.modules.paramconfig.entity.NewnetelementInfo;

public class ParamModCmd implements Serializable{
	
	private NewnetelementInfo newnetelementInfoVO;
	
	private String command;
	
	private String datetime;
	
	private long statusId;

	public NewnetelementInfo getNewnetelementInfoVO() {
		return newnetelementInfoVO;
	}

	public void setNewnetelementInfoVO(NewnetelementInfo newnetelementInfoVO) {
		this.newnetelementInfoVO = newnetelementInfoVO;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

}
