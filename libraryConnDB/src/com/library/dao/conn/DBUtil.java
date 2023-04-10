package com.library.dao.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.print.DocFlavor.URL;

public class DBUtil {
	public static void main(String[] args) {
		System.out.println(DBUtil.getConnection());
	}
		public static Connection getConnection() {
			Connection conn = null;
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "orauser";
			String pw = "1234";
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(url, id, pw);
				
				conn.setAutoCommit(false);  
			} catch (ClassNotFoundException e) {
				System.out.println("라이브러리를 확인해주세요");
				System.out.println(e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
			return conn;
	}
		
		public static void commit(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.commit();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void rollback(Connection conn) {
			try {
				if(conn != null && !conn.isClosed()) {
					conn.rollback();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
	}
	public static void close(Connection conn, Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) {				
				stmt.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if(stmt != null && !stmt.isClosed()) {				
				stmt.close();
			}
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
