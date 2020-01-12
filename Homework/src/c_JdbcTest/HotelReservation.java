package c_JdbcTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelReservation {

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

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("OracleDriver의 로딩이 정상적으로 이뤄졌습니다.");

			conn = DriverManager.getConnection(url, id, pw);
			stmt = conn.createStatement();
			System.out.println("데이터베이스의 연결에 성공하였습니다.");
			
			startMenu(stmt, rs);
			
//			rs.close();
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	private static void startMenu(Statement stmt, ResultSet rs) {


		boolean flag = true;

		Scanner s = new Scanner(System.in);
		System.out.println("**************************\n"
				+ "호텔 문을 열었습니다.\n"
				+ "**************************\n\n"
				+ "*******************************************\n");
		do {
			System.out.println("어떤 업무를 하시겠습니까?\n"
					+ "1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			int menu = Integer.parseInt(s.nextLine());

			switch(menu) {
			case 1:	checkIn(stmt, rs);
			break;

			case 2: checkOut(stmt, rs);
			break;

			case 3: roomStatus(stmt, rs);
			break;

			case 4: terminate();
			flag = false;
			break;
			}	
		}while(flag);

	}


	private static void checkIn(Statement stmt ,ResultSet rs) {
		String room;
		String name;
		Scanner s = new Scanner(System.in);
		System.out.println("어느방에 체크인 하시겠습니까?\n"
				+ "방번호 입력 => ");
		room = s.nextLine();
		System.out.println("누구를 체크인 하시겠습니까?\n"
				+ "이름 입력 => ");
		name = s.nextLine();

		String checkSql = "SELECT * FROM hotel_mng WHERE room_num " + room;
		try {
			rs = stmt.executeQuery(checkSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs != null) {
			System.out.println(room + "방에는 이미 사람이 있습니다.");
			return;
		}else {
			String sql = "INSERT INTO hotel_mng (room_num, guest_name) VALUES(" + room + ",'" + name + "')";
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("체크인 되었습니다.");
		}

	}

	private static void checkOut(Statement stmt, ResultSet rs) {

		Scanner s = new Scanner(System.in);
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String room = s.nextLine();

		String checkSql = "SELECT * FROM hotel_mng WHERE room_num " + room;
		try {
			rs = stmt.executeQuery(checkSql);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		try {
			if (rs != null) {
				System.out.println(room + "방에는 이미 사람이 있습니다.");
				return;
			}else {
				String sql = "DELETE TABLE FROM hotel_mng WHERE room_num = " + room;
				stmt.executeUpdate(sql);
				System.out.println("체크아웃 되었습니다.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}



	}

	private static void roomStatus(Statement stmt, ResultSet rs) {

		String checkSql = "SELECT * FROM hotel_mng";
		try {
			rs = stmt.executeQuery(checkSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rs == null) {
			System.out.println("현재 투숙객이 존재하지 않습니다.\r\n");
			return;
		}
		try {
			while(rs.next()) {
				try {
					System.out.println("방 번호 : " + rs.getInt("room_num") + "\t이름 : " + rs.getString("guest_name"));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}


	private static void terminate() {

		System.out.println("**************************\r\n" + 
				"* \r\n" + 
				"* 호텔 문을 닫았습니다.\r\n" + 
				"**************************");

	}
}
class Hotel{

	private String room; // 방번호
	private String name; // 이름

	public Hotel(String room, String name) {
		super();
		this.room = room;
		this.name = name;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}