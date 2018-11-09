package com.thinkgem.jeesite.modules.netconfig.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelModuleDetail;
@MyBatisDao
public interface TVisExcelModuleDetailDao extends CrudDao<TVisExcelModuleDetail> {
	public List<TVisExcelModuleDetail> findParamList(TVisExcelModuleDetail tVisExcelModuleDetail);
	public void updateSort(@Param("id")String id,@Param("sort")int sort);
}
