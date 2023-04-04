package com.libary;

import java.util.List;

import com.libary.dao.Dao;
import com.libary.dao.DatabaseDao;
import com.libary.dao.FileDao;
import com.libary.vo.Book;

public class Libary {
	// 필드 (전역변수)  : 클래스 내부 어디서든 사용가능
	private List<Book> list;
	//FileDao 또는 DatabaseDao로 생성할 수 있도록
	// 인터페이스 타입으로 선언
	private Dao dao = new FileDao();
	
	public Libary() {
		// 파일을 읽어서 리스트를 초기화해주는 작업   //getList를 필드에 담아줌
		list = dao.getList();  
	}
	public boolean insert(int no, String title, 
			                      String author, boolean isRent) {
		
		//중복체크
		for(Book book : list) {
			if(book.getNo() == no) {
				System.out.println("중복되는 일련번호가 존재");
				return false;
			}
		}
		
		
		// 외부로부터 받은 데이터를 바탕으로 책을 생성하고 리스트에 추가
		Book book = new Book(no, title, author, isRent);
		// 리스트를 파일에 저장
		list.add(book);
		boolean res = dao.saveFile(list);
		if(res) {
			return true;
		} else {
			return false;
		}
				
		
	}
	
	
	
	@Override
	public String toString() {
		String listStr = "";
		for(Book book : list) {
			listStr += book.toString() + "\n";
		}
		return listStr;
	}
	
	
	public Libary(String type) {
		if(type.equalsIgnoreCase("DB")) {
			dao = new  DatabaseDao();
		}
	
	}
}
