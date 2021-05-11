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
	//接口请求处理类型 1:新增 2:更新
	private String reqType;
	//模板ID 当reqType=1时当前字段不传,当reqType=2时当前字段必传
	private Long templateId;
	
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
	public String getReqType() {
		return reqType;
	}
	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	
}
