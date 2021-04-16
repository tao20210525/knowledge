package com.knowledge.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledge.body.AddKnowledgeReq;
import com.knowledge.domain.Response;
import com.knowledge.entity.BasicInfo;
import com.knowledge.repo.AddKnowledgeRepo;
import com.knowledge.service.AddKnowledgeService;

@Service
public class AddKnowledgeServiceImpl  implements AddKnowledgeService{
 
	@Autowired
	 private AddKnowledgeRepo addKnowledgeRepo;

	
	@Transactional
	@Override
	public Response saveKnowledge(AddKnowledgeReq request) {
		
		BasicInfo basicInfo = new BasicInfo();
		basicInfo.setKnowledgeName("知识标题名称");
		basicInfo.setProductName("产品名称");
		basicInfo.setInsuranceCode("险类码");
		basicInfo.setProductReportno("产品报备号");
		basicInfo.setIsSale("是否在售");
		basicInfo.setContent("保险责任详情");
		basicInfo.setFileName("文件名");
		basicInfo.setCreatedTime(new Date());
		
		addKnowledgeRepo.save(basicInfo);
		
		return Response.ok("00","保存成功");
	}

}
