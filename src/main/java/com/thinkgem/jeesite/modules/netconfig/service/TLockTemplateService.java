/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TLockTemplateDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TLockTemplate;

/**
 * TLockTemplate模版管理Service
 * @author yanghai
 * @version 2018-4-25
 */
@Service
@Transactional(readOnly = true)
public class TLockTemplateService extends CrudService<TLockTemplateDao, TLockTemplate> {

	public TLockTemplate get(String id) {
		return super.get(id);
	}
	
	public List<TLockTemplate> findList(TLockTemplate tLockTemplate) {
		return super.findList(tLockTemplate);
	}
	
	public Page<TLockTemplate> findPage(Page<TLockTemplate> page, TLockTemplate tLockTemplate) {
		return super.findPage(page, tLockTemplate);
	}
	
	@Transactional(readOnly = false)
	public void save(TLockTemplate tLockTemplate) {
		super.save(tLockTemplate);
	}
	
}