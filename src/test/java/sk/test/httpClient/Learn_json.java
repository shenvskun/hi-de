package sk.test.httpClient;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Learn_json {
	public static void main(String[] args) {
		Map m = new HashMap();
		m.put("status", "1");
		System.out.println(JSON.toJSONString(m));
	}
}
