package com.sk.hide.entity;

public class Book {
	private String bookId;
	private String bookName;
	
	public Book() {}
	
	public Book(String bookId, String bookName) {
		this.bookId = bookId;
		this.bookName = bookName;
	}


	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
}
