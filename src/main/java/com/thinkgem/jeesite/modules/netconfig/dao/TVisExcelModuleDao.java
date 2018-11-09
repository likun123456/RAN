package com.thinkgem.jeesite.modules.netconfig.dao;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModule;
@MyBatisDao
public interface TVisExcelModuleDao extends CrudDao<TVisExcelModule> {
	public void updateSort(@Param("id")String id,@Param("sort")int sort);
}
