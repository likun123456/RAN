package com.thinkgem.jeesite.modules.paramconfig.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.modules.netconfig.dao.TNewnetelementDao;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.paramconfig.dao.NetParamCompareDao;

@Service
@Transactional(readOnly = true)
public class NetParamCompareService {

	@Autowired
	private NetParamCompareDao netParamCompareDao;
	
	@Autowired
	private TNewnetelementDao tNewnetelementDao;
	
	
	public List<LinkedHashMap<String, Object>> searchMultiNetBySql(String netType, String netid, String date,String paramtype,String keyword,String removeFlag, int offset, int limit) {
		
		String sql = this.spellMultiNetSql(offset, limit,false, netType, netid, date, keyword, paramtype,removeFlag);
		Map<String,String> newNetelementMap = this.getNewNetelements(netType);
		List<LinkedHashMap<String, Object>> list =  netParamCompareDao.searchMultiNetBySql(sql);
		Iterator it = null;
		for(int i=0;i<list.size();i++){
			LinkedHashMap map = list.get(i);
			it = map.keySet().iterator();
			Map<String,String> paramValueMap = new LinkedHashMap<String,String>();
			while(it.hasNext()){
				 String key = (String) it.next();
	             String str = (String)map.get(key);
				 if(key.contains("paramvalue")){
					 String id = key.split("_")[0];
	            	 paramValueMap.put(newNetelementMap.get(id), str);
	             }
			}
			
			Iterator colit = paramValueMap.keySet().iterator();
			while(colit.hasNext()) {
				String key = (String)colit.next();
				map.put(key, paramValueMap.get(key));
			}
			//map.put("paramValues", paramValueMap);
			
		}
		return list;
	}
	
	private Map<String, String> getNewNetelements(String nettype){
		List<TNewnetelement> newNetelements = tNewnetelementDao.getNewNetelementsByType(nettype);
		Map<String,String> newNetelementMap = new HashMap<String,String>();
		for(TNewnetelement netElement : newNetelements){
			newNetelementMap.put(String.valueOf(netElement.getId()), netElement.getFname());
		}
		return newNetelementMap;
	}
	
	public int searchMultiNetBySqlCount(String netType, String netid, String date,String paramtype,String keyword,String removeFlag, int offset, int limit) {
		String sql = this.spellMultiNetSql(offset, limit,true, netType, netid, date, keyword, paramtype,removeFlag);
		int count = netParamCompareDao.searchMultiNetBySql(sql).size();
		return count;
	}
	
	public List<String> getDynamicColumns(String netType, String netid) {
		List<String> list = new ArrayList<String>();
		Map<String,String> newNetelementMap = this.getNewNetelements(netType);
		if(null != netid && !"".equals(netid)) {
			String[] netids = netid.split(",");
			for(String id : netids) {
				list.add(newNetelementMap.get(id));
			}
		}
		return list;
	}
	
	private String spellMultiNetSql(int pagenum, int pagecount, boolean totalCount, String nettype, String netIds, String date, String keyword,String paramtype,String removeFlag) {
		String pageStr = "";
		String netType="mme";
		if("2".equals(nettype)){
			netType="epg";
		}else if("3".equals(nettype)){
			netType="pcrf";
		}
		StringBuffer hql =new StringBuffer("select t.paramid,d.cnname,d.name,d.version,d.type,d.description,d.endescription,d.applyrange,d.valuerange," +
																				"d.memo,d.defaultvalue,d.suggestvalue, ");
		String [] netIdArr = null;
		if(netIds.contains(",")){
			netIdArr = netIds.split(",");
		}else{
			netIdArr = new String[1];
			netIdArr[0] =netIds;
		}
		for(String netId : netIdArr){
			hql.append("ifnull(GROUP_CONCAT("+netId+"_paramvalue),'') AS '"+netId+"_paramvalue',");
		}
		hql.deleteCharAt((hql.length()-1));
		hql.append(" "+" FROM(");
		hql.append("SELECT netid,paramid,paramvalue,version,");
		for(String netId : netIdArr){
			hql.append("IF(netid  = "+netId+", paramvalue, NULL) AS '"+netId+"_paramvalue',");
		}
		hql.deleteCharAt((hql.length()-1));
		hql.append("FROM t_param_data where fetchdate='" +date.replaceAll("-", "")+ "'");
		hql.append(") t,t_param_dictionary_"+ netType + " d where (t.paramid = d.paramid or t.paramid like CONCAT('%(',d.paramidextend,')')) and t.version=d.version");
		//参数类型
		if(paramtype!=null && !paramtype.equals("")){
			hql.append(" and d.type='"+ paramtype +"'");
		}
		//精确匹配和模糊匹配
		if(keyword!=null && !keyword.equals("")){
			hql.append(" and (d.name='"+keyword+"' or d.paramid like '%"+keyword+"%' or d.cnname like '%"+keyword+"%' or d.name like '%"+keyword+"%' or d.type like '%"+keyword+"%' or d.description like '%"+keyword+"%' or d.endescription like '%"+keyword+"%'" +
				" or d.applyrange like '%"+keyword+"%' or d.valuerange like '%"+keyword+"%' or "+
				"d.memo like '%"+keyword+"%' or d.defaultvalue like '%"+keyword+"%' or d.suggestvalue like '%"+keyword+"%' )");
		}  
		hql.append(" GROUP BY t.paramid ");
		//是否过滤相同项
		if(removeFlag != null && removeFlag.equals("yes") && netIdArr.length>1){
			hql.append(" having ");
			hql.append(netIdArr[0]+"_paramvalue ");
			for(int i=1;i<netIdArr.length-1;i++){
				hql.append(" != "+netIdArr[i]+"_paramvalue or "+netIdArr[i]+"_paramvalue ");
			}
			hql.append(" !=" + netIdArr[netIdArr.length-1]+"_paramvalue ");
		}
		
		if(!totalCount) {
			pageStr = "limit " + pagenum + "," + pagecount;
		}
		
		hql.append(pageStr);
		return hql.toString();
	}
}
