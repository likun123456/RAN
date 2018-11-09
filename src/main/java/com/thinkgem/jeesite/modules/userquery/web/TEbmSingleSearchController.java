/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.userquery.web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcraft.jsch.JSchException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.entity.InputStreamChannelExec;
import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.EbmDecoder;
import com.thinkgem.jeesite.common.utils.SSHUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.userquery.entity.TCdrTreeMould;
import com.thinkgem.jeesite.modules.userquery.entity.TEbmSingleSearch;
import com.thinkgem.jeesite.modules.userquery.service.TEbmSingleSearchService;

/**
 * 单用户EBM查询Controller
 * @author 王晶石
 * @version 2017-06-17
 */
@Controller
@RequestMapping(value = "${adminPath}/userquery/tEbmSingleSearch")
public class TEbmSingleSearchController extends BaseController {

	@Autowired
	private TEbmSingleSearchService tEbmSingleSearchService;
	
	@ModelAttribute
	public TEbmSingleSearch get(@RequestParam(required=false) String id) {
		TEbmSingleSearch entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tEbmSingleSearchService.get(id);
		}
		if (entity == null){
			entity = new TEbmSingleSearch();
		}
		return entity;
	}
	
	@RequiresPermissions("userquery:tEbmSingleSearch:view")
	@RequestMapping(value = {"show", ""})
	public String show(String imsi,boolean isImsi,String msisdn,String startTime,String endTime,Model model) {
		model.addAttribute("imsi", imsi==null?"":imsi);
		model.addAttribute("isImsi", isImsi);
		model.addAttribute("msisdn", msisdn==null?"":msisdn);
		model.addAttribute("startTime", startTime==null?"":startTime);
		model.addAttribute("endTime", endTime==null?"":endTime);
		return "modules/userquery/tEbmSingleSearchList";
	}
	
	@ResponseBody
	@RequiresPermissions("userquery:tEbmSingleSearch:view")
	@RequestMapping(value = {"list"})
	public String list(String netType,String searchType,String imsi_msisdn,String startTime,String endTime,String result,
			int limit, int offset,String sortName,String sortOrder,String eventType,String nType,String eventResult) {
		ServerPagination<TEbmSingleSearch> page = new ServerPagination<TEbmSingleSearch>();
		List<String> eventTypeDicList = new ArrayList<String>();
		String sTime = startTime;
		String eTime = endTime;
		/*if(result.equals("1")){
			startTime = startTime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
			endTime = endTime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
			//File f = new File("C:\\Users\\Administrator\\Desktop\\jingshi\\ebm.log.bak");
			String[] ips = Global.getConfig("EbmSingleCollectIps").split(",");
			String username = Global.getConfig("EbmSingleCollectUsername");
			String password = Global.getConfig("EbmSingleCollectPassword");
			tEbmSingleSearchService.truncateEbmSingleSearchTable();
			for(int x=0;x<ips.length;x++){
				System.out.println(ips[x]);
				System.out.println(username);
				System.out.println(password);
				SSHUtil ssh = new SSHUtil(ips[x],username,password);
				try {
					ssh.connect();
					
				} catch (JSchException e) {
					e.printStackTrace();
				}
				List<TEbmSingleSearch> list = new ArrayList<TEbmSingleSearch>();
	        	String[] netTypes = netType.split(",");
	        	String cmd = "";
	    	    	for(int i=0;i<netTypes.length;i++){
	    	    		if(searchType.equals("IMSI")){
	    	    			cmd = "/opt/EBM/get_single_user_printout.pl -imsi "+imsi_msisdn+" -ebm_xml_folder /opt/EBM/" +netTypes[i]+
	    	    			 " -start_time "+startTime+" -end_time "+endTime+
	    	    	         " -folder_unit day -root_splited_ebm_folder /opt/EBM/"+netTypes[i]+"/SPLITED";
	    	    		}else{
	    	    			cmd = "/opt/EBM/get_single_user_printout.pl -msisdn "+imsi_msisdn+" -msisdn_folder /opt/EBM/IMSI_MSISDN/msisdn " +
	    	    			 "-ebm_xml_folder /opt/EBM/" +netTypes[i]+
	    	    			 " -start_time "+startTime+" -end_time "+endTime+
	    	    	         " -folder_unit day -root_splited_ebm_folder /opt/EBM/"+netTypes[i]+"/SPLITED";
	    	    		}
	    	    		System.out.println(ips+" : "+cmd);
	    	    		try {
	    	    			InputStreamChannelExec Isce = ssh.execCommandReturnStream(cmd);
	    	    			if(Isce.getIs()==null) {
	    	    				System.out.println("null   "+Isce.getIs());
	    	    				continue;
	    	    			}
	    	    			BufferedReader br = new BufferedReader(Isce.getIs());
	    	    			//BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
	    	    			String line = null;
	    	    			String timeStemp = null;
	    	    			String date = "";
	    	    			boolean b = false;
	    	    			boolean b1 = false;
	    	    			while((line = br.readLine())!=null){
	    	    				if(line.startsWith("DATE=")){
	    	    					timeStemp = line.split("=")[1];
	    	    					date = timeStemp.substring(0, 4)+"-"+timeStemp.substring(4, 6)+"-"+timeStemp.substring(6, 8);
	    	    					//System.out.println(date);
	    	    					continue;
	    	    				}
	    	    				if(line.contains("====EVENT====")){
	    	    					b = true;
	    	    					b1 =true;
	    	    					StringBuffer sb = new StringBuffer();
	    	    					TEbmSingleSearch time = new TEbmSingleSearch();
	    	    					String time_hour = "";
	    	    					String time_minute = "";
	    	    					String time_second = "";
	    	    					String event_id = "";
	    	    					String event_result = "";
	    	    					String imsi = "";
	    	    					String msisdn = "";
	    	    					String time_millisecond = "";
	    	    					String cc = "";
	    	    					String scc = "";
	    	    					String eci = "";
	    	    					time.setNtype(netTypes[i].replace("EPG", "SAEGW"));
	    	    					
	    	    					while((line = br.readLine()) != null){
	    	    						sb.append(line+"|");
	    	    						if(line.contains("time_hour =")&&b1){
	    	    							time_hour = line.substring(line.indexOf("=")+2);
	    	    							if(time_hour.trim().length()==1){
	    	    								time.setTime_hour("0"+time_hour);
	    	    							}else{
	    	    								time.setTime_hour(time_hour);
	    	    							}
	    	    							b1 = false;
	    	    							continue;
	    	    						}
	    	    						if(line.contains("time_minute =")&&b){
	    	    							time_minute = line.substring(line.indexOf("=")+2);
	    	    							if(time_minute.trim().length()==1){
	    	    								time.setTime_minute("0"+time_minute);
	    	    							}else{
	    	    								time.setTime_minute(time_minute);
	    	    							}
	    	    							b = false;
	    	    							continue;
	    	    						}
	    	    						if(line.contains("time_second =")){
	    	    							time_second = line.substring(line.indexOf("=")+2);
	    	    							if(time_second.trim().length()==1){
	    	    								time.setTime_second("0"+time_second);
	    	    							}else{
	    	    								time.setTime_second(time_second);
	    	    							}
	    	    							continue;
	    	    						}
	    	    						if(line.contains("time_millisecond =")){
	    	    							time_millisecond = line.substring(line.indexOf("=")+2);
	    	    							if(time_millisecond.trim().length()==1){
	    	    								time.setTime_millisecond("00"+time_millisecond);
	    	    							}else if(time_millisecond.trim().length()==2){
	    	    								time.setTime_millisecond("0"+time_millisecond);
	    	    							}else{
	    	    								time.setTime_millisecond(time_millisecond);
	    	    							}
	    	    							continue;
	    	    						}
	    	    						if(line.contains("event_id =")){
	    	    							event_id = line.substring(line.indexOf("=")+2);
	    	    							time.setEventId(event_id.toUpperCase());
	    	    							if(!eventTypeDicList.contains(event_id.toUpperCase())){
	    	    								eventTypeDicList.add(event_id.toUpperCase());
	    	    							}
	    	    							continue;
	    	    						}
	    	    						if(line.contains("event_result =")){
	    	    							event_result = line.substring(line.indexOf("=")+2);
	    	    							time.setEventResult(event_result.toUpperCase());
	    	    							continue;
	    	    						}
	    	    						if(line.contains("imsi =")){
	    	    							imsi = line.substring(line.indexOf("=")+2);
	    	    							time.setImsi(imsi);
	    	    							continue;
	    	    						}
	    	    						if(line.contains("msisdn =")){
	    	    							msisdn = line.substring(line.indexOf("=")+2);
	    	    							time.setMsisdn(msisdn);
	    	    							continue;
	    	    						}
	    	    						if(line.trim().indexOf("l_cause_prot_type")==0){
	    	    							line = br.readLine();
	    	    							if(line.contains("(")){
	    	    							    cc = line.substring(line.indexOf("=")+3,line.indexOf("("));
	    	    							}else{
	    	    								cc = line.substring(line.indexOf("=")+2);
	    	    							}
	    	    							time.setCc(cc);
	    	    							continue;
	    	    						}
	    	    						if(line.contains("sub_cause_code =")){
	    	    							if(line.contains("(")){
	    	    								scc = line.substring(line.indexOf("=")+3,line.indexOf("("));
	    	    							}else{
	    	    								scc = line.substring(line.indexOf("=")+2);
	    	    							}
	    	    							time.setScc(scc);
	    	    							continue;
	    	    						}
	    	    						if(line.contains("eci =")){
	    	    							eci = line.substring(line.indexOf("=")+2);
	    	    							continue;
	    	    						}
	    	    						if(line.trim().equals("")){
	    	    							time.setTime_stemp(timeStemp+time.getTime_hour()+time.getTime_minute()+time.getTime_second()+time.getTime_millisecond());
	    	    							break;
	    	    						}
	    	    					}
	    	    					time.setSb(sb.toString());
	    	    					String d = date+" "+time.getTime_hour()+":"+time.getTime_minute()+":"+time.getTime_second()+"."+time.getTime_millisecond();
	    	    					if((d.compareTo(sTime)>=0)&&(d.compareTo(eTime)<=0)) {
	    	    						System.out.println(d);
	    	    						time.setDate(d);
	    	    						list.add(time);
	    	    					}
	    	    					continue;
	    	    				}
	    	    			}
	    	    			br.close();
	    	    			if(Isce.getCe()!=null&&!Isce.getCe().isClosed()){
	    	    				Isce.getCe().disconnect();
	    	    			}
	    	    		} catch (IOException e) {
	    	    			e.printStackTrace();
	    	    		} 
	    	    	}
	    	    	System.out.println(ips[x]+"   "+list.size());
	    	    	if(list.size()!=0){
	    	    		tEbmSingleSearchService.insertEbmSingleSearchTable(list);
	    	    	}
	    	    	ssh.disconnect();
			}
    	}*/
		if(sortName!=null&&sortName.equals("eventId")){
			sortName = "event_id";
		}
		if(sortName!=null&&sortName.equals("eventResult")){
			sortName = "event_result";
		}
		
		List<TEbmSingleSearch> tableList = tEbmSingleSearchService.queryEbmSingleSearchTable(offset,limit,sortName,sortOrder,nType,eventType,eventResult);
	    int total = tEbmSingleSearchService.queryListCount(nType,eventType,eventResult);
	    page.setTotal(total);
		page.setRows(tableList);
		page.setEventTypeDicList(eventTypeDicList);
		return JsonMapper.getInstance().toJson(page);
	}
	
	
	@RequiresPermissions("userquery:tEbmSingleSearch:view")
	@RequestMapping(value = "detail")
	public String detail(int id,String imsi,String cc,String scc,String eci,String date,
			String eventId,String eventResult,Model model){
		String ebmlog = tEbmSingleSearchService.queryEbmLogById(id);
		String[] ebmDetail = ebmlog.split("\\|");
		List<TCdrTreeMould> ebmTree = new ArrayList<TCdrTreeMould>();
		Map<Integer,String> levelIds = new HashMap<Integer,String>();//缓存节点level及对应ID
		for(int i=0;i<ebmDetail.length;i++){
			int level = EbmDecoder.getNodeLevel(ebmDetail[i]);
			TCdrTreeMould treeNode = new TCdrTreeMould();
			treeNode.setId(level + "_" + i);
			levelIds.put(level, treeNode.getId());
			if(level == 0){
				treeNode.setPid("0");
			}else{
				treeNode.setPid(levelIds.get(level-1));
			}
			if(ebmDetail[i].trim().contains("=")){
				String s[] = ebmDetail[i].trim().split("=");
				if(s.length==1) {
					treeNode.setName(ebmDetail[i].trim().split("=")[0]);
				}else {
					treeNode.setName(ebmDetail[i].trim().split("=")[0]);
					treeNode.setValue(ebmDetail[i].trim().split("=")[1]);
				}
				
			}else{
				treeNode.setName(ebmDetail[i].trim());
			}
			ebmTree.add(treeNode);
					
		}
		model.addAttribute("ebmDetail", ebmTree);
		model.addAttribute("imsi",imsi);
		model.addAttribute("cc", cc);
		model.addAttribute("scc", scc);
		model.addAttribute("eci", eci);
		model.addAttribute("date",date);
		model.addAttribute("eventId",eventId);
		model.addAttribute("eventResult",eventResult);
		try {
			//TODO 这块最后应该写成127.0.0.1
			//DynamicDataSource.setCurrentLookupKey("127.0.0.1-core_data_ebmlog");
			String ccdes = tEbmSingleSearchService.queryCcdes(cc);
			TEbmSingleSearch t = tEbmSingleSearchService.querySccdesAndAction(scc);
			model.addAttribute("ccdes",ccdes);
			model.addAttribute("sccdes",t.getSccdes());
			model.addAttribute("action",t.getAction());
			model.addAttribute("domain", t.getDomain());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
//			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		
		return "modules/userquery/tEbmSingleSearchDetail";
	}
	
	@RequestMapping(value = { "exportExcel" })
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TEbmSingleSearch> tableList = tEbmSingleSearchService.queryEbmSingleSearchTable(0,0,null,null,null,null,null);
		   String headStr[] = {"时间","网元类型","事件类型","事件结果","IMSI号码","MSISDN号码"};
		   ExportExcel ee = new ExportExcel("单用户EBM列表", headStr);
		   for (int i = 0; i < tableList.size(); i++) {
				Row row = ee.addRow();
				TEbmSingleSearch t =tableList.get(i);
					ee.addCell(row, 0,t.getDate());
					ee.addCell(row, 1,t.getNtype());
					ee.addCell(row, 2,t.getEventId());
					ee.addCell(row, 3,t.getEventResult());
					ee.addCell(row, 4,t.getImsi());
					ee.addCell(row, 5,t.getMsisdn());
			}
		   try {
				ee.write(response, "单用户EBM列表.xlsx");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				ee.dispose();
			}
	}
	
	@RequestMapping(value = "redirectEbm")
	public String redirectEbm(String searchType, String searchValue, String netType,String pastScope, String beginDate, String endDate, Model model) {
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchValue", "8613842227036");
		model.addAttribute("netType", netType);
		model.addAttribute("pastScope", pastScope);
		model.addAttribute("beginDate", beginDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("isRedirect", "true");
		return "modules/userquery/tEbmSingleSearchList";
	}
	
}