/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.paramconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.paramconfig.entity.TParamCmdStatus;
import com.thinkgem.jeesite.modules.paramconfig.dao.TParamCmdStatusDao;

/**
 * 网元参数修改任务状态Service
 * @author 王晶石
 * @version 2017-06-17
 */
@Service
@Transactional(readOnly = true)
public class TParamCmdStatusService extends CrudService<TParamCmdStatusDao, TParamCmdStatus> {

	public TParamCmdStatus get(String id) {
		return super.get(id);
	}
	
	public List<TParamCmdStatus> findList(TParamCmdStatus tParamCmdStatus) {
		return super.findList(tParamCmdStatus);
	}
	
	public Page<TParamCmdStatus> findPage(Page<TParamCmdStatus> page, TParamCmdStatus tParamCmdStatus) {
		return super.findPage(page, tParamCmdStatus);
	}
	
	@Transactional(readOnly = false)
	public void save(TParamCmdStatus tParamCmdStatus) {
		super.save(tParamCmdStatus);
	}
	
	@Transactional(readOnly = false)
	public void delete(TParamCmdStatus tParamCmdStatus) {
		super.delete(tParamCmdStatus);
	}
	
}