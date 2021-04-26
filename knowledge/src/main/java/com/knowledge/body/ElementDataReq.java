package com.knowledge.body;

import java.util.List;
import java.util.Map;

//新增元数据的请求入参
public class ElementDataReq {
	
	//元数据id
	private Long id;

	//类别
	private String category;
	
	//主题域名称
	private String subjectName;
	
	//主题域ID
   private Long subjectId;
	
	//字段中文名
	private String name;
	
	//字段名称
	private String fieldName;
	
	//数据类型
	private String dataType;
	
	//输入类型：1.单行文本、2.单选下拉框、3.多选下拉框、4.时间组件、5.文本编辑器、6.附件
	private String inputType;
	
	private List<Map<String, String>> enumList;	//enumList示例: [{"code":"1","value":"是"}]
	
	//级别
	private String level;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<Map<String, String>> getEnumList() {
		return enumList;
	}

	public void setEnumList(List<Map<String, String>> enumList) {
		this.enumList = enumList;
	}

}
