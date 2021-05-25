package com.knowledge.dao;

import java.util.List;
import java.util.Map;

public interface KnowledgeInfoDao{
    /**
     * 查询知识库顶部看板
     * @param typeCode
     * @param level
     * @param title 模糊查询
     * @param status
     * @return
     */
    List<Map<String, Object>> getKnowledgeInfo(String typeCode, String level, String title, String status);

    /**
     * 查询知识库集合
     * @param typeCode
     * @param level
     * @param title 模糊查询
     * @param status
     * @return
     */
    List<Map<String, Object>> getKnowledgeInfoList(String typeCode, String level, String title, String status);
}
