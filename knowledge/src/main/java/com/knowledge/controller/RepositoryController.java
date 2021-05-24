package com.knowledge.controller;

import com.alibaba.fastjson.JSON;
import com.knowledge.body.*;
import com.knowledge.body.vo.MetadataVo;
import com.knowledge.domain.Response;
import com.knowledge.service.RepositoryService;
import com.knowledge.service.TemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping({"/repository"})
public class RepositoryController {
	
	private Logger logger = LoggerFactory.getLogger(RepositoryController.class);
	
	@Autowired
	private RepositoryService repositoryService;
	
	/**
	 * 保存知识库信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value = { "/save" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response saveRepository(@RequestBody SaveRepositoryReq req){
		logger.info("保存知识库信息-入参-request :{}", JSON.toJSON(req));
		try {
			repositoryService.saveRepository(req);
			return Response.ok("00","添加成功");
		} catch (Exception e) {
			logger.info("saveRepository----error", e);
			return Response.error("99", "系统开小差了,请稍后再试~");
		}
	}

	/**
	 * 查询知识库信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value = { "/query" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response queryRepository(@RequestBody QueryRepositoryReq req){
		logger.info("查询知识库信息-入参-request :{}", JSON.toJSON(req));
		return repositoryService.queryRepository(req);
	}

	/**
	 * 查询知识库统计信息
	 * @param req
	 * @return
	 */
	@RequestMapping(value = { "/main" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response main(@RequestBody QueryRepositoryReq req){
		logger.info("查询知识库信息-入参-request :{}", JSON.toJSON(req));
		return repositoryService.queryMain(req);
	}
	
}
