package com.knowledge.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "KNOWLEDGE_CONSIST") // 知识信息表
public class KnowledgeConsist {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//知识表ID
	@Column(name = "KNOWLEDGE_ID")
	private Long knowledgeId;

	//主题域ID
	@Column(name = "SUBJECT_ID")
	private Long subjectId;

	//主题域名称
	@Column(name = "SUBJECT_NAME")
	private String subjectName;
	
	//字段名称
	@Column(name = "FIELD_NAME")
	private String fieldName;
	
	//元数据ID
	@Column(name = "ELEMENT_ID")
	private Long elementId;
	
	//输入类型
	@Column(name = "INPUT_TYPE")
	private String inputType;
	
	//内容
	@Column(name = "CONTENT")
	private String content;
	
	//排序
	@Column(name = "SORT")
	private String sort;
	
	// 创建人
	@Column(name = "CREATE_BY")
	private String createBy;

	// 创建时间
	@Column(name = "CREATED_TIME")
	private Date createdTime;
	
	// 创建人
	@Column(name = "UPDATE_BY")
	private String updateBy;

	// 创建时间
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	
	// 是否删除：空/0：否 1：是
	@Column(name = "IS_DELETE")
	private String isDelete;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getKnowledgeId() {
		return knowledgeId;
	}

	public void setKnowledgeId(Long knowledgeId) {
		this.knowledgeId = knowledgeId;
	}

	public Long getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Long subjectId) {
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

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

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
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

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

}
