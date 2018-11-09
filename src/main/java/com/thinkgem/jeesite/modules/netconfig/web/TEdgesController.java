package com.thinkgem.jeesite.modules.netconfig.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TCg;
import com.thinkgem.jeesite.modules.netconfig.entity.TComputerroom;
import com.thinkgem.jeesite.modules.netconfig.entity.TEdges;
import com.thinkgem.jeesite.modules.netconfig.entity.TEquipment;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.VisNodes;
import com.thinkgem.jeesite.modules.netconfig.service.TCgService;
import com.thinkgem.jeesite.modules.netconfig.service.TComputerroomService;
import com.thinkgem.jeesite.modules.netconfig.service.TEdgesService;
import com.thinkgem.jeesite.modules.netconfig.service.TEquipmentService;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.VisNodesService;
import com.thinkgem.jeesite.modules.sys.entity.Dict;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;

/**
 * 节点连线设置Controller
 * 
 * @author yh
 * @version 2018-03-09
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/tEdges")
public class TEdgesController extends BaseController {

	@Autowired
	private TEdgesService tEdgesService;
	@Autowired
	private TComputerroomService tComputerroomService;
	@Autowired
	private VisNodesService visNodesService;
	@Autowired
	private TCgService tCgService;
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TEquipmentService tEquipmentService;

	@ModelAttribute
	public TEdges get(@RequestParam(required = false) String id) {
		TEdges entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = tEdgesService.get(id);
		}
		if (entity == null) {
			entity = new TEdges();
		}
		return entity;
	}
	
	@RequiresPermissions("netconfig:tEdges:view")
	@RequestMapping(value = {"list", ""})
	public String list(@ModelAttribute("tEdges") TEdges tVisEdges, HttpServletRequest request, HttpServletResponse response, Model model) {
		List<TComputerroom> roomList = tComputerroomService.findList(null);
		model.addAttribute("roomList", roomList);
		Page<TEdges> page = tEdgesService.findPage(new Page<TEdges>(request, response), tVisEdges); 
		
		List<TNewnetelement> netElements_db = tNewnetelementService.findList(null);
		List<TEquipment> equipment_db = tEquipmentService.findList(null);
		List<TCg> cgElements_db = tCgService.findList(null);
		
		List<TEdges> result = page.getList();
		for(TEdges edge : result){
			setEdgeName(netElements_db, equipment_db, cgElements_db, edge);
		}
		model.addAttribute("page", page);
		return "modules/netconfig/tVisEdgesList";
	}

	private void setEdgeName(List<TNewnetelement> netElements_db, List<TEquipment> equipment_db,
			List<TCg> cgElements_db, TEdges edge) {
		switch(edge.getFromNodeType()){
			case "1":
			case "2":
			case "3":
				for(TNewnetelement netElement : netElements_db){
					if(edge.getFromNodeType().equals(netElement.getType()) && 
							netElement.getId().equals(edge.getFromOid())){
						edge.setFromEquName(netElement.getFname());
						break;
					}
				}
				break;
			case "4":
			case "5":
			case "6":
			case "7":
				for(TEquipment equipment : equipment_db){
					if(edge.getFromNodeType().equals(equipment.getType()) && 
							equipment.getId().equals(edge.getFromOid())){
						edge.setFromEquName(equipment.getName());
						break;
					}
				}
				break;
			case "8":
				for(TCg cgElement : cgElements_db){
					if(cgElement.getId().equals(edge.getFromOid())){
						edge.setFromEquName(cgElement.getFname());
						break;
					}
				}
				break;
		}
		switch(edge.getToNodeType()){
			case "1":
			case "2":
			case "3":
				for(TNewnetelement netElement : netElements_db){
					if(edge.getToNodeType().equals(netElement.getType()) && 
							netElement.getId().equals(edge.getToOid())){
						edge.setToEquName(netElement.getFname());
						break;
					}
				}
				break;
			case "4":
			case "5":
			case "6":
			case "7":
				for(TEquipment equipment : equipment_db){
					if(edge.getToNodeType().equals(equipment.getType()) && 
							equipment.getId().equals(edge.getToOid())){
						edge.setToEquName(equipment.getName());
						break;
					}
				}
				break;
			case "8":
				for(TCg cgElement : cgElements_db){
					if(cgElement.getId().equals(edge.getToOid())){
						edge.setToEquName(cgElement.getFname());
						break;
					}
				}
				break;
		}
	}
	
	@RequiresPermissions("netconfig:tEdges:edit")
	@RequestMapping(value = "edit")
	public String edit(@ModelAttribute("tEdges") TEdges tEdges, Model model) {
		model.addAttribute("tEdges", tEdges);
		return "modules/netconfig/tVisEdgesForm";
	}
	
	@RequiresPermissions("netconfig:tEdges:edit")
	@RequestMapping(value = "update")
	public String update(@ModelAttribute("tEdges")TEdges tEdges, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tEdges)){
			return form(tEdges, model);
		}
		tEdgesService.save(tEdges);
		addMessage(redirectAttributes, "更新设备链路成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/tEdges/list?repage";
	}

	@RequiresPermissions("netconfig:tEdges:edit")
	@RequestMapping(value = "form")
	public String form(@ModelAttribute("tEdges") TEdges tEdges, Model model) {
		List<TComputerroom> crList = tComputerroomService.findList(null);
		model.addAttribute("crList", crList);
		model.addAttribute("tEdges", tEdges);
		return "modules/netconfig/tEdgesForm";
	}

	@RequestMapping(value = { "save" })
	@ResponseBody
	public String save(@RequestParam(required = false) String roomId, @RequestParam(required = false) String fromEqu,
			@RequestParam(required = false) String toEqu) {
		// 处理数据库里的原数据-start
		List<TEdges> historyList = new ArrayList<TEdges>();
		TEdges tEdges = new TEdges();
		tEdges.setRoomId(Integer.parseInt(roomId));
		tEdges.setFromEqu(fromEqu);
		List<TEdges> fromTEdgesList = tEdgesService.findList(tEdges);
		historyList.addAll(fromTEdgesList);
		tEdges.setFromEqu("");
		tEdges.setToEqu(fromEqu);
		List<TEdges> endTEdgesList = tEdgesService.findList(tEdges);

		for (TEdges tEdgesTemp : endTEdgesList) {
			tEdgesTemp.setTemp_field1(tEdgesTemp.getToEqu());
			tEdgesTemp.setToEqu(tEdgesTemp.getFromEqu());
			tEdgesTemp.setFromEqu(tEdgesTemp.getTemp_field1());
			historyList.add(tEdgesTemp);
		}
		// 处理数据库里的原数据-end
		// 处理前台页面选择的数据
		String[] choiceEnd = toEqu.split(",");
		List<TEdges> choiceList = new ArrayList<TEdges>();
		TEdges endTedges;
		for (int i = 0; i < choiceEnd.length; i++) {
			endTedges = new TEdges();
			endTedges.setRoomId(Integer.parseInt(roomId));
			endTedges.setFromEqu(fromEqu);
			endTedges.setToEqu(choiceEnd[i]);
			choiceList.add(endTedges);
		}
		// 删除数据
		for (int m = 0; m < historyList.size(); m++) {
			int remark = 1;
			for (int n = 0; n < choiceList.size(); n++) {
				if (historyList.get(m).getFromEqu().equals(choiceList.get(n).getFromEqu())
						&& historyList.get(m).getToEqu().equals(choiceList.get(n).getToEqu())) {
					remark = 0;
					break;
				}
			}
			if (remark == 1) {
				tEdgesService.deleteFromAndToNode(historyList.get(m));
			}
		}
		// 插入数据
		for (int q = 0; q < choiceList.size(); q++) {
			int remark = 1;
			for (int s = 0; s < historyList.size(); s++) {
				if (historyList.get(s).getFromEqu().equals(choiceList.get(q).getFromEqu())
						&& historyList.get(s).getToEqu().equals(choiceList.get(q).getToEqu())) {
					remark = 0;
					break;
				}
			}
			if (remark == 1) {
				tEdgesService.save(choiceList.get(q));
			}
		}
		return "success";
	}

	/**
	 * 读取详细数据
	 * 
	 * @param TElementcollect
	 * @return
	 */
	@RequestMapping(value = "queryFromEqu")
	@ResponseBody
	public String queryFromEqu(int equType, int roomId) {
		List<VisNodes> fromEquList = null;
		VisNodes visNodes = new VisNodes();
		visNodes.setRoomid(roomId);
		visNodes.setType(equType);
		try {
			fromEquList = visNodesService.findList(visNodes);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		if (fromEquList.size() > 0) {
			if (equType == 1 || equType == 2 || equType == 3) {
				List<TNewnetelement> tNewnetelementList = tNewnetelementService.findList(new TNewnetelement());
				for (TNewnetelement tNewnetelement : tNewnetelementList) {
					for (int i = 0; i < fromEquList.size(); i++) {
						if (tNewnetelement.getId().equals(fromEquList.get(i).getOid().toString())) {
							fromEquList.get(i).setName(tNewnetelement.getFname());
						}
					}
				}
			} else if (equType == 8) {
				List<TCg> tCgList = tCgService.findList(new TCg());
				for (TCg tcg : tCgList) {
					for (int i = 0; i < fromEquList.size(); i++) {
						if (tcg.getId().equals(fromEquList.get(i).getOid().toString())) {
							fromEquList.get(i).setName(tcg.getFname());
						}
					}
				}
			} else {
				List<TEquipment> tEquipmentList = tEquipmentService.findList(new TEquipment());
				for (TEquipment tequipment : tEquipmentList) {
					for (int i = 0; i < fromEquList.size(); i++) {
						if (tequipment.getId().equals(fromEquList.get(i).getOid().toString())) {
							fromEquList.get(i).setName(tequipment.getName());
						}
					}
				}
			}
		}
		return JsonMapper.getInstance().toJson(fromEquList);
	}

	@RequestMapping(value = { "endEqu" })
	@ResponseBody
	public String endEqu(@RequestParam(required = false) String startEquid) {
		TEdges tEdges = new TEdges();
		tEdges.setFromEqu(startEquid);
		List<TEdges> fromTEdgesList = tEdgesService.findList(tEdges);

		tEdges.setFromEqu("");
		tEdges.setToEqu(startEquid);
		List<TEdges> endTEdgesList = tEdgesService.findList(tEdges);

		List<String> newList = new ArrayList<String>();
		for (TEdges cd : fromTEdgesList) {
			newList.add(cd.getToEqu());
		}

		for (TEdges cd : endTEdgesList) {
			newList.add(cd.getFromEqu());
		}

		return newList.toString();
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
	@RequiresPermissions("performance:multipleIndex:view")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, @RequestParam(required = false) String poolName,
			@RequestParam(required = false) String parameterEpc, HttpServletResponse response) {
		// poolname参数临时借用，与池组没有关系

		List<VisNodes> fromEquList = null;
		VisNodes visNodes = new VisNodes();
		visNodes.setRoomid(Integer.parseInt(poolName));
		try {
			fromEquList = visNodesService.findList(visNodes);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		if (fromEquList.size() > 0) {
			List<TNewnetelement> tNewnetelementList = tNewnetelementService.findList(new TNewnetelement());
			for (TNewnetelement tNewnetelement : tNewnetelementList) {
				for (int i = 0; i < fromEquList.size(); i++) {
					if (tNewnetelement.getId().equals(fromEquList.get(i).getOid().toString())
							&& fromEquList.get(i).getType().toString().equals(tNewnetelement.getType())) {
						fromEquList.get(i).setName(tNewnetelement.getFname());
					}
				}
			}
			List<TCg> tCgList = tCgService.findList(new TCg());
			for (TCg tcg : tCgList) {
				for (int i = 0; i < fromEquList.size(); i++) {
					if (tcg.getId().equals(fromEquList.get(i).getOid().toString())
							&& fromEquList.get(i).getType().toString().equals("8")) {
						fromEquList.get(i).setName(tcg.getFname());
					}
				}
			}
			List<TEquipment> tEquipmentList = tEquipmentService.findList(new TEquipment());
			for (TEquipment tequipment : tEquipmentList) {
				for (int i = 0; i < fromEquList.size(); i++) {
					if (tequipment.getId().equals(fromEquList.get(i).getOid().toString())
							&& fromEquList.get(i).getType().toString().equals(tequipment.getType())) {
						fromEquList.get(i).setName(tequipment.getName());
					}
				}
			}
		}

		List<Map<String, Object>> mapList = Lists.newArrayList();

		List<Dict> dictList = DictUtils.getDictList("biz_vis_net_type");

		for (Dict dict : dictList) {
			Map<String, Object> mapParent = Maps.newHashMap();
			mapParent.put("id", "p" + dict.getValue());
			mapParent.put("pId", "0");
			mapParent.put("pIds", "0");
			mapParent.put("name", dict.getLabel());
			mapList.add(mapParent);
		}

		for (int i = 0; i < fromEquList.size(); i++) {
			VisNodes e = fromEquList.get(i);
			Map<String, Object> map = Maps.newHashMap();
			if(!e.getId().equals(parameterEpc)){
				//过滤掉始端数据
				map.put("id", e.getId());
				map.put("pId", "p" + e.getType());
				map.put("pIds", "0," + "p" + e.getType());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}