package com.knowledge.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ELEMENT_FIELD") // 元数据与元数据组关系表
public class ElementField {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// 元数据组ID
	@Column(name = "METADATA_ID")
	private Long metadataId;

	// 元数据ID
	@Column(name = "ELEMENT_ID")
	private Long elementId;

	// 级别
	@Column(name = "LEVEL")
	private String level;
	
	// 创建人
	@Column(name = "CREATE_BY")
	private String createBy;

	// 创建时间
	@Column(name = "CREATED_TIME")
	private Date createdTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMetadataId() {
		return metadataId;
	}

	public void setMetadataId(Long metadataId) {
		this.metadataId = metadataId;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Long getElementId() {
		return elementId;
	}

	public void setElementId(Long elementId) {
		this.elementId = elementId;
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

}
