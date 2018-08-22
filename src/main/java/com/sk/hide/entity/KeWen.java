package com.sk.hide.entity;


public class KeWen {
	private String id; //pageId 每页对应一篇课文 那课文的id就可以和pageId相同 以后版本升级则不一样 一个keWenId对应多个pageId 
	private String content;
	
	public KeWen() {}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
