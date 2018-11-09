package com.thinkgem.jeesite.modules.paramconfig.web;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.constant.DictConstant;
import com.thinkgem.jeesite.common.entity.Client;
import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.socket.SocketObj;
import com.thinkgem.jeesite.common.socket.subentity.ParamModCmdSocket;
import com.thinkgem.jeesite.common.socket.subentity.TNewnetelementSocket;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.collectset.entity.TCollectordeploy;
import com.thinkgem.jeesite.modules.collectset.entity.TElementcollect;
import com.thinkgem.jeesite.modules.collectset.service.TElementcollectService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamCmdStatus;
import com.thinkgem.jeesite.modules.paramconfig.entity.ParamData;
import com.thinkgem.jeesite.modules.paramconfig.service.NetParamModifyService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;
import com.thinkgem.jeesite.modules.performance.entity.TEbmEvent;
import com.thinkgem.jeesite.modules.performance.service.TTacSuccessRateService;

/**
 * 单网元参数设置管理Controller
 * @author chenhongbo
 * @version 2017-06-16
 */
@Controller
@RequestMapping(value = "${adminPath}/paramconfig/netParamModify")
public class NetParamModifyController extends BaseController  {

	@Autowired
	private TNewnetelementService tNewnetelementService;
	
	@Autowired
	private NetParamModifyService netParamModifyService;
	
	@Autowired
	private TElementcollectService elementcollectService;
	
	@Autowired
	private TTacSuccessRateService tTacSuccessRateService;
	
	@Autowired
	private TPoolService tPoolService;
	
	@ModelAttribute
	public ParamData get(@RequestParam(required=false) String id) {
		return new ParamData();
	}
	
	@RequiresPermissions("paramconfig:netParamModify:view")
	@RequestMapping(value = {"list", ""})
	public String list(@Valid @ModelAttribute("paramData")ParamData paramData, HttpServletRequest request, HttpServletResponse response,Model model) {
		List<PoolNetVO> poolNetList = getNetList();
		
		List<TEbmEvent> ebmEventlist = tTacSuccessRateService.getEbmEventlist("MME");
		model.addAttribute("ebmEventlist", ebmEventlist);
		model.addAttribute("poolNetList", poolNetList);
		
		return "modules/paramconfig/netParamModify";
	}

	private List<PoolNetVO> getNetList() {
		List<TNewnetelement>  netListAll = tNewnetelementService.findList(new TNewnetelement());
		List<TPool> poolList = tPoolService.queryPoolListByType(null);
		List<PoolNetVO>  poolNetList = new ArrayList<PoolNetVO>();
		for(TPool pool : poolList) {
			PoolNetVO  poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for(TNewnetelement n : netListAll) {
				if(pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			poolNetVO.setNetList(netList);
			poolNetVO.setPoolName(pool.getFpoolname());
			poolNetList.add(poolNetVO);
		}
		return poolNetList;
	}
	
	/**
	 * 通过网元及日期控件选择的值动态查询参数类型控件的列表值
	 * @return
	 */
	@RequestMapping(value = "queryParamTypeByNetId")
	@ResponseBody
	public String queryParamTypeByNetId(String netid, String date) {
		netid = "26";
		date = "2018-04-26";
		TNewnetelement newNetelement = tNewnetelementService.get(netid);
		String netType="mme";
		if("2".equals(newNetelement.getType())){
			netType="epg";
		}else if("3".equals(newNetelement.getType())){
			netType="pcrf";
		}
		String version = tNewnetelementService.getVersionByNetIdAndDate(netid, date.replaceAll("-", ""));
		List<String> paramTypes = tNewnetelementService.getParamTypeByNet(netType, version);
		return JsonMapper.getInstance().toJson(paramTypes);
	}
	
	@RequestMapping(value = "queryList")
	@ResponseBody
	public String queryList(String netId, String date, String paramtype, String keyword,  int page, int rows) {
		netId = "26";
		date = "2018-04-26";
		int first = rows * (page - 1);
		ServerPagination<ParamData> serverPage = new ServerPagination<ParamData>();
		TNewnetelement newNetelement = tNewnetelementService.get(netId);
		String netType="mme";
		if("2".equals(newNetelement.getType())){
			netType="epg";
		}else if("3".equals(newNetelement.getType())){
			netType="pcrf";
		}
		List<ParamData> list = netParamModifyService.searchSingleNet(netType,netId, date.replaceAll("-", ""), paramtype, keyword, first, rows);
		int total = netParamModifyService.searchSingleNetCount(netType, netId, date.replaceAll("-", ""), paramtype, keyword);
		serverPage.setTotal(total);
		serverPage.setRows(list);
		return JsonMapper.getInstance().toJson(serverPage);
	}
	
	@RequiresPermissions("paramconfig:netParamModify:edit")
	@RequestMapping(value = "form")
	public String form(@Valid @ModelAttribute("paramData")ParamData paramData, Model model) {
		model.addAttribute("paramData", paramData);
		return "modules/paramconfig/netParamModifyFrom";
	}
	
	@RequestMapping(value = "updateParam")
	@ResponseBody
	public String updateParam(String netid,String modcmd,String cmdvalue,String executetype,String date) {
		Map<String, String> resultMap = new HashMap<String,String>();
		ParamCmdStatus cmdStatus = new ParamCmdStatus();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cmdStatus.setNetid(Integer.valueOf(netid));
		cmdStatus.setCmdcontent(modcmd + cmdvalue);
		TNewnetelement newNetelement = tNewnetelementService.get(netid);
		
		TNewnetelementSocket tNewnetelementSocket = new TNewnetelementSocket();
		tNewnetelementSocket.setFid(Long.parseLong(newNetelement.getId()));
		tNewnetelementSocket.setFname(newNetelement.getFname());
		tNewnetelementSocket.setIpadr(newNetelement.getIpadr());
		tNewnetelementSocket.setPassword1(newNetelement.getPassword1());
		tNewnetelementSocket.setType(newNetelement.getType());
		tNewnetelementSocket.setUsername1(newNetelement.getUsername1());
		if("now".equals(executetype)){
			
			cmdStatus.setExecutetype(1);
			cmdStatus.setExecutetime(df.format(new Date()));
			cmdStatus.setExecutestatus(0);
			Long statusId = netParamModifyService.saveParamStatus(cmdStatus);	
			// 发送socket请求，立即执行指令
			SocketObj socket = new SocketObj();
			socket.setFlag("executeNetParamModCmd");
			ParamModCmdSocket paramModCmdSocket = new ParamModCmdSocket();
			paramModCmdSocket.setCommand(cmdStatus.getCmdcontent());
			paramModCmdSocket.setDatetime(cmdStatus.getExecutetime());
			paramModCmdSocket.setNewnetelement(tNewnetelementSocket);
			paramModCmdSocket.setStatusId(statusId);
			socket.setParamModCmd(paramModCmdSocket);
			TElementcollect telementcollect = new TElementcollect();
			telementcollect.setType(DictConstant.COLLECT_TYPE_PARAM);
			telementcollect.setCollectId(netid);
			List<TElementcollect> elementcollects = elementcollectService.findCollectorIp(telementcollect);
			if(elementcollects != null && elementcollects.size() > 0){
				TCollectordeploy collector = new TCollectordeploy();
				collector.setIp(elementcollects.get(0).getIp());
				Client socketClient = new Client(socket, collector);
				String result = "";
				try {
					result = socketClient.send(7780);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else if("task".equals(executetype)){
			cmdStatus.setExecutetype(2);
			cmdStatus.setExecutetime(date);
			cmdStatus.setExecutestatus(0);
			Long statusId = netParamModifyService.saveParamStatus(cmdStatus);		
			//发送socket请求,创建执行指令任务，按计划时间执行
			SocketObj socket = new SocketObj();
			socket.setFlag("startNetParamModCmdJob");
			ParamModCmdSocket paramModCmdSocket = new ParamModCmdSocket();
			paramModCmdSocket.setCommand(cmdStatus.getCmdcontent());
			paramModCmdSocket.setDatetime(date);
			paramModCmdSocket.setNewnetelement(tNewnetelementSocket);
			paramModCmdSocket.setStatusId(statusId);
			socket.setParamModCmd(paramModCmdSocket);
			
			TElementcollect telementcollect = new TElementcollect();
			telementcollect.setType(DictConstant.COLLECT_TYPE_PARAM);
			telementcollect.setCollectId(netid);
			List<TElementcollect> elementcollects = elementcollectService.findCollectorIp(telementcollect);
			if(elementcollects != null && elementcollects.size() > 0){
				TCollectordeploy collector = new TCollectordeploy();
				collector.setIp(elementcollects.get(0).getIp());
				Client socketClient = new Client(socket, collector);
				String result = "";
				try {
					result = socketClient.send(7780);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		resultMap.put("msg", "success");
		return JsonMapper.getInstance().toJson(resultMap);
	}
	
	@RequestMapping(value = "redirectNetParam")
	public String redirectNetParam(String netId, Model model) {
		model.addAttribute("netId", netId);
		model.addAttribute("dateTime", DateUtils.getDate());
		model.addAttribute("isRedirect", "true");
		List<PoolNetVO> poolNetList = getNetList();
		Map<String,String> netMap = new HashMap<String,String>();
		for(PoolNetVO poolNet : poolNetList){
			for(TNewnetelement net : poolNet.getNetList()){
				netMap.put(net.getId(), net.getFname());
			}
		}
		model.addAttribute("netMap", netMap);
		List<TEbmEvent> ebmEventlist = tTacSuccessRateService.getEbmEventlist("MME");
		model.addAttribute("ebmEventlist", ebmEventlist);
		model.addAttribute("poolNetList", poolNetList);
		return "modules/paramconfig/netParamModify";
	}
	
}
