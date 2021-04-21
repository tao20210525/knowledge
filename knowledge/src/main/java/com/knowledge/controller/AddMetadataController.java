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
import com.knowledge.body.AddMetadataReq;
import com.knowledge.domain.Response;
import com.knowledge.service.AddMetadataService;

//新增元数据
@Controller
@RequestMapping({"/addMetadata"})
public class AddMetadataController {
	
	private Logger logger = LoggerFactory.getLogger(AddMetadataController.class);
	
	@Autowired
	 private AddMetadataService addMetadataService;
	
	@ResponseBody
	@RequestMapping({ "/online" })
	public String callTest() {
		return "测试成功";
	}

	@RequestMapping(value = { "/addData" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response saveBasiInfo(@RequestBody AddMetadataReq req) {

		try {
			logger.info("新增元数据-入参-request :{}", JSON.toJSON(req));

			return addMetadataService.saveMetadata(req);

		} catch (Exception e) {
			logger.info("saveKnowledge----error", e);
			return Response.error("99", "系统开小差了,请稍后再试~");
		}
	}
	
	
}
