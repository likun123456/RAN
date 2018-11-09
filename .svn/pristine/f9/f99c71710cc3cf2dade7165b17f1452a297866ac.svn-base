package com.thinkgem.jeesite.modules.paramconfig.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.paramconfig.dao.ParamExportDao;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamData;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

@Service
@Transactional(readOnly = true)
public class ParamExportService extends CrudService<ParamExportDao, ParamData>   {

	@Autowired
	private ParamExportDao paramExportDao;
	
	public List<ParamData> queryParamConfig(String tableType) {
		List<ParamData> list = new ArrayList<ParamData>();
		List<String> paramTypes = paramExportDao.queryParamTypeList(tableType);
		int i = 2;
		if (paramTypes != null && paramTypes.size() > 0) {
			for (String paramType : paramTypes) {
				ParamData paramTypeVO = new ParamData();
				paramTypeVO.setId(-i+"");
				paramTypeVO.setPid(0);
				paramTypeVO.setName(paramType);
				list.add(paramTypeVO);
				List<ParamData> paramIdList = paramExportDao.queryParamObjectList(tableType, paramType);
				if (paramIdList != null && paramIdList.size() != 0) {
					for (ParamData o : paramIdList) {
						ParamData paramDataVO = new ParamData();
						paramDataVO.setId(o.getId());
						paramDataVO.setPid(-i);
						paramDataVO.setName(o.getParamid());
						if (1==Integer.valueOf(o.getIsExport())) {
							paramDataVO.setTemp_field1("true");
						}else{
							paramDataVO.setTemp_field1("false");
						}
						list.add(paramDataVO);
					}
				}
				i++;
			}
		}
		return list;
	}
	@Transactional(readOnly = false)
	public void updateParamData(ParamData paramData){
		paramExportDao.updateParamCollectConfig(paramData.getTemp_field2());
		//还原网元表
		paramExportDao.updateNewnetelementParamexportedByType(paramData.getTemp_field1());
		//更新网元表
		String tempArrId[]=paramData.getNetid().split(",");
		String []netId=new String[tempArrId.length/2];
		System.arraycopy(tempArrId, 0, netId, 0, tempArrId.length/2);
		paramExportDao.updateNewnetelementParamexportedById(netId);
		//还原paramDictionary表
		String tableType="";
		if (null!=paramData.getTemp_field1()&&!paramData.getTemp_field1().equals("")) {
			tableType=DictUtils.getDictLabel(paramData.getTemp_field1(),"biz_net_type",paramData.getTemp_field1());
			tableType ="SAEGW".equals(tableType)?"epg":tableType;
		}
		paramExportDao.updateParamDictionary(tableType);
		//更新paramDictionary表
		paramExportDao.updateParamDictionaryById(tableType,paramData.getTemp_field3().split(","));
		
	}
}
