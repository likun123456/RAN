package com.thinkgem.jeesite.common.webservice.impl;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.jcraft.jsch.JSchException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.entity.InputStreamChannelExec;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.EbmDecoder;
import com.thinkgem.jeesite.common.utils.SSHUtil;
import com.thinkgem.jeesite.common.webservice.SingleEbmWebService;
import com.thinkgem.jeesite.modules.userquery.entity.TCdrTreeMould;
import com.thinkgem.jeesite.modules.userquery.entity.TEbmSingleSearch;
import com.thinkgem.jeesite.modules.userquery.service.TEbmSingleSearchService;


@WebService(endpointInterface = "com.thinkgem.jeesite.common.webservice.SingleEbmWebService")
public class SingleEbmWebServiceImpl implements SingleEbmWebService{
	
	@Autowired
	private TEbmSingleSearchService tEbmSingleSearchService;

	@Override
	public String showSingleEbmList(String netType, String searchType, String imsi_msisdn, String startTime,
			String endTime, String result, int limit, int offset) {
		if(result.equals("1")){
			startTime = startTime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
			endTime = endTime.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
			String[] ips = Global.getConfig("EbmSingleCollectIps").split(",");
			String username = Global.getConfig("EbmSingleCollectUsername");
			String password = Global.getConfig("EbmSingleCollectPassword");
			tEbmSingleSearchService.truncateEbmSingleSearchTable();
			for(int x=0;x<ips.length;x++){
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
	    	    			String line = null;
	    	    			String timeStemp = null;
	    	    			String date = "";
	    	    			while((line = br.readLine())!=null){
	    	    				if(line.startsWith("DATE=")){
	    	    					timeStemp = line.split("=")[1];
	    	    					date = timeStemp.substring(0, 4)+"-"+timeStemp.substring(4, 6)+"-"+timeStemp.substring(6, 8);
	    	    					continue;
	    	    				}
	    	    				if(line.contains("====EVENT====")){
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
	    	    						if(line.contains("time_hour =")){
	    	    							time_hour = line.substring(line.indexOf("=")+2);
	    	    							if(time_hour.trim().length()==1){
	    	    								time.setTime_hour("0"+time_hour);
	    	    							}else{
	    	    								time.setTime_hour(time_hour);
	    	    							}
	    	    							continue;
	    	    						}
	    	    						if(line.contains("time_minute =")){
	    	    							time_minute = line.substring(line.indexOf("=")+2);
	    	    							if(time_minute.trim().length()==1){
	    	    								time.setTime_minute("0"+time_minute);
	    	    							}else{
	    	    								time.setTime_minute(time_minute);
	    	    							}
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
	    	    							cc = line.substring(line.indexOf("=")+3,line.indexOf("("));
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
	    	    					time.setDate(date+" "+time.getTime_hour()+":"+time.getTime_minute()+":"+time.getTime_second()+"."+time.getTime_millisecond());
	    	    					list.add(time);
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
	    	    	if(list.size()!=0){
	    	    		tEbmSingleSearchService.insertEbmSingleSearchTable(list);
	    	    	}
	    	    	ssh.disconnect();
			}
    	}
		List<TEbmSingleSearch> tableList = tEbmSingleSearchService.queryEbmSingleSearchTable(offset,limit,null,null,null,null,null);
	    int total = tEbmSingleSearchService.queryListCount(null,null,null);
		Map<String,Object> mapData = new LinkedHashMap<String,Object>();
		mapData.put("status", 200);
		List<Map<String,String>> mapList = new ArrayList<Map<String,String>>();
		for(TEbmSingleSearch t : tableList) {
			Map<String,String> map = new LinkedHashMap<String,String>();
			map.put("time", t.getDate());
			map.put("netType",t.getNtype());
			map.put("eventType", t.getEventId());
			map.put("eventResult", t.getEventResult());
			map.put("imsi",t.getImsi());
			map.put("misidn", t.getMsisdn());
			mapList.add(map);
		}
		mapData.put("message", mapList);
		mapData.put("total",total);
		return JsonMapper.getInstance().toJson(mapData);
	}

	@Override
	public String detail(int id, String imsi, String cc, String scc, String eci, String date, String eventId,
			String eventResult) {
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
		Map<String,Object> mapData = new LinkedHashMap<String,Object>();
		mapData.put("DetailebmTree", ebmTree);
		mapData.put("imsi",imsi);
		mapData.put("cc", cc);
		mapData.put("scc", scc);
		mapData.put("eci", eci);
		mapData.put("date",date);
		mapData.put("eventId",eventId);
		mapData.put("eventResult",eventResult);
		try {
			//TODO 这块最后应该写成127.0.0.1
			//DynamicDataSource.setCurrentLookupKey("127.0.0.1-core_data_ebmlog");
			String ccdes = tEbmSingleSearchService.queryCcdes(cc);
			TEbmSingleSearch t = tEbmSingleSearchService.querySccdesAndAction(scc);
			mapData.put("ccdes",ccdes);
			mapData.put("sccdes",t.getSccdes());
			mapData.put("action",t.getAction());
			mapData.put("domain", t.getDomain());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
//			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(mapData);
	}
	
	
	

}
