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
import com.thinkgem.jeesite.modules.netconfig.entity.TOss;
import com.thinkgem.jeesite.modules.netconfig.dao.TOssDao;

/**
 * OSS节点基本信息操作Service
 * @author 王晶石
 * @version 2017-05-27
 */
@Service
@Transactional(readOnly = true)
public class TOssService extends CrudService<TOssDao, TOss> {
	
	@Autowired
	private TOssDao tOssDao;

	public TOss get(String id) {
		return super.get(id);
	}
	
	public List<TOss> findList(TOss tOss) {
		return super.findList(tOss);
	}
	
	public Page<TOss> findPage(Page<TOss> page, TOss tOss) {
		return super.findPage(page, tOss);
	}
	
	@Transactional(readOnly = false)
	public void save(TOss tOss) {
		super.save(tOss);
	}
	
	@Transactional(readOnly = false)
	public void delete(TOss tOss) {
		super.delete(tOss);
	}

	public int getOssByName(String fname) {
		TOss t = new TOss();
		t.setFname(fname);
		return tOssDao.getByName(t);
	}
	
}