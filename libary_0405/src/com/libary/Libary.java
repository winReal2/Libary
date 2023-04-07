package com.libary;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.libary.dao.Dao;
import com.libary.dao.DatabaseDao;
import com.libary.dao.FileDao;
import com.libary.vo.Book;

public class Libary {  
	// 책 목록을 필드로 가짐
	// 필드로 선언시 클래스 내부에서 자유롭게 이용이 가능하다
	// = 전역변수
	List<Book> list = null;
	// 인터페이스를 타입으로 선언 => 인터페이스의 구현체를 이용해 생성
	Dao dao = new FileDao();
	
	//생성자 : 클래스명과 동일한 이름, 리턴(반환)타입이 없다
	Libary() {
		//생성자의 역할 : 리스트 초기화
		list = dao.getlist();
		//System.out.println(list.size());   //app 클래스에서 libary가 실행되는지 안되는지 아는방법!
		System.out.println(toString());
	}
	
	Libary(String daotype) {
		if(daotype.equals("DB")) {
			dao = new DatabaseDao();
		}
		//생성자의 역할 : 리스트 초기화
		list = dao.getlist();
		//System.out.println(list.size());   //app 클래스에서 libary가 실행되는지 안되는지 아는방법!
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		System.out.println("책 목록 ============= lib.toString()");
		String info = "";
		
		//정렬
		Collections.sort(list);
		
		for(Book book : list) {
			info += book.info() + "\n";
		}
		return info;
	}
	

	
	// 1. 책의 정보를 받아서 리스트에 등록 
	// 2. 리스트를 파일로 출력합니다.
	public boolean insertBook(int no, String title, 
						String author, boolean isRent) {
		// 0. 일련번호 중복 체크
		for(Book book : list) {
			if(book.getNo() == no) {
				System.out.println("일련번호가 중복되었습니다. \n 확인 후 다시 입력해주세요.");
				return false;
			}
		}
		
		// 1. 책을 생성
		Book book = new Book(no, title, author, isRent);
		
		// 2. 리스트에 책을 등록
		list.add(book);
		
		// 3. 리스트를 파일로 출력		
		boolean res = dao.listToFile(list);    //메소드의 반환타입이 boolean임
		String title1 = book.getTitle();
		// 4. 파일에 정상적으로 등록이 되지 않은 경우 리스트에서 제거
		if(!res) {
			list.remove(book);
			System.err.println("책이 등록되지 않았습니다. \n 관리자에게 문의해주세요.");
			return false;
		}
		System.out.println("(" + title1 + ")" + "책이 등록되었습니다.");
		System.out.println(toString());
		return true;
	}

	/**
	 * 책의 일련번호를 입력 받아서 리스트에서 삭제합니다
	 * 리스트를 파일로 출력합니다.
	 * @param no
	 * @return
	 */
	public boolean deleteBook(int no) {
		for(Book book : list) {
			if(book.getNo() == no) {    //일련번호가 같으면 삭제
				// 삭제
				list.remove(book);
				//리스트를 파일로 출력
				boolean res = dao.listToFile(list);
				String title = book.getTitle();
				if(!res) {
					// 파일로 출력이 실패할 경우 책을 다시 리스트에 추가합니다.
					list.add(book);
					System.err.println("파일 출력도중 오류가 발생하였습니다.");
					return false;
				}
				System.out.println("(" +title+ ")" +"정상적으로 삭제되었습니다.");
				System.out.println(toString());
				return true;
			}
		}
		System.err.println("일치하는 일련번호가 없습니다. \n 일련번호를 확인해주세요");
		return false;
	}
	/**
	 * 일련번호에 해당하는 책을 찾는다
	 * 책의 상태가 대여가 가능한 상태라면(return = false) 대여(isRent = true)
	 * 대여가 가능한 상태가 아니면 메세지 처리
	 * 리스트를 파일로 출력
	 * @param no
	 * @return
	 */
	public boolean rentBook(int no) {
		for(Book book : list) {
			//일련번호에 해당하는 책을 찾는다
			if(book.getNo()==no) {
				if(!book.isRent()) {
					// 렌트 상태로 변경
					book.setRent(true);
					
					
					//파일로 출력
					boolean res = dao.listToFile(list);
					//데이터 베이스 업데이트
					int i = dao.update(no);
					
					if(i>0) {
						System.out.println(i + "처리되었습니다.");
					} else {
						System.out.println("처리도중 오류가 발생했습니다.");
					} 	book.setRent(false);
					
					String title = book.getTitle();
					System.out.println("("+ title + ")"+ "도서가 대여 되었습니다.");
					System.out.println(toString());
					return true;
//어려운 사람은 주석처리	if(!res) {
//						book.setRent(false);
//						System.err.println("파일로 출력하는데 실패했습니다.");
//					}
//					System.out.println("도서가 대여 처리되었습니다");
//					return true;
				}
				System.err.println("일치하는 일련번호가 없습니다 \n 다시 일련번호를 입력해주세요");
			}
		}
		return false;
	}

	
	/**
	 * 일련번호에 해당하는 도서를 찾는다
	 * 도서가 대여중(true)이라면 반납처리
	 * 아니면 오류메세지 처리 (반납가능한 도서가 아닙니다)
	 * @param no
	 * @return
	 */
	public boolean returnBook(int no) {
		for(Book book : list) {
			if(book.getNo() == no) {
				if(book.isRent()) {
					// 반납처리
					book.setRent(false);
					dao.listToFile(list);
					// DB 업데이트 로직 호출
					dao.update(no);
					String title = book.getTitle();
					System.out.println("("+ title + ")"+ "이 반납되었습니다");
					System.out.println(toString());
					return true;
				} else {
					System.err.println("반납 가능한 도서가 아닙니다.");
				}
			}
		}
		System.out.println("일련번호에 해당하는 책이 존재하지 않습니다. \n 일련번호를 확인해주세요");
		return false;
	}
	
	
}













