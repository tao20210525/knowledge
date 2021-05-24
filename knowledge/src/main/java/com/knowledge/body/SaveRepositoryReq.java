package com.knowledge.body;


import com.knowledge.body.vo.KnowledgeConsistVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 保存知识库信息请求报文
 */
@Data
public class SaveRepositoryReq {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("类别")
    private String typeCode;
    @ApiModelProperty("类别级别：1.一级 2.二级 3.三级 4.四级")
    private String level;
    @ApiModelProperty("知识标题")
    private String title;
    @ApiModelProperty("状态 1.暂存 2.待审核 3.已发布 4.已下架")
    private String status;
    @ApiModelProperty("是否删除：空/0：否 1：是")
    private String isDelete = "0";
    @ApiModelProperty("知识库内容集合")
    private List<KnowledgeConsistVo> knowledgeConsistList;
    @ApiModelProperty("主题域id")
    private Long subjectId;
    @ApiModelProperty("主题域名称")
    private String subjectName;
}
