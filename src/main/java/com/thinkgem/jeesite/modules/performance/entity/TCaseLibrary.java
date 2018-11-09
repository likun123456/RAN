/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 异常原因多维分析(ebmlog)Entity
 * @author 王晶石
 * @version 2017-06-14
 */
public class TCaseLibrary extends DataEntity<TCaseLibrary> {
	
	private static final long serialVersionUID = 1L;
	private Long cc;		// 对应事件的CC
	private Long scc;		// 对应事件SCC
	private String questiondescrible;		// 问题描述
	private String reasondescrible;		// 原因描述
	
	public TCaseLibrary() {
		super();
	}

	public TCaseLibrary(String id){
		super(id);
	}

	
	public Long getCc() {
		return cc;
	}

	public void setCc(Long cc) {
		this.cc = cc;
	}
	
	public Long getScc() {
		return scc;
	}

	public void setScc(Long scc) {
		this.scc = scc;
	}
	
	@Length(min=1, max=200, message="问题描述长度必须介于 1 和 200 之间")
	public String getQuestiondescrible() {
		return questiondescrible;
	}

	public void setQuestiondescrible(String questiondescrible) {
		this.questiondescrible = questiondescrible;
	}
	
	@Length(min=1, max=500, message="原因描述长度必须介于 1 和 500 之间")
	public String getReasondescrible() {
		return reasondescrible;
	}

	public void setReasondescrible(String reasondescrible) {
		this.reasondescrible = reasondescrible;
	}
	
}