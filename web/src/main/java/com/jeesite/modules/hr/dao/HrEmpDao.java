/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.hr.entity.HrEmp;

/**
 * 人员信息DAO接口
 * @author 王志钦
 * @version 2019-04-18
 */
@MyBatisDao
public interface HrEmpDao extends CrudDao<HrEmp> {
	public String findStatus(String idcard);
	public int countEdu(String edu);
	public int countDept(String dpno);
	public int countDimisssion(String dtype);
	
}