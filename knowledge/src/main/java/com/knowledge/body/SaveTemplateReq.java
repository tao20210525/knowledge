package com.knowledge.body;

import java.util.List;

import com.knowledge.body.vo.TemplateSubjectVo;

/**
 * 查询模板信息请求报文
 *
 */
public class SaveTemplateReq {
	
	//类别
	private String typeCode;
	//级别
	private String level;
	//模板主题域集合
	private List<TemplateSubjectVo> templateSubjectList;
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public List<TemplateSubjectVo> getTemplateSubjectList() {
		return templateSubjectList;
	}
	public void setTemplateSubjectList(List<TemplateSubjectVo> templateSubjectList) {
		this.templateSubjectList = templateSubjectList;
	}
	
}
