package com.sk.hide.controller.springmvc;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sk.hide.entity.User;

@Controller
public class Learn_DateBinder2 {
	
//	@InitBinder
//    public void initBind(WebDataBinder binder){
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
//        dateFormat.setLenient(false);  
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   
//    }
	
	@PostMapping("/date2")
	public @ResponseBody Map dateBinder(User user) {
//		System.out.println(user.getBirth());
		
		Map m = new HashMap();
		m.put("res", "success");
		return m;
	}
}
