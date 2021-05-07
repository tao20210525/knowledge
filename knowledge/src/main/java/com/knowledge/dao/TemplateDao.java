package com.knowledge.dao;

import java.util.List;
import java.util.Map;

public  interface TemplateDao {

	/**
	 * 查询模板基本信息
	 * @param typeCode 类别
	 * @param level 级别
	 * @return
	 */
	Map<String, Object> getTemplate(String typeCode, String level);
	/**
	 * 查询模板元素集合
	 * @param templateId 模板ID
	 * @return
	 */
	List<Map<String, Object>> getTemplateElement(Long templateId);

}
