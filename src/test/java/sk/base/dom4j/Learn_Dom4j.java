package sk.base.dom4j;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

public class Learn_Dom4j {
	public static void main(String[] args) throws Exception {
//		root();
		plainTextElement();
	}
	
	static void root() throws Exception {
		String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?><shelf><book1>和耳环</book1><book2>等等</book2></shelf>";
		Document doc = DocumentHelper.parseText(xmlStr);
		
		System.out.println("element节点api");
		System.out.println();
		Element root = doc.getRootElement();
		System.out.println(root.getName());
		
		List<Element> elements = root.elements();
		for (Element ele : elements) {
			System.out.println(ele.getName());
			System.out.println(ele.getData());
			System.out.println(ele.getText());
		}
		
		System.out.println();
		System.out.println("node节点api");
		System.out.println();
		System.out.println("需要系统中有jaxen.jar--才能用selectSingleNode方法--参数传book1 //book1效果一样--参数传shelf也可以获取根节点");
		Node shelfNode = root.selectSingleNode("book1");
		System.out.println(shelfNode.getName());
		System.out.println(shelfNode.getText());
	}
	
	//字符串是不是子节点elements是否为空
	//反true 表明纯文本不是element
	static void plainTextElement() throws Exception{
		String xml = "<root>哈哈哈是</root>";
		Document doc = DocumentHelper.parseText(xml);
		
		Element re = doc.getRootElement();
		List es = re.elements();
		System.out.println(es.isEmpty());
	}
}
