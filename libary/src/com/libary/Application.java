package com.libary;

import java.util.Scanner;

public class Application {
	Scanner scan = new Scanner(System.in);       //스캐너가 아래에서 안되는 이유: 스캐너는 인스턴스 멤버(생성 후 사용가능하고, 변수로 접근가능)
	                                            // 사용하려면 레퍼런스변수. 해야 사용가능

	public static void main(String[] args) {
		// 인스턴스 멤버는 생성 후 사용이 가능!!     
		Application app = new Application();
//		 app.scan.next(); 생성 후 사용가능!
//		 app.getInt();
//		 app.getString();
		
		
		//도서관 생성
		Libary lib = new Libary("db");
		
		//로그아웃처리시 다시 실행
		while(true) {
			//로그인 처리
			System.out.println("아이디를 입력해주세요.");
			String id = app.getString();
			
			if("admin".equalsIgnoreCase(id)) {
				app.adminMenu(lib);
				} else {
					//사용자 메뉴
					app.memberMenu(lib);
			}
		}
	}
					
		
	private void memberMenu(Libary lib) {  //메소드로 만들어 분리
		// 사용자
		//메뉴별로 작업 수행	
		while(true) {
			//도서목록 출력
			lib.info();
			
			//메뉴확인 
			System.out.println("1. 도서대여 2. 도서반납 0. 로그아웃 q. 프로그램 종료");
			System.out.println("메뉴를 입력해주세요.");
			int menu = getInt();
			
			if(menu ==1) {
				System.out.println("도서번호를 입력해주세요.");
				int index = getInt();
				lib.rentBook(index);			
			} else if (menu == 2) {
				System.out.println("도서번호를 입력해주세요.");
				int index = getInt();
				lib.returnBook(index);			
			} else if(menu==0){
				System.out.println("로그아웃합니다.");
				break;
			}else {
				System.err.println("메뉴를 확인해주세요.");
			}			
		}
	}
	private void adminMenu(Libary lib) {
		outter:
		while(true) {
			// 관리자 
			// 도서목록 출력
			lib.info();
			// 메뉴확인
			System.out.println("1.도서등록 2.도서삭제 0.로그아웃 q.프로그램 종료");
			System.out.println("메뉴를 입력해주세요");
			int menu = getInt();
			int no = 0;
			
			switch (menu) {
			case 1:
				System.out.println("일련번호를 입력해주세요");
				no = getInt();
				System.out.println("제목을 입력해주세요");
				String title = getString();
				System.out.println("작가를 입력해주세요");
				String author = getString();
				
				lib.insertBook(no, title, author, false);
				break;
			case 2:
				System.out.println("삭제할 책의 일련번호를 입력해주세요");
				no = getInt();
				lib.deleteBook(no);
				break;
			case 0:
				//로그아웃
				break outter;
			default:
				System.err.println("메뉴를 확인 후 다시 입력해주세요");
			}
		}
		}
	
	/**
	 * 사용자로부터 숫자를 입력 받습니다.
	 * @return
	 */
	public int getInt() {
		int i = 0;
		while(true) {
			try {
				String str = scan.next();			
				if(str.equalsIgnoreCase("q")) {
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				}
				i = Integer.parseInt(str);
				break;
			} catch (Exception e) {
				System.err.println("입력 중 오류가 발생되었습니다.");
			}
		}
		return i;
	}
	/**
	 * 사용자로부터 문자를 입력받습니다.
	 * @return
	 */
	public String getString() {
		String res = "";
		while(true) {
		try {
			res = scan.next();
			// 입력한 값이 숫자인 경우 다시 받아올 수 있도록 처리
			// (문자입력해야되는데 숫자입력할 때 걸러내는 법)
			try {
				Integer.parseInt(res);  
				System.err.println("문자를 입력해주세요."); 
				continue;
			} catch (Exception e) {
			}
			if(res.equalsIgnoreCase("q")) {
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
			break;
		} catch (Exception e) {
			System.err.println("입력 중 오류가 발생했습니다.");
			System.out.println("문자를 입력해주세요.");
		}
		}
		return res;
	}
}
