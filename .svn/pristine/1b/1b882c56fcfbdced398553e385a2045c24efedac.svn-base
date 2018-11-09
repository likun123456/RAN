/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.autocheck.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.autocheck.dao.TAutoTemplateCheckConfigDao;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoCheckConfig;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoTemplateCheckConfig;



@Service
@Transactional(readOnly = true)
public class AutoTemplateCheckConfigService extends CrudService<TAutoTemplateCheckConfigDao, TAutoTemplateCheckConfig> {

	@Autowired
	private TAutoTemplateCheckConfigDao tAutoTemplateCheckConfigDao;
	
	@Transactional(readOnly = false)
	public void deleteAll() {
		tAutoTemplateCheckConfigDao.deleteAll();
	}
	
	@Transactional(readOnly = false)
	public void save(TAutoTemplateCheckConfig tAutoTemplateCheckConfig) {
		super.save(tAutoTemplateCheckConfig);
	}
	
}