/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.common.socket.subentity;

/**
 * 网元基本信息操作Entity
 * @author 王晶石
 * @version 2017-05-25
 */
public class TNewnetelementSocket implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	private String fname;		// f_name
	private Long fid;		    // id
	private String ipadr;		// ipadr
	private String username1;	// username1
	private String password1;	// password1
	private String type;		// type
	

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	
	public String getIpadr() {
		return ipadr;
	}

	public void setIpadr(String ipadr) {
		this.ipadr = ipadr;
	}
	
	public String getUsername1() {
		return username1;
	}

	public void setUsername1(String username1) {
		this.username1 = username1;
	}
	
	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}
	
	
}