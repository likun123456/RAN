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
import com.thinkgem.jeesite.modules.userquery.dao.TAllRatinggroupConfigDao;
import com.thinkgem.jeesite.modules.userquery.entity.TAllRatinggroupConfig;

/**
 * 业务编码中文解释Service
 * @author zhuguangrui
 * @version 2017-07-07
 */
@Service
@Transactional(readOnly = true)
public class TAllRatinggroupConfigService extends CrudService<TAllRatinggroupConfigDao, TAllRatinggroupConfig> {

	@Autowired
	private TAllRatinggroupConfigDao tAllRatinggroupConfigDao;
	
	public TAllRatinggroupConfig get(String id) {
		return super.get(id);
	}
	
	public List<TAllRatinggroupConfig> findList(TAllRatinggroupConfig tAllRatinggroupConfig) {
		return super.findList(tAllRatinggroupConfig);
	}
	
	public Page<TAllRatinggroupConfig> findPage(Page<TAllRatinggroupConfig> page, TAllRatinggroupConfig tAllRatinggroupConfig) {
		return super.findPage(page, tAllRatinggroupConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(TAllRatinggroupConfig tAllRatinggroupConfig) {
		super.save(tAllRatinggroupConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(TAllRatinggroupConfig tAllRatinggroupConfig) {
		super.delete(tAllRatinggroupConfig);
	}
	
	@Transactional(readOnly = false)
	public void batchIntert(List<TAllRatinggroupConfig> list) {
		tAllRatinggroupConfigDao.batchIntert(list);
	}
	
}