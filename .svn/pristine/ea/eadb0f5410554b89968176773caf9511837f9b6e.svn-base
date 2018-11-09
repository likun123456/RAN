/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisDangerStatus;
import com.thinkgem.jeesite.modules.netconfig.dao.TVisDangerStatusDao;

/**
 * 危险命令状态Service
 * @author 王晶石
 * @version 2018-05-07
 */
@Service
@Transactional(readOnly = true)
public class TVisDangerStatusService extends CrudService<TVisDangerStatusDao, TVisDangerStatus> {

	public TVisDangerStatus get(String id) {
		return super.get(id);
	}
	
	public List<TVisDangerStatus> findList(TVisDangerStatus tVisDangerStatus) {
		return super.findList(tVisDangerStatus);
	}
	
	public Page<TVisDangerStatus> findPage(Page<TVisDangerStatus> page, TVisDangerStatus tVisDangerStatus) {
		return super.findPage(page, tVisDangerStatus);
	}
	
	@Transactional(readOnly = false)
	public void save(TVisDangerStatus tVisDangerStatus) {
		super.save(tVisDangerStatus);
	}
	
	@Transactional(readOnly = false)
	public void delete(TVisDangerStatus tVisDangerStatus) {
		super.delete(tVisDangerStatus);
	}
	
}