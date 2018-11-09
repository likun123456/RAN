/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.performance.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.performance.entity.TCaseLibrary;

/**
 * 异常原因多维分析(ebmlog)DAO接口
 * @author 王晶石
 * @version 2017-06-14
 */
@MyBatisDao
public interface TCaseLibraryDao extends CrudDao<TCaseLibrary> {
	
}