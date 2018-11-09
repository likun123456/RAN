package com.thinkgem.jeesite.common.webservice;

import javax.jws.WebService;

@WebService
public interface MobileWebService {
	
	public String getMainIndex();
	
	public String getNetelementIndex(String poolName, String datetime);
	
	public String mainChart();
	
	public String netelementChart(String poolName,String datetime);
	
	public String queryEbmlogStatistics(String startTime,String endTime,String netid,String eventType,int limit, int offset);
	
	public String analysis(String cc,String scc,String netid,String startTime,String endTime,String eventType);
	
	public String login(String username, String password);
	
	public String userQuery(String searchType, String searchValue);
	
	public String showmme(String searchType, String searchValue);
	
	public String showpcrf(String searchValue);
	
	public String showpgw(String pgw,String imsi);
	
	public String showsgw(String sgw,String imsi);
	
	public String aaa();
}
