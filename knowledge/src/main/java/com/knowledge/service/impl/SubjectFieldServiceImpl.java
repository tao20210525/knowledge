package com.knowledge.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
	 * 新增or修改主题域
	 * @param req
	 * @return
	 */
	@Transactional
	@Override
	public Response saveSubjectField(SubjectFieldReq req) {
		
		SubjectField subject = new SubjectField();
		
		//存入主题域信息
		List<SubjectField> subjectList = new ArrayList<SubjectField>();
		
		//存入主题域关系信息
		List<SubjectRelation> subjecRelationtList = new ArrayList<SubjectRelation>();
		
		for(Map<String, String> enumMap : req.getEnumList()) {
			//[{"elementId":"1","type":"1","sort":"1"}]
			Long elementId =  Long.parseLong(enumMap.get("elementId"));
			String type = enumMap.get("type");
			String sort = enumMap.get("sort");
		
	   //编辑情况下，页面点保存后删除数据，重新保存
			if(null != req.getId()) {
				//这里根据主题域id删除信息
				subjectFieldRepo.deleteById(req.getId());
			}
	     
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
		
		subjectList.add(subject);
		
		subjectFieldRepo.saveAll(subjectList);
		
		if(null != subject) {
			//元数据/主题域关系表
			SubjectRelation subjectRelation= new SubjectRelation();
			
			   //编辑情况下，页面点保存后删除数据，重新保存
				if(null != req.getId()) {
					//这里根据主题域id-外键删除信息
					subjectRelationRepo.deleteBySubjectId(req.getId());
				}
			
			//主题域ID
			subjectRelation.setSubjectId(subject.getId());
			 //元数据ID
			subjectRelation.setElementId(elementId); 
			//区分数据来源：1.元数据、2.元数据组
			subjectRelation.setType(type);
			//排序
			subjectRelation.setSort(sort);
			//创建人
			subjectRelation.setCreateBy("admin");
			//创建时间
			subjectRelation.setCreatedTime(new Date());
			
			subjecRelationtList.add(subjectRelation);
			
			subjectRelationRepo.saveAll(subjecRelationtList);
		}
		
		}
		
		return Response.ok("00","新增/修改主题域成功");
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
