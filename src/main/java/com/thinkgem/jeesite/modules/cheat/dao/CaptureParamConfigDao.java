package com.thinkgem.jeesite.modules.cheat.dao;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.cheat.entity.CaptureParamConfig;

@MyBatisDao
public interface CaptureParamConfigDao  extends CrudDao<CaptureParamConfig> {
	
	List<CaptureParamConfig> queryConfig();
	
}
