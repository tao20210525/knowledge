package com.knowledge.service;


import com.knowledge.body.QueryTemplateCompositionReq;
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
	/**
	 * 查询模板字段
	 * @param req
	 * @return
	 */
	Response queryTemplateField(QueryTemplateReq req);
	
	/**
	 * 修改模板元素表里元数据名称
	 */
	
	/**
	 * 查询可组成模板的元素列表
	 * @param req
	 * @return
	 */
	Response queryTemplateComposition(QueryTemplateCompositionReq req);
}
