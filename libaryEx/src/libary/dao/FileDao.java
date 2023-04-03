package libary.dao;

import java.util.List;
import libary.vo.Book;

public class FileDao implements Dao{

	@Override
	public List<Book> getBookList() {
		System.out.println("책 리스트를 조회합니다");
		return null;
	}

	@Override
	public int insertBook(Book book) {
		System.out.println("신규 도서 등록합니다");
		return 0;
	}

	@Override
	public int deleteBook(Book book) {
		System.out.println("도서를 삭제합니다");
		return 0;
	}

	@Override
	public int updateBook(Book book) {
		System.out.println("도서 정보를 업데이트 합니다");
		return 0;
	}
	

}
