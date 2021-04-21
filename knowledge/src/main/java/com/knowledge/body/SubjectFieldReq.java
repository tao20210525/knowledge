package com.knowledge.body;

//新增主题域的请求入参
public class SubjectFieldReq {

	// 类别
	private String category;

	// 主题域名称
	private String subjectName;

	// 知识标题
	private String knowledgeTitle;

	// 元数据ID
	private Long elementId;

	// 区分数据来源：1.元数据、2.元数据组
	private String type;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getKnowledgeTitle() {
		return knowledgeTitle;
	}

	public void setKnowledgeTitle(String knowledgeTitle) {
		this.knowledgeTitle = knowledgeTitle;
	}

	public Long getElementId() {
		return elementId;
	}

	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
