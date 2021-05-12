package com.knowledge.dao;

import java.util.List;

import com.knowledge.body.SubjectFieldReq;
import com.knowledge.body.vo.SubjectFieldVo;

public  interface SubjectFieldDao {

	/**
	 *  查询元数据管理左边菜单栏下所对应的主题域信息
	 * 
	 * @param category 类别
	 * @return
	 */
	List<SubjectFieldVo> getSubjectInfo(SubjectFieldReq req);
	
}
