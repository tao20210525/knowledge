package com.knowledge.dao;

import java.util.List;
import java.util.Map;

public interface KnowledgeDao {

	/**
	 * 查询枚举值
	 * @param elementId 元数据ID
	 * @return
	 */
	List<Map<String, Object>> getEnumData(Long elementId);
	
}
