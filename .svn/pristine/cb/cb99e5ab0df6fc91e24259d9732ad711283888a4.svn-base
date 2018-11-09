/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * EPG配置Entity
 * @author zhuguangrui
 * @version 2018-04-10
 */
public class TVisNetparamEpg extends DataEntity<TVisNetparamEpg> {
	
	private static final long serialVersionUID = 1L;
	private Long netid;		// netid
	private String hw;		// hw
	private String sw;		// sw
	private String oamIpAddress;		// oam_ip_address
	private Date updateTime;		// update_time
	private String netName;
	
	public TVisNetparamEpg() {
		super();
	}

	public TVisNetparamEpg(String id){
		super(id);
	}

	@NotNull(message="netid不能为空")
	public Long getNetid() {
		return netid;
	}

	public void setNetid(Long netid) {
		this.netid = netid;
	}
	
	@Length(min=0, max=50, message="hw长度必须介于 0 和 50 之间")
	public String getHw() {
		return hw;
	}

	public void setHw(String hw) {
		this.hw = hw;
	}
	
	@Length(min=0, max=50, message="swl长度必须介于 0 和 50 之间")
	public String getSw() {
		return sw;
	}

	public void setSwl(String sw) {
		this.sw = sw;
	}
	
	
	@Length(min=0, max=50, message="oam_ip_address长度必须介于 0 和 50 之间")
	public String getOamIpAddress() {
		return oamIpAddress;
	}

	public void setOamIpAddress(String oamIpAddress) {
		this.oamIpAddress = oamIpAddress;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getNetName() {
		return netName;
	}

	public void setNetName(String netName) {
		this.netName = netName;
	}
	
}