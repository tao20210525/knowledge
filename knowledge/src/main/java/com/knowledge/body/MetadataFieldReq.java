package com.knowledge.body;

//新增元数据组的请求入参
public class MetadataFieldReq {

	//类别
	private String category;
	
	//主题域ID
	private Long subjectId;
	
	//元数据组名称
	private String name;
	
	//元数据ID
	private Long elementId;
	
	//级别
	private String level;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getElementId() {
		return elementId;
	}

	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
