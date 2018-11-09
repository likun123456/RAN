package com.thinkgem.jeesite.modules.netconfig.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @author chenhongbo
 * @version 2017-06-02
 */
public class TFormulaScriptsCommands extends DataEntity<TFormulaScriptsCommands>   {

	private static final long serialVersionUID = 1L;

	private String command;//command;
	private Long orderNum;//order_num; 
	private int scriptsId;
	
	public TFormulaScriptsCommands() {
		super();
	}

	public TFormulaScriptsCommands(String id){
		super(id);
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public Long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Long orderNum) {
		this.orderNum = orderNum;
	}

	public int getScriptsId() {
		return scriptsId;
	}

	public void setScriptsId(int scriptsId) {
		this.scriptsId = scriptsId;
	}
	
	
}
