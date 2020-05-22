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
import com.jeesite.modules.hr.entity.HrPost;
import com.jeesite.modules.hr.service.HrPostService;

/**
 * 岗位名称Controller
 * @author 王志钦
 * @version 2019-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/hr/hrPost")
public class HrPostController extends BaseController {

	@Autowired
	private HrPostService hrPostService;
	
	/**
	 * 获取数据
	 */
	@ModelAttribute
	public HrPost get(String cd, boolean isNewRecord) {
		return hrPostService.get(cd, isNewRecord);
	}
	
	/**
	 * 查询列表
	 */
	@RequiresPermissions("hr:hrPost:view")
	@RequestMapping(value = {"list", ""})
	public String list(HrPost hrPost, Model model) {
		model.addAttribute("hrPost", hrPost);
		return "modules/hr/hrPostList";
	}
	
	/**
	 * 查询列表数据
	 */
	@RequiresPermissions("hr:hrPost:view")
	@RequestMapping(value = "listData")
	@ResponseBody
	public Page<HrPost> listData(HrPost hrPost, HttpServletRequest request, HttpServletResponse response) {
		hrPost.setPage(new Page<>(request, response));
		Page<HrPost> page = hrPostService.findPage(hrPost);
		return page;
	}

	/**
	 * 查看编辑表单
	 */
	@RequiresPermissions("hr:hrPost:view")
	@RequestMapping(value = "form")
	public String form(HrPost hrPost, Model model) {
		model.addAttribute("hrPost", hrPost);
		return "modules/hr/hrPostForm";
	}

	/**
	 * 保存岗位名称
	 */
	@RequiresPermissions("hr:hrPost:edit")
	@PostMapping(value = "save")
	@ResponseBody
	public String save(@Validated HrPost hrPost) {
		hrPostService.save(hrPost);
		return renderResult(Global.TRUE, text("保存岗位名称成功！"));
	}
	
	/**
	 * 删除岗位名称
	 */
	@RequiresPermissions("hr:hrPost:edit")
	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(HrPost hrPost) {
		hrPostService.delete(hrPost);
		return renderResult(Global.TRUE, text("删除岗位名称成功！"));
	}
	
}