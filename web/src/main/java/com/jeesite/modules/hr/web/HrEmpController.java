/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.json.JSONObject;
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
import com.jeesite.modules.hr.entity.HrDept;
import com.jeesite.modules.hr.entity.HrEmp;
import com.jeesite.modules.hr.entity.HrPost;
import com.jeesite.modules.hr.service.HrDeptService;
import com.jeesite.modules.hr.service.HrDimissionService;
import com.jeesite.modules.hr.service.HrEmpService;
import com.jeesite.modules.hr.service.HrPostService;

/**
 * 人员信息Controller
 * @author 王志钦
 * @version 2019-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrEmp")
public class HrEmpController extends BaseController {

	@Autowired
	private HrEmpService hrEmpService;	
	@Autowired
	private HrDeptService hrDeptService;
	@Autowired
	private HrPostService hrPostService;
	@Autowired
	private HrDimissionService hrDimissionService;
	
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public HrEmp get(String wno, boolean isNewRecord) {
		return hrEmpService.get(wno, isNewRecord);
	}
	
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:hrEmp:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrEmp hrEmp, Model model) {
		model.addAttribute("hrEmp", hrEmp);
		HrDept hrDept=new HrDept();
		List<HrDept>deptList=hrDeptService.findList(hrDept);
		model.addAttribute("deptList", deptList);
		HrPost hrPost=new HrPost();
		List<HrPost>postList=hrPostService.findList(hrPost);
		model.addAttribute("postList", postList);
		return "modules/hr/hrEmpList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:hrEmp:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<HrEmp> listData(HrEmp hrEmp, HttpServletRequest request, HttpServletResponse response) {
		hrEmp.setPage(new Page<>(request, response));
		Page<HrEmp> page = hrEmpService.findPage(hrEmp);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:hrEmp:view")
	@RequestMapping(value = "form")
	public String form(HrEmp hrEmp, Model model) {
		model.addAttribute("hrEmp", hrEmp);
		HrDept hrDept=new HrDept();
		List<HrDept>deptList=hrDeptService.findList(hrDept);
		model.addAttribute("deptList", deptList);
		HrPost hrPost=new HrPost();
		List<HrPost>postList=hrPostService.findList(hrPost);
		model.addAttribute("postList", postList);
		return "modules/hr/hrEmpForm";
	}

	/**
	 * 保存人员信息
	 */
	@RequiresPermissions("hr:hrEmp:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated HrEmp hrEmp) {
		String status=hrEmpService.findStatus(hrEmp);
		String msg="";
		try{
		switch(status){
		case "0":
			hrEmpService.save(hrEmp);
			msg="保存人员信息成功";
			break;
		case "1":
			msg="保存失败，该人员合同已经终止";
			break;
		case "2":
			msg="保存失败，该人员合同已经解除";
			break;	
		}
		}catch(Exception e){
			msg="保存失败，没有该人员的合同信息";
		}
		return renderResult(Global.TRUE, text(msg));
	}
	
	/**
	 * 删除人员信息
	 */
	@RequiresPermissions("hr:hrEmp:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HrEmp hrEmp) {
		hrEmpService.delete(hrEmp);
		return renderResult(Global.TRUE, text("删除人员信息成功！"));
	}
	
	/**
	 * 导出用户数据
	 */
	@RequiresPermissions("hr:hrEmp:view")
	@RequestMapping(value = "exportData")
	public void exportData(HrEmp hrEmp, Boolean isAll, String ctrlPermi, HttpServletResponse response) {
		
		List<HrEmp> list = hrEmpService.findList(hrEmp);
		String fileName = "员工数据" + DateUtils.getDate("yyyyMMdd") + ".xlsx";
		try(ExcelExport ee = new ExcelExport("员工数据", HrEmp.class)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 下载导入用户数据模板
	 */
	@RequiresPermissions("hr:hrEmp:view")
	@RequestMapping(value = "importTemplate")
	public void importTemplate(HttpServletResponse response) {
		HrEmp hrEmp = new HrEmp();
		List<HrEmp> list = ListUtils.newArrayList(hrEmp);
		String fileName = "员工数据模板.xlsx";
		try(ExcelExport ee = new ExcelExport("用户数据", HrEmp.class, Type.IMPORT)){
			ee.setDataList(list).write(response, fileName);
		}
	}

	/**
	 * 导入用户数据
	 */
	@ResponseBody
	@RequiresPermissions("hr:hrEmp:edit")
	@PostMapping(value = "importData")
	public String importData(MultipartFile file, String updateSupport) {
		try {
			boolean isUpdateSupport = Global.YES.equals(updateSupport);
			String message = hrEmpService.importData(file, isUpdateSupport);
			return renderResult(Global.TRUE, "posfull:"+message);
		} catch (Exception ex) {
			return renderResult(Global.FALSE, "posfull:"+ex.getMessage());
		}
	}
	
	/**
	 * 人员统计分析
	 */
	@RequiresPermissions("hr:hrEmp:view")
	@RequestMapping(value = {"hrEmpCount", ""})
	public String sysDesktop(HrEmp hrEmp, Model model) {
		model.addAttribute("d1", hrEmpService.countDept("1"));
		model.addAttribute("d2", hrEmpService.countDept("2"));
		model.addAttribute("d3", hrEmpService.countDept("3"));
		model.addAttribute("d4", hrEmpService.countDept("4"));
		model.addAttribute("d5", hrEmpService.countDept("5"));
		model.addAttribute("d6", hrEmpService.countDept("6"));
		model.addAttribute("d7", hrEmpService.countDept("7"));
		model.addAttribute("d8", hrEmpService.countDept("8"));
		model.addAttribute("d9", hrEmpService.countDept("9"));
		model.addAttribute("edu1", hrEmpService.countEdu("1"));
		model.addAttribute("edu2", hrEmpService.countEdu("2"));
		model.addAttribute("edu3", hrEmpService.countEdu("3"));
		model.addAttribute("edu4", hrEmpService.countEdu("4"));
		model.addAttribute("de1", hrEmpService.countDimission("1"));
		model.addAttribute("de2", hrEmpService.countDimission("2"));
		model.addAttribute("de3", hrEmpService.countDimission("3"));
		model.addAttribute("de4", hrEmpService.countDimission("4"));
		model.addAttribute("de5", hrEmpService.countDimission("5"));
		model.addAttribute("de6", hrEmpService.countDimission("6"));
		model.addAttribute("de7", hrEmpService.countDimission("7"));
		model.addAttribute("de8", hrEmpService.countDimission("8"));
		model.addAttribute("hrEmp", hrEmp);
		return "modules/hr/hrEmpCount";
	}
	
}