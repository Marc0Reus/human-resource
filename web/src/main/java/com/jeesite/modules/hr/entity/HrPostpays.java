/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.entity;

import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 岗位信息Entity
 * @author 王志钦
 * @version 2019-04-16
 */
@Table(name="hr_postpays", alias="a", columns={
		@Column(name="pno", attrName="pno", label="岗位编号", isPK=true),
		@Column(name="paybase", attrName="paybase", label="月岗位工资"),
		@Column(name="memo", attrName="memo", label="说明"),
	}, orderBy="a.pno DESC"
)
public class HrPostpays extends DataEntity<HrPostpays> {
	
	private static final long serialVersionUID = 1L;
	private String pno;		// 岗位编号
	private Double paybase;		// 月岗位工资
	private String memo;		// 说明
	
	public HrPostpays() {
		this(null);
	}

	public HrPostpays(String id){
		super(id);
	}
	
	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}
	
	public Double getPaybase() {
		return paybase;
	}

	public void setPaybase(Double paybase) {
		this.paybase = paybase;
	}
	
	@Length(min=0, max=50, message="说明长度不能超过 50 个字符")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}