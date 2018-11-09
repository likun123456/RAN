/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.autocheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.autocheck.entity.TAutoCheckConfig;
import com.thinkgem.jeesite.modules.autocheck.dao.TAutoCheckConfigDao;

/**
 * 自动巡检配置Service
 * @author zhuguangrui
 * @version 2017-08-05
 */
@Service
@Transactional(readOnly = true)
public class TAutoCheckConfigService extends CrudService<TAutoCheckConfigDao, TAutoCheckConfig> {

	@Autowired
	private TAutoCheckConfigDao tAutoCheckConfigDao;
	
	public TAutoCheckConfig get(String id) {
		return super.get(id);
	}
	
	public List<TAutoCheckConfig> findList(TAutoCheckConfig tAutoCheckConfig) {
		return super.findList(tAutoCheckConfig);
	}
	
	public Page<TAutoCheckConfig> findPage(Page<TAutoCheckConfig> page, TAutoCheckConfig tAutoCheckConfig) {
		return super.findPage(page, tAutoCheckConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(TAutoCheckConfig tAutoCheckConfig) {
		super.save(tAutoCheckConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(TAutoCheckConfig tAutoCheckConfig) {
		super.delete(tAutoCheckConfig);
	}
	@Transactional(readOnly = false)
	public void updateConfig(TAutoCheckConfig config) {
		tAutoCheckConfigDao.updateConfig(config);
	}
	
}