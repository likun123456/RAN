/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 单用户EBM查询Entity
 * @author 王晶石
 * @version 2017-06-17
 */
public class TEbmSingleSearch extends DataEntity<TEbmSingleSearch> {
	
	private static final long serialVersionUID = 1L;
	private String date;		// date
	private String ntype;		// ntype
	private String eventId;		// event_id
	private String eventResult;		// event_result
	private String imsi;		// imsi
	private String msisdn;		// msisdn
	
	private String time_hour;
	private String time_minute;
	private String time_second;
	private String time_stemp;
	private String sb;
	private String time_millisecond;
	
	private String cc;
	private String scc;
	private String eci;
	
	private String sccdes;
	private String action;
	private String domain;
	private List<TCdrTreeMould> ebmDetail;
	
	
	public TEbmSingleSearch() {
		super();
	}

	public TEbmSingleSearch(String id){
		super(id);
	}

	@Length(min=0, max=50, message="date长度必须介于 0 和 50 之间")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@Length(min=0, max=20, message="ntype长度必须介于 0 和 20 之间")
	public String getNtype() {
		return ntype;
	}

	public void setNtype(String ntype) {
		this.ntype = ntype;
	}
	
	@Length(min=0, max=50, message="event_id长度必须介于 0 和 50 之间")
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	@Length(min=0, max=50, message="event_result长度必须介于 0 和 50 之间")
	public String getEventResult() {
		return eventResult;
	}

	public void setEventResult(String eventResult) {
		this.eventResult = eventResult;
	}
	
	@Length(min=0, max=20, message="imsi长度必须介于 0 和 20 之间")
	public String getImsi() {
		return imsi;
	}

	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	
	@Length(min=0, max=20, message="msisdn长度必须介于 0 和 20 之间")
	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getTime_hour() {
		return time_hour;
	}

	public void setTime_hour(String time_hour) {
		this.time_hour = time_hour;
	}

	public String getTime_minute() {
		return time_minute;
	}

	public void setTime_minute(String time_minute) {
		this.time_minute = time_minute;
	}

	public String getTime_second() {
		return time_second;
	}

	public void setTime_second(String time_second) {
		this.time_second = time_second;
	}

	public String getTime_stemp() {
		return time_stemp;
	}

	public void setTime_stemp(String time_stemp) {
		this.time_stemp = time_stemp;
	}

	public String getSb() {
		return sb;
	}

	public void setSb(String sb) {
		this.sb = sb;
	}

	public String getTime_millisecond() {
		return time_millisecond;
	}

	public void setTime_millisecond(String time_millisecond) {
		this.time_millisecond = time_millisecond;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getScc() {
		return scc;
	}

	public void setScc(String scc) {
		this.scc = scc;
	}

	public String getEci() {
		return eci;
	}

	public void setEci(String eci) {
		this.eci = eci;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getSccdes() {
		return sccdes;
	}

	public void setSccdes(String sccdes) {
		this.sccdes = sccdes;
	}

	public List<TCdrTreeMould> getEbmDetail() {
		return ebmDetail;
	}

	public void setEbmDetail(List<TCdrTreeMould> ebmDetail) {
		this.ebmDetail = ebmDetail;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
	
}