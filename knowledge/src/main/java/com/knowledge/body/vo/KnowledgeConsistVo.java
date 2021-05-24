package com.knowledge.body.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class KnowledgeConsistVo {

	@ApiModelProperty("知识库id")
	private String knowledgeId;
	@ApiModelProperty("知识标题")
	private String title;
	@ApiModelProperty("状态")
	private String status;
	@ApiModelProperty("元数据id")
	private Long elementId;
	@ApiModelProperty("字段名称")
	private String fieldName;
	@ApiModelProperty("输入类型")
	private String inputType;
	@ApiModelProperty("内容")
	private String content;
	@ApiModelProperty("排序")
	private String sort;

}
