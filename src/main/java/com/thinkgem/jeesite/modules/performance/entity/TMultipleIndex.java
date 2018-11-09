package com.thinkgem.jeesite.modules.performance.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 性能指标综合查询Entity
 * @author 杨海
 * @version 2017-05-31
 */
public class TMultipleIndex extends DataEntity<TMultipleIndex> {
	
	private static final long serialVersionUID = 1L;
	private String netid;		// netid
	private String netType;
	private String endTime;		
	private String startTime;
	private String formulaType;
	
	public String getFormulaType() {
		return formulaType;
	}

	public void setFormulaType(String formulaType) {
		this.formulaType = formulaType;
	}

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public TMultipleIndex() {
		super();
	}

	public TMultipleIndex(String id){
		super(id);
	}
	public String getNetid() {
		return netid;
	}

	public void setNetid(String netid) {
		this.netid = netid;
	}
	
}