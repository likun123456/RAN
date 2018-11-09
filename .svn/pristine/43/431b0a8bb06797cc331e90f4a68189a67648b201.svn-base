/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 公式配置Entity
 * @author liuliang
 * @version 2017-06-01
 */
public class TFormula extends DataEntity<TFormula> {
	
	private static final long serialVersionUID = 1L;
	private String nettype;		// 网元类型
	private Integer countertype;		// 统计类型
	private String thresholdup;		// 预警值上限
	private String thresholdup2;		// 阀值上限
	private String thresholddown;		// 预警值下限
	private String thresholddown2;		// 阀值下限
	private String resumeup;		// 恢复值上限
	private String resumeup2;		// 阀值恢复上限
	private String resumedown;		// 恢复值下限
	private String resumedown2;		// 阀值恢复下限
	private String formulatext;		// 公式内容
	private String formula;		// 公式名称
	private String name;		// 公式名称
	private String flag;		// 统计状态
	private String thresholdupScript;		// 预警脚本
	private String thresholdup2Script;		// 预警脚本
	private String thresholddownScript;		// 预警脚本
	private String thresholddown2Script;		// 预警脚本
	private String resumeupScript;		// 预警脚本
	private String resumeup2Script;		// 预警脚本
	private String resumedownScript;		// 预警脚本
	private String resumedown2Script;		// 预警脚本
	private Integer earlywarning;         //预警类型
	private String objtype;
	
	private Integer formulaType;	//公式类型
	private String topThreshold;	//公式超过该阀值数据上限
	private String downThreshold;	//公式超过该阀值数据下限
	
	public Integer getFormulaType() {
		return formulaType;
	}

	public void setFormulaType(Integer formulaType) {
		this.formulaType = formulaType;
	}

	public String getDownThreshold() {
		return downThreshold;
	}

	public void setDownThreshold(String downThreshold) {
		this.downThreshold = downThreshold;
	}

	public String getTopThreshold() {
		return topThreshold;
	}

	public void setTopThreshold(String topThreshold) {
		this.topThreshold = topThreshold;
	}

	public TFormula() {
		super();
	}

	public TFormula(String id){
		super(id);
	}

	@Length(min=0, max=10, message="公式类型长度必须介于 0 和 10 之间")
	public String getNettype() {
		return nettype;
	}

	public void setNettype(String nettype) {
		this.nettype = nettype;
	}
	
	public Integer getCountertype() {
		return countertype;
	}

	public void setCountertype(Integer countertype) {
		this.countertype = countertype;
	}
	
	@Length(min=0, max=10, message="预警值上限长度必须介于 0 和 10 之间")
	public String getThresholdup() {
		return thresholdup;
	}

	public void setThresholdup(String thresholdup) {
		this.thresholdup = thresholdup;
	}
	
	@Length(min=0, max=10, message="阀值上限长度必须介于 0 和 10 之间")
	public String getThresholdup2() {
		return thresholdup2;
	}

	public void setThresholdup2(String thresholdup2) {
		this.thresholdup2 = thresholdup2;
	}
	
	@Length(min=0, max=10, message="预警值下限长度必须介于 0 和 10 之间")
	public String getThresholddown() {
		return thresholddown;
	}

	public void setThresholddown(String thresholddown) {
		this.thresholddown = thresholddown;
	}
	
	@Length(min=0, max=10, message="阀值下限长度必须介于 0 和 10 之间")
	public String getThresholddown2() {
		return thresholddown2;
	}

	public void setThresholddown2(String thresholddown2) {
		this.thresholddown2 = thresholddown2;
	}
	
	@Length(min=0, max=10, message="恢复值上限长度必须介于 0 和 10 之间")
	public String getResumeup() {
		return resumeup;
	}

	public void setResumeup(String resumeup) {
		this.resumeup = resumeup;
	}
	
	@Length(min=0, max=10, message="阀值恢复上限长度必须介于 0 和 10 之间")
	public String getResumeup2() {
		return resumeup2;
	}

	public void setResumeup2(String resumeup2) {
		this.resumeup2 = resumeup2;
	}
	
	@Length(min=0, max=10, message="恢复值下限长度必须介于 0 和 10 之间")
	public String getResumedown() {
		return resumedown;
	}

	public void setResumedown(String resumedown) {
		this.resumedown = resumedown;
	}
	
	@Length(min=0, max=10, message="阀值恢复下限长度必须介于 0 和 10 之间")
	public String getResumedown2() {
		return resumedown2;
	}

	public void setResumedown2(String resumedown2) {
		this.resumedown2 = resumedown2;
	}
	
	@Length(min=0, max=1000, message="公式内容长度必须介于 0 和 1000 之间")
	public String getFormulatext() {
		return formulatext;
	}

	public void setFormulatext(String formulatext) {
		this.formulatext = formulatext;
	}
	
	@Length(min=0, max=1000, message="公式名称长度必须介于 0 和 1000 之间")
	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}
	
	@Length(min=0, max=300, message="公式名称长度必须介于 0 和 300 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=1, message="统计状态长度必须介于 0 和 1 之间")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Length(min=0, max=11, message="预警脚本长度必须介于 0 和 11 之间")
	public String getThresholdupScript() {
		return thresholdupScript;
	}

	public void setThresholdupScript(String thresholdupScript) {
		this.thresholdupScript = thresholdupScript;
	}
	
	@Length(min=0, max=11, message="预警脚本长度必须介于 0 和 11 之间")
	public String getThresholdup2Script() {
		return thresholdup2Script;
	}

	public void setThresholdup2Script(String thresholdup2Script) {
		this.thresholdup2Script = thresholdup2Script;
	}
	
	@Length(min=0, max=11, message="预警脚本长度必须介于 0 和 11 之间")
	public String getThresholddownScript() {
		return thresholddownScript;
	}

	public void setThresholddownScript(String thresholddownScript) {
		this.thresholddownScript = thresholddownScript;
	}
	
	@Length(min=0, max=11, message="预警脚本长度必须介于 0 和 11 之间")
	public String getThresholddown2Script() {
		return thresholddown2Script;
	}

	public void setThresholddown2Script(String thresholddown2Script) {
		this.thresholddown2Script = thresholddown2Script;
	}
	
	@Length(min=0, max=11, message="预警脚本长度必须介于 0 和 11 之间")
	public String getResumeupScript() {
		return resumeupScript;
	}

	public void setResumeupScript(String resumeupScript) {
		this.resumeupScript = resumeupScript;
	}
	
	@Length(min=0, max=11, message="预警脚本长度必须介于 0 和 11 之间")
	public String getResumeup2Script() {
		return resumeup2Script;
	}

	public void setResumeup2Script(String resumeup2Script) {
		this.resumeup2Script = resumeup2Script;
	}
	
	@Length(min=0, max=11, message="预警脚本长度必须介于 0 和 11 之间")
	public String getResumedownScript() {
		return resumedownScript;
	}

	public void setResumedownScript(String resumedownScript) {
		this.resumedownScript = resumedownScript;
	}
	
	@Length(min=0, max=11, message="预警脚本长度必须介于 0 和 11 之间")
	public String getResumedown2Script() {
		return resumedown2Script;
	}

	public void setResumedown2Script(String resumedown2Script) {
		this.resumedown2Script = resumedown2Script;
	}

	public Integer getEarlywarning() {
		return earlywarning;
	}

	public void setEarlywarning(Integer earlywarning) {
		this.earlywarning = earlywarning;
	}

	public String getObjtype() {
		return objtype;
	}

	public void setObjtype(String objtype) {
		this.objtype = objtype;
	}
	
}