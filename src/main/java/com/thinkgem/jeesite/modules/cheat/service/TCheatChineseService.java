/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.entity.TCheatChinese;
import com.thinkgem.jeesite.modules.cheat.dao.TCheatChineseDao;

/**
 * 话单欺诈类型中文设置Service
 * @author zhuguangrui
 * @version 2017-08-24
 */
@Service
@Transactional(readOnly = true)
public class TCheatChineseService extends CrudService<TCheatChineseDao, TCheatChinese> {

	public TCheatChinese get(String id) {
		return super.get(id);
	}
	
	public List<TCheatChinese> findList(TCheatChinese tCheatChinese) {
		return super.findList(tCheatChinese);
	}
	
	public Page<TCheatChinese> findPage(Page<TCheatChinese> page, TCheatChinese tCheatChinese) {
		return super.findPage(page, tCheatChinese);
	}
	
	@Transactional(readOnly = false)
	public void save(TCheatChinese tCheatChinese) {
		super.save(tCheatChinese);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCheatChinese tCheatChinese) {
		super.delete(tCheatChinese);
	}
	
}