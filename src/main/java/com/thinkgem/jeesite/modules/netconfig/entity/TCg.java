/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单表生成Entity
 * @author wangjingshi
 * @version 2017-05-25
 */
public class TCg extends DataEntity<TCg> {
	
	private static final long serialVersionUID = 1L;
	private String fname;		// f_name
	private String faddress;		// f_address
	private String fusername;		// f_username
	private String fpassword;		// f_password
	private int croom;
	public int getCroom() {
		return croom;
	}

	public void setCroom(int croom) {
		this.croom = croom;
	}

	public TCg() {
		super();
	}

	public TCg(String id){
		super(id);
	}

	@Length(min=0, max=50, message="f_name长度必须介于 0 和 50 之间")
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	@Length(min=0, max=50, message="f_address长度必须介于 0 和 50 之间")
	public String getFaddress() {
		return faddress;
	}

	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	
	@Length(min=0, max=50, message="f_username长度必须介于 0 和 50 之间")
	public String getFusername() {
		return fusername;
	}

	public void setFusername(String fusername) {
		this.fusername = fusername;
	}
	
	@Length(min=0, max=50, message="f_password长度必须介于 0 和 50 之间")
	public String getFpassword() {
		return fpassword;
	}

	public void setFpassword(String fpassword) {
		this.fpassword = fpassword;
	}
	
}