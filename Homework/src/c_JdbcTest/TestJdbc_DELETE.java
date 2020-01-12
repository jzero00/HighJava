package c_JdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestJdbc_DELETE {

	public static void main(String[] args) {

		// 1. DB접속
		// -Connection 클래스
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		// 2. 연결 문자열 생성
		// -접속에 필요한 정보로 구성된 문자열, Connection String
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id= "PC05";
		String pw = "java";

		// DB작업 > 외부 입출력 > try-catch 필수

		try {
			// 3. JDBC 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("OracleDriver의 로딩이 정상적으로 이뤄졌습니다.");

			// 4. 접속
			// - Connection 객체 생성 + 접속 작업.
			conn = DriverManager.getConnection(url, id, pw);
			stmt = conn.createStatement();
			System.out.println("데이터베이스의 연결에 성공하였습니다.");

			// 5. SQL
			String room;
			String name;
			Scanner s = new Scanner(System.in);
			System.out.println("어느방을 체크아웃 하시겠습니까?\n"
					+ "방번호 입력 => ");
			room = s.nextLine();

			String sql = "SELECT * FROM hotel_mng";
			rs = stmt.executeQuery(sql);

			
			sql = "DELETE hotel_mng "
					+ "WHERE room_num = " + room;
			stmt.executeUpdate(sql);

			
			while(rs.next()) {
				System.out.println("방번호 : " + rs.getInt("room_num") + "\t이름 : " + rs.getString("guest_name"));
			}

			rs.close();
			stmt.close();
			conn.close();
			System.out.println(conn.isClosed()?"접속종료":"접속중");// 접속중(false), 접속종료(true)
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
