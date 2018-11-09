/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisNetparamMme;
import com.thinkgem.jeesite.modules.netconfig.dao.TVisNetparamMmeDao;

/**
 * MME配置Service
 * @author zhuguangrui
 * @version 2018-03-27
 */
@Service
@Transactional(readOnly = true)
public class TVisNetparamMmeService extends CrudService<TVisNetparamMmeDao, TVisNetparamMme> {

	public TVisNetparamMme get(String netId) {
		return super.get(netId);
	}
	
	public List<TVisNetparamMme> findList(TVisNetparamMme tVisNetparamMme) {
		return super.findList(tVisNetparamMme);
	}
	
	public Page<TVisNetparamMme> findPage(Page<TVisNetparamMme> page, TVisNetparamMme tVisNetparamMme) {
		return super.findPage(page, tVisNetparamMme);
	}
	
	@Transactional(readOnly = false)
	public void save(TVisNetparamMme tVisNetparamMme) {
		super.save(tVisNetparamMme);
	}
	
	@Transactional(readOnly = false)
	public void delete(TVisNetparamMme tVisNetparamMme) {
		super.delete(tVisNetparamMme);
	}
	
}