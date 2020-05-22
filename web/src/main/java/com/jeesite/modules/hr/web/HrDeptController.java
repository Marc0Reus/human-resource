/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.web;

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

import com.jeesite.common.config.Global;
import com.jeesite.common.entity.Page;
import com.jeesite.common.web.BaseController;
import com.jeesite.modules.hr.entity.HrDept;
import com.jeesite.modules.hr.service.HrDeptService;

/**
 * 部门基本信息Controller
 * @author 王志钦
 * @version 2019-04-19
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrDept")
public class HrDeptController extends BaseController {

	@Autowired
	private HrDeptService hrDeptService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public HrDept get(String dpno, boolean isNewRecord) {
		return hrDeptService.get(dpno, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:hrDept:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrDept hrDept, Model model) {
		model.addAttribute("hrDept", hrDept);
		return "modules/hr/hrDeptList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:hrDept:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<HrDept> listData(HrDept hrDept, HttpServletRequest request, HttpServletResponse response) {
		hrDept.setPage(new Page<>(request, response));
		Page<HrDept> page = hrDeptService.findPage(hrDept);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:hrDept:view")
	@RequestMapping(value = "form")
	public String form(HrDept hrDept, Model model) {
		model.addAttribute("hrDept", hrDept);
		return "modules/hr/hrDeptForm";
	}

	/**
	 * 保存部门基本信息
	 */
	@RequiresPermissions("hr:hrDept:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated HrDept hrDept) {
		hrDeptService.save(hrDept);
		return renderResult(Global.TRUE, text("保存部门基本信息成功！"));
	}
	
	/**
	 * 删除部门基本信息
	 */
	@RequiresPermissions("hr:hrDept:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HrDept hrDept) {
		hrDeptService.delete(hrDept);
		return renderResult(Global.TRUE, text("删除部门基本信息成功！"));
	}
	
}