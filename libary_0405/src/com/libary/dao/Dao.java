package com.libary.dao;

import java.util.List;

import com.libary.vo.Book;

public interface Dao {
	// 파일을 읽어서 리스트를 반환합니다.
	List<Book> getlist();
	int delete(int no);
	int update(int no);
	int insert(Book book);
	
	
	//리스트 파일로 출력합니다.
	boolean listToFile(List<Book> list);
}
