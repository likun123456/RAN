/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * EPA节点管理Entity
 * @author 王晶石
 * @version 2017-11-15
 */
public class TDns extends DataEntity<TDns> {
	
	private static final long serialVersionUID = 1L;
	private String fname;		// f_name
	private String faddress;		// f_address
	private String fusername;		// f_username
	private String fpassword;		// f_password
	private int type;
	private int factory;
	private int fnid;
	
	public int getFnid() {
		return fnid;
	}

	public void setFnid(int fnid) {
		this.fnid = fnid;
	}

	public int getFactory() {
		return factory;
	}

	public void setFactory(int factory) {
		this.factory = factory;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public TDns() {
		super();
	}

	public TDns(String id){
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