package com.knowledge.controller;

import com.knowledge.body.SaveRepositoryReq;
import com.knowledge.body.vo.KnowledgeConsistVo;
import com.knowledge.domain.Response;
import com.knowledge.service.AuditingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;


@Controller
@RequestMapping("/audit")
public class AuditingController {
    @Autowired
    AuditingService auditingService;

    /**
     * 更新与发布
     */
    @RequestMapping(value = { "/releaseInfo" }, method = { RequestMethod.POST })
    public Response updateAndrelease(@RequestBody SaveRepositoryReq upReq) {

        try {
            //查询原有数据
            List<KnowledgeConsistVo> list = auditingService.getAuditInfoById(upReq.getId());
            //获得新数据（有可能有更改的数据）
            List<KnowledgeConsistVo> newList = upReq.getKnowledgeConsistList();
            auditingService.updateAuditInfo(list,newList);
            //更新状态
            auditingService.updateAuditStatus(upReq.getId());
            return Response.ok("00","发布成功");
        } catch (Exception e) {
            return Response.error("99", "系统开小差了,请稍后再试~");
        }


    }

}
