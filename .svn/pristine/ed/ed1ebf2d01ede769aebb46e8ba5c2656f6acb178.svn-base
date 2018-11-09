/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.userquery.entity.TDiameterConfig;
import com.thinkgem.jeesite.modules.userquery.dao.TDiameterConfigDao;

/**
 * 信令追踪DIAMETER端口设置Service
 * @author 王晶石
 * @version 2017-07-31
 */
@Service
@Transactional(readOnly = true)
public class TDiameterConfigService extends CrudService<TDiameterConfigDao, TDiameterConfig> {

	public TDiameterConfig get(String id) {
		return super.get(id);
	}
	
	public List<TDiameterConfig> findList(TDiameterConfig tDiameterConfig) {
		return super.findList(tDiameterConfig);
	}
	
	public Page<TDiameterConfig> findPage(Page<TDiameterConfig> page, TDiameterConfig tDiameterConfig) {
		return super.findPage(page, tDiameterConfig);
	}
	
	@Transactional(readOnly = false)
	public void save(TDiameterConfig tDiameterConfig) {
		super.save(tDiameterConfig);
	}
	
	@Transactional(readOnly = false)
	public void delete(TDiameterConfig tDiameterConfig) {
		super.delete(tDiameterConfig);
	}
	
}