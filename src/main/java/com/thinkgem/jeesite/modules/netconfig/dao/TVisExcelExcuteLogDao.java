/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcelExcuteLog;

/**
 * 执行模板日志DAO接口
 * @author 王晶石
 * @version 2018-04-19
 */
@MyBatisDao
public interface TVisExcelExcuteLogDao extends CrudDao<TVisExcelExcuteLog> {

	List<TVisExcelExcuteLog> findListByIds(@Param("ids")String ids);
	
}