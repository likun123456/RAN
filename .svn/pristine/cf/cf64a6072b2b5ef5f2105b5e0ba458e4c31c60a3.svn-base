package com.thinkgem.jeesite.modules.performance.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jcraft.jsch.SftpException;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.entity.FTPInfo;
import com.thinkgem.jeesite.common.utils.FtpCarrierUtils;
import com.thinkgem.jeesite.common.utils.SFtpCarrierUtil;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.EPCExcelUtil;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.EbmXmlVO;
import com.thinkgem.jeesite.modules.performance.entity.TEbmlogStatistics;
import com.thinkgem.jeesite.modules.performance.service.TEbmlogConfigService;
import com.thinkgem.jeesite.modules.performance.service.TEbmlogStatisticsService;
import com.thinkgem.jeesite.modules.userquery.entity.TLteWholeNetParam;
import com.thinkgem.jeesite.modules.userquery.service.TLteWholeNetParamService;

/**
 * ebmConfigController
 * @author yanghai
 * @version 2017-06-21
 */
@Controller
@RequestMapping(value = "${adminPath}/performance/ebmConfig")
@Scope("prototype")
public class TEbmlogConfigController extends BaseController {

	@Autowired
	private TEbmlogStatisticsService tEbmlogStatisticsService;
	@Autowired
	private TEbmlogConfigService tTEbmlogConfigService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TCollectordeployService tCollectordeployService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TLteWholeNetParamService tLteWholeNetParamService;
	
	@ModelAttribute
	public TEbmlogStatistics get(@RequestParam(required=false) String id) {
		TEbmlogStatistics entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tEbmlogStatisticsService.get(id);
		}
		if (entity == null){
			entity = new TEbmlogStatistics();
		}
		return entity;
	}
	
	@RequiresPermissions("performance:ebmconfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("tEbmlogStatistics")TEbmlogStatistics tEbmlogStatistics, HttpServletRequest request, HttpServletResponse response, Model model) {
		TNewnetelement tNewnetelement = new TNewnetelement();
		tNewnetelement.setType("1");//type设置为1代表MME类型网元
		List<TNewnetelement> netList = tNewnetelementService.findList(tNewnetelement);
		model.addAttribute("netList", netList);
		return "modules/performance/tEbmlogConfigList";
	}
	
	
	/**
	 * 根据选择的类型，查询对应的网元
	 * 
	 * @param extId
	 * @param type
	 * @param grade
	 * @param isAll
	 * @param parameterEpc
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll,
			HttpServletResponse response) {
		
		List<TPool> poolList = tPoolService.queryPoolListByType("MME");
		List<TPool> saegwpoolList = tPoolService.queryPoolListByType("SAEGW");
		List<TPool> pcrfpoolList = tPoolService.queryPoolListByType("PCRF");
		List<Map<String, Object>> mapList = Lists.newArrayList();
			TNewnetelement tnewnetelement = new TNewnetelement();
			tnewnetelement.setType("1");
			List<TNewnetelement> list = tNewnetelementService.findList(tnewnetelement);
			for(TPool pool : poolList){
				Map<String, Object> mapParent = Maps.newHashMap();
				mapParent.put("id", "p"+pool.getId());
				mapParent.put("pId", "0");
				mapParent.put("pIds", "0");
				mapParent.put("name", pool.getFpoolname());
				mapList.add(mapParent);
			 }
			for (int i = 0; i < list.size(); i++) {
				TNewnetelement e = list.get(i);
				Map<String, Object> map = Maps.newHashMap();
			/*	if (i == 0) {
					Map<String, Object> mapParent = Maps.newHashMap();
					mapParent.put("id", "0");
					mapParent.put("pId", "0");
					mapParent.put("pIds", "0");
					mapParent.put("name", "网元");
					mapList.add(mapParent);
				}*/
				map.put("id", e.getId());
				map.put("pId", "p" + e.getFnid());
				map.put("pIds", "0,"+ "p" + e.getFnid());
				map.put("name", e.getFname());
				mapList.add(map);
			}
			
			TNewnetelement Saegwtnewnetelement = new TNewnetelement();
			Saegwtnewnetelement.setType("2");
			List<TNewnetelement> saegwlist = tNewnetelementService.findList(Saegwtnewnetelement);
			for(TPool pool : saegwpoolList){
				Map<String, Object> mapParent = Maps.newHashMap();
				mapParent.put("id", "p"+pool.getId());
				mapParent.put("pId", "0");
				mapParent.put("pIds", "0");
				mapParent.put("name", pool.getFpoolname());
				mapList.add(mapParent);
			 }
			for (int i = 0; i < saegwlist.size(); i++) {
				TNewnetelement e = saegwlist.get(i);
				Map<String, Object> map = Maps.newHashMap();
			/*	if (i == 0) {
					Map<String, Object> mapParent = Maps.newHashMap();
					mapParent.put("id", "0");
					mapParent.put("pId", "0");
					mapParent.put("pIds", "0");
					mapParent.put("name", "网元");
					mapList.add(mapParent);
				}*/
				map.put("id", e.getId());
				map.put("pId", "p" + e.getFnid());
				map.put("pIds", "0,"+ "p" + e.getFnid());
				map.put("name", e.getFname());
				mapList.add(map);
			}
			
			TNewnetelement pcrftnewnetelement = new TNewnetelement();
			pcrftnewnetelement.setType("3");
			List<TNewnetelement> pcrflist = tNewnetelementService.findList(pcrftnewnetelement);
			for(TPool pool : pcrfpoolList){
				Map<String, Object> mapParent = Maps.newHashMap();
				mapParent.put("id", "p"+pool.getId());
				mapParent.put("pId", "0");
				mapParent.put("pIds", "0");
				mapParent.put("name", pool.getFpoolname());
				mapList.add(mapParent);
			 }
			for (int i = 0; i < pcrflist.size(); i++) {
				TNewnetelement e = pcrflist.get(i);
				Map<String, Object> map = Maps.newHashMap();
			/*	if (i == 0) {
					Map<String, Object> mapParent = Maps.newHashMap();
					mapParent.put("id", "0");
					mapParent.put("pId", "0");
					mapParent.put("pIds", "0");
					mapParent.put("name", "网元");
					mapList.add(mapParent);
				}*/
				map.put("id", e.getId());
				map.put("pId", "p" + e.getFnid());
				map.put("pIds", "0,"+ "p" + e.getFnid());
				map.put("name", e.getFname());
				mapList.add(map);
			}
		return mapList;
	}
	
	@RequestMapping(value ="uploadEbmXmlFile")
	public String uploadEbmXmlFile(@Valid @ModelAttribute("tEbmlogStatistics")TEbmlogStatistics tEbmlogStatistics,@RequestParam(value = "upload6", required = false) MultipartFile file) {
		List<FTPInfo> ftpInfos = tCollectordeployService.queryFTPInfoList();
		boolean b = false;
		String uploadname = ((CommonsMultipartFile)file).getFileItem().getName();
		String uploadnameArr[] = uploadname.split("\\\\");
		upload6=new File(uploadnameArr[uploadnameArr.length-1]);
		String[] netidArray = tEbmlogStatistics.getNetid().split(",");
		try {
			file.transferTo(upload6);
			String ffv = analysisEbmXml(upload6);
			for(String netid : netidArray){
				b = uploadEbmXml(netid,ftpInfos,ffv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(b){
			return "modules/performance/tEbmlogConfigList";
		}else{
			return "uploaderror";
		}
	}
	
	private String analysisEbmXml(File upload) throws DocumentException {
		SAXReader reader = new SAXReader();
    	Document document = reader.read(upload);
    	Element root=document.getRootElement();
    	Element e = root.element("general");
    	String ffv = e.element("ffv").getText();
		return ffv;
	}
	
	public boolean uploadEbmXml(String netId,List<FTPInfo> ftpInfos,String ffv) throws Exception{
		if(ftpInfos == null || ftpInfos.size() == 0){
			return false;
		}
		boolean flag = false;
		for(FTPInfo ftpInfo : ftpInfos){
			SFtpCarrierUtil ftpCarrier = new SFtpCarrierUtil(ftpInfo);
			ftpCarrier.connect();
			String remoteFileDir = Global.getConfig("EbmLoguploadPath")+netId;
			String remoteFilePath = remoteFileDir+"_xml/";
			File file = new File(remoteFilePath);
        	if(!file.exists()){
        		file.mkdirs();
        	}
        	if(getUpload6() != null){
        		flag = ftpCarrier.uploadEbmXmlFile(remoteFilePath,upload6.getName(),getUpload6().getAbsolutePath(),ffv);
        	}
			ftpCarrier.disconnect();
			if(!flag){
				return false;
			}
		}
		return flag;
	}
	
	
	
	@RequestMapping(value ="uploadFile")
	public String uploadFile(@Valid @ModelAttribute("tEbmlogStatistics")TEbmlogStatistics tEbmlogStatistics,@RequestParam(value = "upload", required = false) MultipartFile file,@RequestParam(value = "upload2", required = false) MultipartFile file2,@RequestParam(value = "upload3", required = false) MultipartFile file3,HttpServletRequest request){
		List<FTPInfo> ftpInfos = tCollectordeployService.queryFTPInfoList();
		boolean b = false;
		String uploadname = ((CommonsMultipartFile)file).getFileItem().getName();
		String uploadnameArr[] = uploadname.split("\\\\");
		String upload2name = ((CommonsMultipartFile)file2).getFileItem().getName();
		String upload2nameArr[] = upload2name.split("\\\\");
		String upload3name = ((CommonsMultipartFile)file3).getFileItem().getName();
		String upload3nameArr[] = upload3name.split("\\\\");
		upload=new File(uploadnameArr[uploadnameArr.length-1]);
		upload2=new File(upload2nameArr[upload2nameArr.length-1]);
		upload3=new File(upload3nameArr[upload3nameArr.length-1]);
		try {
			
			file.transferTo(upload);
			file2.transferTo(upload2);
			file3.transferTo(upload3);
			String[] netidArray = tEbmlogStatistics.getNetid().split(",");
			for(String netid : netidArray){
				b = upload(netid,ftpInfos);
			}
			List<EbmXmlVO> ebmxmllist = null;
			System.out.println("====="+upload2.getName());
			if(b && upload2.getName().equals("ebm_cause_codes.xml")){
				//2.解析xml文件
				ebmxmllist = analysisXmlFile(tEbmlogStatistics.getNetid());
				System.out.println("===ebmxmllist===: "+ebmxmllist.size());
			}
			if(b && file3!= null){
				analysisExcel(ebmxmllist);
			}
			for(String netid : netidArray){
				if(ebmxmllist !=null && ebmxmllist.size() > 0){
					String ip = tCollectordeployService.getEbmLogCollectorIp(netid);
					DynamicDataSource.setCurrentLookupKey(ip+"-core_data_ebmlog");
					tTEbmlogConfigService.insertEbmXml(ebmxmllist,netid);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		if(b){
			return "modules/performance/tEbmlogConfigList";
		}else{
			return "uploaderror";
		}
	}
	@RequestMapping(value ="uploadFileTxt")
	public String uploadFileTxt(@RequestParam(value = "upload4", required = false) MultipartFile file4, HttpServletRequest request){
		try {
			upload4=new File(((CommonsMultipartFile)file4).getFileItem().getName());
			file4.transferTo(upload4);
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		boolean b = false;
		if(upload4 != null){
			try {
	    		InputStreamReader read = new InputStreamReader(new FileInputStream(upload4),"UTF-8");
				BufferedReader bufferdReader = new BufferedReader(read);
				String lineTxt = null;
				Map<String,String> map = new LinkedHashMap<String,String>(); 
				while((lineTxt = bufferdReader.readLine())!=null){
					String[] lineTxtArr = lineTxt.split("\t");
					if(lineTxtArr.length>2){
						map.put(lineTxtArr[0], lineTxtArr[1]+" "+lineTxtArr[2]);
					}else if(lineTxtArr.length == 2){
						map.put(lineTxtArr[0], lineTxtArr[1]);
					}
				}
				read.close();
				b = tTEbmlogConfigService.insertImeitacTranslate(map);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    	}
		if(b){
			return "modules/performance/tEbmlogConfigList";
		}else{
			return "uploaderror";
		}
	}
	@RequestMapping(value ="uploadFileExcel")
	public String uploadFileExcel(@RequestParam(value = "upload5", required = false) MultipartFile file5, HttpServletRequest request,RedirectAttributes redirectAttributes) {
		try {
			upload5=new File(((CommonsMultipartFile)file5).getFileItem().getName());
			file5.transferTo(upload5);
			
			ImportExcel ei = new ImportExcel(upload5.getAbsolutePath(), 0);
			Map<String,TLteWholeNetParam> map = new HashMap<String,TLteWholeNetParam>();
			TLteWholeNetParam param = null;
			List<TLteWholeNetParam> oldlist = tLteWholeNetParamService.getLteWholeNetParamList();
			System.out.println("oldlist:"+oldlist.size());
			tLteWholeNetParamService.delete(new TLteWholeNetParam());
			for (int i = ei.getDataRowNum(); i <= ei.getLastDataRowNum(); i++) {
				Row row = ei.getRow(i);
				String factory = ei.getCellValue(row, 0).toString();
				/*String areaNo = ei.getCellValue(row, 1).toString();
				String areaName = ei.getCellValue(row, 2).toString();*/
				String stationNo = ei.getCellValue(row, 3).toString();
				String stationName = ei.getCellValue(row, 4).toString();
				String enodebId = ei.getCellValue(row, 5).toString();
				if(enodebId.indexOf(".0")>0){
					enodebId = enodebId.replace(".0", "");
				}
				String longitude = ei.getCellValue(row, 7).toString();
				String latitude = ei.getCellValue(row, 8).toString();
				/*String pci = ei.getCellValue(row, 6).toString();
				
				String frequencyPoint = ei.getCellValue(row, 9).toString();
				String tac = ei.getCellValue(row, 10).toString();
				String azimuth = ei.getCellValue(row, 11).toString();
				String downtilt = ei.getCellValue(row, 12).toString();
				String celllid = ei.getCellValue(row, 13).toString();
				String stationHeigth = ei.getCellValue(row, 14).toString();
				String businessIp = ei.getCellValue(row, 15).toString();
				String netadminIp = ei.getCellValue(row, 16).toString();
				String tai = ei.getCellValue(row, 17).toString();*/
				String eci = ei.getCellValue(row, 18).toString();
				if(eci.indexOf("E")>0){
					eci = eci.substring(0, eci.indexOf("E")).replace(".", "");
				}
				/*String neighborCount2g = ei.getCellValue(row, 19).toString();
				String neighborCount4g = ei.getCellValue(row, 20).toString();
				String rootOrderIndex = ei.getCellValue(row, 21).toString();*/
				param = new TLteWholeNetParam();
				param.setFactory(factory);
			/*	param.setAreaNo(areaNo);
				param.setAreaName(areaName);*/
				param.setStationNo(stationNo);
				param.setStationName(stationName);
				param.setEnodebId(enodebId);
				param.setLongitude(longitude);
				param.setLatitude(latitude);
			/*	param.setPci(pci);
				
				param.setFrequencyPoint(frequencyPoint);
				param.setTac(tac);
				param.setAzimuth(azimuth);
				param.setDowntilt(downtilt);
				param.setCelllid(celllid);
				param.setStationHeigth(stationHeigth);
				param.setBusinessIp(businessIp);
				param.setNetadminIp(netadminIp);
				param.setTai(tai);*/
				param.setEci(eci);
				/*param.setNeighborCount2g(neighborCount2g);
				param.setNeighborCount4g(neighborCount4g);
				param.setRootOrderIndex(rootOrderIndex);*/
				if(!map.containsKey(eci)){
					map.put(eci, param);
				}
				
			}
			for(int i=0;i<oldlist.size();i++){
				String oldeci = oldlist.get(i).getEci();
				if(!map.containsKey(oldeci)){
					map.put(oldeci, oldlist.get(i));
				}
			}
			tLteWholeNetParamService.batchIntert(map);
			File f = new File(upload5.getAbsolutePath());
			f.delete();
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "导入成功");
		return "redirect:"+Global.getAdminPath()+"/performance/ebmConfig/?repage";
	}
	
	public List<EbmXmlVO> analysisXmlFile(String netId) throws Exception{
    	EbmXmlVO ebmxmlvo; 
    	List<EbmXmlVO> ebmxmllist = new ArrayList<EbmXmlVO>();
    	//以dom4j形式解析xml文件
    	SAXReader reader = new SAXReader();
    	Document document = reader.read(getUpload2());
    	Element root=document.getRootElement();
    	Element causecodes = root.element("causecodes");
    	@SuppressWarnings("unchecked")
		List<Element> protocols = causecodes.elements("protocol");
    	for (Iterator<Element> it = protocols.iterator(); it.hasNext();) {    
    	      Element elm = (Element) it.next();   
    	      Attribute attribute=elm.attribute("name");    
    	      String text = attribute.getText();
    	      if(text.equals("NAS") || text.equals("DIAMETER")){
    	    	  @SuppressWarnings("unchecked")
				List<Element> cc = elm.elements("cc");
    	    	  for (Iterator<Element> itt = cc.iterator();itt.hasNext();) {
    	    		  Element e = (Element) itt.next();
    	    		  Attribute cc_attribute=e.attribute("value"); 
    	    	      String cc_val = cc_attribute.getText();
    	    	      String cc_des = e.elementText("description");
    	    	      ebmxmlvo = new EbmXmlVO();
    	    	      ebmxmlvo.setNetid(netId);
    	    	      ebmxmlvo.setCc(cc_val);
    	    	      ebmxmlvo.setCcdes(cc_des);
    	    	      ebmxmllist.add(ebmxmlvo);
    	    	  }
    	      }
    	}  
    	Element subcausecodes = root.element("subcausecodes");
    	@SuppressWarnings("unchecked")
		List<Element> scc = subcausecodes.elements("scc");
    	for (Iterator<Element> ittt = scc.iterator();ittt.hasNext();) {
  		  Element e = (Element) ittt.next();
  		  Attribute scc_attribute=e.attribute("value"); 
  	      String scc_val = scc_attribute.getText();
  	      String scc_des = e.elementText("description");
  	      String scc_action = e.elementText("action");
  	      ebmxmlvo = new EbmXmlVO();
  	      ebmxmlvo.setNetid(netId);
  	      ebmxmlvo.setScc(scc_val);
  	      ebmxmlvo.setSccdes(scc_des);
  	      ebmxmlvo.setAction(scc_action);
  	      ebmxmllist.add(ebmxmlvo);
  	   }
    	return ebmxmllist;
    }
	
	private void analysisExcel(List<EbmXmlVO> ebmxmllist){
    	try {
    		List<List<String>> result = null;
    		if(upload3.getName().endsWith(".xlsx")){
    			result = new EPCExcelUtil().readXlsx(getUpload3().getAbsolutePath());
    		}else if(upload3.getName().endsWith(".xls")){
    			result = new EPCExcelUtil().readXls(getUpload3().getAbsolutePath());
    		}
            if(result != null){
	            for(EbmXmlVO ebmXML : ebmxmllist){
	            	for (int i = 0; i < result.size(); i++) {
	                    List<String> model = result.get(i);
	                    if(ebmXML.getScc() != null && model.size() >= 5 && model.get(0).split("\"").length >=2 && model.get(0).split("\"")[1].equals(ebmXML.getScc())){
	                    	ebmXML.setDomain(model.get(4));
	                    }
	                }
	            }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public boolean upload(String netId,List<FTPInfo> ftpInfos) throws Exception{
		if(ftpInfos == null || ftpInfos.size() == 0){
			return false;
		}
		boolean flag = false;
		String ip = tCollectordeployService.getEbmLogCollectorIp(netId);
		for(FTPInfo ftpInfo : ftpInfos){
			if(ftpInfo.getHost().equals(ip)) {
				SFtpCarrierUtil ftpCarrier = new SFtpCarrierUtil(ftpInfo);
				ftpCarrier.connect();
				String remoteFileDir = Global.getConfig("EbmLoguploadPath")+netId;
				String remoteFilePath = remoteFileDir+"_xml/";
				File file = new File(remoteFilePath);
	        	if(!file.exists()){
	        		file.mkdirs();
	        	}
	        	if(getUpload() != null){
	        		
	        		flag = ftpCarrier.uploadFile(remoteFilePath,upload.getName(),getUpload().getAbsolutePath());
	        	}
	        	if(getUpload2() != null){
	        		flag = ftpCarrier.uploadFile(remoteFilePath, upload2.getName(),getUpload2().getAbsolutePath());
	        	}
	        	if(getUpload3() != null){
	        		flag = ftpCarrier.uploadFile(remoteFilePath, upload3.getName(),getUpload3().getAbsolutePath());
	        	}
				ftpCarrier.disconnect();
				if(!flag){
					return false;
				}
			}
		}
		return flag;
	}
	

	public boolean upload1(String netId,List<FTPInfo> ftpInfos) throws Exception{
		if(ftpInfos == null || ftpInfos.size() == 0){
			return false;
		}
		boolean flag = false;
		for(FTPInfo ftpInfo : ftpInfos){
			FtpCarrierUtils ftpCarrier = new FtpCarrierUtils(ftpInfo);
			ftpCarrier.connect();
			String remoteFileDir = Global.getConfig("EbmLoguploadPath")+netId;
			String remoteFilePath = remoteFileDir+"_xml/";
			File file = new File(remoteFilePath);
        	if(!file.exists()){
        		file.mkdirs();
        	}
        	if(getUpload() != null){
        		flag = ftpCarrier.uploadXmlFile(getUpload().getAbsolutePath(), remoteFilePath, upload.getName(),remoteFileDir);
        	}
        	if(getUpload2() != null){
        		flag = ftpCarrier.uploadXmlFile(getUpload2().getAbsolutePath(), remoteFilePath, upload2.getName(),remoteFileDir);
        	}
        	if(getUpload3() != null){
        		flag = ftpCarrier.uploadXmlFile(getUpload3().getAbsolutePath(), remoteFilePath, upload3.getName(),remoteFileDir);
        	}
			ftpCarrier.close();
			if(!flag){
				return false;
			}
		}
		return flag;
	}
	
	private File upload;//上传文件xml
	private File upload2;//上传文件2xml
	private File upload3;//包含domain的excel文件
	private File upload4;//上传IMEI TAC database(号码翻译手机名的excel)文件
	private File upload5;
	private File upload6;
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public File getUpload2() {
		return upload2;
	}

	public void setUpload2(File upload2) {
		this.upload2 = upload2;
	}

	public File getUpload3() {
		return upload3;
	}

	public void setUpload3(File upload3) {
		this.upload3 = upload3;
	}

	public File getUpload4() {
		return upload4;
	}

	public void setUpload4(File upload4) {
		this.upload4 = upload4;
	}

	public File getUpload5() {
		return upload5;
	}

	public void setUpload5(File upload5) {
		this.upload5 = upload5;
	}

	public File getUpload6() {
		return upload6;
	}

	public void setUpload6(File upload6) {
		this.upload6 = upload6;
	}
	
	
}