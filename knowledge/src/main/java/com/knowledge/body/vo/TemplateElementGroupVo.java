package com.knowledge.body.vo;

import java.util.List;

public class TemplateElementGroupVo {
	
	//元数据组名称
	private String metadataName;
	//元数据组ID
	private Long metadataId;
	//元数据集合
	private List<TemplateElementVo> elementList;
	//排序
	private String sort;
	
	public List<TemplateElementVo> getElementList() {
		return elementList;
	}
	public void setElementList(List<TemplateElementVo> elementList) {
		this.elementList = elementList;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getMetadataName() {
		return metadataName;
	}
	public void setMetadataName(String metadataName) {
		this.metadataName = metadataName;
	}
	public Long getMetadataId() {
		return metadataId;
	}
	public void setMetadataId(Long metadataId) {
		this.metadataId = metadataId;
	}
	
}
