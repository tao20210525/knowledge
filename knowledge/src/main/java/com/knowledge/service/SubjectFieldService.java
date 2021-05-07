package com.knowledge.service;

import java.util.List;

import com.knowledge.body.SubjectFieldReq;
import com.knowledge.domain.Response;
import com.knowledge.entity.SubjectField;

public abstract interface SubjectFieldService {
	
	/**
	 * 新增or修改主题域
	 * @param req
	 * @return
	 */
	Response saveSubjectField(SubjectFieldReq request);
	
	
	/**
	 * 查询主题域信息
	 * 
	 */
	List<SubjectField> querySubjectField();
}
