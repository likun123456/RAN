/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.collectset.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.entity.FTPInfo;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.collectset.dao.TCollectordeployDao;
import com.thinkgem.jeesite.modules.collectset.entity.TCollectordeploy;

/**
 * 采集器配置Service
 * @author yanghai
 * @version 2017-05-24
 */
@Service
@Transactional(readOnly = true)
public class TCollectordeployService extends CrudService<TCollectordeployDao, TCollectordeploy> {
	
	@Autowired
	TCollectordeployDao tCollectordeployDao;

	public TCollectordeploy get(String id) {
		return super.get(id);
	}
	
	public List<TCollectordeploy> findList(TCollectordeploy tCollectordeploy) {
		return super.findList(tCollectordeploy);
	}
	
	public Page<TCollectordeploy> findPage(Page<TCollectordeploy> page, TCollectordeploy tCollectordeploy) {
		return super.findPage(page, tCollectordeploy);
	}
	
	@Transactional(readOnly = false)
	public void save(TCollectordeploy tCollectordeploy) {
		super.save(tCollectordeploy);
	}
	
	@Transactional(readOnly = false)
	public void delete(TCollectordeploy tCollectordeploy) {
		super.delete(tCollectordeploy);
	}
	
	public String getEbmLogCollectorIp(String netid) {
		String ip = tCollectordeployDao.getEbmLogCollectorIp(netid);
		return ip;
	}
	
	public String getCheatCollectorIp(String netid) {
		String ip = tCollectordeployDao.getCheatCollectorIp(netid);
		return ip;
	}
	
	public List<FTPInfo> queryFTPInfoList(){
		List<FTPInfo> ftpInfoList=new ArrayList<FTPInfo>();
		FTPInfo fTPInfo=null;
		List<TCollectordeploy> collectList=this.findList(new TCollectordeploy());
		for(TCollectordeploy tCollectordeploy:collectList){
			if(tCollectordeploy.getCoredataebmlog()==1) {
				fTPInfo=new FTPInfo();
				fTPInfo.setHost(tCollectordeploy.getIp());
				fTPInfo.setUserName(tCollectordeploy.getUsername());
				fTPInfo.setPassword(tCollectordeploy.getPassword());
				/*配置EBM数据库的认为 是EBM采集器*/
				ftpInfoList.add(fTPInfo);
			}
		}
		return ftpInfoList;
	}
	
}