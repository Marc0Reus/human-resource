/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.common.service.ServiceException;
import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.modules.hr.entity.HrDimission;
import com.jeesite.modules.hr.entity.HrPaysinfo;
import com.jeesite.modules.hr.dao.HrDimissionDao;
import com.jeesite.modules.hr.dao.HrPaysinfoDao;

/**
 * 薪酬信息Service
 * @author 王志钦
 * @version 2019-04-21
 */
@Service
@Transactional(readOnly=true)
public class HrPaysinfoService extends CrudService<HrPaysinfoDao, HrPaysinfo> {
	
	@Autowired
	private HrPaysinfoDao hrPaysinfoDao;

	
	/**
	 * 获取单条数据
	 * @param hrPaysinfo
	 * @return
	 */
	@Override
	public HrPaysinfo get(HrPaysinfo hrPaysinfo) {
		return super.get(hrPaysinfo);
	}
	
	/**
	 * 查询分页数据
	 * @param hrPaysinfo 查询条件
	 * @param hrPaysinfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<HrPaysinfo> findPage(HrPaysinfo hrPaysinfo) {
		return super.findPage(hrPaysinfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param hrPaysinfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(HrPaysinfo hrPaysinfo) {
		super.save(hrPaysinfo);
	}
	
	/**
	 * 判断是否有该人员
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public String findEmp(HrPaysinfo hrPaysinfo) {
		String wno=hrPaysinfo.getWno();
		return hrPaysinfoDao.findEmp(wno);
	}
	
	/**
	 * 薪酬分布下限
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public int paysRangeMin(int min) {
		return hrPaysinfoDao.paysRangeMin(min);
	}
	
	/**
	 * 薪酬分布上限
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public int paysRangeMax(int max) {
		return hrPaysinfoDao.paysRangeMax(max);
	}
	
	/**
	 * 薪酬分布区间
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public int paysRange(int num1,int num2) {
		return hrPaysinfoDao.paysRange(num1,num2);
	}
	
	
	/**
	 * 个税总和
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public double taxCount() {
		return hrPaysinfoDao.taxCount();
	}
	/**
	 * 浮动工资总和
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public double floatCount() {
		return hrPaysinfoDao.floatCount();
	}
	/**
	 * 奖金总和
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public double bounsCount() {
		return hrPaysinfoDao.bounsCount();
	}
	/**
	 * 社保个人总和
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public double sempCount() {
		return hrPaysinfoDao.sempCount();
	}
	/**
	 * 社保公司总和
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public double scomCount() {
		return hrPaysinfoDao.scomCount();
	}
	/**
	 * 公积金个人总和
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public double fundempCount() {
		return hrPaysinfoDao.fundempCount();
	}
	/**
	 * 公积金公司总和
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public double fundcomCount() {
		return hrPaysinfoDao.fundcomCount();
	}
	/**
	 * 实发工资总和
	 * @param hrPaysinfo
	 */
	@Transactional(readOnly=false)
	public double payCount() {
		return hrPaysinfoDao.payCount();
	}

	
	
	
	/**
	 * 更新状态
	 * @param hrPaysinfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(HrPaysinfo hrPaysinfo) {
		super.updateStatus(hrPaysinfo);
	}
	
	/**
	 * 删除数据
	 * @param hrPaysinfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(HrPaysinfo hrPaysinfo) {
		super.delete(hrPaysinfo);
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
			List<HrPaysinfo> list = ei.getDataList(HrPaysinfo.class);
			for (HrPaysinfo info : list) {
				if(isUpdateSupport)
				{
				this.save(info);
				successNum++;
				}
				else{
					this.insert(info);
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