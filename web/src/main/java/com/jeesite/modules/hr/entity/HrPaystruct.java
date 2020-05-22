/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.entity;

import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 薪酬结构Entity
 * @author 王志钦
 * @version 2019-04-16
 */
@Table(name="hr_paystruct", alias="a", columns={
		@Column(name="pnm", attrName="pnm", label="名称", isPK=true),
		@Column(name="per", attrName="per", label="比重"),
	}, orderBy="a.pnm DESC"
)
public class HrPaystruct extends DataEntity<HrPaystruct> {
	
	private static final long serialVersionUID = 1L;
	private String pnm;		// 名称
	private Double per;		// 比重
	
	public HrPaystruct() {
		this(null);
	}

	public HrPaystruct(String id){
		super(id);
	}
	
	public String getPnm() {
		return pnm;
	}

	public void setPnm(String pnm) {
		this.pnm = pnm;
	}
	
	@NotNull(message="比重不能为空")
	public Double getPer() {
		return per;
	}

	public void setPer(Double per) {
		this.per = per;
	}
	
}