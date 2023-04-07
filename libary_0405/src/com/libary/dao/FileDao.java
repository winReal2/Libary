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
	public List<Book> getlist() {
		// 파일로부터 데이터를 읽어서 리스트로 반환
//		System.out.println("FileDao.getList() 시작");
		//리스트 선언
		List<Book> list = new ArrayList<>();
		
		// 리소스를 자동으로 닫아줍니다.  ex) fr.close();
		// 파일에 있는 문자를 읽어오기 위해 FileReader 작성후 
		// trycatch문 후 BufferedReader(단독사용불가) 작성 ()안에 기본스트림을 파라메타로 넣어줍니다.> 
		try (FileReader fr = new FileReader("booklist.txt");
				 BufferedReader br = new BufferedReader(fr);) {
			// 한 줄씩 읽어옵니다(readLine)
			// 파일의 끝(EOF : End Of File)이면 null을 반환
			// null이면 반복문 탈출
			String readLine = "";
			while ((readLine = br.readLine()) != null) {
				//문자열을 이용해서 Book객체를 생성
				Book book = makeBook(readLine);
				//생성된 객체를 리스트에 담아줍니다.
				if(book != null) {
					list.add(book);
				}
			}
			return list;
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
//			e.printStackTrace();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
//			e1.printStackTrace();
		}
				
//		System.out.println("FileDao.getList() 종료");
		return list;
	}
	
	//makeBook메소드 생성
	/**
	 * 문자열을 파싱(가공)하여 Book객체를 생성
	 * @param readLine
	 * @return
	 */
	private Book makeBook(String readLine) {
		//반환 선언시 return은 마지막에만 넣어도 된다
		// Book book = null;
		// 공백을 기준으로 배열로 저장 
		String[] str = readLine.split(" ");

		// 강제형변환시 오류가 발생할 수 있으므로 
		// 파일이 올바르지 않은 형태로 저장되어있을 경우 오류가 발생
		// 프로그램에서 오류가 발생했을 때, 
		// 프로그램이 비정상적으로 종료되는 것을 막기 위해서 예외처리
		
		try {
			int no = Integer.parseInt(str[0]);   //문자열을 int타입으로 변환 (Wrapper클래스)
			String title = str[1];
			String author = str[2];
			boolean isRent = Boolean.parseBoolean(str[3]);    //문자열을 boolean 타입으로 변환
			
			Book book = new Book(no, title, author, isRent);
			return book;
			// Book을 생성하기 위해 알맞은 타입으로 저장
			// 책을 생성
			// 오류가 발생할 경우 null을 반환
			// 책을 반환
		} catch (Exception e) {
			//문자열 파싱 중 오류가 발생했을 경우 메세지 처리, return null
			System.err.println(readLine + " : 파싱중 오류가 발생했습니다.");
			return null; 
		}
	}

	@Override
	/**
	 * 리스트를 매개변수로 받아 파일로 출력합니다.
	 */
	public boolean listToFile(List<Book> list) {
		System.out.println("listToFile======시작");
		try (FileWriter fw = new FileWriter("booklist.txt");) {
			//리스트에 담긴 책의 정보를 파일로 출력합니다.
			for(Book book : list) {
				fw.write( book.toString() + "\n");				
			}
			fw.flush();
			return true;
			
		} catch (IOException e) {
			System.err.println(e.getMessage());
		 // e.printStackTrace();
		}
		return false;
	}

	@Override
	public int delete(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(Book book) {
		// TODO Auto-generated method stub
		return 0;
	}
}
