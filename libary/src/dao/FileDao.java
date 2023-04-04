package dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import com.libary.vo.Book;

public class FileDao implements Dao{

	public static void main(String[] args) {
		FileDao dao = new FileDao();
//		dao.getBookList();
		List<Book> list = new ArrayList<>();
		list.add(new Book(0, "책1", "저자1", false ));
		dao.insertBook(list);
	}
	@Override
	public List<Book> getBookList() {
		System.out.println("책 리스트 조회합니다");
		List<Book> list = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("booklist.txt"));) {
			// 파일 읽어오기 
			//null(더이상 읽어올 데이터가 없으면)이 될때까지 읽어오기
			String str = "";
			System.out.println("======" + br.readLine());
			while ((str = br.readLine()) != null) {
				// 1 제목1 작가1 false
				
				String[] strArray = str.split(" ");
				// 생성자의 타입에 맞게 타입을 변환하여 변수에 저장
				int no = Integer.parseInt(strArray[0]);
				String title = strArray[1];
				String author = strArray[2];
				boolean isRent = Boolean.parseBoolean(strArray[3]);
				
				list.add(new Book(no, title, author, isRent));
			}
			
			// 공백으로 자르기
			// 책을 생성할 파라메터 만들어주기
			// 책을 생성하기
			// 리스트에 담기
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		return list;  //리스트는 꼭 반환!
	}

@Override
public int insertBook(List<Book> list) {
	// true 매개변수를 입력하면 이어쓰기가 됩니다.
	// new FileWriter("bookList.txt", true)
	try (FileWriter fw = new FileWriter("bookList.txt", true);){
		
		for(Book book : list) {
			fw.write(book.toString() + "\n");
		}
		
		fw.flush();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
}

@Override
public int deleteBook(List<Book> list) {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int updateBook(List<Book> list) {
	// TODO Auto-generated method stub
	return 0;
}
	
	



}
