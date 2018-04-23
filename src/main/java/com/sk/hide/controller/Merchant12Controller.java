package com.sk.hide.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

@RestController
public class Merchant12Controller {
	
	@PostMapping("/notify")
	public Map notify(HttpServletRequest request) throws Exception {
		System.out.println("==========================================");
		System.out.println("==========================================");
		String msg1 = request.getParameter("MSG");
		msg1 = URLDecoder.decode(msg1);
		System.out.println(JSON.parseObject(new String(Base64Utils.decodeFromString(msg1), "utf-8"), Map.class));
		
		String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMTX7SZSL+B7fA+rg1Ewj0x8OtDLfS5SqG5OJFxFt+2THeyCKZ45hW9sVYMHuk1Ur4BIT+LEtzHQo2R/ZLFYtLVLnKQ8igwi1LN/EZrHq5g1r1zSKq3so+kUsBeUSPN9P9iX1pDEXvECWDZNNtPAhsSZPV8Z67wWjXhZeR9tbFvdAgMBAAECgYBwVSko7U4VNWMfLinKc4PTijGmZfoSLWmhSUbGwaKtGX/CMKW/feQAKjrCg+51oxZkht/P8aL58q7L80drTcwRiZdJsce1oxM9VCIEzxoJPdZ0raZpV4K4QZSOm40EkHdhNBDjBCexIj+63yRqGNutPRGneKcRE4y/HG4ZFbrTAQJBAOqIJ2imGAnYL2H8e6eeLGKXYJQ7g9bFPyywslDRw2mMEaLcfMCtFfBhY3BsPngMC26k9Uad/BpATR8uyLh9/WkCQQDW3JxOmLfX3xuHC9xdqiPfJJAQaHaZNa2LsfbsNYfRlKmVFDRlwA6V36AbtviGl3woy78f7AwjThTguSJGnnhVAkEAoTz52vhVjm8SJEtwt8wGt8FapI5GFWGGDTNtZCLSNb4WYeEz40Mb0iciNig0ylN9iJPgDKyQpXuPieCgHjaAAQJBALJZbWXQXspR6AexULpvAFe5gdwc2lVSuTu7/bsUtCjDvgCBRP0w7up5Ne3Lc4BFPkqe1Ycp6OllZu8ivdDgugECQDF8MtUddtqooKkG1S5UiLhs1BK92v5z2UUwc1l2UD0d5OSxcDxfT3iG15AhCgx+UfgBm6YnBL9tGPArvAz5QLw=";
		
		Map param = new HashMap();
		param.put("status", "1");
		String msg = JSON.toJSONString(param);
		msg = Base64.encode(msg.getBytes("UTF-8"));
		msg = URLEncoder.encode(msg, "UTF-8");

		String sign = RSA.sign(msg, privateKey, "UTF-8");
		sign = URLEncoder.encode(sign, "UTF-8");
		
		Map retMap  = new HashMap();
		retMap.put("MSG", msg);
		retMap.put("SIGN", sign);
		return retMap;
	}

	
	
}
