package sk.base.json;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.json.JSONObject;
import org.json.XML;

public class Learn_JSON {

	public static void main(String[] args) {
//		fastJsonXml();
		geshi();
	}
	
	//json的基本格式  键值对用双引号中间:分隔  键值对中间用,分隔 花括号对象 方括号数组
	static void geshi() {
		Map m = new HashMap();
		m.put("status", "1");
		m.put("status2", "1");
		String json = JSON.toJSONString(m);
		System.out.println(json);
	}
	
	//json-lib可以将xml转json fastjson?
	//利用fastjson和 json
	static void fastJsonXml() {
		Map map = new HashMap();
        map.put("k1", "v1");
        map.put("k2", "v2");
        Map m = new HashMap();
        m.put("a", "b");
        m.put("c", "d");
        map.put("k3", m);

        //json串
        String jsonStr = JSON.toJSONString(map);
        System.out.println("source json : " + jsonStr);

        //json转xml
        String xml = json2xml(jsonStr);
        System.out.println("xml  :  " + xml);
        //xml转json
        String targetJson = xml2json(xml);
        System.out.println("target json : " + targetJson);
	}
	//★★关键点
	//用json的XML类实现xml字符串和jsonObject之间的互转  toString 转xml  toJSONObject转JSONObject
	//
	public static String json2xml(String json) {
        JSONObject jsonObj = new JSONObject(json);
        return "<xml>" + XML.toString(jsonObj) + "</xml>";
    }

    /**
     * xml to json
     * @param xml
     * @return
     */
    public static String xml2json(String xml) {
        JSONObject xmlJSONObj = XML.toJSONObject(xml.replace("<xml>", "").replace("</xml>", ""));
        return xmlJSONObj.toString();
    }

}
