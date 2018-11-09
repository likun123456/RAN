/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.userquery.entity.TCapMmePath;
import com.thinkgem.jeesite.modules.userquery.dao.TCapMmePathDao;

/**
 * 单用户信令追踪MME抓包Service
 * @author zhuguangrui
 * @version 2017-06-15
 */
@Service
@Transactional(readOnly = true)
public class TCapMmePathService extends CrudService<TCapMmePathDao, TCapMmePath> {

	public TCapMmePath get(String id) {
		return super.get(id);
	}
	
	public List<TCapMmePath> findList(TCapMmePath tCapMmePath) {
		return super.findList(tCapMmePath);
	}
	
	public Page<TCapMmePath> findPage(Page<TCapMmePath> page, TCapMmePath tCapMmePath) {
		return super.findPage(page, tCapMmePath);
	}
	
	@Transactional(readOnly = false)
	public void save(TCapMmePath tCapMmePath) {
		super.save(tCapMmePath);
	}
	
	@Transactional(readOnly = false)
	public String saveAndGetId(TCapMmePath tCapMmePath) {
		super.save(tCapMmePath);
		return tCapMmePath.getId();
	}
	
	@Transactional(readOnly = false)
	public void delete(TCapMmePath tCapMmePath) {
		super.delete(tCapMmePath);
	}
	
}