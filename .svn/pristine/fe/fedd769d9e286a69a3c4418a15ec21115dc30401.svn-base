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
import com.thinkgem.jeesite.modules.userquery.entity.TCapPcrfPath;
import com.thinkgem.jeesite.modules.userquery.dao.TCapPcrfPathDao;

/**
 * 单用户信令追踪PCRF抓包Service
 * @author zhuguangrui
 * @version 2017-06-16
 */
@Service
@Transactional(readOnly = true)
public class TCapPcrfPathService extends CrudService<TCapPcrfPathDao, TCapPcrfPath> {
	
	@Autowired
	TCapPcrfPathDao tCapPcrfPathDao;

	public TCapPcrfPath get(String id) {
		return super.get(id);
	}
	
	public List<TCapPcrfPath> findList(TCapPcrfPath tCapPcrfPath) {
		return super.findList(tCapPcrfPath);
	}
	
	public Page<TCapPcrfPath> findPage(Page<TCapPcrfPath> page, TCapPcrfPath tCapPcrfPath) {
		return super.findPage(page, tCapPcrfPath);
	}
	
	@Transactional(readOnly = false)
	public void save(TCapPcrfPath tCapPcrfPath) {
		super.save(tCapPcrfPath);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCapPcrfPath tCapPcrfPath) {
		super.delete(tCapPcrfPath);
	}

	public List<TCapPcrfPath> queryPcrfInfoById(String p_id) {
		List<TCapPcrfPath> list = tCapPcrfPathDao.queryPcrfInfoById(p_id);
		return list;
	}
	
}