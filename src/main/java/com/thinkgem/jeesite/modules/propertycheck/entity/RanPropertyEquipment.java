/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.propertycheck.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 资产信息核查Entity
 * @author 李昆
 * @version 2018-11-09
 */
public class RanPropertyEquipment extends DataEntity<RanPropertyEquipment> {
	
	private static final long serialVersionUID = 1L;
	private String serialnumber;		// 串口序列号
	private String sitename;		// 基站点
	private String managerobject;		// 管理对象
	private String productname;		// 产品名称
	private String productnumber;		// 产品型号
	private String productionrevision;		// 产品修订
	private String productiondate;		// 产品出厂日期
	private String manufacturerid;		// 第三方厂商ID
	private String manufacturerrevision;		// 第三制造方修订
	private String negotiatedbitrate;		// 第三方协定比特率
	private String status;		// 设备状态：0:无效设备，1：新设备，2:复用设备
	private Date logdate;		// log日志的生成时间
	private String mocategory;		// 对象类型
	private String extendfield;		// 扩展字段
	
	public RanPropertyEquipment() {
		super();
	}

	public RanPropertyEquipment(String id){
		super(id);
	}

	@Length(min=1, max=20, message="串口序列号长度必须介于 1 和 20 之间")
	public String getSerialnumber() {
		return serialnumber;
	}

	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	
	@Length(min=1, max=12, message="基站点长度必须介于 1 和 12 之间")
	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}
	
	@Length(min=0, max=205, message="管理对象长度必须介于 0 和 205 之间")
	public String getManagerobject() {
		return managerobject;
	}

	public void setManagerobject(String managerobject) {
		this.managerobject = managerobject;
	}
	
	@Length(min=0, max=20, message="产品名称长度必须介于 0 和 20 之间")
	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	@Length(min=0, max=20, message="产品型号长度必须介于 0 和 20 之间")
	public String getProductnumber() {
		return productnumber;
	}

	public void setProductnumber(String productnumber) {
		this.productnumber = productnumber;
	}
	
	@Length(min=0, max=5, message="产品修订长度必须介于 0 和 5 之间")
	public String getProductionrevision() {
		return productionrevision;
	}

	public void setProductionrevision(String productionrevision) {
		this.productionrevision = productionrevision;
	}
	
	@Length(min=0, max=8, message="产品出厂日期长度必须介于 0 和 8 之间")
	public String getProductiondate() {
		return productiondate;
	}

	public void setProductiondate(String productiondate) {
		this.productiondate = productiondate;
	}
	
	@Length(min=0, max=20, message="第三方厂商ID长度必须介于 0 和 20 之间")
	public String getManufacturerid() {
		return manufacturerid;
	}

	public void setManufacturerid(String manufacturerid) {
		this.manufacturerid = manufacturerid;
	}
	
	@Length(min=0, max=5, message="第三制造方修订长度必须介于 0 和 5 之间")
	public String getManufacturerrevision() {
		return manufacturerrevision;
	}

	public void setManufacturerrevision(String manufacturerrevision) {
		this.manufacturerrevision = manufacturerrevision;
	}
	
	@Length(min=0, max=5, message="第三方协定比特率长度必须介于 0 和 5 之间")
	public String getNegotiatedbitrate() {
		return negotiatedbitrate;
	}

	public void setNegotiatedbitrate(String negotiatedbitrate) {
		this.negotiatedbitrate = negotiatedbitrate;
	}
	
	@Length(min=1, max=1, message="设备状态：0:无效设备，1：新设备，2:复用设备长度必须介于 1 和 1 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="log日志的生成时间不能为空")
	public Date getLogdate() {
		return logdate;
	}

	public void setLogdate(Date logdate) {
		this.logdate = logdate;
	}
	
	@Length(min=1, max=1, message="对象类型长度必须介于 1 和 1 之间")
	public String getMocategory() {
		return mocategory;
	}

	public void setMocategory(String mocategory) {
		this.mocategory = mocategory;
	}
	
	@Length(min=0, max=100, message="扩展字段长度必须介于 0 和 100 之间")
	public String getExtendfield() {
		return extendfield;
	}

	public void setExtendfield(String extendfield) {
		this.extendfield = extendfield;
	}
	
}