package com.libary;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.libary.dao.FileDao;
import com.libary.vo.Book;

public class App {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {	
		//FileDao 테스트
//		FileDao dao = new FileDao();
//		List<Book> list = new ArrayList<>();
//		list.add(new Book(3, "오늘도", "우리는", false));
//		dao.listToFile(list);
		
		//도서관 생성
		Libary lib = new Libary("DB");
		// app을 생성하지 않고 getString(), getInt()메소드 사용할 수 없다
		// main메소드는 static메소드 (정적메소드)
		// 정적 필드에서는 인스턴스필드를 사용할 수 없음
		// 그래서 생성하고 사용해야함!
		// 또는 getString(), getInt()를 정적 멤버로 만들어줍니다.
		App app = new App();
		 // *************로그인 시작
		while(true) {
		// 로그인
		// id를 입력받아서 admin이면 관리자 아니면 사용
			System.out.print("id : ");

		// 자주 사용하는 로직은 메소드로 빼서 만들면 코드가 간결해집니다.	
		// 스캐너로부터 입력을 받아서 리턴해주는 역할	
		String id = getString();
		if(id.equalsIgnoreCase("admin")) {
			
			outter:    // if문보다 while문에서 사용하고, !! outter 아래가 아니라 위, 그 밖으로 나간다는 뜻!!
			while(true) {
				System.out.println("관리자 메뉴 입니다.");
				System.out.println("1.도서등록  2.도서삭제  0.로그아웃  q.프로그램 종료");
				System.out.println("메뉴를 입력해주세요");
				
				//메뉴를 입력 받습니다.
				int menu = getInt();
				int no = 0;
				switch (menu) {
				case 1:
					System.out.println("일련번호를 입력해주세요");    //물어볼때 꼭 써주기!
					no = getInt();
					System.out.println("도서제목를 입력해주세요");
					String title = getString();
					System.out.println("도서작가를 입력해주세요");
					String author = getString();
					
					lib.insertBook(no, title, author, false);
					break;
				case 2:
					System.out.println("일련번호를 입력해주세요");
					no = getInt();
					
					lib.deleteBook(no);
					break;
				case 0:
					System.out.println("로그아웃 되었습니다.");
					
					break outter;
				default:
					System.err.println(menu + "는 없는 메뉴입니다.");
					break;
				}
		}  // 관리자 반복 끝
			
			
		} else {
			System.out.println(id + "님 환영합니다.");
			
			//사용자 메뉴 반복
			userOutter:
			while(true) {
				System.out.println("사용자 메뉴입니다.");
				System.out.println("1.도서대여  2.도서반납  0.로그아웃  q.프로그램 종료");
				System.out.println("메뉴를 선택해주세요");
				
				int menu = getInt();
				int no = 0;
				switch (menu) {
				case 1:
					System.out.println("대여할 도서의 일련번호를 입력해주세요");
					no = getInt();
					lib.rentBook(no);
//					= lib.rentBook(getInt());  한줄로 퉁칠 수 있음
					break;
				case 2:
					System.out.println("반납할 도서의 일련번호를 입력해주세요");
					no = getInt();
					lib.returnBook(no);
					break;
				case 0:
					System.out.println("로그아웃 하였습니다.");
					break userOutter;
				default:
					System.out.println(menu +  "는 없는 메뉴입니다.");
					break;
				}
			}
		}
	}  // *************로그인 반복 끝
		
		// 관리자 - 책 등록, 책 삭제
		// 사용자 - 책 대여, 책 반납
		
		
		
//		// 책 추가
//		lib.insertBook(1, "불편한나키", " 나키나키", false);
//		lib.insertBook(2, "불편한나키2", " 나키나키", false);
//		
//		// 아래 세 문장은 동일함
//		// lib.toString()
//		// System.out.println(lib);
//		// System.out.println(lib.toString());
//		System.out.println(lib.toString());
//		
//		
//		//책 삭제
//		boolean res = lib.deleteBook(1);    //메소드를 역으로 만듦
//		
//		
//		// 책 대여
//		res = lib.rentBook(2);
//		
//		// 책 반납
//		res = lib.returnBook(2);
		
	}
	//필드에 적어놔서 사용가능
	
	/**
	 * getString 의 역할!
	 * 사용자로부터 입력받은 문자열을 반환합니다.
	 * - 숫자가 입력될 경우 다시 입력을 요청
	 * - Q,q 입력될 경우 프로그램 종료
	 * @return
	 */
	public static String getString(){
		String str = "";
		while(true) {      
		try {
			str = scan.next();
			// q, Q가 입력되었으면 프로그램 종료
			if(str.equalsIgnoreCase("q")) {
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
			// 숫자 변환시 숫자로 변경되면 문자를 입력해달라고 다시요청
			Integer.parseInt(str);
			
			System.err.println("문자를 입력해주세요");
		} catch (Exception e) {
			// 문자가 입력된 경우 반복문 탈출
			break;
		}
	}
		return str;
	}
	
	/**
	 * getInt의 역할!
	 * 사용자로부터 정수를 입력받아 반환합니다.
	 * -문자가 입력되었을 경우 다시 입력 받습니다.
	 * -Q,q가 입력되었을 경우 프로그램을 종료합니다.
	 * @return
	 */
	public static int getInt() {
		int i = 0;
		String str = " ";
		
		while(true) {
			try {
				// 사용자 입력받기
				str = scan.next();
				System.out.println(str+"================");
				// Q인지 확인하기
				if("q".equals(str)){
					System.out.println("프로그램 종료합니다");
					System.exit(0);
				}
				// 숫자로 변환하기
				i = Integer.parseInt(str);
				break;
				
			} catch (Exception e) {
					//오류 발생시 다시 사용자 입력으로
					System.out.println("숫자를 입력해주세요");
			}
		}
		return i;
	}
	
}
