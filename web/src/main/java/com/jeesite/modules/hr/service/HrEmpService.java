/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.service;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.common.service.ServiceException;
import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.common.validator.ValidatorUtils;
import com.jeesite.modules.hr.entity.HrEmp;
import com.jeesite.modules.hr.dao.HrCntmngDao;
import com.jeesite.modules.hr.dao.HrEmpDao;
import com.jeesite.modules.sys.entity.EmpUser;
import com.jeesite.modules.sys.entity.User;
import com.jeesite.modules.sys.utils.UserUtils;

/**
 * 人员信息Service
 * @author 王志钦
 * @version 2019-04-18
 */
@Service
@Transactional(readOnly=true)
public class HrEmpService extends CrudService<HrEmpDao, HrEmp> {
	
	@Autowired
	private HrEmpDao hrEmpDao;
	/**
	 * 获取单条数据
	 * @param hrEmp
	 * @return
	 */
	@Override
	public HrEmp get(HrEmp hrEmp) {
		return super.get(hrEmp);
	}
	
	/**
	 * 查询分页数据
	 * @param hrEmp 查询条件
	 * @param hrEmp.page 分页对象
	 * @return
	 */
	@Override
	public Page<HrEmp> findPage(HrEmp hrEmp) {
		return super.findPage(hrEmp);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param hrEmp
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(HrEmp hrEmp) {
		super.save(hrEmp);
	}
	
	/**
	 * 更新状态
	 * @param hrEmp
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(HrEmp hrEmp) {
		super.updateStatus(hrEmp);
	}
	
	/**
	 * 合同状态
	 * @param hrEmp
	 */
	@Transactional(readOnly=false)
	public String findStatus(HrEmp hrEmp) {
		String idcard=hrEmp.getIdcard();
		return hrEmpDao.findStatus(idcard);
	}
	
	/**
	 * 学历统计
	 * @param hrEmp
	 */
	@Transactional(readOnly=false)
	public int countEdu(String edu) {
		return hrEmpDao.countEdu(edu);
	}
	
	/**
	 * 部门统计
	 * @param hrEmp
	 */
	@Transactional(readOnly=false)
	public int countDept(String dpno) {
		return hrEmpDao.countDept(dpno);
	}
	
	/**
	 * 离职原因统计
	 * @param hrEmp
	 */
	@Transactional(readOnly=false)
	public int countDimission(String dtype) {
		return hrEmpDao.countDept(dtype);
	}
	
	/**
	 * 删除数据
	 * @param hrEmp
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(HrEmp hrEmp) {
		super.delete(hrEmp);
	}
	
	/**
	 * 导入用户数据
	 * @param file 导入的用户数据文件
	 * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
	 */
	@Transactional(readOnly=false)
	public String importData(MultipartFile file, Boolean isUpdateSupport) {
		if (file == null){
			throw new ServiceException("请选择导入的数据文件！");
		}
		int successNum = 0; int failureNum = 0;
		StringBuilder successMsg = new StringBuilder();
		StringBuilder failureMsg = new StringBuilder();
		try(ExcelImport ei = new ExcelImport(file, 2, 0)){
			List<HrEmp> list = ei.getDataList(HrEmp.class);
			for (HrEmp emp : list) {
				if(isUpdateSupport)
				{
				this.save(emp);
				successNum++;
				}
				else{
					this.insert(emp);
					successNum++;
				}
			}
		}
		catch (Exception e) {
			failureNum++;
			failureMsg.append(e.getMessage());
			logger.error(e.getMessage(), e);
		}
		if (failureNum > 0) {
			failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
			throw new ServiceException(failureMsg.toString());
		}else{
			successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
		}
		return successMsg.toString();
	}
	
}