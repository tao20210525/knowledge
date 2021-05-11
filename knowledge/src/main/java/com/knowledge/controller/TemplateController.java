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
import com.knowledge.body.QueryTemplateCompositionReq;
import com.knowledge.body.QueryTemplateReq;
import com.knowledge.body.SaveTemplateReq;
import com.knowledge.domain.Response;
import com.knowledge.service.TemplateService;

@Controller
@RequestMapping({"/template"})
public class TemplateController {
	
	private Logger logger = LoggerFactory.getLogger(TemplateController.class);
	
	@Autowired
	private TemplateService templateService;
	
	/**
	 * 查询模板信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value = { "/queryTemplate" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response queryTemplate(@RequestBody QueryTemplateReq req){
		logger.info("查询模板信息-入参-request :{}", JSON.toJSON(req));
		return templateService.queryTemplate(req);
	}
	
	/**
	 * 保存模板信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value = { "/saveTemplate" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response saveTemplate(@RequestBody SaveTemplateReq req){
		logger.info("保存模板信息-入参-request :{}", JSON.toJSON(req));
		return templateService.saveTemplate(req);
	}
	
	/**
	 * 查询模板字段
	 * @param req
	 * @return
	 */
	@RequestMapping(value = { "/queryTemplateField" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response queryTemplateField(@RequestBody QueryTemplateReq req){
		logger.info("查询模板字段-入参-request :{}", JSON.toJSON(req));
		return templateService.queryTemplateField(req);
	}
	
	/**
	 * 查询可组成模板的元素列表
	 * @param req
	 * @return
	 */
	@RequestMapping(value = { "/queryTemplateComposition" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response queryTemplateComposition(@RequestBody QueryTemplateCompositionReq req){
		logger.info("查询可组成模板的元素列表-入参-request :{}", JSON.toJSON(req));
		return templateService.queryTemplateComposition(req);
	}
	
	
}
