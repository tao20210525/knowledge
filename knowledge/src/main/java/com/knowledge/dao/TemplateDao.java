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
	/**
	 * 根据模板ID删除模板主题域数据
	 * @param templateId 模板ID
	 * @return
	 */
	int delTemplateRelationship(Long templateId);
	/**
	 * 根据模板ID删除模板元素数据
	 * @param templateId 模板ID
	 * @return
	 */
	int delTemplateElement(Long templateId);
	/**
	 * 根据类别查询元数据列表(支持模糊搜索)
	 * @param typeCode 类别Code
	 * @param keyword 搜索关键字
	 * @return
	 */
	List<Map<String, Object>> getElementByTypeCode(String typeCode, String keyword);
	/**
	 * 根据类别查询元数据组列表(支持模糊搜索)
	 * @param typeCode 类别Code
	 * @param keyword 搜索关键字
	 * @return
	 */
	List<Map<String, Object>> getElementGroupByTypeCode(String typeCode, String keyword);
	/**
	 * 根据类别查询主题域列表(支持模糊搜索)(此方法只返回主题域子集合里第一层数据)
	 * @param typeCode 类别Code
	 * @param keyword 搜索关键字
	 * @return
	 */
	List<Map<String, Object>> getSubjectByTypeCode(String typeCode, String keyword);
	/**
	 * 根据元数据组ID集合查询元数据列表
	 * @param metadataIdList 元数据组ID集合
	 * @return
	 */
	List<Map<String, Object>> getElementByMetadataIdList(List<Long> metadataIdList);
}
