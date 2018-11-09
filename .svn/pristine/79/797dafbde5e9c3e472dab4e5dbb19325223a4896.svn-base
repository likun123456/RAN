/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.entity.TConfig;
import com.thinkgem.jeesite.modules.cheat.dao.TConfigDao;

/**
 * 抓包参数设置Service
 * @author 王晶石
 * @version 2017-10-23
 */
@Service
@Transactional(readOnly = true)
public class TConfigService extends CrudService<TConfigDao, TConfig> {

	public TConfig get(String id) {
		return super.get(id);
	}
	
	public List<TConfig> findList(TConfig tConfig) {
		return super.findList(tConfig);
	}
	
	public Page<TConfig> findPage(Page<TConfig> page, TConfig tConfig) {
		return super.findPage(page, tConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(TConfig tConfig) {
		super.save(tConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(TConfig tConfig) {
		super.delete(tConfig);
	}
	
}