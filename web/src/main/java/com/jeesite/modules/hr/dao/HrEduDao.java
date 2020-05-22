/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.hr.entity.HrEdu;

/**
 * 学历信息DAO接口
 * @author 王志钦
 * @version 2019-04-17
 */
@MyBatisDao
public interface HrEduDao extends CrudDao<HrEdu> {
	
}