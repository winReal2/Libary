package com.libary;

import java.util.ArrayList;
import java.util.List;
import com.libary.vo.Book;
import dao.Dao;
import dao.DatabaseDao;
import dao.FileDao;


public class Libary {
	// 도서리스트  (메모리에서 관리 = 프로그램 종료시 소멸)
	private List<Book> bookList = new ArrayList<>();
	
	// 데이터의 입출력 관리 data access object
	private Dao dao = null;
	
	/**
	 * 생성자
	 * 
	 */
	public Libary(String confDao) {
		if("db".equals(confDao)) {  //만약 넘어온 값이 db라면
			this.dao = new DatabaseDao();    //데이터베이스 다오를 생성
		} else {
			this.dao = new FileDao();      //파일다오를 생성
		}
		// 책의 리스트를 조회(파일 또는 DB를 이용해서 조회)
		// 책을 생성해서 리스트에 담아준다!
		
		// TODO : 일련번호가 겹치지 않았으면 좋겠다!
		// 데이터베이스를 이용할 경우 번호를 기본키로 사용시   
		// 번호가 중복되면 중복된 번호는 입력이 안되므로 오류 발생
		bookList.add(new Book(1, "책1", "작가1", false));
		bookList.add(new Book(2, "책2", "작가2", false));
		bookList.add(new Book(3, "책3", "작가3", false));
		bookList.add(new Book(4, "책4", "작가4", false));
	}
	/**
	 * 신규도서 추가
	 * @param no
	 * @param title
	 * @param author
	 * @param isRent
	 * @return
	 */
	public boolean insertBook(int no, String title, String author, boolean isRent) {
	
	//리스트에 책을 추가합니다.
	Book book = new Book(no, title, author, isRent);   //book 변수생성
//	bookList.add(new Book(no, title, author, isRent));
	bookList.add(book);
	dao.insertBook(book);
	return true;
	}
	/**
	 * 책 대여
	 * 책의 일련번호를 매개변수로 받아서
	 * isRent값을 수정합니다.
	 * @param index
	 * @return
	 */
	public boolean rentBook(int index) {
		for(Book book : bookList) {
			// if문 : 사용자가 입력한 일련번호가 존재하는 경우
			if(book.getNo() == index) {
				// 이미 대여중인 경우에는 대여불가
				if(book.isRent()) {
					System.err.println("이미 대여중인 도서입니다");
					return false;
				}
				book.setRent(true);   
				dao.updateBook(book);
				return true;
			}
		}
		//일련번호 찾지 못했을 때
		System.err.println("일련번호에 해당하는 도서를 찾지 못했습니다.");
		return false;
	}
	
	/**
	 * 책 삭제하기
	 */
	public boolean deleteBook(int index) {
		for(Book book : bookList) {
			if(index == book.getNo()) {
				dao.deleteBook(book);
				return bookList.remove(book);  //괄호안에 인덱스 넣으면 안됨(이유생각해보기)
			}
		}
		System.err.println("일련번호를 찾을 수 없습니다"); 
		return false;      // 메세지 처리후 false 반환
	}
	// 인덱스 번호가 없는 경우(rentBook메소드에서 못찾을때)
	

	
	
	// 책 반납
	public boolean returnBook(int index) {
		for(Book book : bookList) {
			// if문 : 사용자가 입력한 일련번호가 존재하는 경우
			if(book.getNo() == index) {
				System.out.println("isRent : " + book.isRent());
				// 도서가 대여가능 상태인 경우
				if(!book.isRent()) {
					System.err.println("반납가능한 도서가 아닙니다. \n 관리자에게 문의해주세요.");
					dao.updateBook(book);    //대여여부 변경 (= 책의 정보를 수정)
					return false;
				}
				book.setRent(false);
				return true;
			}
		}
		//일련번호 찾지 못했을 때
		System.err.println("일련번호에 해당하는 도서를 찾지 못했습니다.");
		return false;
	}
	
	//리스트 정보를 출력합니다
	public void info() {
		System.out.println("도서목록=====================");
		for(Book book : bookList) {   //리스트를 돌면서
			book.info();              //책의 정보를 출력
		}
		System.out.println("===========================");
	}
	
	public static void main(String[] args) {
		Libary lib = new Libary("db"); 		// 라이브러리 생성
		lib.info();			//도서리스트 출력
		//lib.insertBook(5, "책5", "저자5", false);		//신규도서 등록
		System.out.println("도서삭제 실행  lib.deleteBook(10)===================");
		lib.deleteBook(10);
		//lib.info();			//도서리스트 출력
		System.out.println("도서대여 실행  lib.rentBook(1)===================");
		lib.rentBook(1); 	//도서 대여
		lib.info();			//도서리스트 출력
		System.out.println("도서반납 실행  lib.returnBook(1)===================");
		lib.returnBook(1);   //도서 반납 후 출력
		lib.info();
		
	}	
}

