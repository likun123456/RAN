package com.thinkgem.jeesite.modules.paramconfig.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamData;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamPackage;

/**
 * 网元参数更新包管理DAO
 * @author chenhongbo
 * @version 2017-06-13
 */
@MyBatisDao
public interface ParamPackageManagerDao extends CrudDao<ParamPackage> {
	
	void batchIntertDictionary(Map<String, Object> map);
	
	void deleteDictionaryByVersion(@Param("tableRemark")String tableRemark,@Param("version")String version);
}
