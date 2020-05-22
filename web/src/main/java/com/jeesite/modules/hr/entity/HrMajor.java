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
 * 学位信息Entity
 * @author 王志钦
 * @version 2019-04-16
 */
@Table(name="hr_major", alias="a", columns={
		@Column(name="cd", attrName="cd", label="学位代码", isPK=true),
		@Column(name="nm", attrName="nm", label="学位名称"),
	}, orderBy="a.cd DESC"
)
public class HrMajor extends DataEntity<HrMajor> {
	
	private static final long serialVersionUID = 1L;
	private String cd;		// 学位代码
	private String nm;		// 学位名称
	
	public HrMajor() {
		this(null);
	}

	public HrMajor(String id){
		super(id);
	}
	
	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}
	
	@Length(min=0, max=40, message="学位名称长度不能超过 40 个字符")
	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}
	
}