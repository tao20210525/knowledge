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

import com.knowledge.body.MetadataManagementReq;
import com.knowledge.body.vo.MetadataManagementVo;
import com.knowledge.domain.Response;
import com.knowledge.service.HomePageService;

@Controller
@RequestMapping({"/homePage"})
public class HomePageController {
	
	private Logger logger = LoggerFactory.getLogger(HomePageController.class);
	
	@Autowired
	 private HomePageService homePageService;
	
	
	
	/**
	 * 首页-元数据管理
	 * 查询元数据/元数据组数据 
	 * 页面列表信息
	 * @return
	 */
	@RequestMapping(value = { "/metadataManagement" }, method = { RequestMethod.POST })
	@ResponseBody
	public Response queryMetadataManagement(@RequestBody MetadataManagementReq req) {

		try {
			logger.info("首页-元数据管理--start");
      
			List<MetadataManagementVo> list = homePageService.queryData(req);
			
			return Response.ok("00","查询成功",list);

		} catch (Exception e) {
			logger.info("saveSubjectField----error", e);
			return Response.error("99", "系统开小差了,请稍后再试~");
		}
	}

	
}
