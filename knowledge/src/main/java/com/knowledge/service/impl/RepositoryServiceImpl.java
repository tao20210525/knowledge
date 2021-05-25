package com.knowledge.service.impl;

import com.knowledge.Exception.KnowledgeException;
import com.knowledge.body.QueryRepositoryReq;
import com.knowledge.body.SaveRepositoryReq;
import com.knowledge.body.vo.KnowledgeConsistVo;
import com.knowledge.dao.KnowledgeInfoDao;
import com.knowledge.domain.Response;
import com.knowledge.entity.KnowledgeConsist;
import com.knowledge.entity.KnowledgeInfo;
import com.knowledge.repo.KnowledgeConsistRepo;
import com.knowledge.repo.KnowledgeInfoRepo;
import com.knowledge.service.RepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class RepositoryServiceImpl implements RepositoryService {

    private Logger logger = LoggerFactory.getLogger(RepositoryServiceImpl.class);

    @Autowired
    private KnowledgeInfoDao knowledgeInfoDao;

    @Autowired
    private KnowledgeInfoRepo knowledgeInfoRepo;

    @Autowired
    private KnowledgeConsistRepo knowledgeConsistRepo;

    @Override
    public Response queryRepository(QueryRepositoryReq req) {
        logger.info("查询知识库信息Service开始");
        Response response = Response.ok("00", "查询成功");
        try {
            List<Map<String, Object>> resultMap = new ArrayList<>();
            resultMap = knowledgeInfoDao.getKnowledgeInfoList(req.getTypeCode(), req.getLevel(), req.getTitle(), req.getStatus());
            response.setContent(resultMap);
        } catch (Exception e) {
            this.logger.info("查询知识库列表Service异常：" + e);
            response.setCode("99");
            response.setMessage("查询知识库列表异常");
        }
        return response;
    }

    /**
     * 保存知识库
     */
    @Override
    @Transactional(rollbackOn = KnowledgeException.class)
    public Response saveRepository(SaveRepositoryReq req) throws KnowledgeException {
        try {
            KnowledgeInfo knowledgeInfo = new KnowledgeInfo();
            KnowledgeInfo info;

            if (null != req.getId()) {  //修改knowledgeInfo
                knowledgeInfo = knowledgeInfoRepo.getOne(req.getId());
            }else{
                knowledgeInfo.setCreateBy("admin");
                knowledgeInfo.setCreatedTime(new Date());
            }
            knowledgeInfo.setTypeCode(req.getTypeCode());
            knowledgeInfo.setLevel(req.getLevel());
            knowledgeInfo.setStatus(req.getStatus());
            knowledgeInfo.setTitle(req.getTitle());
            knowledgeInfo.setUpdateBy("admin");
            knowledgeInfo.setUpdateTime(new Date());
            knowledgeInfo.setIsDelete(req.getIsDelete());
            info = knowledgeInfoRepo.save(knowledgeInfo);
            List<KnowledgeConsist> knowledgeConsistList = new ArrayList<KnowledgeConsist>();
            List<KnowledgeConsistVo> knowledgeConsistVosList = req.getKnowledgeConsistList();
            if (knowledgeConsistVosList != null && knowledgeConsistVosList.size() > 0) {
                if (null != req.getId()) {  //修改KnowledgeConsist
                    KnowledgeConsist knowledgeConsist = new KnowledgeConsist();
                    knowledgeConsist.setKnowledgeId(req.getId());
                    knowledgeConsist.setIsDelete("0");
                    Example<KnowledgeConsist> example = Example.of(knowledgeConsist);
                    List<KnowledgeConsist> all = knowledgeConsistRepo.findAll(example);
                    all.stream().forEach(e -> e.setIsDelete("1"));
                    knowledgeConsistRepo.saveAll(all);
                }
                for (KnowledgeConsistVo knowledgeConsistVo : knowledgeConsistVosList) {
                    KnowledgeConsist knowledgeConsist = new KnowledgeConsist();
                    knowledgeConsist.setKnowledgeId(info.getId());
                    knowledgeConsist.setSubjectId(req.getSubjectId());
                    knowledgeConsist.setSubjectName(req.getSubjectName());
                    knowledgeConsist.setFieldName(knowledgeConsistVo.getFieldName());
                    knowledgeConsist.setInputType(knowledgeConsistVo.getInputType());
                    knowledgeConsist.setContent(knowledgeConsistVo.getContent());
                    knowledgeConsist.setElementId(knowledgeConsistVo.getElementId());
                    knowledgeConsist.setSort(knowledgeConsistVo.getSort());
                    knowledgeConsist.setCreateBy("admin");
                    knowledgeConsist.setCreatedTime(new Date());
                    knowledgeConsist.setUpdateBy("admin");
                    knowledgeConsist.setUpdateTime(new Date());
                    knowledgeConsist.setIsDelete(knowledgeInfo.getIsDelete());
                    knowledgeConsistList.add(knowledgeConsist);
                }
                knowledgeConsistRepo.saveAll(knowledgeConsistList);
            }
        } catch (Exception e) {
            throw new KnowledgeException("操作失败");
        }
        return Response.ok("00", "操作成功");
    }

    @Override
    public Response queryMain(QueryRepositoryReq req) {
        logger.info("查询顶部看板区域Service开始");
        Response response = Response.ok("00", "查询成功");
        try {
            List<Map<String, Object>> resultMap = new ArrayList<>();
            resultMap = knowledgeInfoDao.getKnowledgeInfo(req.getTypeCode(), req.getLevel(), req.getTitle(), req.getStatus());
            response.setContent(resultMap);
        } catch (Exception e) {
            this.logger.info("查询顶部看板区域Service异常：" + e);
            response.setCode("99");
            response.setMessage("查询顶部看板区域异常");
        }
        return response;
    }

    @Override
    public Response exportKnowledge(QueryRepositoryReq req) {
        return null;
    }
}
