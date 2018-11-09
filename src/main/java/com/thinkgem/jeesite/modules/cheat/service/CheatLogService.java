/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.entity.CheatLog;
import com.thinkgem.jeesite.modules.cheat.dao.CheatLogDao;

/**
 * 计费欺诈日志Service
 * @author zhuguangrui
 * @version 2017-09-06
 */
@Service
@Transactional(readOnly = true)
public class CheatLogService extends CrudService<CheatLogDao, CheatLog> {

	public CheatLog get(String id) {
		return super.get(id);
	}
	
	public List<CheatLog> findList(CheatLog cheatLog) {
		return super.findList(cheatLog);
	}
	
	public Page<CheatLog> findPage(Page<CheatLog> page, CheatLog cheatLog) {
		return super.findPage(page, cheatLog);
	}
	
	@Transactional(readOnly = false)
	public void save(CheatLog cheatLog) {
		super.save(cheatLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(CheatLog cheatLog) {
		super.delete(cheatLog);
	}
	
}