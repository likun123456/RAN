package com.thinkgem.jeesite.modules.performance.entity;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.thinkgem.jeesite.common.persistence.DataEntity;

public class TStatisticsVO extends DataEntity<TStatisticsVO> {
	private static final long serialVersionUID = 8542483840527867885L;
	private BigInteger netID;
	private Date dateTime;
	private String KPI;
	private Double result;
	private Double dresult;
	private Double wresult;
	public Double getDresult() {
		return dresult;
	}
	public void setDresult(Double dresult) {
		this.dresult = dresult;
	}
	public Double getWresult() {
		return wresult;
	}
	public void setWresult(Double wresult) {
		this.wresult = wresult;
	}
	public Double getAwresult() {
		return awresult;
	}
	public void setAwresult(Double awresult) {
		this.awresult = awresult;
	}
	private Double awresult;
	private String fname;
	private String startTime;
	private String endTime;
	private Double maxValue;
	private Double minValue;
	private String countertype;
	private String name;//KPI name
	private String formulatext;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getNetID() {
		return netID;
	}
	public void setNetID(BigInteger netID) {
		this.netID = netID;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
		this.temp_field1=new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss").format(dateTime);
	}
	public String getKPI() {
		return KPI;
	}
	public void setKPI(String kPI) {
		KPI = kPI;
	}
	public Double getResult() {
		return result;
	}
	public void setResult(Double result) {
		this.result = result;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}
	public Double getMinValue() {
		return minValue;
	}
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}
	public String getCountertype() {
		return countertype;
	}
	public void setCountertype(String countertype) {
		this.countertype = countertype;
	}
	public String getFormulatext() {
		return formulatext;
	}
	public void setFormulatext(String formulatext) {
		this.formulatext = formulatext;
	}
	
}
