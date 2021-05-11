package com.knowledge.body;

/**
 * 查询模板信息请求报文
 *
 */
public class QueryTemplateCompositionReq {
	
	//类别 不选择时传空
	private String typeCode;
	//元素类型 1:元数据 2:元数据组 3:主题域
	private String elementType;
	//模糊搜索关键字
	private String keyword;
	
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getElementType() {
		return elementType;
	}
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
}
