package com.thinkgem.jeesite.modules.cheat.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.cheat.entity.CheatPackage;
import com.thinkgem.jeesite.modules.cheat.service.CheatPackageService;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;
import com.thinkgem.jeesite.modules.netconfig.entity.TPool;
import com.thinkgem.jeesite.modules.netconfig.service.TNewnetelementService;
import com.thinkgem.jeesite.modules.netconfig.service.TPoolService;
import com.thinkgem.jeesite.modules.performance.entity.PoolNetVO;

/**
 * 计费欺诈包提取Controller
 * 
 * @author yanghai
 * @version 2017-08-29
 */
@Controller
@RequestMapping(value = "${adminPath}/cheat/cheatPackageDown")
public class CheatPackageController extends BaseController {
	@Autowired
	private CheatPackageService cheatPackageService;
	@Autowired
	private TPoolService tPoolService;
	@Autowired
	private TNewnetelementService tNewnetelementService;

	@ModelAttribute
	public CheatPackage get(@RequestParam(required = false) String id) {
		CheatPackage entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = cheatPackageService.get(id);
		}
		if (entity == null) {
			entity = new CheatPackage();
		}
		return entity;
	}

	@RequiresPermissions("cheat:cheatPackageDown:view")
	@RequestMapping(value = { "down" })
	public String down(@Valid @ModelAttribute("tCheatPackage") CheatPackage tCheatPackage, HttpServletRequest request,
			HttpServletResponse response, Model model) {
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
		return "modules/cheat/cheatPackageDown";
	}

	@RequiresPermissions("cheat:cheatPackageDown:view")
	@RequestMapping(value = "downPackage")
	public void downPackage(HttpServletResponse response,
			@ModelAttribute("tCheatPackage") CheatPackage tCheatPackage) {
		String timeStamp = tCheatPackage.getDateTime().replaceAll(" ", "").replaceAll("-", "").replaceAll(":", "");
		File file = new File(
				Global.getConfig("localinexistenceIPCapFilePath") + File.separator + tCheatPackage.getNetId()
						+ File.separator + timeStamp + File.separator + "zip" + File.separator + timeStamp + ".zip");
		if(!file.exists()){
			if(!file.getParentFile().exists()) {  
				file.getParentFile().mkdirs();
			}
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
            // 以流的形式下载文件。
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(file.getName().getBytes("UTF-8"),"ISO-8859-1"));
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
         } 
         catch (IOException ex) {
            ex.printStackTrace();
        }
	}

	private String result;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
