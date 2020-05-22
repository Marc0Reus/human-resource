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
import com.jeesite.modules.hr.entity.HrPaystruct;
import com.jeesite.modules.hr.service.HrPaystructService;

/**
 * 薪酬结构Controller
 * @author 王志钦
 * @version 2019-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrPaystruct")
public class HrPaystructController extends BaseController {

	@Autowired
	private HrPaystructService hrPaystructService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public HrPaystruct get(String pnm, boolean isNewRecord) {
		return hrPaystructService.get(pnm, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:hrPaystruct:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrPaystruct hrPaystruct, Model model) {
		model.addAttribute("hrPaystruct", hrPaystruct);
		return "modules/hr/hrPaystructList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:hrPaystruct:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<HrPaystruct> listData(HrPaystruct hrPaystruct, HttpServletRequest request, HttpServletResponse response) {
		hrPaystruct.setPage(new Page<>(request, response));
		Page<HrPaystruct> page = hrPaystructService.findPage(hrPaystruct);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:hrPaystruct:view")
	@RequestMapping(value = "form")
	public String form(HrPaystruct hrPaystruct, Model model) {
		model.addAttribute("hrPaystruct", hrPaystruct);
		return "modules/hr/hrPaystructForm";
	}

	/**
	 * 保存薪酬结构
	 */
	@RequiresPermissions("hr:hrPaystruct:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated HrPaystruct hrPaystruct) {
		hrPaystructService.save(hrPaystruct);
		return renderResult(Global.TRUE, text("保存薪酬结构成功！"));
	}
	
	/**
	 * 删除薪酬结构
	 */
	@RequiresPermissions("hr:hrPaystruct:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HrPaystruct hrPaystruct) {
		hrPaystructService.delete(hrPaystruct);
		return renderResult(Global.TRUE, text("删除薪酬结构成功！"));
	}
	
}