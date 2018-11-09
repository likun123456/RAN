/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.cheat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.dao.TFreeRatingGroupDao;
import com.thinkgem.jeesite.modules.cheat.entity.TFreeRatingGroup;

/**
 * 免费业务代码Service
 * @author zhuguangrui
 * @version 2017-08-22
 */
@Service
@Transactional(readOnly = true)
public class TFreeRatingGroupService extends CrudService<TFreeRatingGroupDao, TFreeRatingGroup> {

	@Autowired
	private TFreeRatingGroupDao freeRatingGroupDao;
	
	public TFreeRatingGroup get(String id) {
		return super.get(id);
	}
	
	public List<TFreeRatingGroup> findList(TFreeRatingGroup freeRatingGroup) {
		return super.findList(freeRatingGroup);
	}
	
	public Page<TFreeRatingGroup> findPage(Page<TFreeRatingGroup> page, TFreeRatingGroup freeRatingGroup) {
		return super.findPage(page, freeRatingGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(TFreeRatingGroup freeRatingGroup) {
		super.save(freeRatingGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(TFreeRatingGroup freeRatingGroup) {
		super.delete(freeRatingGroup);
	}
	
	@Transactional(readOnly = false)
	public void batchIntert(List<TFreeRatingGroup> list) {
		freeRatingGroupDao.batchIntert(list);
	}
	
}