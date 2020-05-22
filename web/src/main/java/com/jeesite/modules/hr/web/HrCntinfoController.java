/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jeesite.common.collect.ListUtils;
import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.lang.DateUtils;
import com.jeesite.common.utils.excel.ExcelExport;
import com.jeesite.common.utils.excel.annotation.ExcelField.Type;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.hr.entity.HrCntinfo;
import com.jeesite.modules.hr.service.HrCntinfoService;

/**
 * 劳动合同基本信息Controller
 * @author 王志钦
 * @version 2019-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrCntinfo")
public class HrCntinfoController extends BaseController {

	@Autowired
	private HrCntinfoService hrCntinfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public HrCntinfo get(String cntid, boolean isNewRecord) {
		return hrCntinfoService.get(cntid, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:hrCntinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrCntinfo hrCntinfo, Model model) {
		model.addAttribute("hrCntinfo", hrCntinfo);
		return "modules/hr/hrCntinfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:hrCntinfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<HrCntinfo> listData(HrCntinfo hrCntinfo, HttpServletRequest request, HttpServletResponse response) {
		hrCntinfo.setPage(new Page<>(request, response));
		Page<HrCntinfo> page = hrCntinfoService.findPage(hrCntinfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:hrCntinfo:view")
	@RequestMapping(value = "form")
	public String form(HrCntinfo hrCntinfo, Model model) {
		model.addAttribute("hrCntinfo", hrCntinfo);
		return "modules/hr/hrCntinfoForm";
	}

	/**
	 * 保存劳动合同基本信息
	 */
	@RequiresPermissions("hr:hrCntinfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated HrCntinfo hrCntinfo) {
		hrCntinfoService.save(hrCntinfo);
		return renderResult(Global.TRUE, text("保存劳动合同基本信息成功！"));
	}
	
	/**
	 * 删除劳动合同基本信息
	 */
	@RequiresPermissions("hr:hrCntinfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HrCntinfo hrCntinfo) {
		hrCntinfoService.delete(hrCntinfo);
		return renderResult(Global.TRUE, text("删除劳动合同基本信息成功！"));
	}
	/**
	 * 导出用户数据
	 */
	@RequiresPermissions("hr:hrCntinfo:view")
	@RequestMapping(value = "exportData")
	public void exportData(HrCntinfo hrCntinfo, Boolean isAll, String ctrlPermi, HttpServletResponse response) {
		
		List<HrCntinfo> list = hrCntinfoService.findList(hrCntinfo);
		String fileName = "合同台账信息" + DateUtils.getDate("yyyyMMdd") + ".xlsx";
		try(ExcelExport ee = new ExcelExport("合同台账信息", HrCntinfo.class)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 下载导入用户数据模板
	 */
	@RequiresPermissions("hr:hrCntinfo:view")
	@RequestMapping(value = "importTemplate")
	public void importTemplate(HttpServletResponse response) {
		HrCntinfo hrCntinfo = new HrCntinfo();
		List<HrCntinfo> list = ListUtils.newArrayList(hrCntinfo);
		String fileName = "合同台账模板.xlsx";
		try(ExcelExport ee = new ExcelExport("合同台账", HrCntinfo.class, Type.IMPORT)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 导入用户数据
	 */
	@ResponseBody
	@RequiresPermissions("hr:hrCntinfo:edit")
	@PostMapping(value = "importData")
	public String importData(MultipartFile file, String updateSupport) {
		try {
			boolean isUpdateSupport = Global.YES.equals(updateSupport);
			String message = hrCntinfoService.importData(file, isUpdateSupport);
			return renderResult(Global.TRUE, "posfull:"+message);
		} catch (Exception ex) {
			return renderResult(Global.FALSE, "posfull:"+ex.getMessage());
		}
	}



	
}