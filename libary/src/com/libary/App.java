package com.libary;

import java.util.Scanner;

public class App {
	Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		
		App app = new App();
		
		app.getInt();
		app.getString();
	}
		
		public int getInt() {
			int i= 0;
			while(true) {
				try {
					String str = scan.next();
					if(str.equalsIgnoreCase("q"));
					System.out.println("종료합니다.");
					System.exit(0);
					i = Integer.parseInt(str);
					break;
				} catch (Exception e) {
					System.out.println("오류발생!");
				}
			}
			return i;	
	}
		
		public int getString() {
			String res = "";
			while(true) {
				try {
					res = scan.next();
					
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
}
