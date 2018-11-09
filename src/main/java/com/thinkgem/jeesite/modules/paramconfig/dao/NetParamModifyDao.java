package com.thinkgem.jeesite.modules.paramconfig.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamCmdStatus;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamData;

@MyBatisDao
public interface NetParamModifyDao extends CrudDao<ParamData> {

	public List<ParamData> searchSingleNet(@Param("netType")String netType,@Param("netId")String netId,@Param("date")String date,@Param("paramtype")String paramtype,@Param("keyword")String keyword,@Param("offset")int offset,
            @Param("limit")int limit);
	
	public Integer searchSingleNetCount(@Param("netType")String netType,@Param("netId")String netId,@Param("date")String date,@Param("paramtype")String paramtype,@Param("keyword")String keyword);

	public Long saveParamStatus(ParamCmdStatus cmdStatus);
}
