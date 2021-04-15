package com.knowledge.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回对象
 * @author zhail
 *
 *通用返回信息
 */
public class Response {
	
	// 0:失败 1：成功
	private int result = 1;
	
	//返回代码
	private String code;

	//返回信息
	private String message;
	
	//返回内容
	private Object content;
	
	//附加内容
	private Map<String,Object> additions = new HashMap<String,Object>();
	
	
	public Response () { }
	
	public Response (Object content) { 
		this.content = content;
	}
	
	
	public Response (String code,String message) { 
		this.code = code;
		this.message = message;
	}
	
	public Response (String code,Object content) { 
		this.code = code;
		this.content = content;
	}
	
	
	public Response (int result,String code,String message) { 
		this.result = result;
		this.code = code;
		this.message = message;
	}
	
	
	public Response (String code,String message,Object content) { 
		this.code = code;
		this.message = message;
		this.content = content;
	}
	
	
	public Response (int result,String code,String message,Object content) { 
		this.result = result;
		this.code = code;
		this.message = message;
		this.content = content;
	}
	
	
	public Response (int result,String code,String message,Object content,Map<String,Object> additions) { 
		this.result = result;
		this.code = code;
		this.message = message;
		this.content = content;
		this.additions = additions;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Map<String, Object> getAdditions() {
		return additions;
	}

	public void setAdditions(Map<String, Object> additions) {
		this.additions = additions;
	}
	
	/**
	 * 添加附加信息
	 * 
	 * @param key  键值
	 * @param obj  value值
	 * @return当前对象
	 */
	public Response addAdditions(String key,Object obj) {
		this.additions.put(key, obj);
		return this;
	}
	
	public static Response ok() {
		return new Response("成功");
	}
	
	/**
	 * 返回成功结果默认
	 * @param obj
	 * @return
	 */
	public static Response ok(Object obj) {
		return new Response(obj);
	}
	
	/**
	 * 返回成功结果
	 * 
	 * @param code   成功代码
	 * @param successMsg  成功提示信息
	 * @return
	 */
	public static Response ok(String code,String successMsg) {
		return new Response(code,successMsg);
	}
	
	/**
	 * 返回成功结果
	 * 
	 * @param code  成功代码
	 * @param obj    返回内容
	 * @return
	 */
	public static Response ok(String code,Object obj) {
		return new Response(code,obj);
	}
	
	/**
	 * 返回成功结果
	 * 
	 * @param code    成功代码
	 * @param successMsg  成功提示信息
	 * @param obj  返回内容
	 * @return
	 */
	public static Response ok(String code,String successMsg,Object obj) {
		return new Response(code,obj);
	}
	
	/**
	 * 返回错误信息默认
	 * 
	 * @param errorMsg 错误信息
	 * @return
	 */
	public static Response error(String errorMsg) {
		return new Response(errorMsg);
	}
	
	/**
	 * 返回错误代码和错误信息
	 * 
	 * @param errorCode  错误代码
	 * @param errorMsg   错误信息
	 * @return
	 */
	public static Response error(String errorCode,String errorMsg) {
		return new Response(errorCode,errorMsg);
	}
	
	/**
	 * 返回错误代码和错误信息
	 * 
	 * @param errorCode 错误代码
	 * @param errorMsg  错误信息
	 * @param obj  内容提示信息
	 * @return
	 */
	public static Response error(String errorCode,String errorMsg,Object obj) {
		return new Response(errorCode,errorMsg,obj);
	}
	
	/**
	 * 自定义返回所有信息
	 * 
	 * @param result  返回值 是否成功
	 * @param code   代码
	 * @param msg    信息
	 * @param obj      内容
	 * @return
	 */
	public static Response custom(int result,String code,String msg,Object obj) {
		return new Response(result,code,msg,obj);
	}
	
	public static Response setAllParam(int result,String code,String msg,Object obj,Map<String,Object> additions) {
		return new Response(result,code,msg,obj,additions);
	}
	
	
	
}
