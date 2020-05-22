/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.dao;

import org.apache.ibatis.annotations.Param;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.hr.entity.HrPaysinfo;

/**
 * 薪酬信息DAO接口
 * @author 王志钦
 * @version 2019-04-21
 */
@MyBatisDao
public interface HrPaysinfoDao extends CrudDao<HrPaysinfo> {
	//寻找人员是否存在系统中
	public String findEmp(String wno);
	public int paysRangeMin(Integer min);
	public int paysRangeMax(Integer max);
	public int paysRange(@Param(value="num1")Integer num1,@Param(value="num2")Integer num2);
	public double taxCount();
	public double floatCount();
	public double bounsCount();
	public double sempCount();
	public double scomCount();
	public double fundempCount();
	public double fundcomCount();
	public double payCount();
	
}