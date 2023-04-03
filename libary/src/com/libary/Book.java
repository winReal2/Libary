package com.libary;

public class Book {
	private int no;           // 책 일련번호
	private String title;     // 제목
	private String author;    // 작가
	private boolean isRent;   // 대여여부(true : 대여중, false : 대여가능)
	                          // 대여자, 대여일 등이 필드로 추가될 수 있음.
	
	
	public Book(int no, String title, String author, boolean isRent) {
		this.no = no;
		this.title = title;
		this.author = author;
		this.isRent = isRent;
	}
	
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public boolean isRent() {
		return isRent;
	}
	public void setRent(boolean isRent) {
		this.isRent = isRent;
	}
	
}