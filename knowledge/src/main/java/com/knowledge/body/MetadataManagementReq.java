package com.knowledge.body;

//首页-查询元数据管理
public class MetadataManagementReq {
	
	//下拉类型 1:元数据/组 2:名称 3:标识 4:数据类型 5:输入类型 6:操作人
	private String type;
	
	//模糊查询名称
	private String name;

	//类别
	private String category;
	
	//主题域id
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
}
