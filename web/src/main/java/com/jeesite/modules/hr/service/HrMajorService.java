/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.hr.entity.HrMajor;
import com.jeesite.modules.hr.dao.HrMajorDao;

/**
 * 学位信息Service
 * @author 王志钦
 * @version 2019-04-16
 */
@Service
@Transactional(readOnly=true)
public class HrMajorService extends CrudService<HrMajorDao, HrMajor> {
	
	/**
	 * 获取单条数据
	 * @param hrMajor
	 * @return
	 */
	@Override
	public HrMajor get(HrMajor hrMajor) {
		return super.get(hrMajor);
	}
	
	/**
	 * 查询分页数据
	 * @param hrMajor 查询条件
	 * @param hrMajor.page 分页对象
	 * @return
	 */
	@Override
	public Page<HrMajor> findPage(HrMajor hrMajor) {
		return super.findPage(hrMajor);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param hrMajor
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(HrMajor hrMajor) {
		super.save(hrMajor);
	}
	
	/**
	 * 更新状态
	 * @param hrMajor
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(HrMajor hrMajor) {
		super.updateStatus(hrMajor);
	}
	
	/**
	 * 删除数据
	 * @param hrMajor
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(HrMajor hrMajor) {
		super.delete(hrMajor);
	}
	
}