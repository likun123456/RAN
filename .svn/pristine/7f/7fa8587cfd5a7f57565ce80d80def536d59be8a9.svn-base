/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.netconfig.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
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

/**
 * 拓扑节点管理Controller
 * @author zhuguangrui
 * @version 2018-03-08
 */
@Controller
@RequestMapping(value = "${adminPath}/netconfig/visNodes")
public class VisNodesController extends BaseController {

	@Autowired
	private VisNodesService visNodesService;
	
	@Autowired
	private TComputerroomService roomService;
	@Autowired
	private TCgService cgService;
	@Autowired
	private TNewnetelementService newnetelementService;
	@Autowired
	private TEquipmentService equipmentService;
	
	@Autowired
	private TEdgesService tedgesService;
	
	@ModelAttribute
	public VisNodes get(@RequestParam(required=false) String id) {
		VisNodes entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = visNodesService.get(id);
		}
		if (entity == null){
			entity = new VisNodes();
		}
		return entity;
	}
	
	
	/**
	 * 初始化机房节点信息
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "initPool")
	@ResponseBody
	public String initPool(String roomId){
		
		
		VisNodes visNodes = new VisNodes();
		visNodes.setRoomid(Integer.valueOf(roomId));
		List<VisNodes> nodeIns = visNodesService.findList(visNodes);
		
		TNewnetelement tnewnetelement = new TNewnetelement();
		tnewnetelement.setCroom(roomId);
		List<TNewnetelement> netElements_db = newnetelementService.findList(tnewnetelement);
		
		List<TEquipment> equipment_db = equipmentService.findList(null);
		
		TCg cg = new TCg();
		if(roomId != null && !roomId.equals("")){
			cg.setCroom(Integer.valueOf(roomId));
		}
		List<TCg> cgElements_db = cgService.findList(cg);
		
		
		List<TNewnetelement> mmeElements_to = new ArrayList<TNewnetelement>();
		List<TNewnetelement> saegwElements_to = new ArrayList<TNewnetelement>();
		List<TNewnetelement> pcrfElements_to = new ArrayList<TNewnetelement>();
		List<TEquipment> switchElements_to = new ArrayList<TEquipment>();
		List<TEquipment> routerElements_to = new ArrayList<TEquipment>();
		List<TEquipment> firewallElements_to = new ArrayList<TEquipment>();
		List<TEquipment> pnetElements_to = new ArrayList<TEquipment>();
		List<TCg> cgElements_to = new ArrayList<TCg>();
		
		List<TNewnetelement> mmeElements = new ArrayList<TNewnetelement>();
		List<TNewnetelement> saegwElements = new ArrayList<TNewnetelement>();
		List<TNewnetelement> pcrfElements = new ArrayList<TNewnetelement>();
		List<TEquipment> switchElements = new ArrayList<TEquipment>();
		List<TEquipment> routerElements = new ArrayList<TEquipment>();
		List<TEquipment> firewallElements = new ArrayList<TEquipment>();
		List<TEquipment> pnetElements = new ArrayList<TEquipment>();
		List<TCg> cgElements = new ArrayList<TCg>();
		
		Map<String,List> pool = new HashMap<String,List>();
		
		Map<String,String> vnodes = new HashMap<String,String>();
		for(VisNodes node : nodeIns){
			if(vnodes.containsKey(String.valueOf(node.getType()))){
				String oids = vnodes.get(String.valueOf(node.getType())).concat(",").concat(String.valueOf(node.getOid()));
				vnodes.put(String.valueOf(node.getType()), oids);
			}else{
				vnodes.put(String.valueOf(node.getType()), String.valueOf(node.getOid()));
			}
		}
				
			for(TNewnetelement netItem : netElements_db){
				if("1".equals(netItem.getType())){
					if(vnodes.containsKey("1") && vnodes.get("1").contains(netItem.getId())){
						mmeElements_to.add(netItem);
					}else{
						mmeElements.add(netItem);
					}
				}
				if("2".equals(netItem.getType())){
					if(vnodes.containsKey("2") && vnodes.get("2").contains(netItem.getId())){
						saegwElements_to.add(netItem);
					}else{
						saegwElements.add(netItem);
					}
				}
				if("3".equals(netItem.getType())){
					if(vnodes.containsKey("3") && vnodes.get("3").contains(netItem.getId())){
						pcrfElements_to.add(netItem);
					}else{
						pcrfElements.add(netItem);
					}
				}
			}
			for(TEquipment equipItem : equipment_db){
				if("4".equals(equipItem.getType())){
					if(vnodes.containsKey("4") && vnodes.get("4").contains(equipItem.getId())){
						switchElements_to.add(equipItem);
					}else{
						switchElements.add(equipItem);
					}
				}
				if("5".equals(equipItem.getType())){
					if(vnodes.containsKey("5") && vnodes.get("5").contains(equipItem.getId())){
						routerElements_to.add(equipItem);
					}else{
						routerElements.add(equipItem);
					}
				}
				if("6".equals(equipItem.getType())){
					if(vnodes.containsKey("6") && vnodes.get("6").contains(equipItem.getId())){
						firewallElements_to.add(equipItem);
					}else{
						firewallElements.add(equipItem);
					}
				}
				if("7".equals(equipItem.getType())){
					if(vnodes.containsKey("7") && vnodes.get("7").contains(equipItem.getId())){
						pnetElements_to.add(equipItem);
					}else{
						pnetElements.add(equipItem);
					}
				}
			}
			for(TCg cgItem : cgElements_db){
				if(vnodes.containsKey("8") && vnodes.get("8").contains(cgItem.getId())){
					cgElements_to.add(cgItem);
				}else{
					cgElements.add(cgItem);
				}
			}
		
		pool.put("mmeElements", mmeElements);
		pool.put("saegwElements", saegwElements);
		pool.put("pcrfElements", pcrfElements);
		pool.put("switchElements", switchElements);
		pool.put("routerElements", routerElements);
		pool.put("firewallElements", firewallElements);
		pool.put("pnetElements", pnetElements);
		pool.put("cgElements", cgElements);
		
		
		pool.put("mmeElements_to", mmeElements_to);
		pool.put("saegwElements_to", saegwElements_to);
		pool.put("pcrfElements_to", pcrfElements_to);
		pool.put("switchElements_to", switchElements_to);
		pool.put("routerElements_to", routerElements_to);
		pool.put("firewallElements_to", firewallElements_to);
		pool.put("pnetElements_to", pnetElements_to);
		pool.put("cgElements_to", cgElements_to);
		
		return JsonMapper.getInstance().toJson(pool);
	}

	@RequiresPermissions("netconfig:visNodes:view")
	@RequestMapping(value = "form")
	public String form(VisNodes visNodes, Model model) {
		List<TComputerroom> rooms = roomService.findList(null);
		model.addAttribute("rooms", rooms);
		return "modules/netconfig/visNodesForm";
	}
	
	

	@RequiresPermissions("netconfig:visNodes:view")
	@RequestMapping(value = "save")
	public String save(@RequestParam Integer roomId,@RequestParam String idElements, RedirectAttributes redirectAttributes) {
		VisNodes visNodes = new VisNodes();
		visNodes.setRoomid(roomId);
		//查询当前机房下的所有设备
		List<VisNodes> db_visNodes = visNodesService.findList(visNodes);
		StringBuffer sb = new StringBuffer();
		TEdges tedges = new TEdges();
		if(StringUtils.isNotBlank(idElements)){
			for(VisNodes db_visNode : db_visNodes){
				if(!idElements.contains(db_visNode.getType() +"_"+ db_visNode.getOid())){
					visNodesService.delete(db_visNode);
					System.out.println("移除的设备" + db_visNode.getType() +"_"+ db_visNode.getOid());
					
					tedges.setFromEqu(db_visNode.getId());
					tedges.setToEqu(db_visNode.getId());
					tedgesService.deleteFromOrToNode(tedges);
					
				}
				sb.append(db_visNode.getType()).append("_").append(db_visNode.getOid()).append(",");
			}
			String[] idArray = idElements.split(",");
			for(String idItem : idArray){
				if(!sb.toString().contains(idItem)){
					VisNodes node = new VisNodes();
					node.setRoomid(roomId);
					node.setType(Integer.valueOf(idItem.split("_")[0]));
					node.setOid(Integer.valueOf(idItem.split("_")[1]));
					visNodesService.save(node);
					System.out.println("新增的设备" + idItem);
				}
			}
		}
		addMessage(redirectAttributes, "保存拓扑节点成功");
		return "redirect:"+Global.getAdminPath()+"/netconfig/visNodes/form?repage";
	}
	

}