/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 网元基本信息操作Entity
 * @author 王晶石
 * @version 2017-05-25
 */
public class TNewnetelement extends DataEntity<TNewnetelement> {
	
	private static final long serialVersionUID = 1L;
	private String fname;		// f_name
	private Long fnid;		// f_nid
	private String epgtype;		// epgtype
	private String hwtype;		// hwtype
	private String softwareversion;		// softwareversion
	private String ipadr;		// ipadr
	private String username1;		// username1
	private String password1;		// password1
	private String username2;		// username2
	private String password2;		// password2
	private String adminipadr;   //网管ip
	private String adminname;    //网管用户名
	private String adminpassword; //网管密码
	private String countftpurl;		// countftpurl
	private String cdrftpurl;		// cdrftpurl
	private String oss;		// oss
	private String epa;		// oss
	private String type;		// type
	private String enabled;		// enabled
	private String isdownload;		// isdownload
	private String isstore;		// isstore
	private Long cgid;		// cgid
	private String ebmlog;		// ebmlog
	private String mmemaxuserattached;		// mmemaxuserattached
	private String sgwmaxbearers;		// sgwmaxbearers
	private String sgwmaxthroughput;		// sgwmaxthroughput
	private String pgwmaxbearers;		// pgwmaxbearers
	private String pgwmaxthroughput;		// pgwmaxthroughput
	private String ggsnmaxpdpcontexts;		// ggsnmaxpdpcontexts
	private String ggsnmaxthroughput;		// ggsnmaxthroughput
	private String downloadebmlogtime;		// 执行下载ebmlog的时间
	private String paramexported;		// paramexported
	private String cardcode;		// 板卡信息
	private String poolname;    //池组名称
	private String factory;  //厂商
	private String croom;//机房
	public String getCroom() {
		return croom;
	}

	public void setCroom(String croom) {
		this.croom = croom;
	}

	public TNewnetelement() {
		super();
	}

	public TNewnetelement(String id){
		super(id);
	}

	@Length(min=0, max=100, message="f_name长度必须介于 0 和 100 之间")
	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public Long getFnid() {
		return fnid;
	}

	public void setFnid(Long fnid) {
		this.fnid = fnid;
	}
	
	@Length(min=0, max=100, message="epgtype长度必须介于 0 和 100 之间")
	public String getEpgtype() {
		return epgtype;
	}

	public void setEpgtype(String epgtype) {
		this.epgtype = epgtype;
	}
	
	@Length(min=0, max=100, message="hwtype长度必须介于 0 和 100 之间")
	public String getHwtype() {
		return hwtype;
	}

	public void setHwtype(String hwtype) {
		this.hwtype = hwtype;
	}
	
	@Length(min=0, max=100, message="softwareversion长度必须介于 0 和 100 之间")
	public String getSoftwareversion() {
		return softwareversion;
	}

	public void setSoftwareversion(String softwareversion) {
		this.softwareversion = softwareversion;
	}
	
	@Length(min=0, max=100, message="ipadr长度必须介于 0 和 100 之间")
	public String getIpadr() {
		return ipadr;
	}

	public void setIpadr(String ipadr) {
		this.ipadr = ipadr;
	}
	
	@Length(min=0, max=100, message="username1长度必须介于 0 和 100 之间")
	public String getUsername1() {
		return username1;
	}

	public void setUsername1(String username1) {
		this.username1 = username1;
	}
	
	@Length(min=0, max=100, message="password1长度必须介于 0 和 100 之间")
	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	
	@Length(min=0, max=100, message="username2长度必须介于 0 和 100 之间")
	public String getUsername2() {
		return username2;
	}

	public void setUsername2(String username2) {
		this.username2 = username2;
	}
	
	@Length(min=0, max=100, message="password2长度必须介于 0 和 100 之间")
	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	@Length(min=0, max=100, message="countftpurl长度必须介于 0 和 100 之间")
	public String getCountftpurl() {
		return countftpurl;
	}

	public void setCountftpurl(String countftpurl) {
		this.countftpurl = countftpurl;
	}
	
	@Length(min=0, max=100, message="cdrftpurl长度必须介于 0 和 100 之间")
	public String getCdrftpurl() {
		return cdrftpurl;
	}

	public void setCdrftpurl(String cdrftpurl) {
		this.cdrftpurl = cdrftpurl;
	}
	
	@Length(min=0, max=100, message="oss长度必须介于 0 和 100 之间")
	public String getOss() {
		return oss;
	}

	public void setOss(String oss) {
		this.oss = oss;
	}
	
	@Length(min=0, max=11, message="type长度必须介于 0 和 11 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=1, message="enabled长度必须介于 0 和 1 之间")
	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	@Length(min=0, max=11, message="isdownload长度必须介于 0 和 11 之间")
	public String getIsdownload() {
		return isdownload;
	}

	public void setIsdownload(String isdownload) {
		this.isdownload = isdownload;
	}
	
	@Length(min=0, max=11, message="isstore长度必须介于 0 和 11 之间")
	public String getIsstore() {
		return isstore;
	}

	public void setIsstore(String isstore) {
		this.isstore = isstore;
	}
	
	public Long getCgid() {
		return cgid;
	}

	public void setCgid(Long cgid) {
		this.cgid = cgid;
	}
	
	@Length(min=0, max=100, message="ebmlog长度必须介于 0 和 100 之间")
	public String getEbmlog() {
		return ebmlog;
	}

	public void setEbmlog(String ebmlog) {
		this.ebmlog = ebmlog;
	}
	
	@Length(min=0, max=15, message="mmemaxuserattached长度必须介于 0 和 15 之间")
	public String getMmemaxuserattached() {
		return mmemaxuserattached;
	}

	public void setMmemaxuserattached(String mmemaxuserattached) {
		this.mmemaxuserattached = mmemaxuserattached;
	}
	
	@Length(min=0, max=15, message="sgwmaxbearers长度必须介于 0 和 15 之间")
	public String getSgwmaxbearers() {
		return sgwmaxbearers;
	}

	public void setSgwmaxbearers(String sgwmaxbearers) {
		this.sgwmaxbearers = sgwmaxbearers;
	}
	
	@Length(min=0, max=15, message="sgwmaxthroughput长度必须介于 0 和 15 之间")
	public String getSgwmaxthroughput() {
		return sgwmaxthroughput;
	}

	public void setSgwmaxthroughput(String sgwmaxthroughput) {
		this.sgwmaxthroughput = sgwmaxthroughput;
	}
	
	@Length(min=0, max=15, message="pgwmaxbearers长度必须介于 0 和 15 之间")
	public String getPgwmaxbearers() {
		return pgwmaxbearers;
	}

	public void setPgwmaxbearers(String pgwmaxbearers) {
		this.pgwmaxbearers = pgwmaxbearers;
	}
	
	@Length(min=0, max=15, message="pgwmaxthroughput长度必须介于 0 和 15 之间")
	public String getPgwmaxthroughput() {
		return pgwmaxthroughput;
	}

	public void setPgwmaxthroughput(String pgwmaxthroughput) {
		this.pgwmaxthroughput = pgwmaxthroughput;
	}
	
	@Length(min=0, max=15, message="ggsnmaxpdpcontexts长度必须介于 0 和 15 之间")
	public String getGgsnmaxpdpcontexts() {
		return ggsnmaxpdpcontexts;
	}

	public void setGgsnmaxpdpcontexts(String ggsnmaxpdpcontexts) {
		this.ggsnmaxpdpcontexts = ggsnmaxpdpcontexts;
	}
	
	@Length(min=0, max=15, message="ggsnmaxthroughput长度必须介于 0 和 15 之间")
	public String getGgsnmaxthroughput() {
		return ggsnmaxthroughput;
	}

	public void setGgsnmaxthroughput(String ggsnmaxthroughput) {
		this.ggsnmaxthroughput = ggsnmaxthroughput;
	}
	
	
	
	public String getDownloadebmlogtime() {
		return downloadebmlogtime;
	}

	public void setDownloadebmlogtime(String downloadebmlogtime) {
		this.downloadebmlogtime = downloadebmlogtime;
	}

	@Length(min=0, max=6, message="paramexported长度必须介于 0 和 6 之间")
	public String getParamexported() {
		return paramexported;
	}

	public void setParamexported(String paramexported) {
		this.paramexported = paramexported;
	}
	
	@Length(min=0, max=1000, message="板卡信息长度必须介于 0 和 1000 之间")
	public String getCardcode() {
		return cardcode;
	}

	public void setCardcode(String cardcode) {
		this.cardcode = cardcode;
	}

	public String getPoolname() {
		return poolname;
	}

	public void setPoolname(String poolname) {
		this.poolname = poolname;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getEpa() {
		return epa;
	}

	public void setEpa(String epa) {
		this.epa = epa;
	}

	public String getAdminipadr() {
		return adminipadr;
	}

	public void setAdminipadr(String adminipadr) {
		this.adminipadr = adminipadr;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getAdminpassword() {
		return adminpassword;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}
	
	
}