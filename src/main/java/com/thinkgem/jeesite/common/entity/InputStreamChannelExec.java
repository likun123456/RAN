package com.thinkgem.jeesite.common.entity;

import java.io.InputStreamReader;

import com.jcraft.jsch.ChannelExec;

public class InputStreamChannelExec {
	
	private InputStreamReader is;
	private ChannelExec ce;
	
	public InputStreamReader getIs() {
		return is;
	}
	public void setIs(InputStreamReader is) {
		this.is = is;
	}
	public ChannelExec getCe() {
		return ce;
	}
	public void setCe(ChannelExec ce) {
		this.ce = ce;
	}
	
	

}
