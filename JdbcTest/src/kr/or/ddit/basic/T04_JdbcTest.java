package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class T04_JdbcTest {

	/*
	 * LPROD 테이블에 새로운 데이터 추가하기
	 * 
	 * lprod_gu와 lprod_nm은 직접 입력받아 처리하고
	 * lprod_id는 현재의 lprod_id중 제일 큰 값보다 1증가된 값으로 한다.
	 * (기타 사항 : lprod_gu도 중복되는지 검사한다.)
	 */

	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		// 1. DB접속
		// -Connection 클래스
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;


		// 2. 연결 문자열 생성
		// -접속에 필요한 정보로 구성된 문자열, Connection String
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id= "PC05";
		String pw = "java";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("OracleDriver의 로딩이 정상적으로 이뤄졌습니다.");
			
			conn = DriverManager.getConnection(url, id, pw);
			stmt = conn.createStatement();
			System.out.println("데이터베이스의 연결에 성공하였습니다.");
			
//			System.out.println("lprod_gu 값을 입력해주세요");
//			String lprod_gu = s.nextLine();
//			
//			System.out.println("lprod_nm 값을 입력해주세요");
//			String lprod_nm = s.nextLine();
//			
//			String checkLprod_gu = "SELECT lprod_gu FROM lprod WHERE lprod_gu = '" + lprod_gu + "'";
//			rs = stmt.executeQuery(checkLprod_gu);
			
			String sql = "SELECT MAX(lprod_id) FROM lprod";
			rs = stmt.executeQuery(sql);
			int num = 0;
			if(rs.next()) {
				num = rs.getInt("MAX(lprod_id)");
			}
			num++;
			
			int count;
			String sql2 = "SELECT COUNT(*) cnt FROM lprod WHERE lprod_gu = ?";
			pstmt = conn.prepareStatement(sql2);
			String gu = null;
			do {
				System.out.println("LPROD_GU 입력 : ");
				gu = s.next();
				
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery();
				count = 0;
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				if(count > 0) {
					System.out.println("lprod_gu " + gu + "는 이미 있는 상품입니다.");
				}
			}while(count > 0);
			
			
			
			
			
			System.out.println(rs.getString("lprod_id"));
			
			
			
			
			
		}catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try {rs.close();}catch (SQLException e2) {}
			if(stmt != null) try {stmt.close();}catch (SQLException e2) {}
			if(conn != null) try {conn.close();}catch (SQLException e2) {}
		}
		
		

	}
}
