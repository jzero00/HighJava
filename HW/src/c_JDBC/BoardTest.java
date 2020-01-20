package c_JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;


import c_JDBC.util.DBUtil;

public class BoardTest {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private Scanner scan = new Scanner(System.in); 

	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 새글 작성");
		System.out.println("  2. 글 삭제");
		System.out.println("  3. 글 수정");
		System.out.println("  4. 전체 목록 출력");
		System.out.println("  5. 작성 목록 검색.");
		System.out.println("  6. 프로그램 종료.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}

	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
			case 1 :  // 새글 작성
				registerPost();
				break;
			case 2 :  // 글 삭제
				deletePost();
				break;
			case 3 :  // 글 수정
				updatePost();
				break;
			case 4 :  // 게시글 목록 출력
				displayPostAll();
				break;
			case 5 :  // 게시글 검색
//				searchPost();
				break;
			case 6 :  // 프로그램 종료
				System.out.println("작업을 마칩니다.");
				break;
			default :
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=6);
	}

	/**
	 * 등록글을 삭제하는 메소드(입력받은 글 번호를 이용하여 삭제한다.)
	 */
	private void deletePost() {
		System.out.println();
		System.out.println("삭제할 글ID를 입력하세요 >> ");
		String boardNo = scan.next();

		try {
			conn = DBUtil.getConnection();
			String sql = "DELETE FROM jdbc_board WHERE board_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);

			int cnt = pstmt.executeUpdate();
			if(cnt > 0) {
				System.out.println(boardNo + "번글 삭제 성공...");
			} else {
				System.out.println(boardNo + "번글 삭제 실패!!!!");
			}
		} catch (SQLException e) {
			System.out.println(boardNo + "번글 삭제 실패!!!");
			e.printStackTrace();
		} finally {
			disconnect();
		}

	}

	/**
	 * 글 수정
	 */
	private void updatePost() {
		
		boolean check = true;
		String BoardNo;
		String title;
		String cont;
		System.out.println();
		
		do {
			System.out.println("수정할 글의 번호를 선택해주세요.");
			BoardNo = scan.nextLine();
			
			check = getPost(BoardNo);
			
			if (check == false) {
				System.out.println("수정할 글이 존재하지 않습니다.");
				return;
			}
		} while (check == false);
		
		System.out.print("제목 : ");
		title = scan.nextLine().trim();
		System.out.print("내용 : ");
		cont = scan.nextLine().trim();
		try {
			conn = DBUtil.getConnection();
			String sql = "UPDATE jdbc_board SET board_title = ?, board_content = ? WHERE board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, cont);
			pstmt.setString(3, BoardNo);
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println("수정에 성공하셨습니다.");
			} else {
				System.out.println("수정에 실패하셨습니다.");
			}
			
			System.out.println("--------------------------------------------------------");
			System.out.println("출력작업 끝...");
			
			
		} catch (SQLException e) {
			System.out.println("수정에 실패하셨습니다.");
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}

	private boolean getPost(String boardNo) {
		
		boolean chk = false;
		
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) cnt from jdbc_board where Board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			
			rs = pstmt.executeQuery();
			
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
			disconnect();
		}
		return chk;
	}

	/**
	 * 글 전체 목록을 출력하는 메소드
	 */
	private void displayPostAll() {

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println(" 번호\t제목\t작성자\t작성날짜");
		System.out.println("--------------------------------------------");

		try {
			conn = DBUtil.getConnection();

			String sql = "SELECT * FROM jdbc_board";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				String boardNo = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");

				System.out.println(boardNo + "\t" + boardTitle + "\t" + boardWriter +  "\t" + boardDate);
				System.out.println();
				System.out.println("--------------------------------------------");
			}
			System.out.println("출력작업 끝....");
		} catch (SQLException e) {
			System.out.println("전체 목록 가져오기 실패!!!");
			e.printStackTrace();
		} finally {
			disconnect();	//자원 반납
		}
	}

	/**
	 * 새로운 글을 등록하는 메소드
	 */
	private void registerPost() {

		String memId = "";

		System.out.println("========================");
		System.out.println("게시판에 새로운 글을 작성합니다.");
		System.out.println("------------------------");
		System.out.println("게시글의 제목를 입력하세요");
		System.out.print("제목 >> ");
		String board_title = scan.next();

		System.out.println("작성자의 이름을 입력하세요");
		System.out.print("작성자 이름 >> ");
		String board_writer = scan.next();

		scan.nextLine();	//버퍼 비우기
		System.out.print("내용을 입력하세요 ");
		String board_content = scan.nextLine();

		try {
			conn = DBUtil.getConnection();
			String sql = "insert into jdbc_board"
					+ " values(board_seq.nextval, ?, ?, sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_writer);
			pstmt.setString(3, board_content);

			int cnt = pstmt.executeUpdate();

			if(cnt > 0) {
				System.out.println(memId + "회원 추가 작업 성공...");
			} else {
				System.out.println(memId + "회원 추가 작업 실패!!!");
			} 
		} catch (SQLException e) {
			System.out.println(memId + "회원 추가 작업 실패!!!");
		} finally {		
			disconnect();	//회원 반납
		}
	}

	/**
	 * 회원 아이디를 이용하여 회원이 있는지 알려주는 메소드
	 * @param memId
	 * @return true: 이미 존재함, false: 신규회원
	 */
	private boolean searchPost(int boardNo) {

		boolean chk = false;
		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT COUNT(*) AS cnt FROM jdbc_board WHERE board_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);

			rs = pstmt.executeQuery();

			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}

			if(cnt > 0) {
				chk = true;
			} 	
		} catch (SQLException e) {
			e.printStackTrace();
			chk = false;
		} finally {
			disconnect();
		}

		return chk;
	}

	/**
	 * 자원반납용 메소드
	 */
	private void disconnect() {
		//사용했던 자원 반납
		if(rs!=null)try{ rs.close(); }catch(SQLException ee){}
		if(stmt!=null)try{ stmt.close(); }catch(SQLException ee){}
		if(pstmt!=null)try{ pstmt.close(); }catch(SQLException ee){}
		if(conn!=null)try{ conn.close(); }catch(SQLException ee){}
	}

	public static void main(String[] args) {
		BoardTest memObj = new BoardTest();
		memObj.start();
	}

}






