/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.entity;

import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 部门基本信息Entity
 * @author 王志钦
 * @version 2019-04-19
 */
@Table(name="hr_dept", alias="a", columns={
		@Column(name="dpno", attrName="dpno", label="部门编号", isPK=true),
		@Column(name="dpnm", attrName="dpnm", label="部门名称", queryType=QueryType.LIKE),
		@Column(name="wno", attrName="wno", label="部门经理"),
	}, orderBy="a.dpno DESC"
)
public class HrDept extends DataEntity<HrDept> {
	
	private static final long serialVersionUID = 1L;
	private String dpno;		// 部门编号
	private String dpnm;		// 部门名称
	private String wno;		// 部门经理
	
	public HrDept() {
		this(null);
	}

	public HrDept(String id){
		super(id);
	}
	
	public String getDpno() {
		return dpno;
	}

	public void setDpno(String dpno) {
		this.dpno = dpno;
	}
	
	@NotBlank(message="部门名称不能为空")
	@Length(min=0, max=30, message="部门名称长度不能超过 30 个字符")
	public String getDpnm() {
		return dpnm;
	}

	public void setDpnm(String dpnm) {
		this.dpnm = dpnm;
	}
	
	@Length(min=0, max=5, message="部门经理长度不能超过 5 个字符")
	public String getWno() {
		return wno;
	}

	public void setWno(String wno) {
		this.wno = wno;
	}
	
}