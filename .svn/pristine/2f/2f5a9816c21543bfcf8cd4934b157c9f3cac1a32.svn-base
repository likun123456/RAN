package com.thinkgem.jeesite.modules.cheat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.dao.CaptureParamConfigDao;
import com.thinkgem.jeesite.modules.cheat.entity.CaptureParamConfig;

@Service
@Transactional(readOnly = true)
public class CaptureParamConfigService extends CrudService<CaptureParamConfigDao, CaptureParamConfig>   {

	@Autowired
	private CaptureParamConfigDao captureParamConfigDao;
	
	public List<CaptureParamConfig> queryConfig() {
		return captureParamConfigDao.queryConfig();
	}
	
}
