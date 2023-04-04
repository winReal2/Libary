package com.libary.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.libary.vo.Book;

public class FileDao implements Dao {

	@Override
	// getList 메소드가 하는 역할
	// 패스트 하려면 클래스 이름(FileDao)과 메소드 이름(getList)을 알아야하고 반환되는게 뭔지(List<Book>) 알아야함
	public List<Book> getList() {     //메소드명 : getList
		System.out.println("FileDao.getList() 시작");
		List<Book> list = new ArrayList<>();  
		
		/**
		 * 파일을 읽어서 리스트를 반환! reader이용!
		 */
		// 반납할 자원(리소스)가 있는 경우 try ()안에서 생성할 경우 자동으로 close해줍니다
		try ( FileReader fr = new FileReader("bookList.txt");
				BufferedReader br = new BufferedReader(fr); ) {
			
			String str = "";
			//파일로부터 한줄을 읽어들여 널이 될때까지 반복합니다.
			// null : 파일의 끝
			while ((str = br.readLine()) != null) {
		
				Book book = makeBook(str);
				// 리스트 파싱 중 오류가 발생한 건은 제외
				if(book != null) {
					list.add(book);
				}
				
			}
			
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		
		return list;
	}
	  	// 문자열을 받아서 책을 생성하여 반환합니다.
		// book을 선업합니다. => 반환용
		public Book makeBook (String str) {
			Book book = null;
			try{
				
					//0 책1 저자1 false
					String[] strArry = str.split(" ");  // 공백을 이용해서 문자열을 잘라 배열로 저장
					
					int no = Integer.parseInt(strArry[0]) ;
					String title = strArry[1];
					String author = strArry[2];
					boolean isRent = Boolean.parseBoolean(strArry[3]);
					
					book = new Book(no, title, author,isRent);
					
			} catch (Exception e) {
				//데이터 파싱 중 오류가 발생하더라도 프로그램이 종료되지 않도록 try구문을 활용하여 오류를 처리
				System.err.println("리스트 생성 중 오류가 발생하였습니다");
				System.err.println("==============readLine() : " + str);
			}
			// 책이 정상적으로 생성된 경우 book을 반환
			// 생성 중 오류가 발생시 null 을 반환
			return book;
		}
		@Override
		public boolean saveFile(List<Book> list) {
			//리스트를 파일로 저장합니다.
			try(FileWriter fw = new FileWriter("bookList.txt");) {
				
				for(Book book : list) {
					//책의 정보를 공백으로 연결하여 반환
					//파일로 저장할 때 개행을 해줘야함! (+\n)
					fw.write(book.toString()+"\n");
				}
				//파일로 출력
				fw.flush();
				//파일출력 성공
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//파일출력 실패
			return false;
		}
}










