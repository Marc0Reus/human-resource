/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.hr.entity.HrPaystruct;
import com.jeesite.modules.hr.dao.HrPaystructDao;

/**
 * 薪酬结构Service
 * @author 王志钦
 * @version 2019-04-16
 */
@Service
@Transactional(readOnly=true)
public class HrPaystructService extends CrudService<HrPaystructDao, HrPaystruct> {
	
	/**
	 * 获取单条数据
	 * @param hrPaystruct
	 * @return
	 */
	@Override
	public HrPaystruct get(HrPaystruct hrPaystruct) {
		return super.get(hrPaystruct);
	}
	
	/**
	 * 查询分页数据
	 * @param hrPaystruct 查询条件
	 * @param hrPaystruct.page 分页对象
	 * @return
	 */
	@Override
	public Page<HrPaystruct> findPage(HrPaystruct hrPaystruct) {
		return super.findPage(hrPaystruct);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param hrPaystruct
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(HrPaystruct hrPaystruct) {
		super.save(hrPaystruct);
	}
	
	/**
	 * 更新状态
	 * @param hrPaystruct
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(HrPaystruct hrPaystruct) {
		super.updateStatus(hrPaystruct);
	}
	
	/**
	 * 删除数据
	 * @param hrPaystruct
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(HrPaystruct hrPaystruct) {
		super.delete(hrPaystruct);
	}
	
}