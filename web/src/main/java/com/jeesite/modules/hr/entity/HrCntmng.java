/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.jeesite.modules.hr.entity;

import java.util.Date;
import com.jeesite.common.mybatis.annotation.JoinTable;
import com.jeesite.common.mybatis.annotation.JoinTable.Type;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.jeesite.common.entity.DataEntity;
import com.jeesite.common.mybatis.annotation.Column;
import com.jeesite.common.mybatis.annotation.Table;
import com.jeesite.common.mybatis.mapper.query.QueryType;

/**
 * 劳动合同管理信息Entity
 * @author 王志钦
 * @version 2019-05-04
 */
@Table(name="hr_cntmng", alias="a", columns={
		@Column(name="idcard", attrName="idcard", label="身份证信息", isPK=true,queryType=QueryType.LIKE),
		@Column(name="tm", attrName="tm", label="管理时间"),
		@Column(name="etm", attrName="etm", label="合同生效时间"),
		@Column(name="duetm", attrName="duetm", label="劳动合同到期时间"),
		@Column(name="mng", attrName="mng", label="合同管理"),
		@Column(name="cntaddr", attrName="cntaddr", label="合同地址"),
	}, orderBy="a.idcard DESC"
)
public class HrCntmng extends DataEntity<HrCntmng> {
	
	private static final long serialVersionUID = 1L;
	private String idcard;		// 身份证信息
	private Date tm;		// 管理时间
	private Date etm;		// 合同生效时间
	private Date duetm;		// 劳动合同到期时间
	private String mng;		// 合同管理
	private String cntaddr;		// 合同地址
	
	public HrCntmng() {
		this(null);
	}

	public HrCntmng(String id){
		super(id);
	}
	
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message="管理时间不能为空")
	public Date getTm() {
		return tm;
	}

	public void setTm(Date tm) {
		this.tm = tm;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getEtm() {
		return etm;
	}

	public void setEtm(Date etm) {
		this.etm = etm;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getDuetm() {
		return duetm;
	}

	public void setDuetm(Date duetm) {
		this.duetm = duetm;
	}
	
	@Length(min=0, max=1, message="合同管理长度不能超过 1 个字符")
	public String getMng() {
		return mng;
	}

	public void setMng(String mng) {
		this.mng = mng;
	}
	
	public String getCntaddr() {
		return cntaddr;
	}

	public void setCntaddr(String cntaddr) {
		this.cntaddr = cntaddr;
	}
	
}