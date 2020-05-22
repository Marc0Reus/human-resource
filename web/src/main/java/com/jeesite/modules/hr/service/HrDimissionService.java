/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.hr.entity.HrDimission;
import com.jeesite.modules.hr.entity.HrEmp;
import com.jeesite.modules.hr.dao.HrDimissionDao;

/**
 * 离职人员信息Service
 * @author 王志钦
 * @version 2019-04-18
 */
@Service
@Transactional(readOnly=true)
public class HrDimissionService extends CrudService<HrDimissionDao, HrDimission> {
	
	@Autowired
	private HrDimissionDao hrDimissionDao;
	
	
	/**
	 * 获取单条数据
	 * @param hrDimission
	 * @return
	 */
	@Override
	public HrDimission get(HrDimission hrDimission) {
		return super.get(hrDimission);
	}
	
	/**
	 * 查询分页数据
	 * @param hrDimission 查询条件
	 * @param hrDimission.page 分页对象
	 * @return
	 */
	@Override
	public Page<HrDimission> findPage(HrDimission hrDimission) {
		return super.findPage(hrDimission);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param hrDimission
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(HrDimission hrDimission) {
		super.save(hrDimission);
	}
	
	/**
	 * 更新状态
	 * @param hrDimission
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(HrDimission hrDimission) {
		super.updateStatus(hrDimission);
	}
	
	/**
	 * 更新劳动合同状态
	 * @param idcard
	 */
	@Transactional(readOnly=false)
	public void updateCntstatus(String idcard) {
		hrDimissionDao.updateInfo(idcard);
		hrDimissionDao.updateMng(idcard);
		}
	
	/**
	 * 判断是否有该人员
	 * @param hrDimission
	 */
	@Transactional(readOnly=false)
	public String findEmp(HrDimission hrDimission) {
		String wno=hrDimission.getWno();
		return hrDimissionDao.findEmp(wno);
	}
	
	/**
	 * 删除hr_emp中的员工数据
	 * @param hrDimission
	 */
	@Transactional(readOnly=false)
	public void deleteEmp(HrDimission hrDimission) {
		String wno=hrDimission.getWno();
		hrDimissionDao.deleteEmp(wno);
	}
	
	/**
	 * 删除数据
	 * @param hrDimission
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(HrDimission hrDimission) {
		super.delete(hrDimission);
	}
	
}