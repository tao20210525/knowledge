package com.knowledge.service;

import com.knowledge.body.vo.KnowledgeConsistVo;

import java.util.List;


public interface AuditingService {

    /**
     * 更新及发布
     */
    List<KnowledgeConsistVo> getAuditInfoById(Long id);

    void updateAuditInfo(List<KnowledgeConsistVo> list, List<KnowledgeConsistVo> newList);

    void updateAuditStatus(Long id);
}
