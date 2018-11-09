/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.paramconfig.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.paramconfig.entity.TParamExportedExcel;
import com.thinkgem.jeesite.modules.paramconfig.dao.TParamExportedExcelDao;

/**
 * 网元参数信息报表Service
 * @author 王晶石
 * @version 2017-06-16
 */
@Service
@Transactional(readOnly = true)
public class TParamExportedExcelService extends CrudService<TParamExportedExcelDao, TParamExportedExcel> {

	public TParamExportedExcel get(String id) {
		return super.get(id);
	}
	
	public List<TParamExportedExcel> findList(TParamExportedExcel tParamExportedExcel) {
		return super.findList(tParamExportedExcel);
	}
	
	public Page<TParamExportedExcel> findPage(Page<TParamExportedExcel> page, TParamExportedExcel tParamExportedExcel) {
		return super.findPage(page, tParamExportedExcel);
	}
	
	@Transactional(readOnly = false)
	public void save(TParamExportedExcel tParamExportedExcel) {
		super.save(tParamExportedExcel);
	}
	
	@Transactional(readOnly = false)
	public void delete(TParamExportedExcel tParamExportedExcel) {
		super.delete(tParamExportedExcel);
	}
	
}