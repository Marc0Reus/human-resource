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
import com.jeesite.modules.hr.entity.Addv;
import com.jeesite.modules.hr.service.AddvService;

/**
 * 行政区划Controller
 * @author 王志钦
 * @version 2019-04-14
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/addv")
public class AddvController extends BaseController {

	@Autowired
	private AddvService addvService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public Addv get(String adcd, boolean isNewRecord) {
		return addvService.get(adcd, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:addv:view")
	@RequestMapping(value = {"list", ""})
	public String list(Addv addv, Model model) {
		model.addAttribute("addv", addv);
		return "modules/hr/addvList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:addv:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<Addv> listData(Addv addv, HttpServletRequest request, HttpServletResponse response) {
		addv.setPage(new Page<>(request, response));
		Page<Addv> page = addvService.findPage(addv);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:addv:view")
	@RequestMapping(value = "form")
	public String form(Addv addv, Model model) {
		model.addAttribute("addv", addv);
		return "modules/hr/addvForm";
	}

	/**
	 * 保存行政区划
	 */
	@RequiresPermissions("hr:addv:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated Addv addv) {
		addvService.save(addv);
		return renderResult(Global.TRUE, text("保存行政区划成功！"));
	}
	
	/**
	 * 删除行政区划
	 */
	@RequiresPermissions("hr:addv:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(Addv addv) {
		addvService.delete(addv);
		return renderResult(Global.TRUE, text("删除行政区划成功！"));
	}
	
}