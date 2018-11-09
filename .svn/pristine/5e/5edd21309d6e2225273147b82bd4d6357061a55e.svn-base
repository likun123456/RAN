package com.thinkgem.jeesite.modules.performance.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.netconfig.entity.TFormula;
import com.thinkgem.jeesite.modules.netconfig.service.TFormulaService;
import com.thinkgem.jeesite.modules.performance.entity.TEbmEvent;
import com.thinkgem.jeesite.modules.performance.entity.TIndexKpi;
import com.thinkgem.jeesite.modules.performance.entity.TIndexKpiEvent;
import com.thinkgem.jeesite.modules.performance.entity.TIndexKpiParam;
import com.thinkgem.jeesite.modules.performance.entity.TIndexSettingVO;
import com.thinkgem.jeesite.modules.performance.service.TIndexKpiEventService;
import com.thinkgem.jeesite.modules.performance.service.TIndexKpiParamService;
import com.thinkgem.jeesite.modules.performance.service.TIndexKpiService;
import com.thinkgem.jeesite.modules.performance.service.TIndexSettingService;
import com.thinkgem.jeesite.modules.performance.service.TTacSuccessRateService;

/**
 * 首页参数设置Controller
 * 
 * @author 杨海
 * @version 2017-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/performance/indexsetting")
@Scope("prototype")
public class TIndexSettingControlle extends BaseController {

	@Autowired
	private TIndexSettingService tIndexSettingService;
	@Autowired
	private TIndexKpiService tIndexKpiService;
	@Autowired
	private TIndexKpiParamService tIndexKpiParamService;
	@Autowired
	private TFormulaService tFormulaService;
	@Autowired
	private TTacSuccessRateService tTacSuccessRateService;
	@Autowired
	private TIndexKpiEventService tIndexKpiEventService;

	@ModelAttribute
	public TIndexSettingVO get(@RequestParam(required = false) String id) {
		TIndexSettingVO entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = tIndexSettingService.get(id);
		}
		if (entity == null) {
			entity = new TIndexSettingVO();
		}
		return entity;
	}

	@RequiresPermissions("performance:index:view")
	@RequestMapping(value = "indexParamSetting")
	public String indexParamSetting(@Valid @ModelAttribute("tIndexSettingVO") TIndexSettingVO tIndexSettingVO,
			HttpServletRequest request, HttpServletResponse response, Model model) {
		TFormula tFormula = new TFormula();
		tFormula.setFlag("1");
		List<TFormula> tFormulaList = tFormulaService.findList(tFormula);
		
		List<TFormula> tFormulaMMEList=new ArrayList<TFormula>();
		List<TFormula> saegwList=new ArrayList<TFormula>();
		List<TFormula> tFormulaPCRFList=new ArrayList<TFormula>();
		List<TFormula> tFormulaMSCList=new ArrayList<TFormula>();
		List<TFormula> tFormulaMGWList=new ArrayList<TFormula>();
		List<TFormula> tFormulaHLRFEList=new ArrayList<TFormula>();
		List<TFormula> tFormulaHSSList=new ArrayList<TFormula>();
		List<TFormula> tFormulaCUDBList=new ArrayList<TFormula>();
		List<TFormula> tFormulaPGList=new ArrayList<TFormula>();
		
		for(int i=0;i<tFormulaList.size();i++){
			switch(tFormulaList.get(i).getNettype()){
				case "MME":
					tFormulaMMEList.add(tFormulaList.get(i));
				break;
				case "EPG-PGW":
					saegwList.add(tFormulaList.get(i));
					break;
				case "EPG-SGW":
					saegwList.add(tFormulaList.get(i));
					break;
				case "PCRF":
					tFormulaPCRFList.add(tFormulaList.get(i));
					break;
				case "MSC":
					tFormulaMSCList.add(tFormulaList.get(i));
					break;
				case "MGW":
					tFormulaMGWList.add(tFormulaList.get(i));
					break;
				case "HLRFE":
					tFormulaHLRFEList.add(tFormulaList.get(i));
					break;
				case "HSS":
					tFormulaHSSList.add(tFormulaList.get(i));
					break;
				case "CUDB":
					tFormulaCUDBList.add(tFormulaList.get(i));
					break;
				case "PG":
					tFormulaPGList.add(tFormulaList.get(i));
					break;
			}
		}		
		
		model.addAttribute("mmeList", tFormulaMMEList);
		model.addAttribute("saegwList", saegwList);
		model.addAttribute("pcrfList", tFormulaPCRFList);
		model.addAttribute("mscList", tFormulaMSCList);
		model.addAttribute("mgwList", tFormulaMGWList);
		model.addAttribute("hlrfeList", tFormulaHLRFEList);
		model.addAttribute("hssList", tFormulaHSSList);
		model.addAttribute("cudbList", tFormulaCUDBList);
		model.addAttribute("pgList", tFormulaPGList);
		
		List<TEbmEvent> ebmEventlist = tTacSuccessRateService.getEbmEventlist("");
		model.addAttribute("ebmEventlist", ebmEventlist);
		// 用于回显
		List<TIndexKpi> indexKpiList = tIndexKpiService.findList(new TIndexKpi());
		if (indexKpiList.size() > 0) {
			tIndexSettingVO.setMmekpione(indexKpiList.get(0).getMmekpione());
			tIndexSettingVO.setMmekpitwo(indexKpiList.get(0).getMmekpitwo());
			tIndexSettingVO.setMmekpithree(indexKpiList.get(0).getMmekpithree());
			tIndexSettingVO.setMmekpifour(indexKpiList.get(0).getMmekpifour());
			tIndexSettingVO.setMmekpifive(indexKpiList.get(0).getMmekpifive());
			tIndexSettingVO.setMmekpisix(indexKpiList.get(0).getMmekpisix());

			tIndexSettingVO.setSaegwkpione(indexKpiList.get(0).getSaegwkpione());
			tIndexSettingVO.setSaegwkpitwo(indexKpiList.get(0).getSaegwkpitwo());
			tIndexSettingVO.setSaegwkpithree(indexKpiList.get(0).getSaegwkpithree());
			tIndexSettingVO.setSaegwkpifour(indexKpiList.get(0).getSaegwkpifour());
			tIndexSettingVO.setSaegwkpifive(indexKpiList.get(0).getSaegwkpifive());
			tIndexSettingVO.setSaegwkpisix(indexKpiList.get(0).getSaegwkpisix());

			tIndexSettingVO.setPcrfkpione(indexKpiList.get(0).getPcrfkpione());
			tIndexSettingVO.setPcrfkpitwo(indexKpiList.get(0).getPcrfkpitwo());
			tIndexSettingVO.setPcrfkpithree(indexKpiList.get(0).getPcrfkpithree());
			tIndexSettingVO.setPcrfkpifour(indexKpiList.get(0).getPcrfkpifour());
			tIndexSettingVO.setPcrfkpifive(indexKpiList.get(0).getPcrfkpifive());
			tIndexSettingVO.setPcrfkpisix(indexKpiList.get(0).getPcrfkpisix());
			
			tIndexSettingVO.setMsckpione(indexKpiList.get(0).getMsckpione());
			tIndexSettingVO.setMsckpitwo(indexKpiList.get(0).getMsckpitwo());
			tIndexSettingVO.setMsckpithree(indexKpiList.get(0).getMsckpithree());
			tIndexSettingVO.setMsckpifour(indexKpiList.get(0).getMsckpifour());
			tIndexSettingVO.setMsckpifive(indexKpiList.get(0).getMsckpifive());
			tIndexSettingVO.setMsckpisix(indexKpiList.get(0).getMsckpisix());
			
			tIndexSettingVO.setMgwkpione(indexKpiList.get(0).getMgwkpione());
			tIndexSettingVO.setMgwkpitwo(indexKpiList.get(0).getMgwkpitwo());
			tIndexSettingVO.setMgwkpithree(indexKpiList.get(0).getMgwkpithree());
			tIndexSettingVO.setMgwkpifour(indexKpiList.get(0).getMgwkpifour());
			tIndexSettingVO.setMgwkpifive(indexKpiList.get(0).getMgwkpifive());
			tIndexSettingVO.setMgwkpisix(indexKpiList.get(0).getMgwkpisix());
			
			tIndexSettingVO.setHlrfekpione(indexKpiList.get(0).getHlrfekpione());
			tIndexSettingVO.setHlrfekpitwo(indexKpiList.get(0).getHlrfekpitwo());
			tIndexSettingVO.setHlrfekpithree(indexKpiList.get(0).getHlrfekpithree());
			tIndexSettingVO.setHlrfekpifour(indexKpiList.get(0).getHlrfekpifour());
			tIndexSettingVO.setHlrfekpifive(indexKpiList.get(0).getHlrfekpifive());
			tIndexSettingVO.setHlrfekpisix(indexKpiList.get(0).getHlrfekpisix());

			tIndexSettingVO.setHsskpione(indexKpiList.get(0).getHsskpione());
			tIndexSettingVO.setHsskpitwo(indexKpiList.get(0).getHsskpitwo());
			tIndexSettingVO.setHsskpithree(indexKpiList.get(0).getHsskpithree());
			tIndexSettingVO.setHsskpifour(indexKpiList.get(0).getHsskpifour());
			tIndexSettingVO.setHsskpifive(indexKpiList.get(0).getHsskpifive());
			tIndexSettingVO.setHsskpisix(indexKpiList.get(0).getHsskpisix());
			
			tIndexSettingVO.setCudbkpione(indexKpiList.get(0).getCudbkpione());
			tIndexSettingVO.setCudbkpitwo(indexKpiList.get(0).getCudbkpitwo());
			tIndexSettingVO.setCudbkpithree(indexKpiList.get(0).getCudbkpithree());
			tIndexSettingVO.setCudbkpifour(indexKpiList.get(0).getCudbkpifour());
			tIndexSettingVO.setCudbkpifive(indexKpiList.get(0).getCudbkpifive());
			tIndexSettingVO.setCudbkpisix(indexKpiList.get(0).getCudbkpisix());

			tIndexSettingVO.setPgkpione(indexKpiList.get(0).getPgkpione());
			tIndexSettingVO.setPgkpitwo(indexKpiList.get(0).getPgkpitwo());
			tIndexSettingVO.setPgkpithree(indexKpiList.get(0).getPgkpithree());
			tIndexSettingVO.setPgkpifour(indexKpiList.get(0).getPgkpifour());
			tIndexSettingVO.setPgkpifive(indexKpiList.get(0).getPgkpifive());
			tIndexSettingVO.setPgkpisix(indexKpiList.get(0).getPgkpisix());

			
			tIndexSettingVO.setStarttime(indexKpiList.get(0).getStarttime());
			model.addAttribute("tIndexSettingVO", tIndexSettingVO);
		}
		return "modules/performance/indexParamSetting";
	}

	@RequiresPermissions("performance:index:view")
	@RequestMapping(value = "saveFormula")
	@ResponseBody
	public String saveFormula(@RequestParam(required = false) String mmekpione,
			@RequestParam(required = false) String mmekpitwo, @RequestParam(required = false) String mmekpithree,
			@RequestParam(required = false) String mmekpifour, @RequestParam(required = false) String mmekpifive,
			@RequestParam(required = false) String mmekpisix, @RequestParam(required = false) String saegwkpione,
			@RequestParam(required = false) String saegwkpitwo, @RequestParam(required = false) String saegwkpithree,
			@RequestParam(required = false) String saegwkpifour, @RequestParam(required = false) String saegwkpifive,
			@RequestParam(required = false) String saegwkpisix, @RequestParam(required = false) String pcrfkpione,
			@RequestParam(required = false) String pcrfkpitwo, @RequestParam(required = false) String pcrfkpithree,
			@RequestParam(required = false) String pcrfkpifour, @RequestParam(required = false) String pcrfkpifive,
			@RequestParam(required = false) String pcrfkpisix, @RequestParam(required = false) String msckpione,
			@RequestParam(required = false) String msckpitwo, @RequestParam(required = false) String msckpithree,
			@RequestParam(required = false) String msckpifour, @RequestParam(required = false) String msckpifive,
			@RequestParam(required = false) String msckpisix,@RequestParam(required = false) String mgwkpione,
			@RequestParam(required = false) String mgwkpitwo, @RequestParam(required = false) String mgwkpithree,
			@RequestParam(required = false) String mgwkpifour, @RequestParam(required = false) String mgwkpifive,
			@RequestParam(required = false) String mgwkpisix, @RequestParam(required = false) String hlrfekpione,
			@RequestParam(required = false) String hlrfekpitwo, @RequestParam(required = false) String hlrfekpithree,
			@RequestParam(required = false) String hlrfekpifour, @RequestParam(required = false) String hlrfekpifive,
			@RequestParam(required = false) String hlrfekpisix,@RequestParam(required = false) String hsskpione,
			@RequestParam(required = false) String hsskpitwo, @RequestParam(required = false) String hsskpithree,
			@RequestParam(required = false) String hsskpifour, @RequestParam(required = false) String hsskpifive,
			@RequestParam(required = false) String hsskpisix,@RequestParam(required = false) String cudbkpione,
			@RequestParam(required = false) String cudbkpitwo, @RequestParam(required = false) String cudbkpithree,
			@RequestParam(required = false) String cudbkpifour, @RequestParam(required = false) String cudbkpifive,
			@RequestParam(required = false) String cudbkpisix,@RequestParam(required = false) String pgkpione,
			@RequestParam(required = false) String pgkpitwo, @RequestParam(required = false) String pgkpithree,
			@RequestParam(required = false) String pgkpifour, @RequestParam(required = false) String pgkpifive,
			@RequestParam(required = false) String pgkpisix,@RequestParam(required = false) String starttime) {
		List<TIndexKpi> indexKpiList = tIndexKpiService.findList(new TIndexKpi());
		TIndexKpi entity = new TIndexKpi();
		TIndexKpi tempTIndexKpi;
		if (indexKpiList.size() > 0) {
			tempTIndexKpi = indexKpiList.get(0);
			entity.setId(tempTIndexKpi.getId());
		} else {
			tempTIndexKpi = new TIndexKpi();
		}
		if (null == mmekpione || "".equals(mmekpione)) {
			entity.setMmekpione(tempTIndexKpi.getMmekpione());
		} else {
			entity.setMmekpione(mmekpione);
		}
		if (null == mmekpitwo || "".equals(mmekpitwo)) {
			entity.setMmekpitwo(tempTIndexKpi.getMmekpitwo());
		} else {
			entity.setMmekpitwo(mmekpitwo);
		}
		if (null == mmekpithree || "".equals(mmekpithree)) {
			entity.setMmekpithree(tempTIndexKpi.getMmekpithree());
		} else {
			entity.setMmekpithree(mmekpithree);
		}
		if (null == mmekpifour || "".equals(mmekpifour)) {
			entity.setMmekpifour(tempTIndexKpi.getMmekpifour());
		} else {
			entity.setMmekpifour(mmekpifour);
		}
		if (null == mmekpifive || "".equals(mmekpifive)) {
			entity.setMmekpifive(tempTIndexKpi.getMmekpifive());
		} else {
			entity.setMmekpifive(mmekpifive);
		}
		if (null == mmekpisix || "".equals(mmekpisix)) {
			entity.setMmekpisix(tempTIndexKpi.getMmekpisix());
		} else {
			entity.setMmekpisix(mmekpisix);
		}
		if (null == saegwkpione || "".equals(saegwkpione)) {
			entity.setSaegwkpione(tempTIndexKpi.getSaegwkpione());
		} else {
			entity.setSaegwkpione(saegwkpione);
		}
		if (null == saegwkpitwo || "".equals(saegwkpitwo)) {
			entity.setSaegwkpitwo(tempTIndexKpi.getSaegwkpitwo());
		} else {
			entity.setSaegwkpitwo(saegwkpitwo);
		}
		if (null == saegwkpithree || "".equals(saegwkpithree)) {
			entity.setSaegwkpithree(tempTIndexKpi.getSaegwkpithree());
		} else {
			entity.setSaegwkpithree(saegwkpithree);
		}
		if (null == saegwkpifour || "".equals(saegwkpifour)) {
			entity.setSaegwkpifour(tempTIndexKpi.getSaegwkpifour());
		} else {
			entity.setSaegwkpifour(saegwkpifour);
		}
		if (null == saegwkpifive || "".equals(saegwkpifive)) {
			entity.setSaegwkpifive(tempTIndexKpi.getSaegwkpifive());
		} else {
			entity.setSaegwkpifive(saegwkpifive);
		}
		if (null == saegwkpisix || "".equals(saegwkpisix)) {
			entity.setSaegwkpisix(tempTIndexKpi.getSaegwkpisix());
		} else {
			entity.setSaegwkpisix(saegwkpisix);
		}
		if (null == pcrfkpione || "".equals(pcrfkpione)) {
			entity.setPcrfkpione(tempTIndexKpi.getPcrfkpione());
		} else {
			entity.setPcrfkpione(pcrfkpione);
		}
		if (null == pcrfkpitwo || "".equals(pcrfkpitwo)) {
			entity.setPcrfkpitwo(tempTIndexKpi.getPcrfkpitwo());
		} else {
			entity.setPcrfkpitwo(pcrfkpitwo);
		}
		if (null == pcrfkpithree || "".equals(pcrfkpithree)) {
			entity.setPcrfkpithree(tempTIndexKpi.getPcrfkpithree());
		} else {
			entity.setPcrfkpithree(pcrfkpithree);
		}
		if (null == pcrfkpifour || "".equals(pcrfkpifour)) {
			entity.setPcrfkpifour(tempTIndexKpi.getPcrfkpifour());
		} else {
			entity.setPcrfkpifour(pcrfkpifour);
		}
		if (null == pcrfkpifive || "".equals(pcrfkpifive)) {
			entity.setPcrfkpifive(tempTIndexKpi.getPcrfkpifive());
		} else {
			entity.setPcrfkpifive(pcrfkpifive);
		}
		if (null == pcrfkpisix || "".equals(pcrfkpisix)) {
			entity.setPcrfkpisix(tempTIndexKpi.getPcrfkpisix());
		} else {
			entity.setPcrfkpisix(pcrfkpisix);
		}
		if (null == msckpione || "".equals(msckpione)) {
			entity.setMsckpione(tempTIndexKpi.getMsckpione());
		} else {
			entity.setMsckpione(msckpione);
		}
		if (null == msckpitwo || "".equals(msckpitwo)) {
			entity.setMsckpitwo(tempTIndexKpi.getMsckpitwo());
		} else {
			entity.setMsckpitwo(msckpitwo);
		}
		if (null == msckpithree || "".equals(msckpithree)) {
			entity.setMsckpithree(tempTIndexKpi.getMsckpithree());
		} else {
			entity.setMsckpithree(msckpithree);
		}
		if (null == msckpifour || "".equals(msckpifour)) {
			entity.setMsckpifour(tempTIndexKpi.getMsckpifour());
		} else {
			entity.setMsckpifour(msckpifour);
		}
		if (null == msckpifive || "".equals(msckpifive)) {
			entity.setMsckpifive(tempTIndexKpi.getMsckpifive());
		} else {
			entity.setMsckpifive(msckpifive);
		}
		if (null == msckpisix || "".equals(msckpisix)) {
			entity.setMsckpisix(tempTIndexKpi.getMsckpisix());
		} else {
			entity.setMsckpisix(msckpisix);
		}
		if (null == mgwkpione || "".equals(mgwkpione)) {
			entity.setMgwkpione(tempTIndexKpi.getMgwkpione());
		} else {
			entity.setMgwkpione(mgwkpione);
		}
		if (null == mgwkpitwo || "".equals(mgwkpitwo)) {
			entity.setMgwkpitwo(tempTIndexKpi.getMgwkpitwo());
		} else {
			entity.setMgwkpitwo(mgwkpitwo);
		}
		if (null == mgwkpithree || "".equals(mgwkpithree)) {
			entity.setMgwkpithree(tempTIndexKpi.getMgwkpithree());
		} else {
			entity.setMgwkpithree(mgwkpithree);
		}
		if (null == mgwkpifour || "".equals(mgwkpifour)) {
			entity.setMgwkpifour(tempTIndexKpi.getMgwkpifour());
		} else {
			entity.setMgwkpifour(mgwkpifour);
		}
		if (null == mgwkpifive || "".equals(mgwkpifive)) {
			entity.setMgwkpifive(tempTIndexKpi.getMgwkpifive());
		} else {
			entity.setMgwkpifive(mgwkpifive);
		}
		if (null == mgwkpisix || "".equals(mgwkpisix)) {
			entity.setMgwkpisix(tempTIndexKpi.getMgwkpisix());
		} else {
			entity.setMgwkpisix(mgwkpisix);
		}	
		if (null == hlrfekpione || "".equals(hlrfekpione)) {
			entity.setHlrfekpione(tempTIndexKpi.getHlrfekpione());
		} else {
			entity.setHlrfekpione(hlrfekpione);
		}
		if (null == hlrfekpitwo || "".equals(hlrfekpitwo)) {
			entity.setHlrfekpitwo(tempTIndexKpi.getHlrfekpitwo());
		} else {
			entity.setHlrfekpitwo(hlrfekpitwo);
		}
		if (null == hlrfekpithree || "".equals(hlrfekpithree)) {
			entity.setHlrfekpithree(tempTIndexKpi.getHlrfekpithree());
		} else {
			entity.setHlrfekpithree(hlrfekpithree);
		}
		if (null == hlrfekpifour || "".equals(hlrfekpifour)) {
			entity.setHlrfekpifour(tempTIndexKpi.getHlrfekpifour());
		} else {
			entity.setHlrfekpifour(hlrfekpifour);
		}
		if (null == hlrfekpifive || "".equals(hlrfekpifive)) {
			entity.setHlrfekpifive(tempTIndexKpi.getHlrfekpifive());
		} else {
			entity.setHlrfekpifive(hlrfekpifive);
		}
		if (null == hlrfekpisix || "".equals(hlrfekpisix)) {
			entity.setHlrfekpisix(tempTIndexKpi.getHlrfekpisix());
		} else {
			entity.setHlrfekpisix(hlrfekpisix);
		}		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
		if (null == hsskpione || "".equals(hsskpione)) {
			entity.setHsskpione(tempTIndexKpi.getHsskpione());
		} else {
			entity.setHsskpione(hsskpione);
		}
		if (null == hsskpitwo || "".equals(hsskpitwo)) {
			entity.setHsskpitwo(tempTIndexKpi.getHsskpitwo());
		} else {
			entity.setHsskpitwo(hsskpitwo);
		}
		if (null == hsskpithree || "".equals(hsskpithree)) {
			entity.setHsskpithree(tempTIndexKpi.getHsskpithree());
		} else {
			entity.setHsskpithree(hsskpithree);
		}
		if (null == hsskpifour || "".equals(hsskpifour)) {
			entity.setHsskpifour(tempTIndexKpi.getHsskpifour());
		} else {
			entity.setHsskpifour(hsskpifour);
		}
		if (null == hsskpifive || "".equals(hsskpifive)) {
			entity.setHsskpifive(tempTIndexKpi.getHsskpifive());
		} else {
			entity.setHsskpifive(hsskpifive);
		}
		if (null == hsskpisix || "".equals(hsskpisix)) {
			entity.setHsskpisix(tempTIndexKpi.getHsskpisix());
		} else {
			entity.setHsskpisix(hsskpisix);
		}		
		if (null == cudbkpione || "".equals(cudbkpione)) {
			entity.setCudbkpione(tempTIndexKpi.getCudbkpione());
		} else {
			entity.setCudbkpione(cudbkpione);
		}
		if (null == cudbkpitwo || "".equals(cudbkpitwo)) {
			entity.setCudbkpitwo(tempTIndexKpi.getCudbkpitwo());
		} else {
			entity.setCudbkpitwo(cudbkpitwo);
		}
		if (null == cudbkpithree || "".equals(cudbkpithree)) {
			entity.setCudbkpithree(tempTIndexKpi.getCudbkpithree());
		} else {
			entity.setCudbkpithree(cudbkpithree);
		}
		if (null == cudbkpifour || "".equals(cudbkpifour)) {
			entity.setCudbkpifour(tempTIndexKpi.getCudbkpifour());
		} else {
			entity.setCudbkpifour(cudbkpifour);
		}
		if (null == cudbkpifive || "".equals(cudbkpifive)) {
			entity.setCudbkpifive(tempTIndexKpi.getCudbkpifive());
		} else {
			entity.setCudbkpifive(cudbkpifive);
		}
		if (null == cudbkpisix || "".equals(cudbkpisix)) {
			entity.setCudbkpisix(tempTIndexKpi.getCudbkpisix());
		} else {
			entity.setCudbkpisix(cudbkpisix);
		}
		
		if (null == pgkpione || "".equals(pgkpione)) {
			entity.setPgkpione(tempTIndexKpi.getPgkpione());
		} else {
			entity.setPgkpione(pgkpione);
		}
		if (null == pgkpitwo || "".equals(pgkpitwo)) {
			entity.setPgkpitwo(tempTIndexKpi.getPgkpitwo());
		} else {
			entity.setPgkpitwo(pgkpitwo);
		}
		if (null == pgkpithree || "".equals(pgkpithree)) {
			entity.setPgkpithree(tempTIndexKpi.getPgkpithree());
		} else {
			entity.setPgkpithree(pgkpithree);
		}
		if (null == pgkpifour || "".equals(pgkpifour)) {
			entity.setPgkpifour(tempTIndexKpi.getPgkpifour());
		} else {
			entity.setPgkpifour(pgkpifour);
		}
		if (null == pgkpifive || "".equals(pgkpifive)) {
			entity.setPgkpifive(tempTIndexKpi.getPgkpifive());
		} else {
			entity.setPgkpifive(pgkpifive);
		}
		if (null == pgkpisix || "".equals(pgkpisix)) {
			entity.setPgkpisix(tempTIndexKpi.getPgkpisix());
		} else {
			entity.setPgkpisix(pgkpisix);
		}		
		if (null == starttime || "".equals(starttime)) {
			entity.setStarttime(tempTIndexKpi.getStarttime());
		} else {
			entity.setStarttime(starttime);
		}
		tIndexKpiService.save(entity);
		return "success";
	}

	@RequiresPermissions("performance:index:view")
	@RequestMapping(value = "saveKpiParam")
	@ResponseBody
	public String saveKpiParam(@RequestParam(required = false) String excellentUp,
			@RequestParam(required = false) String excellentDown, @RequestParam(required = false) String goodUp,
			@RequestParam(required = false) String goodDown, @RequestParam(required = false) String wellUp,
			@RequestParam(required = false) String wellDown, @RequestParam(required = false) String badUp,
			@RequestParam(required = false) String badDown, @RequestParam(required = false) String alarmUp,
			@RequestParam(required = false) String alarmDown, @RequestParam(required = false) String formulaType,
			@RequestParam(required = false) String chk) {
		if (chk.equals("true")) {// 所有脚本设置相同门限
			List<TFormula> netList = tFormulaService.findList(new TFormula());
			TIndexKpiParam tIndexKpiParam;
			for (int m = 0; m < netList.size(); m++) {
				tIndexKpiParam = new TIndexKpiParam();
				tIndexKpiParam.setKpi(netList.get(m).getId());
				tIndexKpiParamService.delete(tIndexKpiParam);
				tIndexKpiParam.setExcellentUp(excellentUp);
				tIndexKpiParam.setExcellentDown(excellentDown);
				tIndexKpiParam.setGoodUp(goodUp);
				tIndexKpiParam.setGoodDown(goodDown);
				tIndexKpiParam.setWellUp(wellUp);
				tIndexKpiParam.setWellDown(wellDown);
				tIndexKpiParam.setBadUp(badUp);
				tIndexKpiParam.setBadDown(badDown);
				tIndexKpiParam.setAlarmUp(alarmUp);
				tIndexKpiParam.setAlarmDown(alarmDown);
				tIndexKpiParamService.save(tIndexKpiParam);
			}
		} else {
			if (null == formulaType || "".equals(formulaType)) {
				return "保存失败，请选择指标";
			} else {
				String[] kpis = formulaType.split(",");
				List<TIndexKpiParam> tIndexKpiParamList = tIndexKpiParamService.findList(new TIndexKpiParam());
				TIndexKpiParam tIndexKpiParam;
				for (int m = 0; m < kpis.length; m++) {
					tIndexKpiParam = new TIndexKpiParam();
					for (int n = 0; n < tIndexKpiParamList.size(); n++) {
						if (tIndexKpiParamList.get(n).getKpi().equals(kpis[m])) {
							tIndexKpiParam.setId(tIndexKpiParamList.get(n).getId());
						}
					}
					tIndexKpiParam.setKpi(kpis[m]);
					tIndexKpiParam.setExcellentUp(excellentUp);
					tIndexKpiParam.setExcellentDown(excellentDown);
					tIndexKpiParam.setGoodUp(goodUp);
					tIndexKpiParam.setGoodDown(goodDown);
					tIndexKpiParam.setWellUp(wellUp);
					tIndexKpiParam.setWellDown(wellDown);
					tIndexKpiParam.setBadUp(badUp);
					tIndexKpiParam.setBadDown(badDown);
					tIndexKpiParam.setAlarmUp(alarmUp);
					tIndexKpiParam.setAlarmDown(alarmDown);
					tIndexKpiParamService.save(tIndexKpiParam);
				}
			}
		}
		return "success";
	}

	@RequiresPermissions("performance:index:view")
	@RequestMapping(value = "saveKpiEvent")
	@ResponseBody
	public String saveKpiEvent(@RequestParam(required = false) String event, @RequestParam(required = false) String formulaType,
			@RequestParam(required = false) String chk) {
		if (chk.equals("true")) {// 所有脚本设置相同门限
			TFormula tFormula = new TFormula();
			tFormula.setNettype("MME");
			List<TFormula> formulaList = tFormulaService.findList(tFormula);
			TIndexKpiEvent tIndexKpiEvent;
			for (int m = 0; m < formulaList.size(); m++) {
				tIndexKpiEvent = new TIndexKpiEvent();
				tIndexKpiEvent.setKpi(formulaList.get(m).getId());
				tIndexKpiEventService.delete(tIndexKpiEvent);
				tIndexKpiEvent.setEvent(event);
				tIndexKpiEventService.save(tIndexKpiEvent);
			}
		} else {
			if (null == formulaType || "".equals(formulaType)) {
				return "保存失败，请选择指标";
			} else {
				String[] kpis = formulaType.split(",");
				List<TIndexKpiEvent> tIndexKpiEventList = tIndexKpiEventService.findList(new TIndexKpiEvent());
				TIndexKpiEvent tIndexKpiEvent;
				for (int m = 0; m < kpis.length; m++) {
					tIndexKpiEvent = new TIndexKpiEvent();
					for (int n = 0; n < tIndexKpiEventList.size(); n++) {
						if (tIndexKpiEventList.get(n).getKpi().equals(kpis[m])) {
							tIndexKpiEvent.setId(tIndexKpiEventList.get(n).getId());
						}
					}
					tIndexKpiEvent.setKpi(kpis[m]);
					tIndexKpiEvent.setEvent(event);
					tIndexKpiEventService.save(tIndexKpiEvent);
				}
			}
		}
		return "success";
	}
	
	@RequiresPermissions("performance:index:view")
	@RequestMapping(value = "kpiParamOld")
	@ResponseBody
	public TIndexKpiParam kpiParamOld(@RequestParam(required = false) String kpiId) {
		TIndexKpiParam tIndexKpiParam = new TIndexKpiParam();
		tIndexKpiParam.setKpi(kpiId);
		tIndexKpiParam = tIndexKpiParamService.get(tIndexKpiParam);
		return tIndexKpiParam;
	}
	
	@RequiresPermissions("performance:index:view")
	@RequestMapping(value = "kpiEventOld")
	@ResponseBody
	public TIndexKpiEvent kpiEventOld(@RequestParam(required = false) String kpiId) {
		TIndexKpiEvent tIndexKpiEvent = new TIndexKpiEvent();
		tIndexKpiEvent.setKpi(kpiId);
		tIndexKpiEvent = tIndexKpiEventService.get(tIndexKpiEvent);
		List<TEbmEvent> ebmEventlist = tTacSuccessRateService.getEbmEventlist("");
		for(int i=0;i<ebmEventlist.size();i++){
			if(tIndexKpiEvent.getEvent().equals(ebmEventlist.get(i).getEventname())){
				tIndexKpiEvent.setTemp_field1(ebmEventlist.get(i).getEventfullname());
			}
		}
		return tIndexKpiEvent;
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
	@RequiresPermissions("performance:index:view")
	@ResponseBody
	@RequestMapping(value = "treeDataFormula")
	public List<Map<String, Object>> treeData(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, @RequestParam(required = false) String parameterEpc,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		String[] netType = parameterEpc.split(",");
		for (int m = 0; m < netType.length; m++) {
			TFormula tFormula = new TFormula();
			tFormula.setNettype(netType[m]);
			List<TFormula> list = tFormulaService.findList(tFormula);
			for (int i = 0; i < list.size(); i++) {
				TFormula e = list.get(i);
				Map<String, Object> map = Maps.newHashMap();
				if (i == 0) {
					Map<String, Object> mapParent = Maps.newHashMap();
					if (netType[m].equals("MME")) {
						mapParent.put("id", 9999999);
						mapParent.put("pId", "0");
						mapParent.put("pIds", 9999999);
					} else if (netType[m].equals("EPG-PGW")) {
						mapParent.put("id", 9999998);
						mapParent.put("pId", "0");
						mapParent.put("pIds", 9999998);
					} else if (netType[m].equals("EPG-SGW")) {
						mapParent.put("id", 9999997);
						mapParent.put("pId", "0");
						mapParent.put("pIds", 9999997);
					}else if (netType[m].equals("MSC")) {
						mapParent.put("id", 9999995);
						mapParent.put("pId", "0");
						mapParent.put("pIds", 9999995);
					}else if (netType[m].equals("MGW")) {
						mapParent.put("id", 9999994);
						mapParent.put("pId", "0");
						mapParent.put("pIds", 9999994);
					}else if (netType[m].equals("HLRFE")) {
						mapParent.put("id", 9999993);
						mapParent.put("pId", "0");
						mapParent.put("pIds", 9999993);
					}else if (netType[m].equals("CUDB")) {
						mapParent.put("id", 9999992);
						mapParent.put("pId", "0");
						mapParent.put("pIds", 9999992);
					}else if (netType[m].equals("PG")) {
						mapParent.put("id", 9999991);
						mapParent.put("pId", "0");
						mapParent.put("pIds", 9999991);
					}else {
						mapParent.put("id", 9999996);
						mapParent.put("pId", "0");
						mapParent.put("pIds", 9999996);
					}
					mapParent.put("name", netType[m]);
					mapList.add(mapParent);
				}
				map.put("id", e.getId());
				if (netType[m].equals("MME")) {
					map.put("pId", 9999999);
					map.put("pIds", "0," + 9999999);
				} else if (netType[m].equals("EPG-PGW")) {
					map.put("pId", 9999998);
					map.put("pIds", "0," + 9999998);
				} else if (netType[m].equals("EPG-SGW")) {
					map.put("pId", 9999997);
					map.put("pIds", "0," + 9999997);
				}else if (netType[m].equals("MSC")) {
					map.put("pId", 9999995);
					map.put("pIds", "0," + 9999995);
				}else if (netType[m].equals("MGW")) {
					map.put("pId", 9999994);
					map.put("pIds", "0," + 9999994);
				}else if (netType[m].equals("HLRFE")) {
					map.put("pId", 9999993);
					map.put("pIds", "0," + 9999993);
				}else if (netType[m].equals("CUDB")) {
					map.put("pId", 9999992);
					map.put("pIds", "0," + 9999992);
				}else if (netType[m].equals("PG")) {
					map.put("pId", 9999991);
					map.put("pIds", "0," + 9999991);
				} else {
					map.put("pId", 9999996);
					map.put("pIds", "0," + 9999996);
				}
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
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
	@RequiresPermissions("performance:index:view")
	@ResponseBody
	@RequestMapping(value = "treeDataMMEFormula")
	public List<Map<String, Object>> treeDataMME(@RequestParam(required = false) String extId,
			@RequestParam(required = false) String type, @RequestParam(required = false) Long grade,
			@RequestParam(required = false) Boolean isAll, @RequestParam(required = false) String parameterEpc,
			HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
			TFormula tFormula = new TFormula();
			tFormula.setNettype("MME");
			List<TFormula> list = tFormulaService.findList(tFormula);
			for (int i = 0; i < list.size(); i++) {
					TFormula e = list.get(i);
					Map<String, Object> map = Maps.newHashMap();
					if (i == 0) {
						Map<String, Object> mapParent = Maps.newHashMap();
						mapParent.put("id", 9999999);
						mapParent.put("pId", "0");
						mapParent.put("pIds", 9999999);
						mapParent.put("name", "MME");
						mapList.add(mapParent);
					}
					map.put("id", e.getId());
					map.put("pId", 9999999);
					map.put("pIds", "0," + 9999999);
					map.put("name", e.getName());
					mapList.add(map);
			}
		return mapList;
	}
}