package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class T02_JdbcTest {

	/*
	 * 문제1) 사용자로부터 lprod_id값을 입력받아 입력한 값보다
	 * 		lprod_id가 큰 자료들을 출력하시오.
	 * 
	 * 문제2) lprod_id값을 2개 입력받아 두 값중 작은 값부터 큰값 사이의 자료들을 출력하시오.
	 */

	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;

		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. DB에 접속(Connection 객체 생성)
			String url = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "PC05";
			String password = "java";

			//실제적으로 OracleDriver가 사용되는 부분
			conn = DriverManager.getConnection(url, userId, password);

			//3. Statement 객체 생성 → Connection 객체를 이용한다.
			stmt = conn.createStatement();

			//4. SQL문을 Statement객체를 이용하여 실행하고 실행결과를 ResultSet 객체에 저장한다.
			System.out.println("lprod_id 값을 입력해주세요 : ");
			Scanner s = new Scanner(System.in);
			int num = Integer.parseInt(s.nextLine()); 
			String sql1 = "SELECT * FROM lprod WHERE lprod_id >" + num;	//실행할 SQL문
			System.out.println("lprod_id 값을 2개를 입력해주세요 : ");
			rs1 = stmt.executeQuery(sql1);
			
			int num1 = Integer.parseInt(s.nextLine());
			int num2 = Integer.parseInt(s.nextLine());
			int max, min;
			
			max = Math.max(num1, num2);
			min = Math.min(num1, num2);
			
			String sql2 = "SELECT * FROM lprod WHERE lprod_id >=" + min + " AND lprod_id<=" + max;
			rs2 = stmt.executeQuery(sql2);
			
			System.out.println("실행한 쿼리문 : " + sql1);
			System.out.println("=== 쿼리문 실행 결과 ===");
			
			while(rs1.next()) {
				System.out.println("lprod_id : " + rs1.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs1.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs1.getString("lprod_nm"));
				System.out.println("--------------------------------------");
			}
			
			System.out.println("실행한 쿼리문 : " + sql2);
			System.out.println("=== 쿼리문 실행 결과 ===");
			
			while(rs2.next()) {
				System.out.println("lprod_id : " + rs2.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs2.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs2.getString("lprod_nm"));
				System.out.println("--------------------------------------");
			}
			
			
			
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs1 != null) try {rs1.close();}catch (SQLException e2) {}
			if(stmt != null) try {stmt.close();}catch (SQLException e2) {}
			if(conn != null) try {conn.close();}catch (SQLException e2) {}
		}
	}

}
