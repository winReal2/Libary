package com.libary.vo;

public class Book implements Comparable<Book> {
	// 일련번호
	private int no;
	// 제목
	private String title;
	// 작가
	private String author;
	// 대여여부
	private boolean isRent;
	
	// 순서 :  setter, getter > 생성자 > toString 오버라이딩
	//생성자 만들어줌
	public Book(int no, String title, String author, boolean isRent) {
		super();
		this.no = no;
		this.title = title;
		this.author = author;
		this.isRent = isRent;
	}
	
	//toString 오버라이딩
	@Override
	public String toString() {
		return getNo() 
				+ " " + getTitle() 
				+ " " + getAuthor() 
				+ " " + isRent();
	}
	
	public String info() {
		String str = "대여가능";
		if(isRent) {
			str = "대여중";
		}
		return getNo() 
				+ " " + getTitle() 
				+ " " + getAuthor() 
				+ " " + str;
	}
	
	
	
	//setter, getter 만들어줌
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

	@Override   // 오름차순으로 일련번호 정렬 / 정해진 규칙이라 외우기!
	public int compareTo(Book o) {
		if(this.no > o.getNo()) {
			return 1;
		} else {
			return -1;			
		}
	}
}
