package c_JdbcTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

import c_JDBC.util.DBUtil;

public class BoardTest {

	private Connection conn;
	private Statement st;
	private PreparedStatement ps;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in); 

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 글 작성");
		System.out.println("  2. 글 수정");
		System.out.println("  3. 글 삭제");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 글 검색");
		System.out.println("  6. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 * @throws SQLException 
	 */
	public void start() throws SQLException{
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = Integer.parseInt(scan.nextLine()); // 메뉴번호 입력받기
			switch(choice){
			case 1 :  // 글 작성
				insertBoard();
				break;
			case 2 :  // 글 수정
				updateBoard();
				break;
			case 3 :  // 글 삭제
				deleteBoard();
				break;
			case 4 :  // 전체 목록 출력
				dispalyBoard();
				break;
			case 5 :  // 글 검색
				search();
				break;
			case 6 :  // 작업 끝
				System.out.println("작업을 마칩니다.");
				break;
			default :
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}

	private boolean getBoardNo(int BoardNo) {
		boolean chk = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from jdbc_board where Board_no = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, BoardNo);

			rs = ps.executeQuery();

			int cnt = 0;
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			if (cnt > 0) {
				chk = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return chk;
	}

	private void insertBoard() {
		String title;
		String writer;
		String cont;

		System.out.println();
		System.out.println("글작성을 시작합니다.");

		System.out.print("제목 : ");
		title = scan.nextLine().trim();
		System.out.print("작성자 : ");
		writer = scan.nextLine().replace(" ", "");
		System.out.print("내용 : ");
		cont = scan.nextLine().trim();

		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO jdbc_board VALUES (BOARD_SEQ.NEXTVAL, ?, ?, SYSDATE, ?)";

			ps = conn.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, writer);
			ps.setString(3, cont);
			int cnt = ps.executeUpdate();

			if (cnt > 0) {
				System.out.println("글 작성에 성공하셨습니다.");
			} else {
				System.out.println("글 작성에 실패하셨습니다.");
			}

			System.out.println("--------------------------------------------------------");
			System.out.println("출력작업 끝...");


		} catch (SQLException e) {
			System.out.println("글 작성에 실패하셨습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void updateBoard() throws SQLException {

		boolean check = true;
		int BoardNo;
		String title;
		String cont;
		System.out.println();

		System.out.println("게시글 번호 입력");
        int no = Integer.parseInt(scan.nextLine());
        String sql = "update jdbc_board SET board_content=? where board_no=" + no;
        System.out.println("수정할 게시글 내용 입력:");
        String content = scan.nextLine();
        ps = conn.prepareStatement(sql);
        ps.setString(1, content);
        ps.executeUpdate();
        
		int cnt = ps.executeUpdate();

		if (cnt > 0) {
			System.out.println("수정에 성공하셨습니다.");
		} else {
			System.out.println("수정에 실패하셨습니다.");
		}

		System.out.println("--------------------------------------------------------");
		System.out.println("출력작업 끝...");

	}

	private void deleteBoard() {
		boolean check = true;
		System.out.println();
		int BoardNo;
		do {
			System.out.println("삭제할 글의 제목의 번호를 입력해주세요.");
			BoardNo = Integer.parseInt(scan.nextLine());

			check = getBoardNo(BoardNo);

			if (check == false) {
				System.out.println("삭제할 글이 존재하지 않습니다.");
				return;
			}
		} while (check == false);


		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM jdbc_board WHERE board_no = ?";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, BoardNo);
			int cnt = ps.executeUpdate();

			if (cnt > 0) {
				System.out.println("성공적으로 삭제하였습니다.");
			} else {
				System.out.println("삭제에 실패하셨습니다.");
			}

			System.out.println("--------------------------------------------------------");
			System.out.println("출력작업 끝...");


		} catch (SQLException e) {
			System.out.println("삭제에 실패하셨습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}

	}

	/*
	 * 전체 회원을 출력
	 */
	private void dispalyBoard() {
		System.out.println();
		System.out.println("--------------------------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성날짜\t내용");
		System.out.println("--------------------------------------------------------");

		try {
			conn = DBUtil.getConnection();
			String sql = "select * from jdbc_board";

			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				int boardNo = rs.getInt("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				Date boardDate = rs.getDate("board_date");
				String boardContent = rs.getString("board_content");
				System.out.println(boardNo + "\t" + boardTitle + "\t" + boardWriter + "\t" + boardDate + "\t" + boardContent);
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("출력작업 끝...");


		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	private void search() {
		System.out.println();
		System.out.println("검색할 글의 제목을 입력해주세요.");
		String input = scan.nextLine();
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM jdbc_board WHERE board_title LIKE ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+input+"%");
			rs = ps.executeQuery();
			while (rs.next()) {
				int boardNo = rs.getInt("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				Date boardDate = rs.getDate("board_date");
				String boardContent = rs.getString("board_content");
				System.out.println(boardNo + "\t" + boardTitle + "\t" + boardWriter + "\t" + boardDate + "\t" + boardContent);
			}
			System.out.println("--------------------------------------------------------");
			System.out.println("출력작업 끝...");


		} catch (SQLException e) {
			System.out.println("검색에 실패하셨습니다.");
			e.printStackTrace();
		} finally {
			disConnect();
		}
	}

	/*
	 * 자원반납 메소드
	 */
	public void disConnect() {
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(st!=null)try{ st.close(); }catch(SQLException ee){}
		if(ps!=null)try{ ps.close(); }catch(SQLException ee){} 
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}

	public static void main(String[] args) throws SQLException {
		BoardTest board = new BoardTest();
		board.start();
	}
}
