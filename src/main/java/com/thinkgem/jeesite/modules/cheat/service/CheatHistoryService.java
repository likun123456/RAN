/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.entity.CheatHistory;
import com.thinkgem.jeesite.modules.cheat.dao.CheatHistoryDao;

/**
 * 计费欺诈抓包历史记录Service
 * @author zhuguangrui
 * @version 2017-09-04
 */
@Service
@Transactional(readOnly = true)
public class CheatHistoryService extends CrudService<CheatHistoryDao, CheatHistory> {
	
	@Autowired
	private CheatHistoryDao cheatHistoryDao;
	
	public CheatHistory get(String id) {
		return super.get(id);
	}
	
	public List<CheatHistory> findList(CheatHistory cheatHistory) {
		return super.findList(cheatHistory);
	}
	
	public Page<CheatHistory> findPage(Page<CheatHistory> page, CheatHistory cheatHistory) {
		return super.findPage(page, cheatHistory);
	}
	
	public List<CheatHistory> queryListByImsi(String netid, String imsi,
			String beginDate, String endDate, String date){
		return cheatHistoryDao.queryListByImsi(netid, imsi, beginDate, endDate, date);
	}
	
	public List<CheatHistory> queryFlowList(String netid,String imsi, 
			String datetime,String date,String cheatCase){
		return cheatHistoryDao.queryFlowList(netid, imsi, datetime, date,cheatCase);
	}

	public String queryCapName(String recordTime, String imsi, String ip) {
		return cheatHistoryDao.queryCapName(recordTime,imsi,ip);
	}
	
}