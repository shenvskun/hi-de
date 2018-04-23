package com.sk.hide.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings({"unchecked","rawtypes"})
@RestController
public class HttpClientServerController {
	private Integer count = 1;
	
	@PostMapping("/server")
	public Map server(HttpServletRequest request) throws Exception {
		System.out.println("======进入server=====");
		
		System.out.println("接收第" + count++ + "个请求");
		
		Thread.sleep(2000);
		Map retMap  = new HashMap();
		retMap.put("msg", "请求成功");
		return retMap;
	}

	
	
}
