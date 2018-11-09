/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.paramconfig.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.paramconfig.entity.TParamExportedExcel;

/**
 * 网元参数信息报表DAO接口
 * @author 王晶石
 * @version 2017-06-16
 */
@MyBatisDao
public interface TParamExportedExcelDao extends CrudDao<TParamExportedExcel> {
	
}