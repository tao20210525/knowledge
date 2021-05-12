package com.knowledge.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.knowledge.body.QueryEnumReq;
import com.knowledge.domain.Response;
import com.knowledge.service.KnowledgeService;

@Controller
@RequestMapping({"/knowledge"})
public class KnowledgeController {

	private Logger logger = LoggerFactory.getLogger(KnowledgeController.class);
	
	@Autowired
	private KnowledgeService knowledgeService;
	
	/**
	 * 查询枚举值
	 * @param req
	 * @return
	 */
	@RequestMapping(value = { "/queryEnum" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response queryEnum(@RequestBody QueryEnumReq req){
		logger.info("查询枚举值-入参-request :{}", JSON.toJSON(req));
		return knowledgeService.queryEnum(req);
	}
	
}
