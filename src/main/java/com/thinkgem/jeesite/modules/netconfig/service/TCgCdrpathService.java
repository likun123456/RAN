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
import com.thinkgem.jeesite.modules.netconfig.entity.TCgCdrpath;
import com.thinkgem.jeesite.modules.netconfig.dao.TCgCdrpathDao;

/**
 * 单表生成Service
 * @author wangjingshi
 * @version 2017-05-25
 */
@Service
@Transactional(readOnly = true)
public class TCgCdrpathService extends CrudService<TCgCdrpathDao, TCgCdrpath> {
	
	@Autowired
	TCgCdrpathDao cdrpathDao;
	
	public TCgCdrpath get(String id) {
		return super.get(id);
	}
	
	public List<TCgCdrpath> findList(TCgCdrpath tCgCdrpath) {
		return super.findList(tCgCdrpath);
	}
	
	public Page<TCgCdrpath> findPage(Page<TCgCdrpath> page, TCgCdrpath tCgCdrpath) {
		return super.findPage(page, tCgCdrpath);
	}
	
	@Transactional(readOnly = false)
	public void save(TCgCdrpath tCgCdrpath) {
		super.save(tCgCdrpath);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCgCdrpath tCgCdrpath) {
		super.delete(tCgCdrpath);
	}
	
}