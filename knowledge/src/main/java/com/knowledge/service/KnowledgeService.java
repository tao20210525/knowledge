package com.knowledge.service;


import com.knowledge.body.QueryEnumReq;
import com.knowledge.domain.Response;

public abstract interface KnowledgeService {
	
	/**
	 * 查询枚举值
	 * @param req
	 * @return
	 */
	Response queryEnum(QueryEnumReq req);
	
}
