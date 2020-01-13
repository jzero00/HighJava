package x_AlphaTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import d_JDBC.util.DBUtil;

public class BetaTest {


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
	 * @throws ClassNotFoundException 
	 */
	public void start() {
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
			case 1 :  // 새글 작성
				registerPost();
				break;
			case 2 :  // 글 삭제
				//				deletePost();
				break;
			case 3 :  // 글 수정
				//				updatePost();
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
	 * 새 글을 등록하는 메소드
	 */
	private void registerPost(){
		conn = DBUtil.getConnection();

		boolean chk = false;	//중복 체크

		String boardNo = "";

		try {
			do {
				System.out.println();
				System.out.println("게시글의 번호를 입력하세요");
				System.out.print("번호 >> ");
				boardNo = scan.next();

				String sql = "SELECT COUNT(*) cnt FROM jdbc_board WHERE board_no = " + boardNo;
				rs = stmt.executeQuery(sql);

				int	count = rs.getInt("cnt");
				if(count == 0){
					chk = true;
				}
				if(count > 0){
					System.out.println(boardNo + "번 글은"
							+ " 이미 존재하는 글입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}
			}while(chk == true);


			stmt = conn.createStatement();
			String sql = "select max(board_no) from jdbc_board";
			rs = stmt.executeQuery(sql);
			int num = 0;
			if(rs.next()) {
				num = rs.getInt("max(board_no)");
			}
			num++;

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

			String sql2 = "INSERT INTO jdbc_board (board_no, board_title , board_writer , board_content ,board_date) "
					+ " VALUES (?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, num);
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

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		BoardTest2 test = new BoardTest2();
		test.start();
	}

	/**
	 * 게시글의 목록을 출력하는 메소드
	 *
	 */

	private void displayPostAll() {

		conn = DBUtil.getConnection();

		System.out.println();
		System.out.println("--------------------------------------------");
		System.out.println(" 번호\t제목\t작성자\t작성날짜");
		System.out.println("--------------------------------------------");

		String sql = "SELECT * FROM jdbc_board";
		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				String boardNo = rs.getString("board_no");
				String boardTitle = rs.getString("board_title");
				String boardWriter = rs.getString("board_writer");
				String boardDate = rs.getString("board_date");

				System.out.println(boardNo + "\t" + boardTitle
						+ "\t" + boardWriter +  "\t" + boardDate);
				System.out.println();

				System.out.println("--------------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	class Board{

		String board_no; 
		String board_title;
		String board_writer;
		String board_date;
		String board_content;

		public Board(String board_no, String board_title, String board_writer, String board_date, String board_content) {
			super();
			this.board_no = board_no;
			this.board_title = board_title;
			this.board_writer = board_writer;
			this.board_date = board_date;
			this.board_content = board_content;
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

		public String getBoard_date() {
			return board_date;
		}

		public void setBoard_date(String board_date) {
			this.board_date = board_date;
		}

		public String getBoard_content() {
			return board_content;
		}

		public void setBoard_content(String board_content) {
			this.board_content = board_content;
		}
	}
}