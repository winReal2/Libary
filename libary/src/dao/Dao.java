package dao;

import java.util.List;
import com.libary.vo.Book;

public interface Dao {
	//책리스트 조회
	List<Book> getBookList();
	
	// 관리자 메뉴
	int insertBook(Book book);      // 신규도서 등록
	int deleteBook(Book book);      // 도서 삭제
	int updateBook(Book book);      // 도서정보 업데이트

}
