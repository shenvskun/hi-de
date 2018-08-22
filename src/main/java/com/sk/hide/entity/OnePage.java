package com.sk.hide.entity;

import java.util.List;
import java.util.UUID;

public class OnePage {
	private Book book;
	private Page page;
	private List<Annotation> annos;
	private KeWen keWen;
	
	public OnePage() {}
	
	public List<Annotation> getAnnos() {
		return annos;
	}
	public void setAnnos(List<Annotation> annos) {
		this.annos = annos;
	}

	public KeWen getKeWen() {
		return keWen;
	}

	public void setKeWen(KeWen keWen) {
		this.keWen = keWen;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
