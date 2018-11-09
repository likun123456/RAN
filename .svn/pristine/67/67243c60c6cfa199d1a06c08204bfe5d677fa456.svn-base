/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisNetparamEpg;
import com.thinkgem.jeesite.modules.netconfig.dao.TVisNetparamEpgDao;

/**
 * EPG配置Service
 * @author zhuguangrui
 * @version 2018-04-10
 */
@Service
@Transactional(readOnly = true)
public class TVisNetparamEpgService extends CrudService<TVisNetparamEpgDao, TVisNetparamEpg> {

	public TVisNetparamEpg get(String netId) {
		return super.get(netId);
	}
	
	public List<TVisNetparamEpg> findList(TVisNetparamEpg tVisNetparamEpg) {
		return super.findList(tVisNetparamEpg);
	}
	
	public Page<TVisNetparamEpg> findPage(Page<TVisNetparamEpg> page, TVisNetparamEpg tVisNetparamEpg) {
		return super.findPage(page, tVisNetparamEpg);
	}
	
	@Transactional(readOnly = false)
	public void save(TVisNetparamEpg tVisNetparamEpg) {
		super.save(tVisNetparamEpg);
	}
	
	@Transactional(readOnly = false)
	public void delete(TVisNetparamEpg tVisNetparamEpg) {
		super.delete(tVisNetparamEpg);
	}
	
}