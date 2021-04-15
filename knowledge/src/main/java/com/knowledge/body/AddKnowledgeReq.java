package com.knowledge.body;

import java.sql.Date;

public class AddKnowledgeReq {

	//知识标题名称
	private String knowledgeName;
	
	//产品名称
	private String productName;
	
	//险类码
	private String insuranceCode;
	
	//产品报备号
	private String productReportno;
	
	//是否在售 0:是 1：否
	private String isSale;
	
	//保险责任详情
	private String content;
	
	//上传文件的文件名
	private String fileName;
	
	//创建时间
	private Date createdTime;

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
