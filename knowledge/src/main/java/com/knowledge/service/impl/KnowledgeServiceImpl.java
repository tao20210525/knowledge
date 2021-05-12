package com.knowledge.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.knowledge.body.QueryEnumReq;
import com.knowledge.dao.KnowledgeDao;
import com.knowledge.domain.Response;
import com.knowledge.service.KnowledgeService;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

	private Logger logger = LoggerFactory.getLogger(KnowledgeServiceImpl.class);
	
	@Autowired
	private KnowledgeDao knowledgeDao;
	
	@Override
	public Response queryEnum(QueryEnumReq req) {
		logger.info("查询枚举值Service开始");
		Response response = Response.ok("00", "查询成功");
		try {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			Map<String, Object> enumDictionary = new HashMap<String, Object>();
			List<Map<String, Object>> enumList = new ArrayList<Map<String, Object>>();
			
			List<Map<String, Object>> enumDataList = knowledgeDao.getEnumData(req.getElementId());
			for(Map<String, Object> enumData : enumDataList){
				enumDictionary.put(enumData.get("code").toString(), enumData.get("value"));
				enumList.add(enumData);
			}
			
			resultMap.put("enumList", enumList);
			resultMap.put("enumDictionary", enumDictionary);
			response.setContent(resultMap);
		} catch (Exception e) {
			this.logger.info("查询枚举值Service异常："+ e);
			response.setCode("99");
			response.setMessage("查询枚举值异常");
		}
		return response;
	}

}
