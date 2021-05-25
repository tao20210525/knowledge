package com.knowledge.service;

import com.knowledge.Exception.KnowledgeException;
import com.knowledge.body.QueryRepositoryReq;
import com.knowledge.body.SaveRepositoryReq;
import com.knowledge.domain.Response;

import java.util.List;
import java.util.Map;

public interface RepositoryService {
    /**
     * 查询知识库
     * @param req
     * @return
     */
    Response queryRepository(QueryRepositoryReq req);

    /**
     * 保存知识库
     * @param req
     * @return
     */
    Response saveRepository(SaveRepositoryReq req) throws KnowledgeException;

    /**
     * 查询知识库顶部看板区域统计信息
     * @param req
     * @return
     */
    Response queryMain(QueryRepositoryReq req);

    /**
     * 导出知识库
     * @param req
     * @return
     */
    Response exportKnowledge(QueryRepositoryReq req);
}
