/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.entity.TEpa;
import com.thinkgem.jeesite.modules.netconfig.dao.TEpaDao;

/**
 * EPA节点管理Service
 * @author 王晶石
 * @version 2017-11-15
 */
@Service
@Transactional(readOnly = true)
public class TEpaService extends CrudService<TEpaDao, TEpa> {

	public TEpa get(String id) {
		return super.get(id);
	}
	
	public List<TEpa> findList(TEpa tEpa) {
		return super.findList(tEpa);
	}
	
	public Page<TEpa> findPage(Page<TEpa> page, TEpa tEpa) {
		return super.findPage(page, tEpa);
	}
	
	@Transactional(readOnly = false)
	public void save(TEpa tEpa) {
		super.save(tEpa);
	}
	
	@Transactional(readOnly = false)
	public void delete(TEpa tEpa) {
		super.delete(tEpa);
	}
	
}