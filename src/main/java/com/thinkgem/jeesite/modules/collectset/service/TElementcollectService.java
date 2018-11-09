package com.thinkgem.jeesite.modules.collectset.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.collectset.dao.TElementcollectDao;
import com.thinkgem.jeesite.modules.collectset.entity.TCollectordeploy;
import com.thinkgem.jeesite.modules.collectset.entity.TElementcollect;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TOss;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;

/**
 * 采集器配置Service
 * 
 * @author yanghai
 * @version 2017-05-24
 */
@Service
@Transactional(readOnly = true)
public class TElementcollectService extends CrudService<TElementcollectDao, TElementcollect> {
	@Autowired
	TElementcollectDao telementcollectDao;

	public TElementcollect get(String id) {
		return super.get(id);
	}
	/**
	 * 查询采集器配置的ztree数据
	 * @param plist
	 * @param nlist
	 * @param type
	 * @return
	 */
	public List<TElementcollect> findTreeDataList(List<TPool> plist, List<TNewnetelement> nlist, String type) {
		List<TElementcollect> list = new ArrayList<TElementcollect>();
		int i = 2;
		if (plist != null && plist.size() > 0) {
			for (TPool pool : plist) {
				TElementcollect netVO = new TElementcollect();
				netVO.setFid(-i);
				netVO.setFnid(0);
				netVO.setFname(pool.getFpoolname());
				// 采集类型为话单时只采集GGSN池组下的网元
				if (("MME".equals(pool.getFtype()) || "PCRF".equals(pool.getFtype())) && "2".equals(type)) {
					continue;
				}
				// 采集类型为计费欺诈时只采集GGSN池组下的网元
				if (("MME".equals(pool.getFtype()) || "PCRF".equals(pool.getFtype())) && "6".equals(type)) {
					continue;
				}
				// 采集类型为EbmLog时只采集MME网元
				/*if (("SAEGW".equals(pool.getFtype())) && "4".equals(type)) {
					continue;
				}*/
				// 采集类型为单用户查询时候只采集GGSN池组下的网元
				if (("MME".equals(pool.getFtype()) || "PCRF".equals(pool.getFtype())) && "5".equals(type)) {
					continue;
				}
				list.add(netVO);
				if (nlist != null && nlist.size() > 0) {
					for (TNewnetelement net : nlist) {
						if (net.getFnid().equals(Long.parseLong(pool.getId()))) {
							TElementcollect newnetVO = new TElementcollect();
							newnetVO.setFid(Long.parseLong(net.getId()));
							newnetVO.setFnid(-i);
							newnetVO.setFname(net.getFname());
							list.add(newnetVO);
						}
					}
				}
				i++;
			}
		}
		return this.dealTreeData(list, type);
	}
	
	public List<TOss> findOssTreeDataList(List<TOss> ossList, String type) {
		TElementcollect telementcollect=new TElementcollect();
		telementcollect.setType(Integer.parseInt(type));
		List<TElementcollect> oList = telementcollectDao.findAllList(telementcollect);
		
		if (oList != null && oList.size() > 0) {
			
			for(TElementcollect o : oList) {
				for(TOss oss : ossList) {
					if(Long.parseLong(oss.getId()) == o.getFid()) {
						oss.setTemp_field1("check");
						oss.setTemp_field2(oss.getFname()+"【" + String.valueOf(o.getCollectorname()) + "】");
						break;
					}
				}
			}
		}
		
		return ossList;
	}
	/**
	 * 处理已经配置的采集器与网元的关系
	 * @param list
	 * @param type
	 * @return
	 */
	private List<TElementcollect> dealTreeData(List<TElementcollect> list, String type) {
		TElementcollect telementcollect=new TElementcollect();
		telementcollect.setType(Integer.parseInt(type));
		List<TElementcollect> oList =telementcollectDao.findAllList(telementcollect);
		if (oList != null && oList.size() > 0) {
			for (TElementcollect o : oList) {
				for (TElementcollect ne : list) {
					if (ne.getFid() == Long.parseLong(String.valueOf(o.getFid()))) {
						ne.setTemp_field1("check");
						ne.setTemp_field2(ne.getFname()+"【" + String.valueOf(o.getCollectorname()) + "】");
					}
				}
			}
		}
		return list;
	}
	@Transactional(readOnly = false)
	public void save(TCollectordeploy tCollectordeploy) {
		telementcollectDao.deleteHistory(tCollectordeploy);
		String []tempArr=tCollectordeploy.getTemp_field1().split(",");
		for(int i=0;i<tempArr.length;i++){
			if(null != tempArr[i] && !"".equals(tempArr[i]))  {
				tCollectordeploy.setTemp_field4(tempArr[i]);
				telementcollectDao.insertCollect(tCollectordeploy);
			}
		}
	}
	
	public List<TElementcollect> findCollectorIp(TElementcollect telementcollect){
		return telementcollectDao.findCollectorIp(telementcollect);
	}
	
	
	public String getCollectorIdByIp(String ip) {
		
		return telementcollectDao.getCollectorIdByIp(ip);
		
	}

}