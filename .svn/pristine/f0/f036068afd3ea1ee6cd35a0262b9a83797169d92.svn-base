/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.entity.CheatReport;
import com.thinkgem.jeesite.modules.cheat.dao.CheatReportDao;

/**
 * 话单欺诈业务报表Service
 * @author zhuguangrui
 * @version 2017-08-30
 */
@Service
@Transactional(readOnly = true)
public class CheatReportService extends CrudService<CheatReportDao, CheatReport> {

	@Autowired
	private CheatReportDao cheatReportDao;
	
	public CheatReport get(String id) {
		return super.get(id);
	}
	
	public List<CheatReport> findList(CheatReport cheatReport) {
		return super.findList(cheatReport);
	}
	
	public Page<CheatReport> findPage(Page<CheatReport> page, CheatReport cheatReport) {
		return super.findPage(page, cheatReport);
	}
	
	@Transactional(readOnly = false)
	public void save(CheatReport cheatReport) {
		super.save(cheatReport);
	}
	
	@Transactional(readOnly = false)
	public void delete(CheatReport cheatReport) {
		super.delete(cheatReport);
	}
	
	public List<Map<String,Object>> queryCheatReport(String netId, String reportName, String timeElement,String suffix){
		return cheatReportDao.queryCheatReport(netId,reportName,timeElement,suffix);
	}
	
}