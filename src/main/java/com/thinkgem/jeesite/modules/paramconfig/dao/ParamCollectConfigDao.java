package com.thinkgem.jeesite.modules.paramconfig.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamCollectConfig;

@MyBatisDao
public interface ParamCollectConfigDao  extends CrudDao<ParamCollectConfig> {
	
	ParamCollectConfig queryParamConfig();
	
	ParamCollectConfig queryAutoCheckConfig();
	
	void updateAutoCheckConfig(ParamCollectConfig Config);
}
