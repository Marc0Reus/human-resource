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
import com.jeesite.modules.hr.entity.HrDimission;
import com.jeesite.modules.hr.service.HrDimissionService;

/**
 * 离职人员信息Controller
 * @author 王志钦
 * @version 2019-04-18
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrDimission")
public class HrDimissionController extends BaseController {

	@Autowired
	private HrDimissionService hrDimissionService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public HrDimission get(String wno, boolean isNewRecord) {
		return hrDimissionService.get(wno, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:hrDimission:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrDimission hrDimission, Model model) {
		model.addAttribute("hrDimission", hrDimission);
		return "modules/hr/hrDimissionList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:hrDimission:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<HrDimission> listData(HrDimission hrDimission, HttpServletRequest request, HttpServletResponse response) {
		hrDimission.setPage(new Page<>(request, response));
		Page<HrDimission> page = hrDimissionService.findPage(hrDimission);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:hrDimission:view")
	@RequestMapping(value = "form")
	public String form(HrDimission hrDimission, Model model) {
		model.addAttribute("hrDimission", hrDimission);
		return "modules/hr/hrDimissionForm";
	}

	/**
	 * 保存离职人员信息
	 */
	@RequiresPermissions("hr:hrDimission:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated HrDimission hrDimission) {
		String msg="";
		String idcard=hrDimissionService.findEmp(hrDimission);
		if(idcard!=null){
		hrDimissionService.save(hrDimission);
		msg="保存离职人员信息成功！";
		hrDimissionService.updateCntstatus(idcard);
		hrDimissionService.deleteEmp(hrDimission);
		}
		else{
		msg="保存失败，系统中没有该人员！";
		}
		return renderResult(Global.TRUE, text(msg));
	}
	
	/**
	 * 删除离职人员信息
	 */
	@RequiresPermissions("hr:hrDimission:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HrDimission hrDimission) {
		hrDimissionService.delete(hrDimission);
		return renderResult(Global.TRUE, text("删除离职人员信息成功！"));
	}
	
}