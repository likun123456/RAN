/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.userquery.dao.TEbmSingleSearchDao;
import com.thinkgem.jeesite.modules.userquery.entity.TEbmSingleSearch;

/**
 * 单用户EBM查询Service
 * @author 王晶石
 * @version 2017-06-17
 */
@Service
@Transactional(readOnly = true)
public class TEbmSingleSearchService extends CrudService<TEbmSingleSearchDao, TEbmSingleSearch> {
	
	@Autowired
	private TEbmSingleSearchDao tEbmSingleSearchDao;

	public TEbmSingleSearch get(String id) {
		return super.get(id);
	}
	@Transactional(readOnly = false)
	public void truncateEbmSingleSearchTable() {
		tEbmSingleSearchDao.truncateEbmSingleSearchTable();
	}

	public List<Entry<String, TEbmSingleSearch>> sortHashMapByTime(Map<String, TEbmSingleSearch> map) {
		List<Map.Entry<String,TEbmSingleSearch>> list = new ArrayList<Map.Entry<String,TEbmSingleSearch>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String,TEbmSingleSearch>>() {
            public int compare(Entry<String, TEbmSingleSearch> o1,
                    Entry<String, TEbmSingleSearch> o2) {
            	int i = -1 ;
            	if(Long.parseLong(o1.getValue().getTime_stemp())>Long.parseLong(o2.getValue().getTime_stemp())){
            		i = 1;
            	}
                return i;
            }
        });
    return list;
	}
	@Transactional(readOnly = false)
	public void insertEbmSingleSearchTable(List<TEbmSingleSearch> list) {
		tEbmSingleSearchDao.insertEbmSingleSearchTable(list);
	}
	public List<TEbmSingleSearch> queryEbmSingleSearchTable(int offset, int limit, String sortName, String sortOrder,
			String nType,String eventType,String eventResult) {
		List<TEbmSingleSearch> list = tEbmSingleSearchDao.queryEbmSingleSearchTable(offset,limit,sortName,sortOrder,nType,eventType,
				eventResult);
		return list;
	}
	public int queryListCount(String nType,String eventType,String eventResult) {
		int count = tEbmSingleSearchDao.queryListCount(nType,eventType,eventResult);
		return count;
	}
	public String queryEbmLogById(int id) {
		String ebmlog = tEbmSingleSearchDao.queryEbmLogById(id);
		return ebmlog;
	}
	public String queryCcdes(String cc) {
		String ccdes = tEbmSingleSearchDao.queryCcdes(cc);
		return ccdes;
	}
	public TEbmSingleSearch querySccdesAndAction(String scc) {
		TEbmSingleSearch t = tEbmSingleSearchDao.querySccdesAndAction(scc);
		return t;
	}
	
}