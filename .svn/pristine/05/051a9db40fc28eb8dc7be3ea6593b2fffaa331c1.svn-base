/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.userquery.dao.TUserSignalHostConfigDao;
import com.thinkgem.jeesite.modules.userquery.entity.TUserSignalHostConfig;

/**
 * 信令追踪host配置Service
 * @author chenhongbo
 * @version 2017-07-13
 */
@Service
@Transactional(readOnly = true)
public class TUserSignalHostConfigService extends CrudService<TUserSignalHostConfigDao, TUserSignalHostConfig> {

	@Autowired
	private TUserSignalHostConfigDao tUserSignalHostConfigDao;
	
	public TUserSignalHostConfig get(String id) {
		return super.get(id);
	}
	
	public List<TUserSignalHostConfig> findList(TUserSignalHostConfig tUserSignalHostConfig) {
		return super.findList(tUserSignalHostConfig);
	}
	
	public Page<TUserSignalHostConfig> findPage(Page<TUserSignalHostConfig> page, TUserSignalHostConfig tUserSignalHostConfig) {
		return super.findPage(page, tUserSignalHostConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(TUserSignalHostConfig tUserSignalHostConfig) {
		super.save(tUserSignalHostConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(TUserSignalHostConfig tUserSignalHostConfig) {
		super.delete(tUserSignalHostConfig);
	}
	
	@Transactional(readOnly = false)
	public void batchIntert(List<TUserSignalHostConfig> list) {
		tUserSignalHostConfigDao.batchIntert(list);
	}
	
}