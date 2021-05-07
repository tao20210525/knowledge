package com.knowledge.body.vo;

import java.util.List;

public class TemplateSubjectVo {
	
	//主题域名称
	private String name;
	//主题域ID
	private Long typeId;
	//排序
	private String sort;
	//子集合
	private List<Object> childList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Object> getChildList() {
		return childList;
	}
	public void setChildList(List<Object> childList) {
		this.childList = childList;
	}
	public Long getTypeId() {
		return typeId;
	}
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	
}
