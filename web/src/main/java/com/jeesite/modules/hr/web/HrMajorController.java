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
import com.jeesite.modules.hr.entity.HrMajor;
import com.jeesite.modules.hr.service.HrMajorService;

/**
 * 学位信息Controller
 * @author 王志钦
 * @version 2019-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrMajor")
public class HrMajorController extends BaseController {

	@Autowired
	private HrMajorService hrMajorService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public HrMajor get(String cd, boolean isNewRecord) {
		return hrMajorService.get(cd, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:hrMajor:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrMajor hrMajor, Model model) {
		model.addAttribute("hrMajor", hrMajor);
		return "modules/hr/hrMajorList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:hrMajor:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<HrMajor> listData(HrMajor hrMajor, HttpServletRequest request, HttpServletResponse response) {
		hrMajor.setPage(new Page<>(request, response));
		Page<HrMajor> page = hrMajorService.findPage(hrMajor);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:hrMajor:view")
	@RequestMapping(value = "form")
	public String form(HrMajor hrMajor, Model model) {
		model.addAttribute("hrMajor", hrMajor);
		return "modules/hr/hrMajorForm";
	}

	/**
	 * 保存学位信息
	 */
	@RequiresPermissions("hr:hrMajor:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated HrMajor hrMajor) {
		hrMajorService.save(hrMajor);
		return renderResult(Global.TRUE, text("保存学位信息成功！"));
	}
	
	/**
	 * 删除学位信息
	 */
	@RequiresPermissions("hr:hrMajor:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HrMajor hrMajor) {
		hrMajorService.delete(hrMajor);
		return renderResult(Global.TRUE, text("删除学位信息成功！"));
	}
	
}