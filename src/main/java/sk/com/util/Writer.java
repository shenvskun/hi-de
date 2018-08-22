package sk.com.util;

import org.springframework.stereotype.Component;

@Component
public class Writer {
	private String name;
	public Writer() {}
	public Writer(String name) {
		this.name = name;
	}
	public String sign() {
		return "大书法家 " + name + " ";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
