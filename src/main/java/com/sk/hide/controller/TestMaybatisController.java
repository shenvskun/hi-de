package com.sk.hide.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.hide.dao.UserMapper;
import com.sk.hide.entity.User;

@SuppressWarnings({"unchecked","rawtypes"})
@RestController
public class TestMaybatisController {
	@Autowired
	private UserMapper um;
	
	@GetMapping("/mb")
	public Map server(HttpServletRequest request) throws Exception {
		System.out.println("======进入testMybatisController=====");
		String id = request.getParameter("id");

		User user = new User();
		user.setId(Integer.parseInt(id));
		user = um.find(user);
		
		Thread.sleep(2000);
		Map retMap  = new HashMap();
		retMap.put("name", user.getName());
		return retMap;
	}

	
	
}
