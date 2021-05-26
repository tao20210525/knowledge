package com.knowledge.service.impl;

import com.knowledge.body.vo.KnowledgeConsistVo;
import com.knowledge.dao.AuditingDao;
import com.knowledge.service.AuditingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditingServiceImpl implements AuditingService {

    @Autowired
    AuditingDao auditingDao;


    @Override
    public List<KnowledgeConsistVo> getAuditInfoById(Long id) {
        List<KnowledgeConsistVo> list = auditingDao.getInfo(id);
        return list;
    }

    @Override
    public void updateAuditInfo(List<KnowledgeConsistVo> list, List<KnowledgeConsistVo> newList) {
        auditingDao.updateInfo(list,newList);
    }

    @Override
    public void updateAuditStatus(Long id) {
        auditingDao.updateStatus(id);
    }
    
}
