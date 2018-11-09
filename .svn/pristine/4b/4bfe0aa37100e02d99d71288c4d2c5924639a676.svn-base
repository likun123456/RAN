package com.thinkgem.jeesite.modules.paramconfig.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamData;

/**
 * 网元参数定时导出管理DAO
 * @author yanghai
 * @version 2017-06-16
 */
@MyBatisDao
public interface ParamExportDao extends CrudDao<ParamData> {
	List<String> queryParamTypeList(@Param("tableName")String tableName);
	
	List<ParamData> queryParamObjectList(@Param("tableName")String tableName,@Param("type")String type);
	
	void updateParamCollectConfig(@Param("timeType")String timeType);
	void updateNewnetelementParamexportedByType(@Param("type")String type);
	void updateNewnetelementParamexportedById(@Param("netids")String[] netids);
	void updateParamDictionary(@Param("tableType")String tableType);
	void updateParamDictionaryById(@Param("tableType")String tableType,@Param("ids")String[] ids);
}
