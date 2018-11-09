package com.thinkgem.jeesite.modules.netconfig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TVisExcelDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcel;
@Service
@Transactional(readOnly = true)
public class TVisExcelService extends CrudService<TVisExcelDao,TVisExcel> {
	@Autowired
	TVisExcelDao tVisExcelDao;
	@Transactional(readOnly = false)
	public int saveTVisExcel(TVisExcel tVisExcel){
		return tVisExcelDao.insert(tVisExcel);
	}
	@Transactional(readOnly = false)
	public void updateSort(String id,int sort) {
		tVisExcelDao.updateSort(id, sort);
	}
	
	public int getByName(String name) {
		
		return tVisExcelDao.getByName(name);
		
	}
}
