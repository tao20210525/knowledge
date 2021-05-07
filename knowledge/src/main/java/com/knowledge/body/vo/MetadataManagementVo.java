package com.knowledge.body.vo;

import java.util.Date;

//首页-元数据管理
public class MetadataManagementVo {
	
	//元数据ID
	private String elementId;
	
	//元数据名称
	private String elementName;
	
	//元数据组ID
	private String fieldId;
	
	//元数据组名称
	private String metdataName;
	
	//标识 0:元数据 1:元数据组
	private String identification;
	
	//字段中文名
	private String name;
		
	//字段名称
	private String fieldName;
		
	//数据类型
	private String dataType;
		
	//输入类型：1.单行文本、2.单选下拉框、3.多选下拉框、4.时间组件、5.文本编辑器、6.附件
	private String inputType;
	
	// 更新人
	private String updateBy;

	// 更新时间
	private Date updateTime;
	
	public String getFieldId() {
		return fieldId;
	}

	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public String getMetdataName() {
		return metdataName;
	}

	public void setMetdataName(String metdataName) {
		this.metdataName = metdataName;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
