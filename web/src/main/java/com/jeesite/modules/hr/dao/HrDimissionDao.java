/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.hr.entity.HrDimission;

/**
 * 离职人员信息DAO接口
 * @author 王志钦
 * @version 2019-04-18
 */
@MyBatisDao
public interface HrDimissionDao extends CrudDao<HrDimission> {
	//寻找人员是否存在系统中
	public String findEmp(String wno);
	//删除员工表中的相应数据
	public void deleteEmp(String wno);
	//修改劳动合同状态
	public void updateInfo(String idcard);
	//修改劳动合同管理状态
	public void updateMng(String idcard);
	
}