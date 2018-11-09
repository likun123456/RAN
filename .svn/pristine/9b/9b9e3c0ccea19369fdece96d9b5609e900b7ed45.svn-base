/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TDnsDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TDns;

/**
 * EPA节点管理Service
 * @author 王晶石
 * @version 2017-11-15
 */
@Service
@Transactional(readOnly = true)
public class TDnsService extends CrudService<TDnsDao, TDns> {

	public TDns get(String id) {
		return super.get(id);
	}
	
	public List<TDns> findList(TDns tDns) {
		return super.findList(tDns);
	}
	
	public Page<TDns> findPage(Page<TDns> page, TDns tDns) {
		return super.findPage(page, tDns);
	}
	
	@Transactional(readOnly = false)
	public void save(TDns tDns) {
		super.save(tDns);
	}
	
	@Transactional(readOnly = false)
	public void delete(TDns tDns) {
		super.delete(tDns);
	}
	
}