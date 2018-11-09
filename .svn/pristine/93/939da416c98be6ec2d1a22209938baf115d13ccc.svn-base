/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.performance.dao.TEciSuccessRateDao;
import com.thinkgem.jeesite.modules.performance.entity.TEbmEvent;
import com.thinkgem.jeesite.modules.performance.entity.TEciSuccessRate;

/**
 * eci维度指标查询Service
 * @author 陈宏波
 * @version 2017-06-09
 */
@Service
@Transactional(readOnly = true)
public class TEciSuccessRateService extends CrudService<TEciSuccessRateDao, TEciSuccessRate> {
	
	@Autowired
	private TEciSuccessRateDao tEciSuccessRateDao;

	public TEciSuccessRate get(String id) {
		return super.get(id);
	}
	
	public List<TEciSuccessRate> findList(TEciSuccessRate tEciSuccessRate) {
		return super.findList(tEciSuccessRate);
	}
	
	public Page<TEciSuccessRate> findPage(Page<TEciSuccessRate> page, TEciSuccessRate tEciSuccessRate) {
		return super.findPage(page, tEciSuccessRate);
	}
	
	@Transactional(readOnly = false)
	public void save(TEciSuccessRate tEciSuccessRate) {
		super.save(tEciSuccessRate);
	}
	
	@Transactional(readOnly = false)
	public void delete(TEciSuccessRate tEciSuccessRate) {
		super.delete(tEciSuccessRate);
	}

	public List<TEbmEvent> getEbmEventlist() {
		List<TEbmEvent> list = tEciSuccessRateDao.getEbmEventlist();
		return list;
	}

	public List<TEciSuccessRate> queryList(String date,String startTime, String endTime, String netid, String eventType,int offset,int limit,String sortName,String sortOrder) {
		List<TEciSuccessRate> list = tEciSuccessRateDao.queryList(date,startTime,endTime,netid,eventType,offset,limit,sortName,sortOrder);
		return list;
	}
	
	public List<TEciSuccessRate> queryLnList(String date,String startTime, String endTime, String netid, String eventType,int offset,int limit,String sortName,String sortOrder) {
		List<TEciSuccessRate> list = tEciSuccessRateDao.queryLnList(date,startTime,endTime,netid,eventType,offset,limit,sortName,sortOrder);
		for(int i=0;i<list.size();i++) {
			list.get(i).setFname(Global.getConfig(list.get(i).getNetid()));
		}
		return list;
	}
	
	public int queryListCount(String date,String startTime, String endTime, String netid, String eventType){
		int total = tEciSuccessRateDao.queryListCount(date, startTime, endTime,netid,eventType);
		return total;
	}

	
	
}