/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.entity.TEquipment;
import com.thinkgem.jeesite.modules.netconfig.dao.TEquipmentDao;

/**
 * 拓扑设备管理Service
 * @author yh
 * @version 2018-03-08
 */
@Service
@Transactional(readOnly = true)
public class TEquipmentService extends CrudService<TEquipmentDao, TEquipment> {

	public TEquipment get(String id) {
		return super.get(id);
	}
	
	public List<TEquipment> findList(TEquipment tEquipment) {
		return super.findList(tEquipment);
	}
	
	public Page<TEquipment> findPage(Page<TEquipment> page, TEquipment tEquipment) {
		return super.findPage(page, tEquipment);
	}
	
	@Transactional(readOnly = false)
	public void save(TEquipment tEquipment) {
		super.save(tEquipment);
	}
	
	@Transactional(readOnly = false)
	public void delete(TEquipment tEquipment) {
		super.delete(tEquipment);
	}
	
}