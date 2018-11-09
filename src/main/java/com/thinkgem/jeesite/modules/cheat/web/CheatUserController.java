package com.thinkgem.jeesite.modules.cheat.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.db.DynamicDataSource;
import com.thinkgem.jeesite.common.entity.ServerPagination;
import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.FileUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cheat.entity.CheatLog;
import com.thinkgem.jeesite.modules.cheat.entity.CheatType;
import com.thinkgem.jeesite.modules.cheat.entity.CheatUser;
import com.thinkgem.jeesite.modules.cheat.service.CheatUserService;
import com.thinkgem.jeesite.modules.collectset.service.TCollectordeployService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;

/**
 * 欺诈用户评估表
 * @author chenhongbo
 *
 */

@Controller
@RequestMapping(value = "${adminPath}/cheat/cheatUserAssess")
public class CheatUserController extends BaseController {
	
	@Autowired
	private TNewnetelementService tNewnetelementService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private CheatUserService cheatUserService;
	@Autowired
	private TCollectordeployService tCollectordeployService;
	
	private String filePath = Global.getConfig("localinexistenceIPCapFilePath");
	
	@RequiresPermissions("cheat:cheatUserAssess:view")
	@RequestMapping(value = {"list",""})
	public String list(@Valid @ModelAttribute("tCheatType") CheatType tCheatType,HttpServletRequest request, HttpServletResponse response, Model model) {
		TNewnetelement tnet = new TNewnetelement();
		tnet.setType("6");
		List<TNewnetelement> netListAll = tNewnetelementService.findListByTypeAndCollect(tnet);
		List<TPool> poolList = tPoolService.queryPoolListByType(null);
		List<PoolNetVO> poolNetList = new ArrayList<PoolNetVO>();
		for (TPool pool : poolList) {
			PoolNetVO poolNetVO = new PoolNetVO();
			List<TNewnetelement> netList = new ArrayList<TNewnetelement>();
			for (TNewnetelement n : netListAll) {
				if (pool.getId().equals(n.getFnid().toString())) {
					netList.add(n);
				}
			}
			if (netList.size() != 0) {
				poolNetVO.setNetList(netList);
				poolNetVO.setPoolName(pool.getFpoolname());
				poolNetList.add(poolNetVO);
			}
		}

		model.addAttribute("poolNetList", poolNetList);
		return "modules/cheat/cheatUserList";
	}
	
	@RequiresPermissions("cheat:cheatUserAssess:view")
	@RequestMapping(value = { "queryList" })
	@ResponseBody
	public String queryList(String startTime, String endTime, String netId, String netName, int limit,
			int offset, String sortName, String sortOrder) {
		String[] et = endTime.split(" ");
		if(et.length < 2) {
			endTime = startTime.split(" ")[0] + " " + endTime;
		}
		ServerPagination<CheatUser> page = new ServerPagination<CheatUser>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netId);
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			List<CheatUser> list = cheatUserService.getTableList(startTime, endTime, netId, limit, offset, sortName, sortOrder);
			Integer count = cheatUserService.getTableCount(startTime, endTime, netId);
			DynamicDataSource.setCurrentLookupKey("dataSource");
			page.setTotal(count.intValue());
			page.setRows(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		
		return JsonMapper.getInstance().toJson(page);
	}
	
	@RequiresPermissions("cheat:cheatUserAssess:view")
	@RequestMapping(value = "showCheatlogPage")
	public String showCheatlogPage(String netId, String servedIMSI, String startTime, String endTime, String cheatCase, Model model) {
		model.addAttribute("netId", netId);
		model.addAttribute("servedIMSI", servedIMSI);
		model.addAttribute("startTime", startTime);
		model.addAttribute("endTime", endTime);
		model.addAttribute("cheatCase", cheatCase);
		model.addAttribute("model", model);
		return "modules/cheat/cheatlog";
	}
	
	@RequiresPermissions("cheat:cheatUserAssess:view")
	@RequestMapping(value = {"showCheatlog"})
	@ResponseBody
	public String showCheatlog(String netId, String servedIMSI, String startTime, String endTime, String cheatCase, int limit,int offset) {
		ServerPagination<CheatLog> page = new ServerPagination<CheatLog>();
		List<CheatLog> list = new ArrayList<CheatLog>();
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netId);
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			list = cheatUserService.getCheatlog(netId, servedIMSI, startTime, endTime, cheatCase);
			page.setTotal(list.size());
			page.setRows(list);
			DynamicDataSource.setCurrentLookupKey("dataSource");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return JsonMapper.getInstance().toJson(page);
	}
	
	@RequiresPermissions("cheat:cheatUserAssess:view")
	@RequestMapping(value = "showPieChart")
	public String showPieChart(String netId,String servedIMSI,String startTime,String endTime, Model model) {
		
		try {
			String ip = tCollectordeployService.getCheatCollectorIp(netId);
			DynamicDataSource.setCurrentLookupKey(ip + "-core_data_sub");
			List<Map<String, Object>> list = cheatUserService.getPieChart(netId, startTime, endTime, servedIMSI);
			model.addAttribute("list", list);
			model.addAttribute("imsi", servedIMSI);
			Map<String, Object>  map = cheatUserService.getFlowPieChart(netId, servedIMSI, startTime, endTime);
			model.addAttribute("map", map);
			DynamicDataSource.setCurrentLookupKey("dataSource");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DynamicDataSource.setCurrentLookupKey("dataSource");
		}
		return "modules/cheat/cheatPieChart";
	}
	
	@RequiresPermissions("cheat:cheatUserAssess:view")
	@RequestMapping(value = {"downloadZip"})
	public void downLoadZip(String servedIMSI,String startTime,String endTime,String netId,HttpServletResponse response) {
		String timeStart = startTime.replaceAll(" ", "")
				.replaceAll("-", "").replaceAll(":", "");
		String timeEnd = endTime.replaceAll(" ", "").replaceAll("-", "")
				.replaceAll(":", "");
		String dateStampStart = timeStart.substring(0, 8);
		String dateStampEnd = timeEnd.substring(0, 8);
		int s = Integer.parseInt(dateStampStart);
		int e = Integer.parseInt(dateStampEnd);
		
		String resultPath = filePath + File.separator + netId + File.separator + "downLoadCapTemp";
		File fileResult = new File(resultPath);
		if (!fileResult.exists()) {
			fileResult.mkdir();
		}
		
		for (int m = 0; m <= (e - s); m++) {
			String dirPath = filePath + File.separator + netId + File.separator + (s + m);
			File file = new File(dirPath);
			if (file.exists() && file.isDirectory()) {
				for (File fileCap : file.listFiles()) {
					if (fileCap.getName().indexOf(servedIMSI) >= 0&&fileCap.getName().indexOf("_qz") >= 0) {
						if (fileCap.isFile()) {
							cpFile(fileCap.getAbsolutePath(), resultPath
									+ File.separator
									+ fileCap.getName().substring(0,
											fileCap.getName().length() - 3));
						}
					}
				}
			}
		}
		/*String filename = filePath 
				+ File.separator
				+ netId
				+ File.separator + servedIMSI + "_"+timeStart+"_"+timeEnd+".zip";*/
		String filename = netId + File.separator + servedIMSI + "_"+timeStart+"_"+timeEnd;
		File[] files = fileResult.listFiles();
		if(files != null){
			String[] filePaths = new String[files.length];
			for(int i=0;i<files.length;i++){
				filePaths[i] = files[i].getAbsolutePath();
			}
			try {
				FileUtils.writeZipAndDownload(response, filePaths,filename);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void cpFile(String oldPath, String newPath) {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			inStream = new FileInputStream(oldPath); // 读入原文件
			fs = new FileOutputStream(newPath);
			byte[] buffer = new byte[1444];
			while ((byteread = inStream.read(buffer)) != -1) {
				bytesum += byteread; // 字节数 文件大小
				fs.write(buffer, 0, byteread);
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();
		} finally {
			try {
				fs.close();
				inStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
