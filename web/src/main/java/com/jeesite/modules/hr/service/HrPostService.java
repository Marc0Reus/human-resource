/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.hr.entity.HrPost;
import com.jeesite.modules.hr.dao.HrPostDao;

/**
 * 岗位名称Service
 * @author 王志钦
 * @version 2019-04-22
 */
@Service
@Transactional(readOnly=true)
public class HrPostService extends CrudService<HrPostDao, HrPost> {
	
	/**
	 * 获取单条数据
	 * @param hrPost
	 * @return
	 */
	@Override
	public HrPost get(HrPost hrPost) {
		return super.get(hrPost);
	}
	
	/**
	 * 查询分页数据
	 * @param hrPost 查询条件
	 * @param hrPost.page 分页对象
	 * @return
	 */
	@Override
	public Page<HrPost> findPage(HrPost hrPost) {
		return super.findPage(hrPost);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param hrPost
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(HrPost hrPost) {
		super.save(hrPost);
	}
	
	/**
	 * 更新状态
	 * @param hrPost
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(HrPost hrPost) {
		super.updateStatus(hrPost);
	}
	
	/**
	 * 删除数据
	 * @param hrPost
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(HrPost hrPost) {
		super.delete(hrPost);
	}
	
}