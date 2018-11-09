/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.autocheck.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoCheckConfig;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoCheckLog;
import com.thinkgem.jeesite.modules.autocheck.dao.TAutoCheckLogDao;

/**
 * 智能巡检Service
 * @author zhuguangrui
 * @version 2017-08-04
 */
@Service
@Transactional(readOnly = true)
public class TAutoCheckLogService extends CrudService<TAutoCheckLogDao, TAutoCheckLog> {

	@Autowired
	private TAutoCheckLogDao tautoCheckLogDao;
	
	public TAutoCheckLog get(String id) {
		return super.get(id);
	}
	
	public List<TAutoCheckLog> findList(TAutoCheckLog tAutoCheckLog) {
		return super.findList(tAutoCheckLog);
	}
	
	public Page<TAutoCheckLog> findPage(Page<TAutoCheckLog> page, TAutoCheckLog tAutoCheckLog) {
		return super.findPage(page, tAutoCheckLog);
	}
	
	@Transactional(readOnly = false)
	public void save(TAutoCheckLog tAutoCheckLog) {
		super.save(tAutoCheckLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(TAutoCheckLog tAutoCheckLog) {
		super.delete(tAutoCheckLog);
	}

	public List<TAutoCheckLog> queryList(String netType,String netId,String checkItem,String checkResult,String beginDate,String endDate,int offset,int limit,String sortName,String sortOrder) {
		return tautoCheckLogDao.queryList(netType,netId,checkItem,checkResult,beginDate,endDate,offset,limit,sortName,sortOrder);
	}

	public int queryListCount(String netType,String netId,String checkItem,String checkResult,String beginDate,String endDate) {
		return tautoCheckLogDao.queryListCount(netType,netId,checkItem,checkResult,beginDate,endDate);
	}
	
	public String queryMaxCheckTime(){
		return tautoCheckLogDao.queryMaxCheckTime();
	}	
	public List<TAutoCheckConfig> queryCheckItems(String netType){
		return tautoCheckLogDao.queryCheckItems(netType);
	}
	
}