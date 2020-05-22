/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.hr.entity.HrPostpays;
import com.jeesite.modules.hr.dao.HrPostpaysDao;

/**
 * 岗位信息Service
 * @author 王志钦
 * @version 2019-04-16
 */
@Service
@Transactional(readOnly=true)
public class HrPostpaysService extends CrudService<HrPostpaysDao, HrPostpays> {
	
	/**
	 * 获取单条数据
	 * @param hrPostpays
	 * @return
	 */
	@Override
	public HrPostpays get(HrPostpays hrPostpays) {
		return super.get(hrPostpays);
	}
	
	/**
	 * 查询分页数据
	 * @param hrPostpays 查询条件
	 * @param hrPostpays.page 分页对象
	 * @return
	 */
	@Override
	public Page<HrPostpays> findPage(HrPostpays hrPostpays) {
		return super.findPage(hrPostpays);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param hrPostpays
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(HrPostpays hrPostpays) {
		super.save(hrPostpays);
	}
	
	/**
	 * 更新状态
	 * @param hrPostpays
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(HrPostpays hrPostpays) {
		super.updateStatus(hrPostpays);
	}
	
	/**
	 * 删除数据
	 * @param hrPostpays
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(HrPostpays hrPostpays) {
		super.delete(hrPostpays);
	}
	
}