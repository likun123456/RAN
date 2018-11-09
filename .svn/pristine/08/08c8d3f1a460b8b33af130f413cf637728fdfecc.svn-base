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
import com.thinkgem.jeesite.modules.userquery.dao.TCapUserfacePathDao;
import com.thinkgem.jeesite.modules.userquery.entity.TCapUserfacePath;

/**
 * 单用户信令追踪用户面抓包Service
 * @author 王晶石
 * @version 2017-08-04
 */
@Service
@Transactional(readOnly = true)
public class TCapUserfacePathService extends CrudService<TCapUserfacePathDao, TCapUserfacePath> {
	
	@Autowired
	TCapUserfacePathDao tCapUserfacePathDao;

	public TCapUserfacePath get(String id) {
		return super.get(id);
	}
	
	public List<TCapUserfacePath> findList(TCapUserfacePath tCapUserfacePath) {
		return super.findList(tCapUserfacePath);
	}
	
	public Page<TCapUserfacePath> findPage(Page<TCapUserfacePath> page, TCapUserfacePath tCapUserfacePath) {
		return super.findPage(page, tCapUserfacePath);
	}
	
	@Transactional(readOnly = false)
	public void save(TCapUserfacePath tCapUserfacePath) {
		super.save(tCapUserfacePath);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCapUserfacePath tCapUserfacePath) {
		super.delete(tCapUserfacePath);
	}

	public TCapUserfacePath queryByPid(String id) {
		TCapUserfacePath t = tCapUserfacePathDao.queryByPid(id);
		return t;
	}
	
}