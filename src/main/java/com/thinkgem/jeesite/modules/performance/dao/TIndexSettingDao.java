package com.thinkgem.jeesite.modules.performance.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.performance.entity.TIndexSettingVO;

/**
 * 首页参数设置DAO接口
 * @author 杨海
 * @version 2017-07-05
 */
@MyBatisDao
public interface TIndexSettingDao extends CrudDao<TIndexSettingVO> {

}