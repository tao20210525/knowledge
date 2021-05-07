package com.knowledge.body;

/**
 * 查询模板信息请求报文
 *
 */
public class QueryTemplateReq {
	
	//类别
	private String typeCode;
	//级别
	private String level;
	
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
	
}
