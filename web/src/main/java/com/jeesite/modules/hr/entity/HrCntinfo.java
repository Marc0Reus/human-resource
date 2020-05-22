/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.common.utils.excel.annotation.ExcelField;
import com.jeesite.common.utils.excel.annotation.ExcelFields;
import com.jeesite.common.utils.excel.annotation.ExcelField.Align;

/**
 * 劳动合同基本信息Entity
 * @author 王志钦
 * @version 2019-04-21
 */
@Table(name="hr_cntinfo", alias="a", columns={
		@Column(name="cntid", attrName="cntid", label="合同编号", isPK=true),
		@Column(name="idcard", attrName="idcard", label="身份证号码",queryType=QueryType.LIKE),
		@Column(name="signtm", attrName="signtm", label="合同签订时间"),
		@Column(name="nm", attrName="nm", label="姓名", queryType=QueryType.LIKE),
		@Column(name="sex", attrName="sex", label="性别"),
		@Column(name="addr", attrName="addr", label="住址"),
		@Column(name="wktm", attrName="wktm", label="到岗时间"),
		@Column(name="state", attrName="state", label="合同状态"),
	}, orderBy="a.cntid DESC"
)
public class HrCntinfo extends DataEntity<HrCntinfo> {
	
	private static final long serialVersionUID = 1L;
	private String cntid;		// 合同编号
	private String idcard;		// 身份证号码
	private Date signtm;		// 合同签订时间
	private String nm;		// 姓名
	private String sex;		// 性别
	private String addr;		// 住址
	private Date wktm;		// 到岗时间
	private String state;		// 合同状态
	
	public HrCntinfo() {
		this(null);
	}

	public HrCntinfo(String id){
		super(id);
	}
	@Valid
	@ExcelFields({
		@ExcelField(title="合同编号", attrName="cntid", align=Align.CENTER, sort=10),
		@ExcelField(title="姓名", attrName="nm", align=Align.LEFT, sort=20),
		@ExcelField(title="签约时间", attrName="signtm", align=Align.CENTER, sort=30,dataFormat="yyyy-MM-dd"),
		@ExcelField(title="到岗时间", attrName="wktm", align=Align.CENTER, sort=40,dataFormat="yyyy-MM-dd"),
		@ExcelField(title="身份证号码", attrName="idcard", align = Align.CENTER, sort=60),
		@ExcelField(title="合同状态", attrName="state", align=Align.CENTER, sort=80,dictType="sys_hr_cstatus"),
	}) 

	
	public String getCntid() {
		return cntid;
	}

	public void setCntid(String cntid) {
		this.cntid = cntid;
	}
	
	@NotBlank(message="身份证号码不能为空")
	@Length(min=0, max=18, message="身份证号码长度不能超过 18 个字符")
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getSigntm() {
		return signtm;
	}

	public void setSigntm(Date signtm) {
		this.signtm = signtm;
	}
	
	@NotBlank(message="姓名不能为空")
	@Length(min=0, max=20, message="姓名长度不能超过 20 个字符")
	public String getNm() {
		return nm;
	}

	public void setNm(String nm) {
		this.nm = nm;
	}
	
	@NotBlank(message="性别不能为空")
	@Length(min=0, max=1, message="性别长度不能超过 1 个字符")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=60, message="住址长度不能超过 60 个字符")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getWktm() {
		return wktm;
	}

	public void setWktm(Date wktm) {
		this.wktm = wktm;
	}
	
	@Length(min=0, max=1, message="合同状态长度不能超过 1 个字符")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}