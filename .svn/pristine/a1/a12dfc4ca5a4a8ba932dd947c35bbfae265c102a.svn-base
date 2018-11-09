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
import com.thinkgem.jeesite.modules.userquery.entity.TCapPcrfFilterPath;
import com.thinkgem.jeesite.modules.userquery.dao.TCapPcrfFilterPathDao;

/**
 * 单用户信令追踪PCRF抓包过滤msisdnService
 * @author zhuguangrui
 * @version 2017-06-16
 */
@Service
@Transactional(readOnly = true)
public class TCapPcrfFilterPathService extends CrudService<TCapPcrfFilterPathDao, TCapPcrfFilterPath> {

	public TCapPcrfFilterPath get(String id) {
		return super.get(id);
	}
	
	public List<TCapPcrfFilterPath> findList(TCapPcrfFilterPath tCapPcrfFilterPath) {
		return super.findList(tCapPcrfFilterPath);
	}
	
	public Page<TCapPcrfFilterPath> findPage(Page<TCapPcrfFilterPath> page, TCapPcrfFilterPath tCapPcrfFilterPath) {
		return super.findPage(page, tCapPcrfFilterPath);
	}
	
	@Transactional(readOnly = false)
	public void save(TCapPcrfFilterPath tCapPcrfFilterPath) {
		super.save(tCapPcrfFilterPath);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCapPcrfFilterPath tCapPcrfFilterPath) {
		super.delete(tCapPcrfFilterPath);
	}

	
}