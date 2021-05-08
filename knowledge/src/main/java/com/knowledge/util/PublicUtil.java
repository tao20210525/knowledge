package com.knowledge.util;

public class PublicUtil {

	/**
	 * 首页-元数据管理页面-下拉类型数据转换
	 * @param type 1:元数据/组-identification 2:名称-name 3:标识-field_name 4:数据类型-data_type 5:输入类型-input_type 6:操作人-update_by
	 * @return
	 */
	 public static String getType(String type)
	  {
		 
	    if ("1".equals(type))
	      return "a.identification";
	    if ("2".equals(type)) 
	      return "a.name";
	    if ("3".equals(type))
	      return "a.field_name";
	    if ("4".equals(type))
	      return "a.data_type";
	    if ("5".equals(type))
	      return "a.input_type";
	    if ("6".equals(type))
	      return "a.update_by";
	  
	    return "999";  //不符合标准的枚举值
	  }
	
}
