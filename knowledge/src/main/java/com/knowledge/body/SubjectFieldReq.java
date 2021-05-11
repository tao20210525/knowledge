package com.knowledge.body;

import java.util.List;
import java.util.Map;

//新增主题域的请求入参
public class SubjectFieldReq {
	
	//主题域id
	private Long id;

	// 主题域名称
	private String subjectName;

	// 知识标题
	private String knowledgeTitle;

	//类别
	private String category;
	
	//元数据id/类型-数据区分：1.元数据、2.元数据组/排序
	private List<Map<String, String>> enumList;	//enumList示例: [{"elementId":"1","type":"1","sort":"1"}]

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<Map<String, String>> getEnumList() {
		return enumList;
	}

	public void setEnumList(List<Map<String, String>> enumList) {
		this.enumList = enumList;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
}
