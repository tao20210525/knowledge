package com.knowledge.controller;

import com.alibaba.fastjson.JSON;
import com.knowledge.Exception.KnowledgeException;
import com.knowledge.body.*;
import com.knowledge.body.vo.MetadataVo;
import com.knowledge.constant.ExportConstant;
import com.knowledge.domain.Response;
import com.knowledge.service.RepositoryService;
import com.knowledge.service.TemplateService;
import com.knowledge.util.ExcelExportUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/repository"})
public class RepositoryController {

    private Logger logger = LoggerFactory.getLogger(RepositoryController.class);

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 保存知识库信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/save"}, method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation("保存知识库")
    public Response saveRepository(@RequestBody SaveRepositoryReq req) {
        logger.info("保存知识库信息-入参-request :{}", JSON.toJSON(req));
        try {
            repositoryService.saveRepository(req);
            return Response.ok("00", "添加成功");
        } catch (Exception e) {
            logger.info("saveRepository----error", e);
            return Response.error("99", "系统开小差了,请稍后再试~");
        }
    }

    /**
     * 查询知识库信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/query"}, method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation("查询知识库列表")
    public Response queryRepository(@RequestBody QueryRepositoryReq req) {
        logger.info("查询知识库信息-入参-request :{}", JSON.toJSON(req));
        return repositoryService.queryRepository(req);
    }

    /**
     * 查询知识库统计信息
     *
     * @param req
     * @return
     */
    @RequestMapping(value = {"/main"}, method = {RequestMethod.POST})
    @ResponseBody
    @ApiOperation("查询知识库顶部看板")
    public Response main(@RequestBody QueryRepositoryReq req) {
        logger.info("查询知识库信息-入参-request :{}", JSON.toJSON(req));
        return repositoryService.queryMain(req);
    }

    /**
     * 查询知识库导出
     *
     * @return
     */
    @RequestMapping(value = {"/export"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation("导出excel")
    public void export(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String[]> parameterMap = request.getParameterMap();
            if (parameterMap.size() == 0) {
                response.setStatus(201);
                response.getWriter().println("导出失败！");
                return;
            }
            QueryRepositoryReq req = new QueryRepositoryReq();
            req.setTypeCode(parameterMap.get("typeCode") == null ? null : parameterMap.get("typeCode")[0]);
            req.setLevel(parameterMap.get("level") == null ? null : parameterMap.get("level")[0]);
            req.setStatus(parameterMap.get("status") == null ? null : parameterMap.get("status")[0]);
            Response result = repositoryService.exportKnowledge(req,response);
            //八进制输出流
            response.setContentType("application/octet-stream");
            //刷新缓冲
            response.flushBuffer();
        } catch (IOException e) {
            logger.error("导出失败!");
        }
    }
}
