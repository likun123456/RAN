package com.thinkgem.jeesite.common.socket.subentity;


public class ParamCollectConfigSocket implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private int type;
	
	private String collecttime;
	
	private int biztype;
	
	public ParamCollectConfigSocket() {
		super();
	}


	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCollecttime() {
		return collecttime;
	}

	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}

	public int getBiztype() {
		return biztype;
	}

	public void setBiztype(int biztype) {
		this.biztype = biztype;
	}
	
}
