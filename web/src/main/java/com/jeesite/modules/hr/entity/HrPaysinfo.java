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

import javax.validation.constraints.NotNull;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;
import com.jeesite.common.utils.excel.annotation.ExcelField;
import com.jeesite.common.utils.excel.annotation.ExcelFields;
import com.jeesite.common.utils.excel.annotation.ExcelField.Align;

/**
 * 薪酬信息Entity
 * @author 王志钦
 * @version 2019-04-21
 */
@Table(name="hr_paysinfo", alias="a", columns={
		@Column(name="paysid", attrName="paysid", label="薪酬信息编号", isPK=true),
		@Column(name="wno", attrName="wno", label="工号"),
		@Column(name="paytm", attrName="paytm", label="发放日期"),
		@Column(name="paybase", attrName="paybase", label="月岗位工资"),
		@Column(name="payfloat", attrName="payfloat", label="月浮动工资"),
		@Column(name="bouns", attrName="bouns", label="月度奖金"),
		@Column(name="semp", attrName="semp", label="社保个人部分"),
		@Column(name="scom", attrName="scom", label="社保单位部分"),
		@Column(name="fundemp", attrName="fundemp", label="公积金个人部分"),
		@Column(name="fundcom", attrName="fundcom", label="公积金单位部分"),
		@Column(name="tax", attrName="tax", label="个税"),
		@Column(name="pay", attrName="pay", label="实发"),
		@Column(name="memo", attrName="memo", label="说明"),
	}, orderBy="a.paysid DESC"
)
public class HrPaysinfo extends DataEntity<HrPaysinfo> {
	
	private static final long serialVersionUID = 1L;
	private String paysid;		// 薪酬信息编号
	private String wno;		// 工号
	private Date paytm;		// 发放日期
	private Double paybase;		// 月岗位工资
	private Double payfloat;		// 月浮动工资
	private Double bouns;		// 月度奖金
	private Double semp;		// 社保个人部分
	private Double scom;		// 社保单位部分
	private Double fundemp;		// 公积金个人部分
	private Double fundcom;		// 公积金单位部分
	private Double tax;		// 个税
	private Double pay;		// 实发
	private String memo;		// 说明
	
	public HrPaysinfo() {
		this(null);
	}

	public HrPaysinfo(String id){
		super(id);
	}
	
	@Valid
	@ExcelFields({
		@ExcelField(title="薪酬信息编号", attrName="paysid", align=Align.CENTER, sort=1),
		@ExcelField(title="工号", attrName="wno", align=Align.CENTER, sort=10),
		@ExcelField(title="发放日期", attrName="paytm", align=Align.CENTER, sort=15,dataFormat="yyyy-MM-dd"),
		@ExcelField(title="月岗位工资", attrName="paybase", align = Align.CENTER, sort=20,dataFormat="0.00"),
		@ExcelField(title="月浮动工资", attrName="payfloat", align=Align.CENTER, sort=30,dataFormat="0.00"),
		@ExcelField(title="月度奖金", attrName="bouns", align=Align.CENTER, sort=40,dataFormat="0.00"),
		@ExcelField(title="社保个人部分", attrName="semp", align=Align.LEFT, sort=50,dataFormat="0.00"),
		@ExcelField(title="社保单位部分", attrName="scom", align=Align.CENTER, sort=70,dataFormat="0.00"),
		@ExcelField(title="公积金个人部分", attrName="fundemp", align=Align.CENTER, sort=80,dataFormat="0.00"),
		@ExcelField(title="公积金单位部分", attrName="fundcom", align=Align.CENTER, sort=95,dataFormat="0.00"),
		@ExcelField(title="个税", attrName="tax", align=Align.LEFT, sort=100,dataFormat="0.00"),
		@ExcelField(title="实发", attrName="pay", align=Align.LEFT, sort=110,dataFormat="0.00"),
		@ExcelField(title="说明", attrName="memo", align=Align.CENTER, sort=120),
	})
	
	public String getPaysid() {
		return paysid;
	}

	public void setPaysid(String paysid) {
		this.paysid = paysid;
	}
	
	@NotBlank(message="工号不能为空")
	@Length(min=0, max=5, message="工号长度不能超过 5 个字符")
	public String getWno() {
		return wno;
	}

	public void setWno(String wno) {
		this.wno = wno;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="发放日期不能为空")
	public Date getPaytm() {
		return paytm;
	}

	public void setPaytm(Date paytm) {
		this.paytm = paytm;
	}
	
	public Double getPaybase() {
		return paybase;
	}

	public void setPaybase(Double paybase) {
		this.paybase = paybase;
	}
	
	public Double getPayfloat() {
		return payfloat;
	}

	public void setPayfloat(Double payfloat) {
		this.payfloat = payfloat;
	}
	
	public Double getBouns() {
		return bouns;
	}

	public void setBouns(Double bouns) {
		this.bouns = bouns;
	}
	
	public Double getSemp() {
		return semp;
	}

	public void setSemp(Double semp) {
		this.semp = semp;
	}
	
	public Double getScom() {
		return scom;
	}

	public void setScom(Double scom) {
		this.scom = scom;
	}
	
	public Double getFundemp() {
		return fundemp;
	}

	public void setFundemp(Double fundemp) {
		this.fundemp = fundemp;
	}
	
	public Double getFundcom() {
		return fundcom;
	}

	public void setFundcom(Double fundcom) {
		this.fundcom = fundcom;
	}
	
	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}
	
	public Double getPay() {
		return pay;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}
	
	@Length(min=0, max=50, message="说明长度不能超过 50 个字符")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}