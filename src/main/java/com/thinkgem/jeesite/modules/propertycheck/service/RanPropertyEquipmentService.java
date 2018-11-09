/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.propertycheck.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.propertycheck.entity.RanPropertyEquipment;
import com.thinkgem.jeesite.modules.propertycheck.dao.RanPropertyEquipmentDao;

/**
 * 资产信息核查Service
 * @author 李昆
 * @version 2018-11-09
 */
@Service
@Transactional(readOnly = true)
public class RanPropertyEquipmentService extends CrudService<RanPropertyEquipmentDao, RanPropertyEquipment> {
	@Autowired
	private RanPropertyEquipmentDao ranPropertyEquipmentDao;
	public RanPropertyEquipment get(String id) {
		return super.get(id);
	}
	
	public List<RanPropertyEquipment> findList(RanPropertyEquipment ranPropertyEquipment) {
		return super.findList(ranPropertyEquipment);
	}
	
	public Page<RanPropertyEquipment> findPage(Page<RanPropertyEquipment> page, RanPropertyEquipment ranPropertyEquipment) {
		return super.findPage(page, ranPropertyEquipment);
	}
	
	@Transactional(readOnly = false)
	public void save(RanPropertyEquipment ranPropertyEquipment) {
		super.save(ranPropertyEquipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(RanPropertyEquipment ranPropertyEquipment) {
		super.delete(ranPropertyEquipment);
	}
	@Transactional(readOnly=false)
	public boolean insertBatch(List<RanPropertyEquipment> list) {
		return ranPropertyEquipmentDao.insertBatch(list);
	}
	
}