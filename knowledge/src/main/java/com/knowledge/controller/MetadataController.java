package com.knowledge.controller;

import java.util.List;

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
import com.knowledge.entity.ElementData;
import com.knowledge.service.MetadataService;

@Controller
@RequestMapping({"/metaData"})
public class MetadataController {
	
	private Logger logger = LoggerFactory.getLogger(MetadataController.class);
	
	@Autowired
	 private MetadataService metadataService;
	
	@ResponseBody
	@RequestMapping({ "/online" })
	public String callTest() {
		return "测试成功";
	}
	
	//查询元数据
	@RequestMapping(value = { "/queryMetadata" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response queryMetadata() {

		try {
			logger.info("查询元数据--start");
      
			List<ElementData> list = metadataService.queryMetadata();
			
			return Response.ok("00","查询成功",list);

		} catch (Exception e) {
			logger.info("saveSubjectField----error", e);
			return Response.error("99", "系统开小差了,请稍后再试~");
		}
	}

	//新增or修改元数据
	@RequestMapping(value = { "/addData" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response saveMetadata(@RequestBody ElementDataReq req) {

		try {
			logger.info("新增元数据-入参-request :{}", JSON.toJSON(req));

			return metadataService.saveMetadata(req);

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

			return metadataService.saveMetadataField(req);

		} catch (Exception e) {
			logger.info("saveMetadataField----error", e);
			return Response.error("99", "系统开小差了,请稍后再试~");
		}
	}
	
}
