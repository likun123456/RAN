package com.thinkgem.jeesite.modules.cheat.entity;

import java.util.List;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 免费业务类型欺诈流量折线图
 * 
 * @author yanghai
 *
 */
public class CheatFreeBusinessChart extends DataEntity<CheatFreeBusinessChart> {

	private static final long serialVersionUID = -3720500081506905320L;
	private List<CheatFreeBusiness> listCheatFreeBusiness;


	public List<CheatFreeBusiness> getListCheatFreeBusiness() {
		return listCheatFreeBusiness;
	}

	public void setListCheatFreeBusiness(List<CheatFreeBusiness> listCheatFreeBusiness) {
		this.listCheatFreeBusiness = listCheatFreeBusiness;
	}

	public List<String> getListTime() {
		return listTime;
	}

	public void setListTime(List<String> listTime) {
		this.listTime = listTime;
	}

	private List<String> listTime;
}
