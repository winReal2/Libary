package libary;

import java.util.ArrayList;
import java.util.List;

import libary.dao.Dao;
import libary.dao.DatabaseDao;
import libary.dao.FileDao;
import libary.vo.Book;

public class Libary {
	private List<Book> bookList = new ArrayList<>();
	
	private Dao dao = null;
	
	public Libary(String confDao) {
		if("db".equals(confDao)) {
			this.dao = new DatabaseDao();
		} else {
			this.dao = new FileDao();
		}
		bookList.add(new Book(1, "책1", "작가1", false));
		bookList.add(new Book(2, "책2", "작가2", false));
		bookList.add(new Book(3, "책3", "작가3", false));
	}
	
	// 신규 도서 추가
	public boolean insertBook(int no, String title, String author, boolean isRent) {
		
		Book book = new Book(no, title, author, isRent);
		bookList.add(book);
		dao.insertBook(book);
		return true;
	}
	
	// 도서 대여
	public boolean rentBook(int index) {
		for(Book book : bookList) {
			if(book.getNo() == index) {
				if(book.isRent()) {
					System.out.println("이미 대여중인 도서입니다");
					return false;
				}
				book.setRent(true);
				dao.updateBook(book);
				return true;
			}
		}
		System.out.println("일련번호에 해당하는 도서를 찾지 못했습니다.");
		return false;
	}
	
	// 도서 삭제
	public boolean deleteBook(int index) {
		for(Book book : bookList) {
			if(index == book.getNo()) {
				dao.deleteBook(book);
				return bookList.remove(book);
			}
		}
		System.out.println("일련번호를 찾을 수 없습니다.");
		return false;
	}
	
	public void 
	
}
