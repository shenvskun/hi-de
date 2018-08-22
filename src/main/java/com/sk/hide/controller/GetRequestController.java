package com.sk.hide.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings({"unchecked","rawtypes"})
@RestController
public class GetRequestController {
	
	@GetMapping("/gg")
	public Map server(HttpServletRequest request) throws Exception {

		String name = request.getParameter("name");
		System.out.println(name);
		Map retMap  = new HashMap();
		retMap.put("msg", "请求成功");
		return retMap;
	}

	
	
}
