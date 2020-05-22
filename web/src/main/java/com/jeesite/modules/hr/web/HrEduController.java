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
import com.jeesite.modules.hr.entity.HrEdu;
import com.jeesite.modules.hr.service.HrEduService;

/**
 * 学历信息Controller
 * @author 王志钦
 * @version 2019-04-17
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrEdu")
public class HrEduController extends BaseController {

	@Autowired
	private HrEduService hrEduService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public HrEdu get(String cd, boolean isNewRecord) {
		return hrEduService.get(cd, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:hrEdu:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrEdu hrEdu, Model model) {
		model.addAttribute("hrEdu", hrEdu);
		return "modules/hr/hrEduList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:hrEdu:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<HrEdu> listData(HrEdu hrEdu, HttpServletRequest request, HttpServletResponse response) {
		hrEdu.setPage(new Page<>(request, response));
		Page<HrEdu> page = hrEduService.findPage(hrEdu);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:hrEdu:view")
	@RequestMapping(value = "form")
	public String form(HrEdu hrEdu, Model model) {
		model.addAttribute("hrEdu", hrEdu);
		return "modules/hr/hrEduForm";
	}

	/**
	 * 保存学历信息
	 */
	@RequiresPermissions("hr:hrEdu:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated HrEdu hrEdu) {
		hrEduService.save(hrEdu);
		return renderResult(Global.TRUE, text("保存学历信息成功！"));
	}
	
	/**
	 * 删除学历信息
	 */
	@RequiresPermissions("hr:hrEdu:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HrEdu hrEdu) {
		hrEduService.delete(hrEdu);
		return renderResult(Global.TRUE, text("删除学历信息成功！"));
	}
	
}