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
import com.jeesite.common.utils.excel.fieldtype.CompanyType;
import com.jeesite.common.utils.excel.fieldtype.OfficeType;

/**
 * 人员信息Entity
 * @author 王志钦
 * @version 2019-04-18
 */
@Table(name="hr_emp", alias="a", columns={
		@Column(name="wno", attrName="wno", label="工号", isPK=true),
		@Column(name="idcard", attrName="idcard", label="身份证号码"),
		@Column(name="dpno", attrName="dpno", label="部门编号"),
		@Column(name="pno", attrName="pno", label="岗位编号"),
		@Column(name="enm", attrName="enm", label="姓名",queryType=QueryType.LIKE),
		@Column(name="birthday", attrName="birthday", label="生日"),
		@Column(name="adcd", attrName="adcd", label="行政区划"),
		@Column(name="school", attrName="school", label="毕业学校"),
		@Column(name="major", attrName="major", label="所学专业"),
		@Column(name="edu", attrName="edu", label="学历"),
		@Column(name="deg", attrName="deg", label="学位"),
		@Column(name="addr", attrName="addr", label="家庭住址"),
	}, orderBy="a.wno DESC"
)
public class HrEmp extends DataEntity<HrEmp> {
	
	private static final long serialVersionUID = 1L;
	private String wno;		// 工号
	private String idcard;		// 身份证号码
	private String dpno;		// 部门编号
	private String pno;		// 岗位编号
	private String enm;		// 姓名
	private Date birthday;		// 生日
	private String adcd;		// 行政区划
	private String school;		// 毕业学校
	private String major;		// 所学专业
	private String edu;		// 学历
	private String deg;		// 学位
	private String addr;		// 家庭住址
	
	public HrEmp() {
		this(null);
	}

	public HrEmp(String id){
		super(id);
	}
	
	
	@Valid
	@ExcelFields({
		@ExcelField(title="工号", attrName="wno", align=Align.CENTER, sort=10),
		@ExcelField(title="身份证号码", attrName="idcard", align = Align.CENTER, sort=20),
		@ExcelField(title="部门编号", attrName="dpno", align=Align.CENTER, sort=30),
		@ExcelField(title="岗位编号", attrName="pno", align=Align.CENTER, sort=40),
		@ExcelField(title="姓名", attrName="enm", align=Align.LEFT, sort=50),
		@ExcelField(title="生日", attrName="birthday", align=Align.CENTER, sort=60,dataFormat="yyyy-MM-dd"),
		@ExcelField(title="毕业学校", attrName="school", align=Align.CENTER, sort=70),
		@ExcelField(title="所学专业", attrName="major", align=Align.CENTER, sort=80,dictType="sys_hr_major"),
		@ExcelField(title="学历", attrName="edu", align=Align.CENTER, sort=95,dictType="sys_hr_edu"),
		@ExcelField(title="学位", attrName="deg", align=Align.LEFT, sort=100,dictType="sys_hr_deg"),
		@ExcelField(title="家庭住址", attrName="addr", align=Align.CENTER, sort=110),
	})
	public String getWno() {
		return wno;
	}

	public void setWno(String wno) {
		this.wno = wno;
	}
	
	@NotBlank(message="身份证号码不能为空")
	@Length(min=0, max=18, message="身份证号码长度不能超过 18 个字符")
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	@NotBlank(message="部门编号不能为空")
	@Length(min=0, max=2, message="部门编号长度不能超过 2 个字符")
	public String getDpno() {
		return dpno;
	}

	public void setDpno(String dpno) {
		this.dpno = dpno;
	}
	
	@NotBlank(message="岗位编号不能为空")
	@Length(min=0, max=3, message="岗位编号长度不能超过 3 个字符")
	public String getPno() {
		return pno;
	}

	public void setPno(String pno) {
		this.pno = pno;
	}
	
	@Length(min=0, max=20, message="姓名长度不能超过 20 个字符")
	public String getEnm() {
		return enm;
	}

	public void setEnm(String enm) {
		this.enm = enm;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="生日不能为空")
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=6, message="行政区划长度不能超过 6 个字符")
	public String getAdcd() {
		return adcd;
	}

	public void setAdcd(String adcd) {
		this.adcd = adcd;
	}
	
	@Length(min=0, max=50, message="毕业学校长度不能超过 50 个字符")
	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	@Length(min=0, max=50, message="所学专业长度不能超过 50 个字符")
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	@Length(min=0, max=2, message="学历长度不能超过 2 个字符")
	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}
	
	@Length(min=0, max=1, message="学位长度不能超过 1 个字符")
	public String getDeg() {
		return deg;
	}

	public void setDeg(String deg) {
		this.deg = deg;
	}
	
	@Length(min=0, max=80, message="家庭住址长度不能超过 80 个字符")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}