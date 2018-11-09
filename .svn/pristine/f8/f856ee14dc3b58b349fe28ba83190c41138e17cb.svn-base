/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 执行模板日志Entity
 * @author 王晶石
 * @version 2018-04-19
 */
public class TVisExcelExcuteLog extends DataEntity<TVisExcelExcuteLog> {
	
	private static final long serialVersionUID = 1L;
	private String datetime;		// datetime
	private String netid;		// netid
	private String excelid;		// excelid
	private String excelname;		// excelname
	private String log;		// log
	private String netname;
	private String starttime;
	private String endtime;
	
	public TVisExcelExcuteLog() {
		super();
	}

	public TVisExcelExcuteLog(String id){
		super(id);
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	
	@Length(min=0, max=11, message="netid长度必须介于 0 和 11 之间")
	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}
	
	@Length(min=0, max=11, message="excelid长度必须介于 0 和 11 之间")
	public String getExcelid() {
		return excelid;
	}

	public void setExcelid(String excelid) {
		this.excelid = excelid;
	}
	
	@Length(min=0, max=255, message="excelname长度必须介于 0 和 255 之间")
	public String getExcelname() {
		return excelname;
	}

	public void setExcelname(String excelname) {
		this.excelname = excelname;
	}
	
	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public String getNetname() {
		return netname;
	}

	public void setNetname(String netname) {
		this.netname = netname;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	
}