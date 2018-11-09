package com.thinkgem.jeesite.modules.cheat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.cheat.dao.TCheatPackageDao;
import com.thinkgem.jeesite.modules.cheat.entity.CheatPackage;

/**
 * 欺诈包下载
 * @author 杨海
 */
@Service
@Transactional(readOnly = true)
public class CheatPackageService extends CrudService<TCheatPackageDao, CheatPackage> {

	public CheatPackage get(String id) {
		return super.get(id);
	}
}