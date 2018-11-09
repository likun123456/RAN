package com.thinkgem.jeesite.modules.netconfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.netconfig.dao.TVisExcelModuleDetailDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModuleDetail;
@Service
@Transactional(readOnly = true)
public class TVisExcelModuleDetailService extends CrudService<TVisExcelModuleDetailDao,TVisExcelModuleDetail> {
	@Autowired
	private TVisExcelModuleDetailDao tVisExcelModuleDetailDao;
	
	public List<TVisExcelModuleDetail> findParamList(TVisExcelModuleDetail tVisExcelModuleDetail){
		return tVisExcelModuleDetailDao.findParamList(tVisExcelModuleDetail);
	}
	@Transactional(readOnly = false)
	public void updateSort(String id,int sort) {
		tVisExcelModuleDetailDao.updateSort(id, sort);
	}
}
