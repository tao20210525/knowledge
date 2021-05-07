package com.knowledge.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEMPLATE_ELEMENT") // 模板元素表
public class TemplateElement {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//模板表ID
	@Column(name = "TEMPLATE_ID")
	private Long templateId;
	
	//模板关系表ID
	@Column(name = "TEMPLATE_RELATION_ID")
	private Long templateRelationId;
	
	//父级ID
	@Column(name = "PARENT_ID")
	private Long parentId;
	
	//元素所在层级 1：一级 2：二级
	@Column(name = "LEVEL")
	private String level;
	
	//元素类型：1.元数据 2.元数据组
	@Column(name = "ELEMENT_TYPE")
	private String elementType;
	
	//元数据id
	@Column(name = "ELEMENT_ID")
	private Long elementId;
	
	//字段名称
	@Column(name = "FIELDNAME")
	private String fieldName;
	
	//是否必录 空/0：否 1：是
	@Column(name = "ISNOTNULL")
	private String isNotNull;
	
	//是否可添加 空/0：否 1：是
	@Column(name = "ISCANADD")
	private String isCanAdd;
	
	//排序
	@Column(name = "SORT")
	private String sort;
	
	//元数据组id
	@Column(name = "METADATA_ID")
	private Long metadataId;
	
	//元数据组名称
	@Column(name = "METADATA_NAME")
	private String metadataName;
	
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

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public Long getTemplateRelationId() {
		return templateRelationId;
	}

	public void setTemplateRelationId(Long templateRelationId) {
		this.templateRelationId = templateRelationId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public Long getElementId() {
		return elementId;
	}

	public void setElementId(Long elementId) {
		this.elementId = elementId;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
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

	public Long getMetadataId() {
		return metadataId;
	}

	public void setMetadataId(Long metadataId) {
		this.metadataId = metadataId;
	}

	public String getMetadataName() {
		return metadataName;
	}

	public void setMetadataName(String metadataName) {
		this.metadataName = metadataName;
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
