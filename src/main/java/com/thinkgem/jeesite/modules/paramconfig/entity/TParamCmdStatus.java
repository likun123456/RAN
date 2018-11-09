/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.paramconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 网元参数修改任务状态Entity
 * @author 王晶石
 * @version 2017-06-17
 */
public class TParamCmdStatus extends DataEntity<TParamCmdStatus> {
	
	private static final long serialVersionUID = 1L;
	private Long netid;		// netid
	private String cmdcontent;		// 指令内容
	private String executetype;		// executetype
	private String executetime;		// 执行时间：格式yyyy-MM-dd HH:mm:ss
	private String executestatus;		// 执行状态: 0未执行，1已执行
	private String modcmd;		// modcmd
	private String fname;   //网元名称
	
	public TParamCmdStatus() {
		super();
	}

	public TParamCmdStatus(String id){
		super(id);
	}

	public Long getNetid() {
		return netid;
	}

	public void setNetid(Long netid) {
		this.netid = netid;
	}
	
	@Length(min=0, max=200, message="指令内容长度必须介于 0 和 200 之间")
	public String getCmdcontent() {
		return cmdcontent;
	}

	public void setCmdcontent(String cmdcontent) {
		this.cmdcontent = cmdcontent;
	}
	
	@Length(min=0, max=6, message="executetype长度必须介于 0 和 6 之间")
	public String getExecutetype() {
		return executetype;
	}

	public void setExecutetype(String executetype) {
		this.executetype = executetype;
	}
	
	@Length(min=0, max=35, message="执行时间：格式yyyy-MM-dd HH:mm:ss长度必须介于 0 和 35 之间")
	public String getExecutetime() {
		return executetime;
	}

	public void setExecutetime(String executetime) {
		this.executetime = executetime;
	}
	
	@Length(min=0, max=11, message="执行状态: 0未执行，1已执行长度必须介于 0 和 11 之间")
	public String getExecutestatus() {
		return executestatus;
	}

	public void setExecutestatus(String executestatus) {
		this.executestatus = executestatus;
	}
	
	@Length(min=0, max=500, message="modcmd长度必须介于 0 和 500 之间")
	public String getModcmd() {
		return modcmd;
	}

	public void setModcmd(String modcmd) {
		this.modcmd = modcmd;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	
	
}