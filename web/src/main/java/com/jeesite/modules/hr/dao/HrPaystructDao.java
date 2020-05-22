/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.hr.entity.HrPaystruct;

/**
 * 薪酬结构DAO接口
 * @author 王志钦
 * @version 2019-04-16
 */
@MyBatisDao
public interface HrPaystructDao extends CrudDao<HrPaystruct> {
	
}