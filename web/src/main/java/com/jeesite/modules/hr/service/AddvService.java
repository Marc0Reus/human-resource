/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeesite.common.entity.Page;
import com.jeesite.common.service.CrudService;
import com.jeesite.modules.hr.entity.Addv;
import com.jeesite.modules.hr.dao.AddvDao;

/**
 * 行政区划Service
 * @author 王志钦
 * @version 2019-04-14
 */
@Service
@Transactional(readOnly=true)
public class AddvService extends CrudService<AddvDao, Addv> {
	
	/**
	 * 获取单条数据
	 * @param addv
	 * @return
	 */
	@Override
	public Addv get(Addv addv) {
		return super.get(addv);
	}
	
	/**
	 * 查询分页数据
	 * @param addv 查询条件
	 * @param addv.page 分页对象
	 * @return
	 */
	@Override
	public Page<Addv> findPage(Addv addv) {
		return super.findPage(addv);
	}
	
	/**
	 * 保存数据（插入或更新）
	 * @param addv
	 */
	@Override
	@Transactional(readOnly=false)
	public void save(Addv addv) {
		super.save(addv);
	}
	
	/**
	 * 更新状态
	 * @param addv
	 */
	@Override
	@Transactional(readOnly=false)
	public void updateStatus(Addv addv) {
		super.updateStatus(addv);
	}
	
	/**
	 * 删除数据
	 * @param addv
	 */
	@Override
	@Transactional(readOnly=false)
	public void delete(Addv addv) {
		super.delete(addv);
	}
	
}