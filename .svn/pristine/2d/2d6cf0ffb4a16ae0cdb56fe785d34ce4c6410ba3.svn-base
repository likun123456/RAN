/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 手机型号翻译Entity
 * @author zhuguangrui
 * @version 2017-07-07
 */
public class TImeitacTranslate extends DataEntity<TImeitacTranslate> {
	
	private static final long serialVersionUID = 1L;
	private String imeitac;		// imeitac
	private String phonename;		// phonename
	
	public TImeitacTranslate() {
		super();
	}

	public TImeitacTranslate(String id){
		super(id);
	}

	@Length(min=0, max=10, message="imeitac长度必须介于 0 和 10 之间")
	public String getImeitac() {
		return imeitac;
	}

	public void setImeitac(String imeitac) {
		this.imeitac = imeitac;
	}
	
	@Length(min=0, max=500, message="phonename长度必须介于 0 和 500 之间")
	public String getPhonename() {
		return phonename;
	}

	public void setPhonename(String phonename) {
		this.phonename = phonename;
	}
	
}