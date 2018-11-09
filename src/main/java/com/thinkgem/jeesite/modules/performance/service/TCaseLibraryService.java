/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.performance.entity.TCaseLibrary;
import com.thinkgem.jeesite.modules.performance.dao.TCaseLibraryDao;

/**
 * 异常原因多维分析(ebmlog)Service
 * @author 王晶石
 * @version 2017-06-14
 */
@Service
@Transactional(readOnly = true)
public class TCaseLibraryService extends CrudService<TCaseLibraryDao, TCaseLibrary> {

	public TCaseLibrary get(String id) {
		return super.get(id);
	}
	
	public List<TCaseLibrary> findList(TCaseLibrary tCaseLibrary) {
		return super.findList(tCaseLibrary);
	}
	
	public Page<TCaseLibrary> findPage(Page<TCaseLibrary> page, TCaseLibrary tCaseLibrary) {
		return super.findPage(page, tCaseLibrary);
	}
	
	@Transactional(readOnly = false)
	public void save(TCaseLibrary tCaseLibrary) {
		super.save(tCaseLibrary);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCaseLibrary tCaseLibrary) {
		super.delete(tCaseLibrary);
	}
	
}