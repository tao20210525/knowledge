package com.knowledge.service;


import com.knowledge.body.QueryTemplateReq;
import com.knowledge.body.SaveTemplateReq;
import com.knowledge.domain.Response;

public abstract interface TemplateService {
	
	/**
	 * 查询模板信息
	 * @param req
	 * @return
	 */
	Response queryTemplate(QueryTemplateReq req);
	/**
	 * 保存模板信息
	 * @param req
	 * @return
	 */
	Response saveTemplate(SaveTemplateReq req);
	
}
