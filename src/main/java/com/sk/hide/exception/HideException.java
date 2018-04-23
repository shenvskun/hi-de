package com.sk.hide.exception;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@SuppressWarnings({"serial", "unchecked","rawtypes"})   //★supress 抑制
@ControllerAdvice  // ★对@RequestMpping注解的方法集体生效
public class HideException extends RuntimeException {  //★抛运行时异常 ①方法上不用声明  ②@Transactional管理的事务会回滚
	
	private String msg;
	private int code;
	
	@ExceptionHandler(HideException.class)
	@ResponseBody					//★返回JSON 该注解+pom.xml引入gson包+@EnableWebMvc(httpMessageConverter)
	public Map handerHideException(HttpServletRequest req, HttpServletResponse res, HideException he) {
		res.setStatus(he.getCode());  //★ 将异常的code设置为响应状态码
		
		Map retMap = new HashMap();
		retMap.put("msg", he.getMsg());
		retMap.put("code", he.getCode());
		return retMap;
	}
	
	public HideException() {}

	public HideException(int code, String msg) {
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
}
