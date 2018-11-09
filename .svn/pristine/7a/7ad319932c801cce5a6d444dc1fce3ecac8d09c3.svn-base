/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * OSS节点基本信息操作Entity
 * @author 王晶石
 * @version 2017-05-27
 */
public class TOss extends DataEntity<TOss> {
	
	private static final long serialVersionUID = 1L;
	private String fname;		// OSS名称
	private String faddress;		// OSS地址
	private String fusername;		// 用户名
	private String fpassword;		// 密码
	
	public TOss() {
		super();
	}

	public TOss(String id){
		super(id);
	}

	@Length(min=0, max=50, message="OSS名称长度必须介于 0 和 50 之间")
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	@Length(min=0, max=50, message="OSS地址长度必须介于 0 和 50 之间")
	public String getFaddress() {
		return faddress;
	}

	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	
	@Length(min=0, max=50, message="用户名长度必须介于 0 和 50 之间")
	public String getFusername() {
		return fusername;
	}

	public void setFusername(String fusername) {
		this.fusername = fusername;
	}
	
	@Length(min=0, max=50, message="密码长度必须介于 0 和 50 之间")
	public String getFpassword() {
		return fpassword;
	}

	public void setFpassword(String fpassword) {
		this.fpassword = fpassword;
	}
	
}