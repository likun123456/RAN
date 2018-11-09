package com.thinkgem.jeesite.modules.performance.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JavaType;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.modules.netconfig.entity.TCg;
import com.thinkgem.jeesite.modules.netconfig.entity.TComputerroom;
import com.thinkgem.jeesite.modules.netconfig.entity.TEdges;
import com.thinkgem.jeesite.modules.netconfig.entity.TEquipment;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.entity.TVisExcel;
import com.thinkgem.jeesite.modules.netconfig.entity.VisNodes;
import com.thinkgem.jeesite.modules.netconfig.service.TCgService;
import com.thinkgem.jeesite.modules.netconfig.service.TComputerroomService;
import com.thinkgem.jeesite.modules.netconfig.service.TEdgesService;
import com.thinkgem.jeesite.modules.netconfig.service.TEquipmentService;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.netconfig.service.TVisExcelService;
import com.thinkgem.jeesite.modules.netconfig.service.VisNodesService;
import com.thinkgem.jeesite.modules.performance.entity.NodeVo;

/**
 * 网络拓扑图管理
 * @author 陈宏波
 * @version 2018-03-05
 */
@Controller
@RequestMapping(value = "${adminPath}/performance/topologyManage")
@Scope("prototype")
public class TTopologyManageController {
	
	@Autowired
	private VisNodesService nodeService;
	@Autowired
	private TNewnetelementService newnetelementService;
	@Autowired
	private TEquipmentService equipmentService;
	@Autowired
	private TCgService cgService;
	@Autowired
	private TEdgesService edgesService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TComputerroomService computerroomService;
	@Autowired
	private TVisExcelService tVisExcelService;

	@RequiresPermissions("performance:topologyManage:view")
	@RequestMapping(value = "topologyManageIndex")
	public String topologyManageIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TPool> mmePoolList = tPoolService.queryPoolListByType("MME");
		String poolName = "";
		TNewnetelement tNewnetelement;
		List<TNewnetelement> list;
		for(int i=0;i<mmePoolList.size();i++) {
			poolName = mmePoolList.get(i).getFpoolname();
			switch(poolName) {
			case "MME-POOL1":
				 tNewnetelement = new TNewnetelement();
				 tNewnetelement.setTemp_field1("MME");
				 tNewnetelement.setFnid(Long.parseLong(mmePoolList.get(i).getId()));
				 list = tNewnetelementService.findList(tNewnetelement);
				 model.addAttribute("MMEPOOL1", list);
				 break;
			case "MME-POOL2":
				 tNewnetelement = new TNewnetelement();
				 tNewnetelement.setTemp_field1("MME");
				 tNewnetelement.setFnid(Long.parseLong(mmePoolList.get(i).getId()));
				 list = tNewnetelementService.findList(tNewnetelement);
				 model.addAttribute("MMEPOOL2", list);
				 break;
			case "MME-POOL3":
				 tNewnetelement = new TNewnetelement();
				 tNewnetelement.setTemp_field1("MME");
				 tNewnetelement.setFnid(Long.parseLong(mmePoolList.get(i).getId()));
				 list = tNewnetelementService.findList(tNewnetelement);
				 model.addAttribute("MMEPOOL3", list);
				 break;
			}
		}
		List<TPool> saegwPoolList = tPoolService.queryPoolListByType("SAEGW");
		for(int i=0;i<saegwPoolList.size();i++) {
			poolName = saegwPoolList.get(i).getFpoolname();
			switch(poolName) {
			case "SAEGW-POOL1":
				 tNewnetelement = new TNewnetelement();
				 tNewnetelement.setTemp_field1("SAEGW");
				 tNewnetelement.setFnid(Long.parseLong(saegwPoolList.get(i).getId()));
				 list = tNewnetelementService.findList(tNewnetelement);
				 model.addAttribute("SAEGWPOOL1", list);
				 break;
			case "SAEGW-POOL2":
				 tNewnetelement = new TNewnetelement();
				 tNewnetelement.setTemp_field1("SAEGW");
				 tNewnetelement.setFnid(Long.parseLong(saegwPoolList.get(i).getId()));
				 list = tNewnetelementService.findList(tNewnetelement);
				 model.addAttribute("SAEGWPOOL2", list);
				 break;
			case "SAEGW-POOL3":
				 tNewnetelement = new TNewnetelement();
				 tNewnetelement.setTemp_field1("SAEGW");
				 tNewnetelement.setFnid(Long.parseLong(saegwPoolList.get(i).getId()));
				 list = tNewnetelementService.findList(tNewnetelement);
				 model.addAttribute("SAEGWPOOL3", list);
				 break;
			}
		}
		
		return "modules/performance/topologyManageIndex";
	}
	
	@RequiresPermissions("performance:topologyManage:view")
	@RequestMapping(value = "showComputerRoomTopology")
	public String showComputerRoomTopology(HttpServletRequest request, HttpServletResponse response, Model model,String roomId) {
		List<TComputerroom> roomList = computerroomService.findList(new TComputerroom());
		if(null == roomId || "".equals(roomId)) {
			model.addAttribute("roomId", roomList.size() > 0 ? roomList.get(0).getId() : -1);
		} else {
			model.addAttribute("roomId", roomId);
		}
		model.addAttribute("roomList", roomList);
		
		/*List<TVisExcel> mmeList = new ArrayList<TVisExcel>();
		List<TVisExcel> saegwList = new ArrayList<TVisExcel>();
		
		TVisExcel t = new TVisExcel();
		List<TVisExcel> excelList = tVisExcelService.findList(t);
		for(TVisExcel excel : excelList) {
			if("MME".equals(excel.getType())) {
				mmeList.add(excel);
			} else if("SAEGW".equals(excel.getType())) {
				saegwList.add(excel);
			}
		}
		
		model.addAttribute("mmeList", mmeList);
		model.addAttribute("saegwList", saegwList);*/
		return "modules/performance/computerRoomTopology";
	}
	
	@RequiresPermissions("performance:topologyManage:view")
	@RequestMapping(value = "aotuLayout")
	public String aotuLayout() {
		return "modules/performance/aotuLayout";
	}
	
	/**
	 * 读取节点数据
	 * @param netType 根据点击的节点判断显示哪些内容
	 * @return
	 */
	
	@RequestMapping(value = "loadData")
	@ResponseBody
	public String loadData(String roomId,String netType,String nodeId) {
		List<Map<String,Object>> nodeList = getNodeList(roomId,netType,nodeId);
		List<Map<String,Object>> edgeList = getEdgeList(roomId,netType,nodeId);
		List<Object> list = new ArrayList<Object>();
		list.add(nodeList);
		list.add(edgeList);
		return JsonMapper.getInstance().toJson(list);
	}
	
	/*
	 * 获得连线信息
	 */
	private List<Map<String,Object>> getEdgeList(String roomId,String netType, String nodeId) {
		TEdges edgesParam = new TEdges();
		edgesParam.setRoomId(Integer.parseInt(roomId));
		List<TEdges> visEdgeList = edgesService.findList(edgesParam);
		List<Map<String,Object>> edgeList = new ArrayList<Map<String,Object>>();
		for(TEdges edge : visEdgeList) {
			Map<String,Object> edgeMap = new LinkedHashMap<String,Object>();
			edgeMap.put("id", edge.getFromEqu() + "_" + edge.getToEqu() + "-" + edge.getId());
			edgeMap.put("from", edge.getFromEqu());
			edgeMap.put("to", edge.getToEqu());
			edgeList.add(edgeMap);
		}
		return edgeList;
	}
	
	/*
	 * 获取节点信息
	 */
	private List<Map<String,Object>> getNodeList(String roomId,String netType,String nodeId) {
		VisNodes nodeParam = new VisNodes();
		nodeParam.setRoomid(Integer.parseInt(roomId));
		List<VisNodes> visNodeList = nodeService.findList(nodeParam);
		visNodeList = getExistNode(visNodeList,netType,nodeId);
		List<Map<String,Object>> nodeList = new ArrayList<Map<String,Object>>();
		
		Map<Integer,String> imgType = new HashMap<Integer,String>();
		imgType.put(1, "mme.png");
		imgType.put(2, "saegw.png");
		imgType.put(3, "pcrf.png");
		imgType.put(4, "switch.png");
		imgType.put(5, "router.png");
		imgType.put(6, "fhq.png");
		imgType.put(8, "cg.png");
		//查询网元表
		List<TNewnetelement>  netList = newnetelementService.findList(null);
		//查询设备表
		List<TEquipment> equipmentList = equipmentService.findList(null);
		//查询cg表
		List<TCg> cgList = cgService.findList(null);
		for(VisNodes node : visNodeList) {
			Map<String,Object> nodeMap = createNodeMap(node,netList,equipmentList,cgList,imgType);
			nodeList.add(nodeMap);
		}
		
		return nodeList;
	}
	
	private List<VisNodes> getExistNode(List<VisNodes> visNodeList, String netType,String nodeId) {
		List<VisNodes> newsNodeList = new ArrayList<VisNodes>();
		if("all".equals(netType)) {
			return visNodeList;
		} else {
			for(VisNodes node : visNodeList) {
				if(nodeId.equals(node.getId())) {
					newsNodeList.add(node);
				} else {
					if("MME".equals(netType) && node.getType().intValue() != 1 && node.getType().intValue() != 2 && node.getType().intValue() != 3 && node.getType().intValue() != 8) {
						newsNodeList.add(node);
					}
					if("SAEGW".equals(netType) && node.getType().intValue() != 1 && node.getType().intValue() != 2 && node.getType().intValue() != 3) {
						newsNodeList.add(node);
					}
					if("PCRF".equals(netType) && node.getType().intValue() != 1 && node.getType().intValue() != 2 && node.getType().intValue() != 3 && node.getType().intValue() != 8) {
						if(node.getType().intValue() == 7 ){
							TEquipment equipment = equipmentService.get(node.getOid().toString());
							if(!"PTN传输网".equals(equipment.getName())) {
								newsNodeList.add(node);
							}
						} else {
							newsNodeList.add(node);
						}
						
					}
				}
			}
			return newsNodeList;
		}
	}
	
	private Map<String,Object> createNodeMap(VisNodes node,List<TNewnetelement>  netList,List<TEquipment> equipmentList,List<TCg> cgList,Map<Integer,String> imgType) {
		Map<String,Object> nodeMap = new LinkedHashMap<String,Object>();
		int type = node.getType();
		int oid = node.getOid();
		nodeMap.put("id", Integer.parseInt(node.getId()));
		if(1 == type || 2 == type || 3 == type) {
			//网元
			for(TNewnetelement n : netList) {
				if(Integer.parseInt(n.getId()) == oid) {
					nodeMap.put("label", n.getFname());
					break;
				}
			}
		} else if(4 == type || 5 == type || 6 == type) {
			//交换机、路由器、防火墙
			for(TEquipment e : equipmentList) {
				if(Integer.parseInt(e.getId()) == oid) {
					nodeMap.put("label", e.getName());
					break;
				}
			}
		} else if(8 == type) {
			//cg
			for(TCg cg : cgList) {
				if(Integer.parseInt(cg.getId()) == oid) {
					nodeMap.put("label", cg.getFname());
					break;
				}
			}
		}
		nodeMap.put("shape", "image");
		if(node.getType().intValue() == 7) {
			for(TEquipment e : equipmentList) {
				if(Integer.parseInt(e.getId()) == oid) {
					//nodeMap.put("label", e.getName());
					if("CMNET".equals(e.getName())) {
						nodeMap.put("image", "cmnet.png");
					} else if("IP承载网".equals(e.getName())) {
						nodeMap.put("image", "ipczw.png");
					} else if("GPRS专网".equals(e.getName())) {
						nodeMap.put("image", "gprszw.png");
					} else if("PTN传输网".equals(e.getName())) {
						nodeMap.put("image", "ptncsw.png");
					}
					break;
				}
			}
			nodeMap.put("size", 50);
		} else {
			nodeMap.put("image", imgType.get(node.getType()));
			nodeMap.put("size", 20);
		}
		
		if(node.getX() == null) {
			nodeMap.put("x", 0);
		} else {
			nodeMap.put("x", node.getX());
		}
		
		if(node.getY() == null) {
			nodeMap.put("y", 0);
		} else {
			nodeMap.put("y", node.getY());
		}
		return nodeMap;
	}
	
	@RequestMapping(value = "savePosition")
	@ResponseBody
	public String savePosition(String nodeArray) {
		//jackson 将字符串类型的json 转换成List类型
		try {
			//
			JavaType javaType = JsonMapper.getInstance().getTypeFactory().constructParametricType(ArrayList.class, NodeVo.class);
			List<NodeVo> nodeList =  JsonMapper.getInstance().readValue(nodeArray, javaType);
			for(NodeVo node : nodeList) {
				VisNodes visNode = new VisNodes();
				visNode.setId(""+node.getId());
				visNode.setX(node.getX());
				visNode.setY(node.getY());
				nodeService.savePosition(visNode);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
}
