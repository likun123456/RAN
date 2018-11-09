/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TEdgesDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TEdges;

/**
 * 拓扑设备管理Service
 * @author yh
 * @version 2018-03-08
 */
@Service
@Transactional(readOnly = true)
public class TEdgesService extends CrudService<TEdgesDao, TEdges> {
	
	@Autowired
	private TEdgesDao tedgesDao;

	public TEdges get(String id) {
		return super.get(id);
	}
	
	public List<TEdges> findList(TEdges tEdges) {
		return super.findList(tEdges);
	}
	
	public Page<TEdges> findPage(Page<TEdges> page, TEdges tEdges) {
		return super.findPage(page, tEdges);
	}
	
	@Transactional(readOnly = false)
	public void save(TEdges tEdges) {
		super.save(tEdges);
	}
	
	@Transactional(readOnly = false)
	public void delete(TEdges tEdges) {
		super.delete(tEdges);
	}
	
	@Transactional(readOnly = false)
	public void deleteFromOrToNode(TEdges tedges){
		tedgesDao.deleteFromOrToNode(tedges);
	}
	
	@Transactional(readOnly = false)
	public void deleteFromAndToNode(TEdges tedges){
		tedgesDao.deleteFromAndToNode(tedges);
	}
	
}