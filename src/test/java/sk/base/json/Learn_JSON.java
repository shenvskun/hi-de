package sk.base.json;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class Learn_JSON {

	public static void main(String[] args) {
		Map m = new HashMap();
		m.put("status", "1");
		String json = JSON.toJSONString(m);
		System.out.println(json);
	}

}
