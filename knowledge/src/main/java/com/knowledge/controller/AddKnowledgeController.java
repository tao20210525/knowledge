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
import com.knowledge.body.AddKnowledgeReq;
import com.knowledge.domain.Response;
import com.knowledge.service.AddKnowledgeService;

//新增知识
@Controller
@RequestMapping({"/addKnowledge"})
public class AddKnowledgeController {
	
	private Logger logger = LoggerFactory.getLogger(AddKnowledgeController.class);
	
	@Autowired
	 private AddKnowledgeService addKnowledgeService;
	
	@ResponseBody
	@RequestMapping({ "/online" })
	public String callTest() {
		return "测试成功";
	}
	
	
	@RequestMapping(value = { "/save" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response saveKnowledge(@RequestBody AddKnowledgeReq req) {

		try {
			logger.info("新增知识-入参-request :{}", JSON.toJSON(req));

			return addKnowledgeService.saveKnowledge(req);

		} catch (Exception e) {
			logger.info("saveKnowledge----error", e);
			return Response.error("99", "系统开小差了,请稍后再试~");
		}
	}
	
	
	
	
	

}
