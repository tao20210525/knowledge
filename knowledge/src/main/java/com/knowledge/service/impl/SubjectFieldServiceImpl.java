package com.knowledge.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledge.body.SubjectFieldReq;
import com.knowledge.domain.Response;
import com.knowledge.entity.SubjectField;
import com.knowledge.entity.SubjectRelation;
import com.knowledge.repo.SubjectFieldRepo;
import com.knowledge.repo.SubjectRelationRepo;
import com.knowledge.service.SubjectFieldService;

@Service
public class SubjectFieldServiceImpl  implements SubjectFieldService{
 
	@Autowired
	 private SubjectRelationRepo subjectRelationRepo;
	
	@Autowired
	 private SubjectFieldRepo subjectFieldRepo;
	
	
	/**
	 * 添加主题域信息
	 */
	@Transactional
	@Override
	public Response saveSubjectField(SubjectFieldReq req) {
		
		SubjectField subject = new SubjectField();
		
		//类别
		subject.setCategory(req.getCategory());
		//主题域名称
		subject.setSubjectName(req.getSubjectName());
		//知识标题
		subject.setKnowledgeTitle(req.getKnowledgeTitle());
		//创建人
		subject.setCreateBy("admin");
		//创建时间
		subject.setCreatedTime(new Date());
		
		subjectFieldRepo.save(subject);
		
		if(null != subject) {
			//TODO 元数据/主题域关系表
			SubjectRelation subjectRelation= new SubjectRelation();
			//主题域ID
			subjectRelation.setSubjectId(subject.getId());
			 //元数据ID
			subjectRelation.setElementId(req.getElementId()); 
			//区分数据来源：1.元数据、2.元数据组
			subjectRelation.setType(req.getType());
			//创建人
			subjectRelation.setCreateBy("admin");
			//创建时间
			subjectRelation.setCreatedTime(new Date());
			
			subjectRelationRepo.save(subjectRelation);
		}
		return Response.ok("00","新增主题域成功");
	}

	
	/**
	 * 查询主题域信息
	 * 
	 */
	@Override
	public List<SubjectField> querySubjectField() {
		
		List<SubjectField> list = subjectFieldRepo.getSubjectField();
		if(null == list || list.isEmpty()) {
			return null;
		}
		return list;
	}
	
}
