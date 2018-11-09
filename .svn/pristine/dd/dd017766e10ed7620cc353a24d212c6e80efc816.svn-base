package com.thinkgem.jeesite.modules.paramconfig.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.paramconfig.dao.ParamCollectConfigDao;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamCollectConfig;

@Service
@Transactional(readOnly = true)
public class ParamCollectConfigService extends CrudService<ParamCollectConfigDao, ParamCollectConfig>   {

	@Autowired
	private ParamCollectConfigDao paramCollectConfigDao;
	
	public ParamCollectConfig queryParamConfig() {
		return paramCollectConfigDao.queryParamConfig();
	}
	
	public ParamCollectConfig queryAutoCheckConfig() {
		return paramCollectConfigDao.queryAutoCheckConfig();
	}
	
	@Transactional(readOnly = false)
	public void save(ParamCollectConfig paramCollectConfig) {
		super.save(paramCollectConfig);
	}
	@Transactional(readOnly = false)
	public void updateAutoCheckConfig(ParamCollectConfig Config) {
		paramCollectConfigDao.updateAutoCheckConfig(Config);
	}
}
