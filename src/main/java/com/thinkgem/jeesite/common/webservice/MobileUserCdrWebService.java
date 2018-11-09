package com.thinkgem.jeesite.common.webservice;

import javax.jws.WebService;

@WebService
public interface MobileUserCdrWebService {
	
	public String getUserCdrList(String msisdn,String beginTime,String endTime);
	
}
