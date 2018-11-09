package com.thinkgem.jeesite.modules.paramconfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.paramconfig.dao.NetParamModifyDao;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamCmdStatus;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamData;

@Service
@Transactional(readOnly = true)
public class NetParamModifyService extends CrudService<NetParamModifyDao, ParamData>   {

	@Autowired
	private NetParamModifyDao netParamModifyDao;
	
	
	public List<ParamData> searchSingleNet(String netType, String netId,String date,String paramtype,String keyword,int offset,int limit) {
		
		return netParamModifyDao.searchSingleNet(netType, netId, date, paramtype, keyword, offset, limit);
	}
	
	public Integer searchSingleNetCount(String netType,String netId,String date,String paramtype,String keyword) {
		return netParamModifyDao.searchSingleNetCount(netType, netId, date, paramtype, keyword);
	}
	
	@Transactional(readOnly = false)
	public Long saveParamStatus(ParamCmdStatus cmdStatus) {
		return netParamModifyDao.saveParamStatus(cmdStatus);
	}
}
