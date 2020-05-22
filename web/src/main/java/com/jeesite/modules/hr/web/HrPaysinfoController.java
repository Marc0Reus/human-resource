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
import com.jeesite.modules.hr.entity.HrEmp;
import com.jeesite.modules.hr.entity.HrPaysinfo;
import com.jeesite.modules.hr.service.HrPaysinfoService;

/**
 * 薪酬信息Controller
 * @author 王志钦
 * @version 2019-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrPaysinfo")
public class HrPaysinfoController extends BaseController {

	@Autowired
	private HrPaysinfoService hrPaysinfoService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public HrPaysinfo get(String paysid, boolean isNewRecord) {
		return hrPaysinfoService.get(paysid, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:hrPaysinfo:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrPaysinfo hrPaysinfo, Model model) {
		model.addAttribute("hrPaysinfo", hrPaysinfo);
		return "modules/hr/hrPaysinfoList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:hrPaysinfo:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<HrPaysinfo> listData(HrPaysinfo hrPaysinfo, HttpServletRequest request, HttpServletResponse response) {
		hrPaysinfo.setPage(new Page<>(request, response));
		Page<HrPaysinfo> page = hrPaysinfoService.findPage(hrPaysinfo);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:hrPaysinfo:view")
	@RequestMapping(value = "form")
	public String form(HrPaysinfo hrPaysinfo, Model model) {
		model.addAttribute("hrPaysinfo", hrPaysinfo);
		return "modules/hr/hrPaysinfoForm";
	}

	/**
	 * 保存薪酬信息
	 */
	@RequiresPermissions("hr:hrPaysinfo:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated HrPaysinfo hrPaysinfo) {
		String idcard=hrPaysinfoService.findEmp(hrPaysinfo);
		String msg="";
		if(idcard!=null)
		{
		hrPaysinfoService.save(hrPaysinfo);
		msg="保存薪酬信息成功！";
		}
		else{
			msg="保存失败，系统中没有该人员！";
		}
		
	
		return renderResult(Global.TRUE, text(msg));
	}
	
	/**
	 * 删除薪酬信息
	 */
	@RequiresPermissions("hr:hrPaysinfo:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HrPaysinfo hrPaysinfo) {
		hrPaysinfoService.delete(hrPaysinfo);
		return renderResult(Global.TRUE, text("删除薪酬信息成功！"));
	}
	
	/**
	 * 导出用户数据
	 */
	@RequiresPermissions("hr:hrPaysinfo:view")
	@RequestMapping(value = "exportData")
	public void exportData(HrPaysinfo hrPaysinfo, Boolean isAll, String ctrlPermi, HttpServletResponse response) {
		
		List<HrPaysinfo> list = hrPaysinfoService.findList(hrPaysinfo);
		String fileName = "薪酬信息" + DateUtils.getDate("yyyyMMdd") + ".xlsx";
		try(ExcelExport ee = new ExcelExport("薪酬信息", HrPaysinfo.class)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 下载导入用户数据模板
	 */
	@RequiresPermissions("hr:hrPaysinfo:view")
	@RequestMapping(value = "importTemplate")
	public void importTemplate(HttpServletResponse response) {
		HrPaysinfo hrPaysinfo = new HrPaysinfo();
		List<HrPaysinfo> list = ListUtils.newArrayList(hrPaysinfo);
		String fileName = "薪酬信息模板.xlsx";
		try(ExcelExport ee = new ExcelExport("薪酬信息", HrPaysinfo.class, Type.IMPORT)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 导入用户数据
	 */
	@ResponseBody
	@RequiresPermissions("hr:hrPaysinfo:edit")
	@PostMapping(value = "importData")
	public String importData(MultipartFile file, String updateSupport) {
		try {
			boolean isUpdateSupport = Global.YES.equals(updateSupport);
			String message = hrPaysinfoService.importData(file, isUpdateSupport);
			return renderResult(Global.TRUE, "posfull:"+message);
		} catch (Exception ex) {
			return renderResult(Global.FALSE, "posfull:"+ex.getMessage());
		}
	}
	
	/**
	 * 人员统计分析
	 */
	@RequiresPermissions("hr:hrPaysinfo:view")
	@RequestMapping(value = {"hrPaysCount", ""})
	public String sysDesktop(HrPaysinfo hrPaysinfo, Model model) {
		double a=hrPaysinfoService.sempCount();
		double b=hrPaysinfoService.scomCount();
		double s=a+b;
		double x=hrPaysinfoService.fundempCount();
		double y=hrPaysinfoService.fundcomCount();
		double fund=x+y;
		model.addAttribute("p1", hrPaysinfoService.paysRangeMin(4000));
		model.addAttribute("p2", hrPaysinfoService.paysRange(4000,6000));
		model.addAttribute("p3", hrPaysinfoService.paysRange(6000,8000));
		model.addAttribute("p4", hrPaysinfoService.paysRange(8000,10000));
		model.addAttribute("p5", hrPaysinfoService.paysRange(10000,12000));
		model.addAttribute("p6", hrPaysinfoService.paysRange(12000,15000));
		model.addAttribute("p7", hrPaysinfoService.paysRangeMax(15000));
		model.addAttribute("tax", hrPaysinfoService.taxCount());
		model.addAttribute("float1", hrPaysinfoService.floatCount());
		model.addAttribute("bouns", hrPaysinfoService.bounsCount());
		model.addAttribute("semp", a);
		model.addAttribute("scom", b);
		model.addAttribute("s", s);
		model.addAttribute("fundemp", x);
		model.addAttribute("fundcom", y);
		model.addAttribute("fund", fund);
		model.addAttribute("pay", hrPaysinfoService.payCount());
		model.addAttribute("hrPaysinfo", hrPaysinfo);
		return "modules/hr/hrPaysCount";
	}
	
}
	