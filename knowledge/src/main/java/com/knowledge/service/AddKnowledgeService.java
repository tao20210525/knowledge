package com.knowledge.service;

import com.knowledge.body.AddKnowledgeReq;
import com.knowledge.domain.Response;

public interface AddKnowledgeService {
	
	/**
	 * 添加主题域--基本信息
	 * 
	 */
	Response saveKnowledge(AddKnowledgeReq request);
	

}
