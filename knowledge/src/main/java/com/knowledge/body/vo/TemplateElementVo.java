package com.knowledge.body.vo;

public class TemplateElementVo {
	
	//元素类型 1:元数据 2:元数据组
	private String type;
	//元数据名称
	private String fieldName;
	//元数据ID
	private Long elementId;
	//是否必录 空/0：否 1：是
	private String isNotNull;
	//是否可添加 空/0：否 1：是
	private String isCanAdd;
	//排序
	private String sort;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public Long getElementId() {
		return elementId;
	}
	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}
	public String getIsNotNull() {
		return isNotNull;
	}
	public void setIsNotNull(String isNotNull) {
		this.isNotNull = isNotNull;
	}
	public String getIsCanAdd() {
		return isCanAdd;
	}
	public void setIsCanAdd(String isCanAdd) {
		this.isCanAdd = isCanAdd;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
