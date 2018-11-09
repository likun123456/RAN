/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 话单欺诈业务报表Entity
 * @author zhuguangrui
 * @version 2017-08-30
 */
public class CheatReport extends DataEntity<CheatReport> {
	
	private static final long serialVersionUID = 1L;
	private String cheatCase;		// cheatcase
	private String reportName;		// reportname
	private String inused;		// inused
	
	public CheatReport() {
		super();
	}

	public CheatReport(String id){
		super(id);
	}

	@Length(min=0, max=50, message="cheatcase长度必须介于 0 和 50 之间")
	public String getCheatCase() {
		return cheatCase;
	}

	public void setCheatCase(String cheatCase) {
		this.cheatCase = cheatCase;
	}
	
	@Length(min=0, max=200, message="reportname长度必须介于 0 和 200 之间")
	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	
	@Length(min=0, max=1, message="inused长度必须介于 0 和 1 之间")
	public String getInused() {
		return inused;
	}

	public void setInused(String inused) {
		this.inused = inused;
	}
	
}