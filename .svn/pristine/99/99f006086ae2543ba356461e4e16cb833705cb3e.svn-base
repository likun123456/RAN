/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.userquery.entity.TCapConfig;
import com.thinkgem.jeesite.modules.userquery.dao.TCapConfigDao;

/**
 * 单用户信令追踪配置Service
 * @author zhuguangrui
 * @version 2017-06-16
 */
@Service
@Transactional(readOnly = true)
public class TCapConfigService extends CrudService<TCapConfigDao, TCapConfig> {

	public TCapConfig get(String id) {
		return super.get(id);
	}
	
	public List<TCapConfig> findList(TCapConfig tCapConfig) {
		return super.findList(tCapConfig);
	}
	
	public Page<TCapConfig> findPage(Page<TCapConfig> page, TCapConfig tCapConfig) {
		return super.findPage(page, tCapConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(TCapConfig tCapConfig) {
		super.save(tCapConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCapConfig tCapConfig) {
		super.delete(tCapConfig);
	}
	
}