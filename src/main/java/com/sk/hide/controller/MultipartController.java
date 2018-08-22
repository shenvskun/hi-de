package com.sk.hide.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sk.hide.entity.User;

@Controller
public class MultipartController {
	
	@PostMapping("/file")
	public @ResponseBody Map handleMultipart( @RequestParam("name") String name, @RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2) throws Exception {
		System.out.println("上传的name为：" + name);
		System.out.println("---------------");
		System.out.println("fieldName: " + file.getName());
		System.out.println("fileSize: " + file.getSize());
		System.out.println("contentType: " + file.getContentType());
		System.out.println("originalFileName: " + file.getOriginalFilename()); //中文不会乱码
		System.out.println("toString: " + file.toString());
		System.out.println("textcontent: " + new String(file.getBytes(),"utf-8"));
		System.out.println("file2: " + file2.getOriginalFilename());
		Map m = new HashMap();
		m.put("res", "d");
		return m;
	}
}
