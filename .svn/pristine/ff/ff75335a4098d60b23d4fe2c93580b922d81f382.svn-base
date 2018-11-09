/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.userquery.entity.TCapEpgPath;
import com.thinkgem.jeesite.modules.userquery.dao.TCapEpgPathDao;

/**
 * 单用户信令追踪SAEGW池组抓包Service
 * @author zhuguangrui
 * @version 2017-06-16
 */
@Service
@Transactional(readOnly = true)
public class TCapEpgPathService extends CrudService<TCapEpgPathDao, TCapEpgPath> {

	public TCapEpgPath get(String id) {
		return super.get(id);
	}
	
	public List<TCapEpgPath> findList(TCapEpgPath tCapEpgPath) {
		return super.findList(tCapEpgPath);
	}
	
	public Page<TCapEpgPath> findPage(Page<TCapEpgPath> page, TCapEpgPath tCapEpgPath) {
		return super.findPage(page, tCapEpgPath);
	}
	
	@Transactional(readOnly = false)
	public void save(TCapEpgPath tCapEpgPath) {
		super.save(tCapEpgPath);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCapEpgPath tCapEpgPath) {
		super.delete(tCapEpgPath);
	}
	
}