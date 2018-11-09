/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.entity;


import java.io.Serializable;
import java.util.List;

public class UserCdrDetail implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int index;//记录在集合中的索引
	private int tableIndex;//记录在表格中的索引
	private int length;
	private String servedimsi; //用户IMSI
	private String servedmsisdn; //用户MSISDN
	private String cdrtype;
	private String rattype; //RAT类型
	private String recordopeningtime;//话单开始时间
	private long total_datavolumefbcuplink;//上行总流量
	private long total_datavolumefbcdownlink;//下行总流量
	private List<TCdrTreeMould> cdrDetail; //话单明细：
	public String getServedimsi() {
		return servedimsi;
	}
	public void setServedimsi(String servedimsi) {
		this.servedimsi = servedimsi;
	}
	public String getServedmsisdn() {
		return servedmsisdn;
	}
	public void setServedmsisdn(String servedmsisdn) {
		this.servedmsisdn = servedmsisdn;
	}
	public String getRattype() {
		return rattype;
	}
	public void setRattype(String rattype) {
		this.rattype = rattype;
	}
	public String getRecordopeningtime() {
		return recordopeningtime;
	}
	public void setRecordopeningtime(String recordopeningtime) {
		this.recordopeningtime = recordopeningtime;
	}
	
	public long getTotal_datavolumefbcuplink() {
		return total_datavolumefbcuplink;
	}
	public void setTotal_datavolumefbcuplink(long total_datavolumefbcuplink) {
		this.total_datavolumefbcuplink = total_datavolumefbcuplink;
	}
	public long getTotal_datavolumefbcdownlink() {
		return total_datavolumefbcdownlink;
	}
	public void setTotal_datavolumefbcdownlink(long total_datavolumefbcdownlink) {
		this.total_datavolumefbcdownlink = total_datavolumefbcdownlink;
	}
	public List<TCdrTreeMould> getCdrDetail() {
		return cdrDetail;
	}
	public void setCdrDetail(List<TCdrTreeMould> cdrDetail) {
		this.cdrDetail = cdrDetail;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getCdrtype() {
		return cdrtype;
	}
	public void setCdrtype(String cdrtype) {
		this.cdrtype = cdrtype;
	}
	public int getTableIndex() {
		return tableIndex;
	}
	public void setTableIndex(int tableIndex) {
		this.tableIndex = tableIndex;
	}
	
	
}
