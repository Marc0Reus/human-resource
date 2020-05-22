/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.entity;

import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 离职人员信息Entity
 * @author 王志钦
 * @version 2019-04-18
 */
@Table(name="hr_dimission", alias="a", columns={
		@Column(name="wno", attrName="wno", label="工号", isPK=true),
		@Column(name="dtm", attrName="dtm", label="离职时间"),
		@Column(name="dr", attrName="dr", label="离职原因"),
		@Column(name="dtype", attrName="dtype", label="离职类型"),
	}, orderBy="a.wno DESC"
)
public class HrDimission extends DataEntity<HrDimission> {
	
	private static final long serialVersionUID = 1L;
	private String wno;		// 工号
	private Date dtm;		// 离职时间
	private String dr;		// 离职原因
	private String dtype;		// 离职类型
	
	public HrDimission() {
		this(null);
	}

	public HrDimission(String id){
		super(id);
	}
	
	public String getWno() {
		return wno;
	}

	public void setWno(String wno) {
		this.wno = wno;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDtm() {
		return dtm;
	}

	public void setDtm(Date dtm) {
		this.dtm = dtm;
	}
	
	@Length(min=0, max=255, message="离职原因长度不能超过 255 个字符")
	public String getDr() {
		return dr;
	}

	public void setDr(String dr) {
		this.dr = dr;
	}
	
	@Length(min=0, max=20, message="离职类型长度不能超过 20 个字符")
	public String getDtype() {
		return dtype;
	}

	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	
}