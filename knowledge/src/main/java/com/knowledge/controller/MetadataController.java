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
import com.knowledge.body.ElementDataReq;
import com.knowledge.body.MetadataFieldReq;
import com.knowledge.domain.Response;
import com.knowledge.service.AddMetadataService;

@Controller
@RequestMapping({"/metaData"})
public class MetadataController {
	
	private Logger logger = LoggerFactory.getLogger(MetadataController.class);
	
	@Autowired
	 private AddMetadataService addMetadataService;
	
	@ResponseBody
	@RequestMapping({ "/online" })
	public String callTest() {
		return "测试成功";
	}

	//新增or修改元数据
	@RequestMapping(value = { "/addData" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response saveMetadata(@RequestBody ElementDataReq req) {

		try {
			logger.info("新增元数据-入参-request :{}", JSON.toJSON(req));

			return addMetadataService.saveMetadata(req);

		} catch (Exception e) {
			logger.info("saveMetadata----error", e);
			return Response.error("99", "系统开小差了,请稍后再试~");
		}
	}
	
	//新增元数据组
	@RequestMapping(value = { "/addMetadataField" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response saveMetadataField(@RequestBody MetadataFieldReq req) {

		try {
			logger.info("新增元数据组-入参-request :{}", JSON.toJSON(req));

			return addMetadataService.saveMetadataField(req);

		} catch (Exception e) {
			logger.info("saveMetadataField----error", e);
			return Response.error("99", "系统开小差了,请稍后再试~");
		}
	}
	
}
