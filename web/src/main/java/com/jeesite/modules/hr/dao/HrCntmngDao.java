/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.dao;

import com.jeesite.common.dao.CrudDao;
import com.jeesite.common.mybatis.annotation.MyBatisDao;
import com.jeesite.modules.hr.entity.HrCntmng;

/**
 * 劳动合同管理信息DAO接口
 * @author 王志钦
 * @version 2019-05-04
 */
@MyBatisDao
public interface HrCntmngDao extends CrudDao<HrCntmng> {
	//修改合同基本信息状态为正常
	public void updateStatus0(String idcard);
	//修改合同基本信息状态为终止
	public void updateStatus1(String idcard);
	//修改合同基本信息状态为解除
	public void updateStatus2(String idcard);
}