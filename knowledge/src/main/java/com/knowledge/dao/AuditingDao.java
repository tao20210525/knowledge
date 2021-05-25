package com.knowledge.dao;

import com.knowledge.body.vo.KnowledgeConsistVo;

import java.util.List;

public interface AuditingDao {


    List<KnowledgeConsistVo> getInfo(Long id);

    void updateInfo(List<KnowledgeConsistVo> list, List<KnowledgeConsistVo> newList);

    void updateStatus(Long id);

}
