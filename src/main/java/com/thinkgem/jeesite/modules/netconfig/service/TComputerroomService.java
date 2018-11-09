/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.entity.TComputerroom;
import com.thinkgem.jeesite.modules.netconfig.dao.TComputerroomDao;

/**
 * 机房管理Service
 * @author yh
 * @version 2018-03-08
 */
@Service
@Transactional(readOnly = true)
public class TComputerroomService extends CrudService<TComputerroomDao, TComputerroom> {

	public TComputerroom get(String id) {
		return super.get(id);
	}
	
	public List<TComputerroom> findList(TComputerroom tComputerroom) {
		return super.findList(tComputerroom);
	}
	
	public Page<TComputerroom> findPage(Page<TComputerroom> page, TComputerroom tComputerroom) {
		return super.findPage(page, tComputerroom);
	}
	
	@Transactional(readOnly = false)
	public void save(TComputerroom tComputerroom) {
		super.save(tComputerroom);
	}
	
	@Transactional(readOnly = false)
	public void delete(TComputerroom tComputerroom) {
		super.delete(tComputerroom);
	}
	
}