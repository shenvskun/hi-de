package sk.com.util;

//帮书法家写回信

@FindMe("mb")
public class MyBean {
	@AutowiredMe("writer")
	private Writer writer;
	
	public String doSth(String name) {
		String s = "hellow" + name + "/r/n" + writer.sign();
		System.out.println(s);
		return s;
	}
}
