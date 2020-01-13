package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 	JDBC를 이용한 데이터베이스 처리 순서
 * 
 *	순서 : JDBC드라이버 로딩 → 해당 DB에 접속 → 질의(SQL명령을 수행한다.)
 *		→ 질의 결과를 받아서 처리한다. → 종료 (자원 반납)
 *
 *	1. JDBC드라이버 로딩 (오라클 기준)
 *		→ JDBC 드라이버는 DB를 만든 회사에서 제공한다.
 *		Class.forName("oracle.jdbc.driver.OracleDriver");
 *
 *	2. 접속하기 : 접속이 성공하면 Connection 객체가 생성된다.
 *		DriverManager.getConnection()메소드를 이용한다.
 *
 *	3. 질의하기 : Statement객체 또는 PreparedStatement객체를 이용하여 SQL 문장을 실행한다.
 *
 *	4. 결과
 *		1) SQL문이 select일 경우 → ResultSet객체가 만들어진다.
 *			ResultSet객체에는 select한 결과가 저장된다.
 *		2) SQL문이 insert, update, delete일 경우 → 정수값을 반환한다.
 *			(정수 값은 보통 실행에 성공한 레코드 수를 말한다.)
 */
public class T01_JdbcTest {

	public static void main(String[] args) {

		//DB작업에 필요한 객체변수 선언
		Connection conn = null; 
		Statement stmt = null;
		ResultSet rs = null;	//쿼리 문이 select일 경우에 필요함.

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
			String sql = "SELECT * FROM lprod";	//실행할 SQL문

			//	SQL문이 SELECT일 경우에는 excuteQuery()메소드를 사용하고
			//	INSERT, UPDATE, DELETE 일 경우에는 executeUpdate()메소드를 사용함.
			rs = stmt.executeQuery(sql);

			//5. ResultSet객체에 저장되어 있는 자료를 반복문과 next()메소드를 이용해 차례로 읽어와 처리한다.
			System.out.println("실행한 쿼리문 : " + sql);
			System.out.println("==== 쿼리문 실행 결과 ====");

			//rs.next()	→ ResultSet객체의 데이터를 가리키는 포인터를 다음 레코드로 이동시키고 그곳에 자료가 있으면 true,
			//				없으면 false를 반환한다.

			while(rs.next()) {
				//	컬럼의 자료를 가져오는 방법
				//	방법1) rs.get자료형이름("컬럼명")
				//	방법2) rs.get자료형이름(컬럼번호)	→ 컬럼번호는 1번부터 시작.
				System.out.println("lprod_id : " + rs.getInt("lprod_id"));
				System.out.println("lprod_gu : " + rs.getString("lprod_gu"));
				System.out.println("lprod_nm : " + rs.getString("lprod_nm"));
				System.out.println("--------------------------------------");
			}
			System.out.println("출력 끝....");
		}catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			//6. 종료 ( 사용했던 자원을 모두 반납한다.)
			if(rs != null) try {rs.close();}catch (SQLException e2) {}
			if(stmt != null) try {stmt.close();}catch (SQLException e2) {}
			if(conn != null) try {conn.close();}catch (SQLException e2) {}
		}
	}
}