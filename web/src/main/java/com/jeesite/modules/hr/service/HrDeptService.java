/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.hr.entity.HrDept;
import com.jeesite.modules.hr.dao.HrDeptDao;

/**
 * 部门基本信息Service
 * @author 王志钦
 * @version 2019-04-19
 */
@Service
@Transactional(readOnly=true)
public class HrDeptService extends CrudService<HrDeptDao, HrDept> {
	
	/**
	 * 获取单条数据
	 * @param hrDept
	 * @return
	 */
	@Override
	public HrDept get(HrDept hrDept) {
		return super.get(hrDept);
	}
	
	/**
	 * 查询分页数据
	 * @param hrDept 查询条件
	 * @param hrDept.page 分页对象
	 * @return
	 */
	@Override
	public Page<HrDept> findPage(HrDept hrDept) {
		return super.findPage(hrDept);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param hrDept
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(HrDept hrDept) {
		super.save(hrDept);
	}
	
	/**
	 * 更新状态
	 * @param hrDept
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(HrDept hrDept) {
		super.updateStatus(hrDept);
	}
	
	/**
	 * 删除数据
	 * @param hrDept
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(HrDept hrDept) {
		super.delete(hrDept);
	}
	
}