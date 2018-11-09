package com.thinkgem.jeesite.modules.netconfig.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * @author chenhongbo
 * @version 2017-06-02
 */
public class TFormulaScripts extends DataEntity<TFormulaScripts>  {

	private static final long serialVersionUID = 1L;
	private String name;  //name
	private byte scriptsType; //scripts_type
	private List<TFormulaScriptsCommands> commandsList = new ArrayList<TFormulaScriptsCommands>(); //拥有命令列表
	
	public TFormulaScripts() {
		super();
	}

	public TFormulaScripts(String id){
		super(id);
	}

	@Length(min=0, max=50, message="name长度必须介于 0 和 50 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte getScriptsType() {
		return scriptsType;
	}

	public void setScriptsType(byte scriptsType) {
		this.scriptsType = scriptsType;
	}

	public List<TFormulaScriptsCommands> getCommandsList() {
		return commandsList;
	}

	public void setCommandsList(List<TFormulaScriptsCommands> commandsList) {
		this.commandsList = commandsList;
	}
	
}
