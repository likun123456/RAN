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
import com.thinkgem.jeesite.modules.userquery.entity.TCapAllPath;
import com.thinkgem.jeesite.modules.userquery.dao.TCapAllPathDao;

/**
 * 单用户信令追踪Service
 * @author zhuguangrui
 * @version 2017-06-15
 */
@Service
@Transactional(readOnly = true)
public class TCapAllPathService extends CrudService<TCapAllPathDao, TCapAllPath> {
	
	@Autowired
	TCapAllPathDao tCapAllPathDao;

	public TCapAllPath get(String id) {
		return super.get(id);
	}
	
	public List<TCapAllPath> findList(TCapAllPath tCapAllPath) {
		return super.findList(tCapAllPath);
	}
	
	public Page<TCapAllPath> findPage(Page<TCapAllPath> page, TCapAllPath tCapAllPath) {
		return super.findPage(page, tCapAllPath);
	}
	
	@Transactional(readOnly = false)
	public void save(TCapAllPath tCapAllPath) {
		super.save(tCapAllPath);
	}
	
	@Transactional(readOnly = false)
	public String saveAndGetId(TCapAllPath tCapAllPath) {
		super.save(tCapAllPath);
		return tCapAllPath.getId();
	}
	
	@Transactional(readOnly = false)
	public void delete(TCapAllPath tCapAllPath) {
		super.delete(tCapAllPath);
	}

	public List<TCapAllPath> queryPcrfList(String id) {
		List<TCapAllPath> list = tCapAllPathDao.queryPcrfList(id);
		return list;
	}
	
}