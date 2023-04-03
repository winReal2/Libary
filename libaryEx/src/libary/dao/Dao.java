package libary.dao;

import java.util.List;
import libary.vo.Book;

public interface Dao {
	List<Book> getBookList();
	
	int insertBook(Book book);
	int deleteBook(Book book);
	int updateBook(Book book);
	
}
