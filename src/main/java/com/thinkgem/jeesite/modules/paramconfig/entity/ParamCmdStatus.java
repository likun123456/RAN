package com.thinkgem.jeesite.modules.paramconfig.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class ParamCmdStatus extends DataEntity<ParamCmdStatus> {

	private static final long serialVersionUID = 1L;

	private int netid;
	
	private String cmdcontent;
	
	private int executetype;
	
	private String executetime;
	
	private int executestatus;
	
	private String modcmd;

	public int getNetid() {
		return netid;
	}

	public void setNetid(int netid) {
		this.netid = netid;
	}

	public String getCmdcontent() {
		return cmdcontent;
	}

	public void setCmdcontent(String cmdcontent) {
		this.cmdcontent = cmdcontent;
	}

	public int getExecutetype() {
		return executetype;
	}

	public void setExecutetype(int executetype) {
		this.executetype = executetype;
	}

	public String getExecutetime() {
		return executetime;
	}

	public void setExecutetime(String executetime) {
		this.executetime = executetime;
	}

	public int getExecutestatus() {
		return executestatus;
	}

	public void setExecutestatus(int executestatus) {
		this.executestatus = executestatus;
	}

	public String getModcmd() {
		return modcmd;
	}

	public void setModcmd(String modcmd) {
		this.modcmd = modcmd;
	}
	
	
	
}
