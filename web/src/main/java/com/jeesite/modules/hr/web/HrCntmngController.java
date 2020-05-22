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
import com.jeesite.modules.hr.entity.HrCntmng;
import com.jeesite.modules.hr.service.HrCntmngService;

/**
 * 劳动合同管理信息Controller
 * @author 王志钦
 * @version 2019-05-04
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrCntmng")
public class HrCntmngController extends BaseController {

	@Autowired
	private HrCntmngService hrCntmngService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public HrCntmng get(String idcard, boolean isNewRecord) {
		return hrCntmngService.get(idcard, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:hrCntmng:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrCntmng hrCntmng, Model model) {
		model.addAttribute("hrCntmng", hrCntmng);
		return "modules/hr/hrCntmngList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:hrCntmng:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<HrCntmng> listData(HrCntmng hrCntmng, HttpServletRequest request, HttpServletResponse response) {
		hrCntmng.setPage(new Page<>(request, response));
		Page<HrCntmng> page = hrCntmngService.findPage(hrCntmng);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:hrCntmng:view")
	@RequestMapping(value = "form")
	public String form(HrCntmng hrCntmng, Model model) {
		model.addAttribute("hrCntmng", hrCntmng);
		return "modules/hr/hrCntmngForm";
	}

	/**
	 * 保存劳动合同管理信息
	 */
	@RequiresPermissions("hr:hrCntmng:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated HrCntmng hrCntmng) {
		hrCntmngService.save(hrCntmng);
		hrCntmngService.updateCstatus(hrCntmng);
		return renderResult(Global.TRUE, text("保存劳动合同管理信息成功！"));
	}
	
	/**
	 * 删除劳动合同管理信息
	 */
	@RequiresPermissions("hr:hrCntmng:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HrCntmng hrCntmng) {
		hrCntmngService.delete(hrCntmng);
		return renderResult(Global.TRUE, text("删除劳动合同管理信息成功！"));
	}
	
}