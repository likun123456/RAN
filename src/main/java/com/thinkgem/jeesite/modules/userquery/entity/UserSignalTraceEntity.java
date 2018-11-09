/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;

/**
 * 单用户信令追踪Entity
 * @author zhuguangrui
 * @version 2017-05-31
 */
public class UserSignalTraceEntity extends DataEntity<UserSignalTraceEntity> {
	
	private static final long serialVersionUID = 1L;
	private String numberType;
	private String number;
	private TPool mmePools;
	private TPool saegwPools;
	private TNewnetelement pcrfNetElements;
	private Integer captureDuration;
	private Integer captureScope;
	
	public UserSignalTraceEntity() {
		super();
	}

	public UserSignalTraceEntity(String id){
		super(id);
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getNumberType() {
		return numberType;
	}

	public void setNumberType(String numberType) {
		this.numberType = numberType;
	}

	public Integer getCaptureDuration() {
		return captureDuration;
	}

	public void setCaptureDuration(Integer captureDuration) {
		this.captureDuration = captureDuration;
	}

	public TPool getMmePools() {
		return mmePools;
	}

	public void setMmePools(TPool mmePools) {
		this.mmePools = mmePools;
	}

	public TPool getSaegwPools() {
		return saegwPools;
	}

	public void setSaegwPools(TPool saegwPools) {
		this.saegwPools = saegwPools;
	}

	public TNewnetelement getPcrfNetElements() {
		return pcrfNetElements;
	}

	public void setPcrfNetElements(TNewnetelement pcrfNetElements) {
		this.pcrfNetElements = pcrfNetElements;
	}

	public Integer getCaptureScope() {
		return captureScope;
	}

	public void setCaptureScope(Integer captureScope) {
		this.captureScope = captureScope;
	}
	
}