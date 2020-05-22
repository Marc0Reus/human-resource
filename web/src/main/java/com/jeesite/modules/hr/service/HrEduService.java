/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.hr.entity.HrEdu;
import com.jeesite.modules.hr.dao.HrEduDao;

/**
 * 学历信息Service
 * @author 王志钦
 * @version 2019-04-17
 */
@Service
@Transactional(readOnly=true)
public class HrEduService extends CrudService<HrEduDao, HrEdu> {
	
	/**
	 * 获取单条数据
	 * @param hrEdu
	 * @return
	 */
	@Override
	public HrEdu get(HrEdu hrEdu) {
		return super.get(hrEdu);
	}
	
	/**
	 * 查询分页数据
	 * @param hrEdu 查询条件
	 * @param hrEdu.page 分页对象
	 * @return
	 */
	@Override
	public Page<HrEdu> findPage(HrEdu hrEdu) {
		return super.findPage(hrEdu);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param hrEdu
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(HrEdu hrEdu) {
		super.save(hrEdu);
	}
	
	/**
	 * 更新状态
	 * @param hrEdu
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(HrEdu hrEdu) {
		super.updateStatus(hrEdu);
	}
	
	/**
	 * 删除数据
	 * @param hrEdu
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(HrEdu hrEdu) {
		super.delete(hrEdu);
	}
	
}