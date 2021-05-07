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
import com.knowledge.body.SubjectFieldReq;
import com.knowledge.domain.Response;
import com.knowledge.entity.SubjectField;
import com.knowledge.service.SubjectFieldService;

@Controller
@RequestMapping({"/subjectField"})
public class SubjectFieldController {
	
	private Logger logger = LoggerFactory.getLogger(SubjectFieldController.class);
	
	@Autowired
	 private SubjectFieldService subjectFieldService;
	
	/**
	 * 新增or修改主题域
	 * @param req
	 * @return
	 */
	@RequestMapping(value = { "/addsubjectField" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response saveSubjectField(@RequestBody SubjectFieldReq req) {

		try {
			logger.info("新增or修改主题域-入参-request :{}", JSON.toJSON(req));

			return subjectFieldService.saveSubjectField(req);

		} catch (Exception e) {
			logger.info("saveSubjectField----error", e);
			return Response.error("99", "系统开小差了,请稍后再试~");
		}
	}
	
	//查询主题域
	@RequestMapping(value = { "/querySubjectField" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response querySubjectField() {

		try {
			logger.info("查询主题域--start");
      
			List<SubjectField> list = subjectFieldService.querySubjectField();
			
			return Response.ok("00","查询成功",list);

		} catch (Exception e) {
			logger.info("saveSubjectField----error", e);
			return Response.error("99", "系统开小差了,请稍后再试~");
		}
	}
	
}
