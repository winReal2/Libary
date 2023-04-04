package dao;

import java.util.List;

import com.libary.vo.Book;

public class DatabaseDao implements Dao{

	@Override
	public List<Book> getBookList() {
		System.out.println("DB - 리스트 조회");
		return null;
	}


	

	@Override
	public int insertBook(List<Book> list) {
		System.out.println("DB - 신규도서 등록");
		return 0;
	}

	@Override
	public int deleteBook(List<Book> list) {
		System.out.println("DB - 도서 삭제");
		return 0;
	}

	@Override
	public int updateBook(List<Book> list) {
		System.out.println("DB - 도서정보 업데이트");
		return 0;
	}

}
