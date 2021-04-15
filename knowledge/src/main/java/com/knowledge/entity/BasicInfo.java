package com.knowledge.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BASICINFO")
public class BasicInfo {
	
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "seq_basic_info",sequenceName = "seq_basic_info")
	@GeneratedValue(generator = "seq_basic_info")
	private Long id;

	@Column(name = "KNOWLEDGE_NAME")
	private String knowledgeName;
	
	@Column(name = "PRODUCT_NAME")
	private String productName;
	
	@Column(name = "INSURANCE_CODE")
	private String insuranceCode;
	
	@Column(name = "PRODUCT_REPORTNO")
	private String productReportno;
	
	@Column(name = "IS_SALE")
	private String isSale;
	
	@Column(name = "CONTENT")
	private String content;
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "CREATED_TIME")
	private Date createdTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKnowledgeName() {
		return knowledgeName;
	}

	public void setKnowledgeName(String knowledgeName) {
		this.knowledgeName = knowledgeName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getInsuranceCode() {
		return insuranceCode;
	}

	public void setInsuranceCode(String insuranceCode) {
		this.insuranceCode = insuranceCode;
	}

	public String getProductReportno() {
		return productReportno;
	}

	public void setProductReportno(String productReportno) {
		this.productReportno = productReportno;
	}

	public String getIsSale() {
		return isSale;
	}

	public void setIsSale(String isSale) {
		this.isSale = isSale;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	
	
}
