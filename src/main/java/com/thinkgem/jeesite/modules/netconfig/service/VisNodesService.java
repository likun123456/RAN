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
import com.thinkgem.jeesite.modules.netconfig.entity.VisNodes;
import com.thinkgem.jeesite.modules.netconfig.dao.VisNodesDao;

/**
 * 拓扑节点管理Service
 * @author zhuguangrui
 * @version 2018-03-08
 */
@Service
@Transactional(readOnly = true)
public class VisNodesService extends CrudService<VisNodesDao, VisNodes> {
	
	@Autowired
	VisNodesDao visNodesDao;

	public VisNodes get(String id) {
		return super.get(id);
	}
	
	public List<VisNodes> findList(VisNodes visNodes) {
		return super.findList(visNodes);
	}
	
	public Page<VisNodes> findPage(Page<VisNodes> page, VisNodes visNodes) {
		return super.findPage(page, visNodes);
	}
	
	@Transactional(readOnly = false)
	public void save(VisNodes visNodes) {
		super.save(visNodes);
	}
	
	@Transactional(readOnly = false)
	public void delete(VisNodes visNodes) {
		super.delete(visNodes);
	}
	
	@Transactional(readOnly = false)
	public void savePosition(VisNodes node) {
		visNodesDao.savePosition(node);
	}

	public String findnodeidByNidAndType(String netid, String netType) {
		return visNodesDao.findnodeidByNidAndType(netid,netType);
	}
	
}