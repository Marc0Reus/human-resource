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
import com.jeesite.modules.hr.entity.HrCntmng;
import com.jeesite.modules.hr.dao.HrCntmngDao;
import com.jeesite.modules.file.utils.FileUploadUtils;

/**
 * 劳动合同管理信息Service
 * @author 王志钦
 * @version 2019-05-04
 */
@Service
@Transactional(readOnly=true)
public class HrCntmngService extends CrudService<HrCntmngDao, HrCntmng> {
	
	@Autowired
	private HrCntmngDao hrCntmngDao;
	/**
	 * 获取单条数据
	 * @param hrCntmng
	 * @return
	 */
	@Override
	public HrCntmng get(HrCntmng hrCntmng) {
		return super.get(hrCntmng);
	}
	
	/**
	 * 查询分页数据
	 * @param hrCntmng 查询条件
	 * @param hrCntmng.page 分页对象
	 * @return
	 */
	@Override
	public Page<HrCntmng> findPage(HrCntmng hrCntmng) {
		return super.findPage(hrCntmng);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param hrCntmng
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(HrCntmng hrCntmng) {
		super.save(hrCntmng);
		// 保存上传附件
		FileUploadUtils.saveFileUpload(hrCntmng.getId(), "hrCntmng_file");
	}
	
	/**
	 * 更新状态
	 * @param hrCntmng
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(HrCntmng hrCntmng) {
		super.updateStatus(hrCntmng);
	}
	
	/**
	 * 更新合同状态
	 * @param hrCntmng
	 */
	@Transactional(readOnly=false)
	public void updateCstatus(HrCntmng hrCntmng) {
		String idcard=hrCntmng.getIdcard();
		switch(hrCntmng.getMng()){
		case "1":
			hrCntmngDao.updateStatus0(idcard);
			break;
		case "2":
			hrCntmngDao.updateStatus0(idcard);
			break;
		case "3":
			hrCntmngDao.updateStatus1(idcard);
			break;
		case "4":
			hrCntmngDao.updateStatus2(idcard);
			break;			
		
		}
		
	}
	/**
	 * 删除数据
	 * @param hrCntmng
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(HrCntmng hrCntmng) {
		super.delete(hrCntmng);
	}
	
}