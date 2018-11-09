package com.thinkgem.jeesite.common.webservice.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.common.entity.StreamGobbler;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CdrDecoder;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.webservice.MobileUserCdrWebService;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import com.thinkgem.jeesite.modules.userquery.entity.TAllRatinggroupConfig;
import com.thinkgem.jeesite.modules.userquery.entity.TCdrTreeMould;
import com.thinkgem.jeesite.modules.userquery.entity.TImeitacTranslate;
import com.thinkgem.jeesite.modules.userquery.entity.UserCdrDetail;
import com.thinkgem.jeesite.modules.userquery.service.TAllRatinggroupConfigService;
import com.thinkgem.jeesite.modules.userquery.service.TImeitacTranslateService;

@WebService(endpointInterface = "com.thinkgem.jeesite.common.webservice.MobileUserCdrWebService")
public class MobileUserCdrWebServiceImpl implements MobileUserCdrWebService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private TAllRatinggroupConfigService allRatinggroupService;
	@Autowired
	private TImeitacTranslateService imeitacTranslateService;

	@Override
	public String getUserCdrList(String msisdn, String beginTime, String endTime) {
		
		String[] numbers = msisdn.split(",");
		List<UserCdrDetail> userCDRDetailList = new ArrayList<UserCdrDetail>();
		String UUID = IdGen.uuid();
		List<TAllRatinggroupConfig> allRatinggroup = allRatinggroupService.findList(null);
		Map<String,String> rgMap = new HashMap<String,String>();
		for(TAllRatinggroupConfig rg : allRatinggroup){
			rgMap.put(rg.getRatinggroup(), rg.getName());
		}
		int index = 0;
		for(String number : numbers){
			List<UserCdrDetail> oneUserCDRDetails = getDetailList(UUID,number,beginTime,endTime,rgMap,index);
			if(oneUserCDRDetails != null){
				userCDRDetailList.addAll(oneUserCDRDetails);
			}
		}
		Map<String,Object> returnMap = new LinkedHashMap<String,Object>();
		returnMap.put("status", "200");
		//List<UserCdrDetail> userCDRDetailList = analysis("E:/8613963918581.txt",beginTime,endTime,rgMap,index);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(UserCdrDetail cdrDetail : userCDRDetailList){
			Map<String,Object> cdrDetailMap = new LinkedHashMap<String,Object>();
			cdrDetailMap.put("imsi", cdrDetail.getServedimsi());
			cdrDetailMap.put("msisdn", cdrDetail.getServedmsisdn());
			cdrDetailMap.put("hdType", cdrDetail.getCdrtype());
			cdrDetailMap.put("ratType", cdrDetail.getRattype());
			cdrDetailMap.put("startTime", cdrDetail.getRecordopeningtime());
			cdrDetailMap.put("upData", cdrDetail.getTotal_datavolumefbcuplink());
			cdrDetailMap.put("downData", cdrDetail.getTotal_datavolumefbcdownlink());
			cdrDetailMap.put("cdrDetail", cdrDetail.getCdrDetail());
			list.add(cdrDetailMap);
		}
		returnMap.put("message", list);
				
		return JsonMapper.getInstance().toJson(returnMap);
		
	}
	
	private List<UserCdrDetail> getDetailList(String UUID,String number,String beginTime,String endTime, Map<String, String> rgMap,int index) {
		List<UserCdrDetail> userCDRDetailList = null;
		FileUtils.createDirectory("/opt/Ericsson/core/FILE/search/"+UUID+"/out");
		FileUtils.createDirectory("/opt/Ericsson/core/FILE/search/"+UUID+"/EndOutPut");
		//1、根据前台输入的日期间隔拿到这些日期,并且遍历这些日期
		String[] beginDateAndTime = beginTime.split(" ");
		String[] endDateAndTime = endTime.split(" ");
		String beginDate = beginDateAndTime[0];
		String endDate = endDateAndTime[0];
		List<String> datelist = DateUtils.getDateList(beginDate, endDate,"yyyyMMdd");	
		if(number.toLowerCase().contains("x") || "unknown".equalsIgnoreCase(number)){
			//解析UNKNOWN的话单数据
			executeCatFolder(UUID,number,datelist);
		}else{
			executeGetCdrCmd(UUID,number,datelist);
		}
		//获取文件名
		File binFilePath = new File("/opt/Ericsson/core/FILE/search/"+UUID+"/out");
		File[] files = binFilePath.listFiles();
		if(files!= null && files.length > 0){
			executeDecoderCmd(UUID, number);
		    //解析文件，拿到结果集
			userCDRDetailList = analysis("/opt/Ericsson/core/FILE/search/"+UUID+"/EndOutPut/cdrDetail.txt",beginTime,endTime,rgMap,index);
		}
		return userCDRDetailList;
	}
	
	/**
	 * 解析话单文件
	 * @param sourcePath
	 * @param rgMap 
	 * @param index 
	 * @return
	 */
	private List<UserCdrDetail> analysis(String sourcePath,String beginTime,String endTime, Map<String, String> rgMap, int index){
		String UTF_8 = "UTF-8";
		BufferedReader reader = null;
		FileInputStream fileInputStream;
		int recordCount = 0;
		String rootId = "0";
		Map<Integer,String> levelIds = new HashMap<Integer,String>();//缓存节点level及对应ID
		Map<String,String> servedImeisvMap = new HashMap<String,String>();//缓存servedImeisv及手机型号
		List<UserCdrDetail> cdrDetailList = null;
		try {
			fileInputStream = new FileInputStream(sourcePath);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, UTF_8);
			reader = new BufferedReader(inputStreamReader);
			String line = null;
			cdrDetailList = new ArrayList<UserCdrDetail>();
			UserCdrDetail cdrDetailItem = null;
            while ((line = reader.readLine()) != null) {
				recordCount++;
				if (line.trim().equals("")) {
					continue;
				}
		   outer:if (line.contains("pgwRecord")) {
					cdrDetailItem = new UserCdrDetail();
					cdrDetailItem.setCdrtype("PGW话单");
					long total_datavolumeFBCUplink = 0;
					long total_datavolumeFBCDownlink = 0;
					List<TCdrTreeMould> cdrTree = new ArrayList<TCdrTreeMould>();
					int level = CdrDecoder.getNodeLevel(line);
					TCdrTreeMould treeNode = new TCdrTreeMould();
					treeNode.setId(level + "" + recordCount);
					levelIds.put(level, treeNode.getId());
					if(level == 1){
						treeNode.setPid(rootId);
					}else{
						treeNode.setPid(levelIds.get(level-1));
					}
					String nodeKey = CdrDecoder.getNodeKey(line);
					treeNode.setDesc(DictUtils.getDictLabel(nodeKey, "biz_cdr_tree_node", nodeKey));
					treeNode.setName(nodeKey);
					treeNode.setValue("");
					cdrTree.add(treeNode);
					while ((line = reader.readLine()) != null) {
					    recordCount++;
						if (line.trim().equals("")) {
						continue;
						}
						level = CdrDecoder.getNodeLevel(line);
						treeNode = new TCdrTreeMould();
						treeNode.setId(level + "" + recordCount);
						levelIds.put(level, treeNode.getId());
						if(level == 1){
							treeNode.setPid(rootId);
						}else{
							treeNode.setPid(levelIds.get(level-1));
						}
						nodeKey = CdrDecoder.getNodeKey(line);
						String nodeValue = CdrDecoder.getNodeValue(line);
						String nodeFullValue = CdrDecoder.getNodeFullValue(line);
						treeNode.setName(nodeKey);
						treeNode.setDesc(DictUtils.getDictLabel(nodeKey, "biz_cdr_tree_node", nodeKey));
						treeNode.setValue(CdrDecoder.getTransformValue(DictUtils.getDictLabel(nodeKey, "biz_cdr_node_value_translate", nodeKey),nodeValue,nodeFullValue,true));
						if("servedIMEISV".equals(nodeKey)){
							String imeitacTranslate = null;
							String imeisvKey = nodeValue.substring(0, 8);
							if(servedImeisvMap.containsKey(imeisvKey)){
								imeitacTranslate = servedImeisvMap.get(imeisvKey);
							}else{
								TImeitacTranslate imeitacObj = imeitacTranslateService.get(imeisvKey);
								if(imeitacObj != null){
									imeitacTranslate = imeitacObj.getPhonename();
								}
								servedImeisvMap.put(imeisvKey, imeitacTranslate);
							}
							if(imeitacTranslate != null){
								treeNode.setValue(nodeValue + " (" +imeitacTranslate + ")");
							}else{
								treeNode.setValue(nodeFullValue);
							}
						}
						if("ratingGroup".equals(nodeKey) || "serviceIdentifier".equals(nodeKey)){
							if(rgMap.containsKey(nodeValue)){
								treeNode.setValue(nodeValue + " (" + rgMap.get(nodeValue) + ")");
							}else{
								treeNode.setValue(nodeFullValue);
							}
						}
						cdrTree.add(treeNode);
						if (line.contains("servedIMSI")) {
							cdrDetailItem.setServedimsi(treeNode.getValue());
						}
						if (line.contains("servedMSISDN")) {
							cdrDetailItem.setServedmsisdn(treeNode.getValue());
						}
						if (line.contains("rATType")) {
							cdrDetailItem.setRattype(treeNode.getValue());
						}
						if (line.contains("recordOpeningTime")) {
							cdrDetailItem.setRecordopeningtime(treeNode.getValue());
							if(treeNode.getValue().compareTo(beginTime) < 0 || endTime.compareTo(treeNode.getValue()) < 0){
								break outer;
							}
						}
						if (line.contains("datavolumeFBCUplink")) {
							total_datavolumeFBCUplink = total_datavolumeFBCUplink + Long.parseLong(nodeValue);
						}
						if (line.contains("datavolumeFBCDownlink")) {
							total_datavolumeFBCDownlink = total_datavolumeFBCDownlink + Long.parseLong(nodeValue);
						}
						if (line.contains("consolidationResult")||
								(line.contains("localSequenceNumber") && level==2 && line.trim().split(" ").length>1)) {
							break;
						}
					}
					cdrDetailItem.setIndex(index++);
					cdrDetailItem.setCdrDetail(cdrTree);
					cdrDetailItem.setTotal_datavolumefbcuplink(total_datavolumeFBCUplink);
					cdrDetailItem.setTotal_datavolumefbcdownlink(total_datavolumeFBCDownlink);
					cdrDetailList.add(cdrDetailItem);
				}
			}
			reader.close();
			inputStreamReader.close();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cdrDetailList;
	}
	
	/**
	 * 执行cat脚本查询unknown的话单数据，生成二进制文件
	 * @param UUID
	 * @param datelist
	 */
	private void executeCatFolder(String UUID,String number, List<String> datelist) {
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		try {
			cmd[2] = "cat";
			File poolIdPath = new File("/opt/Ericsson/core/FILE/usercdrdown/cdrfiledone/desc/");
			if(poolIdPath.exists()){
				File[] poolIds = poolIdPath.listFiles();
				if(poolIds != null){
					for(File poolId : poolIds){
						for(String dateFolder :datelist){
							cmd[2] += " /opt/Ericsson/core/FILE/usercdrdown/cdrfiledone/desc/"+poolId.getName() + File.separator + dateFolder + "/aaaa";
						}
					}
				}
			}
			cmd[2] += " >> /opt/Ericsson/core/FILE/search/"+UUID+"/out/"+number;
			Process proc = Runtime.getRuntime().exec(cmd);
			System.out.println(cmd[2]);
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(),"output",logger);
			outputGobbler.start();
			proc.waitFor();
			proc.destroy();
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}
	
	/**
	 * 执行get_cdr脚本查询话单数据，生成二进制文件
	 * @param UUID
	 * @param number
	 * @param dateList
	 */
	private void executeGetCdrCmd(String UUID, String number,List<String> dateList) {
		String[] cmd = new String[3];
		cmd[0] = "sh";
		cmd[1] = "-c";
		try {
			cmd[2] = "/opt/Ericsson/core/bin/get_cdr " + number + " " + "/opt/Ericsson/core/FILE/search/"+UUID+"/out/" + number;
			File poolIdPath = new File("/opt/Ericsson/core/FILE/usercdrdown/cdrfiledone/desc/");
			if(poolIdPath.exists()){
				File[] poolIds = poolIdPath.listFiles();
				if(poolIds != null){
					for(File poolId : poolIds){
						for(String dateFolder :dateList){
							cmd[2] += " /opt/Ericsson/core/FILE/usercdrdown/cdrfiledone/desc/"+poolId.getName() + File.separator + dateFolder + "/";
						}
					}
				}
			}
			Process proc = Runtime.getRuntime().exec(cmd);
			System.out.println(cmd[2]);
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(),"output",logger);
			outputGobbler.start();
			proc.waitFor();
			proc.destroy();
		} catch (Exception ex) {
			ex.getStackTrace();
		}
	}
	
	/**
	 * 用decoder脚本解析二进制话单文件
	 * @param request
	 * @param redirectAttributes
	 * @param UUID
	 * @param FileName
	 */
	private void executeDecoderCmd(String UUID,String number) {
		String[] command = new String[3];
		command[0] = "sh";
		command[1] = "-c";
		try {
			command[2] = "/opt/Ericsson/core/bin/" + "cdr_decoder -a /opt/Ericsson/core/bin/hljmcc -b " 
					+ "/opt/Ericsson/core/FILE/search/"+UUID+"/out/" + number 
					+ " >> /opt/Ericsson/core/FILE/search/"+UUID+"/EndOutPut/cdrDetail.txt";
			System.out.println("==================execute  cdr_decoder======== begin===============");
			System.out.println(command[2]);
			Process proc = Runtime.getRuntime().exec(command);
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(),"output",logger);
			outputGobbler.start();
		    proc.waitFor();
			proc.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("----!!!----"+e.getMessage());
		}
	}


}
