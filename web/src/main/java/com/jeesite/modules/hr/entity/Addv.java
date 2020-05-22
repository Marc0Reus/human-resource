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
 * 行政区划Entity
 * @author 王志钦
 * @version 2019-04-14
 */
@Table(name="addv", alias="a", columns={
		@Column(name="adcd", attrName="adcd", label="行政区划代码", isPK=true),
		@Column(name="adnm", attrName="adnm", label="行政区划名称"),
	}, orderBy="a.adcd DESC"
)
public class Addv extends DataEntity<Addv> {
	
	private static final long serialVersionUID = 1L;
	private String adcd;		// 行政区划代码
	private String adnm;		// 行政区划名称
	
	public Addv() {
		this(null);
	}

	public Addv(String id){
		super(id);
	}
	
	public String getAdcd() {
		return adcd;
	}

	public void setAdcd(String adcd) {
		this.adcd = adcd;
	}
	
	@Length(min=0, max=50, message="行政区划名称长度不能超过 50 个字符")
	public String getAdnm() {
		return adnm;
	}

	public void setAdnm(String adnm) {
		this.adnm = adnm;
	}
	
}