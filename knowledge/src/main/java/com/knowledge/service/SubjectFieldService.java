package com.knowledge.service;

import java.util.List;

import com.knowledge.body.SubjectFieldReq;
import com.knowledge.body.vo.SubjectFieldVo;
import com.knowledge.domain.Response;
import com.knowledge.entity.SubjectField;

public abstract interface SubjectFieldService {
	
	/**
	 * 首页-元数据管理-查询某个菜单下的主题域信息
	 * 
	 */
	List<SubjectFieldVo> querySubjectInfo(SubjectFieldReq req);
	
	/**
	 * 新增or修改主题域
	 * @param req
	 * @return
	 */
	Response saveSubjectField(SubjectFieldReq request);
	
	
	/**
	 * 查询主题域信息--针对新增元数据/新增元数据组页面
	 * 
	 */
	List<SubjectField> querySubjectField();
}
