/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.common.service.ServiceException;
import com.jeesite.common.utils.excel.ExcelImport;
import com.jeesite.modules.hr.entity.HrCntinfo;
import com.jeesite.modules.hr.dao.HrCntinfoDao;

/**
 * 劳动合同基本信息Service
 * @author 王志钦
 * @version 2019-04-21
 */
@Service
@Transactional(readOnly=true)
public class HrCntinfoService extends CrudService<HrCntinfoDao, HrCntinfo> {
	
	/**
	 * 获取单条数据
	 * @param hrCntinfo
	 * @return
	 */
	@Override
	public HrCntinfo get(HrCntinfo hrCntinfo) {
		return super.get(hrCntinfo);
	}
	
	/**
	 * 查询分页数据
	 * @param hrCntinfo 查询条件
	 * @param hrCntinfo.page 分页对象
	 * @return
	 */
	@Override
	public Page<HrCntinfo> findPage(HrCntinfo hrCntinfo) {
		return super.findPage(hrCntinfo);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param hrCntinfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(HrCntinfo hrCntinfo) {
		super.save(hrCntinfo);
	}
	
	/**
	 * 更新状态
	 * @param hrCntinfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(HrCntinfo hrCntinfo) {
		super.updateStatus(hrCntinfo);
	}
	
	/**
	 * 删除数据
	 * @param hrCntinfo
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(HrCntinfo hrCntinfo) {
		super.delete(hrCntinfo);
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
			List<HrCntinfo> list = ei.getDataList(HrCntinfo.class);
			for (HrCntinfo info : list) {
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