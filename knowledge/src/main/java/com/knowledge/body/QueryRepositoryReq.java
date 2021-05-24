package com.knowledge.body;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 查询知识库信息请求报文
 */
@Data
public class QueryRepositoryReq {
    @ApiModelProperty("类别")
    private String typeCode;
    @ApiModelProperty("级别")
    private String level;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("是否删除")
    private String isDelete;
}
