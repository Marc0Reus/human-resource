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
import com.jeesite.modules.hr.entity.HrPostpays;
import com.jeesite.modules.hr.service.HrPostpaysService;

/**
 * 岗位信息Controller
 * @author 王志钦
 * @version 2019-04-16
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrPostpays")
public class HrPostpaysController extends BaseController {

	@Autowired
	private HrPostpaysService hrPostpaysService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public HrPostpays get(String pno, boolean isNewRecord) {
		return hrPostpaysService.get(pno, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:hrPostpays:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrPostpays hrPostpays, Model model) {
		model.addAttribute("hrPostpays", hrPostpays);
		return "modules/hr/hrPostpaysList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:hrPostpays:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<HrPostpays> listData(HrPostpays hrPostpays, HttpServletRequest request, HttpServletResponse response) {
		hrPostpays.setPage(new Page<>(request, response));
		Page<HrPostpays> page = hrPostpaysService.findPage(hrPostpays);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:hrPostpays:view")
	@RequestMapping(value = "form")
	public String form(HrPostpays hrPostpays, Model model) {
		model.addAttribute("hrPostpays", hrPostpays);
		return "modules/hr/hrPostpaysForm";
	}

	/**
	 * 保存岗位信息
	 */
	@RequiresPermissions("hr:hrPostpays:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated HrPostpays hrPostpays) {
		hrPostpaysService.save(hrPostpays);
		return renderResult(Global.TRUE, text("保存岗位信息成功！"));
	}
	
	/**
	 * 删除岗位信息
	 */
	@RequiresPermissions("hr:hrPostpays:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HrPostpays hrPostpays) {
		hrPostpaysService.delete(hrPostpays);
		return renderResult(Global.TRUE, text("删除岗位信息成功！"));
	}
	
}