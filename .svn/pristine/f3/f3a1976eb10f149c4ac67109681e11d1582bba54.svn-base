/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 话单欺诈类型中文设置Entity
 * @author zhuguangrui
 * @version 2017-08-24
 */
public class TCheatChinese extends DataEntity<TCheatChinese> {
	
	private static final long serialVersionUID = 1L;
	private String cheatCase;		// 欺诈类型
	private String cheatCaseChinese;		// 欺诈类型中文翻译
	private String loopholeType;		// 漏洞类型
	private String cheatPrinciple;		// 欺诈原理
	private String cheatScene;		// 欺诈场景
	private String cheatSceneAnalysis;		// 欺诈场景分析
	private String solution;		// 解决方案
	
	public TCheatChinese() {
		super();
	}

	public TCheatChinese(String id){
		super(id);
	}

	@Length(min=0, max=50, message="欺诈类型长度必须介于 0 和 50 之间")
	public String getCheatCase() {
		return cheatCase;
	}

	public void setCheatCase(String cheatCase) {
		this.cheatCase = cheatCase;
	}
	
	@Length(min=0, max=25, message="欺诈类型中文翻译长度必须介于 0 和 25 之间")
	public String getCheatCaseChinese() {
		return cheatCaseChinese;
	}

	public void setCheatCaseChinese(String cheatCaseChinese) {
		this.cheatCaseChinese = cheatCaseChinese;
	}
	
	@Length(min=0, max=1000, message="漏洞类型长度必须介于 0 和 1000 之间")
	public String getLoopholeType() {
		return loopholeType;
	}

	public void setLoopholeType(String loopholeType) {
		this.loopholeType = loopholeType;
	}
	
	@Length(min=0, max=1000, message="欺诈原理长度必须介于 0 和 1000 之间")
	public String getCheatPrinciple() {
		return cheatPrinciple;
	}

	public void setCheatPrinciple(String cheatPrinciple) {
		this.cheatPrinciple = cheatPrinciple;
	}
	
	@Length(min=0, max=1000, message="欺诈场景长度必须介于 0 和 1000 之间")
	public String getCheatScene() {
		return cheatScene;
	}

	public void setCheatScene(String cheatScene) {
		this.cheatScene = cheatScene;
	}
	
	@Length(min=0, max=1000, message="欺诈场景分析长度必须介于 0 和 1000 之间")
	public String getCheatSceneAnalysis() {
		return cheatSceneAnalysis;
	}

	public void setCheatSceneAnalysis(String cheatSceneAnalysis) {
		this.cheatSceneAnalysis = cheatSceneAnalysis;
	}
	
	@Length(min=0, max=1000, message="解决方案长度必须介于 0 和 1000 之间")
	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}
	
}