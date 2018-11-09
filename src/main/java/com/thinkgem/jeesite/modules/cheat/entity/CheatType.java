package com.thinkgem.jeesite.modules.cheat.entity;

import com.thinkgem.jeesite.common.persistence.DataEntity;
/**
 * 分类流量评估
 * @author yanghai
 *
 */
public class CheatType extends DataEntity<CheatType> {

	private static final long serialVersionUID = -8665869055592203651L;
	private String netId;
	private String netName;
	public String getCheatCaseChinese() {
		return cheatCaseChinese;
	}
	public void setCheatCaseChinese(String cheatCaseChinese) {
		this.cheatCaseChinese = cheatCaseChinese;
	}
	private String cheatCaseChinese;
	public String getNetName() {
		return netName;
	}
	public void setNetName(String netName) {
		this.netName = netName;
	}
	private String gran;
	private String freetotal;
	private String total;
	private String recordtime;
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	private String percent;
	private String ratType;
	private String servedMSISDN;
	private String servedIMSI;
	private String cheatCase;
	private String ratingGroup;
	public String getFreetotal() {
		return freetotal;
	}
	public void setFreetotal(String freetotal) {
		this.freetotal = freetotal;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getRatType() {
		return ratType;
	}
	public void setRatType(String ratType) {
		this.ratType = ratType;
	}
	public String getServedMSISDN() {
		return servedMSISDN;
	}
	public void setServedMSISDN(String servedMSISDN) {
		this.servedMSISDN = servedMSISDN;
	}
	public String getServedIMSI() {
		return servedIMSI;
	}
	public void setServedIMSI(String servedIMSI) {
		this.servedIMSI = servedIMSI;
	}
	public String getCheatCase() {
		return cheatCase;
	}
	public void setCheatCase(String cheatCase) {
		this.cheatCase = cheatCase;
	}
	public String getRatingGroup() {
		return ratingGroup;
	}
	public void setRatingGroup(String ratingGroup) {
		this.ratingGroup = ratingGroup;
	}
	public String getNetId() {
		return netId;
	}
	public void setNetId(String netId) {
		this.netId = netId;
	}
	public String getGran() {
		return gran;
	}
	public void setGran(String gran) {
		this.gran = gran;
	}
}
