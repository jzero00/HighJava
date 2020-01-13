package x_AlphaTest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;


import d_JDBC.util.DBUtil;

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
		System.out.println(" =====  작 업 선 택  ===== ");
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
				searchPost();
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
		System.out.println();
		String boardNo = "";
		boolean chk = true;

		do {
			System.out.println("수정할 글의 번호를 입력하세요 >> ");
			boardNo = scan.next();

			chk = getPost(boardNo);

			if(chk == false) {
				System.out.println(boardNo + "번 글은 등록되지 않은 글입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력하세요.");
			}
		}while(chk == false);

		System.out.println("수정할 내용을 입력하세요.");
		System.out.print("수정할 제목 >> ");
		String boardTitle  = scan.next();

		System.out.println("수정할 작성자 이름 >> ");
		String boardWriter  = scan.next();

		scan.nextLine();	//버퍼 비우기
		System.out.print("새로운 회원 주소 >> ");
		String boardContent  = scan.nextLine();

		try {
			conn = DBUtil.getConnection();

			String sql = "UPDATE jdbc_board SET board_title = ?, board_writer = ?, board_content = ? WHERE board_no = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardTitle);
			pstmt.setString(2, boardWriter);
			pstmt.setString(3, boardContent);

			int cnt = pstmt.executeUpdate();

			if(cnt > 0){
				System.out.println(boardNo + "글의 정보를 수정했습니다.");
			} else {
				System.out.println(boardNo + "글의 정보를 수정실패!!!!");
			}
		} catch (SQLException e) {
			System.out.println(boardNo + "글의 정보를 수정실패!!!!");
			e.printStackTrace();
		} finally {
			disconnect();	//자원 반납
		}
	}

	private boolean getPost(String boardNo) {
		boolean flag = false;
		try {
			String sql = "SELECT * FROM jdbc_board WHERE board_no" + boardNo;

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if(rs.getString("board_no") == null) {
				flag = true;
			} 

		} catch (SQLException e) {
			System.out.println(boardNo + "번째 글 등록 작업 실패!!!");
		} finally {		
			disconnect();	//자원 반납
		}
		return flag;
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
				//				String boardContent =  rs.getString("board_content");

				System.out.println(boardNo + "\t" + boardTitle + "\t" + boardWriter +  "\t" + boardDate);
				System.out.println();
				//				System.out.println("내용");
				//				System.out.println(boardContent);
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
	 * 새 글을 작성하는 메소드
	 */
	private void registerPost() {

		boolean chk = false;	//중복 체크

		String boardNo = "";

		do {
			System.out.println();
			System.out.println("게시글의 번호를 입력하세요");
			System.out.print("번호 >> ");
			boardNo = scan.next();

			chk = getPost(boardNo);
			if(chk) {
				System.out.println(boardNo + "번째 글은 이미 존재합니다.");
				System.out.println("다시 입력하세요.");
			}

		}while(chk == true);
		System.out.println("========================");
		System.out.println("게시판에 새로운 글을 작성합니다.");
		System.out.println("------------------------");
		System.out.println("게시글의 제목를 입력하세요");
		System.out.print("제목 >> ");
		String board_title = scan.next();

		System.out.println("작성자의 이름을 입력하세요");
		System.out.print("작성자 이름 >> ");
		String board_writer = scan.nextLine();

		scan.nextLine();	//버퍼 비우기
		System.out.print("내용을 입력하세요 ");
		String board_content = scan.nextLine();

		SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		String format_time = format.format (System.currentTimeMillis());

		try {
			conn = DBUtil.getConnection();

			String chkNo = "SELECT MAX(board_no) FROM jdbc_board";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(chkNo);

			System.out.println(rs.getString("MAX(board_no)"));

			if(rs.getString("MAX(board_no)") != null) {
				boardNo = rs.getString("MAX(board_no)") + "1";
			} else {
				boardNo = "1";
			}
		} catch (SQLException e) {
			System.out.println(boardNo + "번째 글 등록 작업 실패!!!");
		} finally {		
			disconnect();	//자원 반납
		}


		try {
			conn = DBUtil.getConnection();
			String sql = "INSERT INTO jdbc_board (board_no, board_title , board_writer , board_content ,board_date) "
					+ " VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardNo);
			pstmt.setString(2, board_title);
			pstmt.setString(3, board_writer);
			pstmt.setString(4, board_content);
			pstmt.setString(5, format_time);

			int cnt = pstmt.executeUpdate();

			if(cnt > 0) {
				System.out.println(boardNo + "번째 글 등록 작업 성공...");
			} else {
				System.out.println(boardNo + "번째 글 등록 작업 실패!!!");
			} 
		} catch (SQLException e) {
			System.out.println(boardNo + "번째 글 등록 작업 실패!!!");
		} finally {		
			disconnect();	//자원 반납
		}
	}

	/**
	 * 게시글 번호를 이용하여 게시글이 있는지 알려주는 메소드
	 */
	private void searchPost() {

		try {
			conn = DBUtil.getConnection();
			String sql = "SELECT * FROM jdbc_board WHERE board_no = ?";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			System.out.println("게시글 번호 : " + rs.getString("board_no ") + "\t제목 : " + rs.getString("board_title") + "\t작성자 : "
					+ "" + rs.getString("board_writer") + "\t작성날자" + rs.getString("board_date"));
			System.out.println("내용");
			System.out.println(rs.getString("board_content"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
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

class Board{
	private String board_no;
	private String board_title;
	private String board_writer;
	private String board_content;
	private String board_date;
	
	public Board(String board_no, String board_title, String board_writer, String board_content, String board_date) {
		super();
		this.board_no = board_no;
		this.board_title = board_title;
		this.board_writer = board_writer;
		this.board_content = board_content;
		this.board_date = board_date;
	}

	public String getBoard_no() {
		return board_no;
	}

	public void setBoard_no(String board_no) {
		this.board_no = board_no;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getBoard_writer() {
		return board_writer;
	}

	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public String getBoard_date() {
		return board_date;
	}

	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	
}




