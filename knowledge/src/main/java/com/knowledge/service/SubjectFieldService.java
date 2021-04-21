package com.knowledge.service;

import com.knowledge.body.SubjectFieldReq;
import com.knowledge.domain.Response;

public abstract interface SubjectFieldService {
	
	/**
	 * 添加主题域信息
	 * 
	 */
	Response saveSubjectField(SubjectFieldReq request);
	
	
	
}
