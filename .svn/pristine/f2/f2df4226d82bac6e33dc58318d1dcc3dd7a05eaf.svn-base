/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisDanger;
import com.thinkgem.jeesite.modules.netconfig.dao.TVisDangerDao;

/**
 * 危险指令Service
 * @author 王晶石
 * @version 2018-05-07
 */
@Service
@Transactional(readOnly = true)
public class TVisDangerService extends CrudService<TVisDangerDao, TVisDanger> {

	public TVisDanger get(String id) {
		return super.get(id);
	}
	
	public List<TVisDanger> findList(TVisDanger tVisDanger) {
		return super.findList(tVisDanger);
	}
	
	public Page<TVisDanger> findPage(Page<TVisDanger> page, TVisDanger tVisDanger) {
		return super.findPage(page, tVisDanger);
	}
	
	@Transactional(readOnly = false)
	public void save(TVisDanger tVisDanger) {
		super.save(tVisDanger);
	}
	
	@Transactional(readOnly = false)
	public void delete(TVisDanger tVisDanger) {
		super.delete(tVisDanger);
	}
	
}