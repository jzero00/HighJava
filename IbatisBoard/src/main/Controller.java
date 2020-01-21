package main;

import java.util.List;
import java.util.Scanner;

import vo.BoardVO;

public class Controller {

	BoardService bdService = BoardServiceImpl.getInstance();


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
	 */
	public void start(){
		int choice;
		do{
			displayMenu(); //메뉴 출력
			choice = Integer.parseInt(scan.nextLine()); // 메뉴번호 입력받기
			switch(choice){
			case 1 :  // 글 작성
				regPost();
				break;
			case 2 :  // 글 수정
				updateBoard();
				break;
			case 3 :  // 글 삭제
				deleteBoard();
				break;
			case 4 :  // 전체 목록 출력
				displayBoard();
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

	private void search() {
		scan.nextLine();	//입력 버퍼 지우기
		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		
		System.out.println("게시글 번호 >> ");
		int boardNo = Integer.parseInt(scan.nextLine().trim());
		
		System.out.println("게시글 제목 >> ");
		String title = scan.nextLine().trim();
		
		System.out.println("게시글 작성자 >> ");
		String writer = scan.nextLine().trim();
		
		System.out.println("게시글 내용 >> ");
		String content = scan.nextLine().trim();
		
		BoardVO bv = new BoardVO();
		
		bv.setBoard_no(boardNo);
		bv.setBoard_title(title);
		bv.setBoard_writer(writer);
		bv.setBoard_content(content);
		
		List<BoardVO> postList = bdService.searchPost(bv);
		
		if(postList.size() == 0){
			System.out.println("출력할 게시글 정보가 없습니다.");
		} else {
			System.out.println("--------------------------------------------------------");
			System.out.println("번호\t제목\t작성자\t작성날짜\t\t\t\t내용");
			System.out.println("--------------------------------------------------------");
			for(BoardVO bv2 : postList) {
				System.out.println(bv2.getBoard_no() + "\t" + bv2.getBoard_title() + "\t"
						+ bv2.getBoard_writer() + "\t" + bv2.getBoard_content());
			}
		}
		
	}

	private void deleteBoard() {
		
		System.out.println();
		System.out.println("삭제할 글ID를 입력하세요 >> ");
		int boardNo = Integer.parseInt(scan.nextLine());
		
		int cnt = bdService.deletePost(boardNo);
		
		if(cnt > 0) {
			System.out.println(boardNo + "번째 글을 삭제하였습니다.");
		} else {
			System.out.println(boardNo + "번째 글을 삭제하지 못했습니다.");
		}
	}

	private void displayBoard() {
		
		System.out.println("--------------------------------------------------------");
		System.out.println("번호\t제목\t작성자\t작성날짜\t\t\t\t내용");
		System.out.println("--------------------------------------------------------");
		
		List<BoardVO> boardList = bdService.getAllPostList();
		if(boardList.size() == 0) {
			System.out.println("출력할 게시판 정보가 없습니다.");
		} else {
			for(BoardVO bv : boardList) {
				System.out.println(bv.getBoard_no() + "\t" + bv.getBoard_title() + "\t" +
						bv.getBoard_writer() + "\t" + bv.getBoard_date() + "\t" + bv.getBoard_content());	
			}
		}
		System.out.println("--------------------------------------------");
		System.out.println("출력작업 끝");
	}

	private int updateBoard() {
		
		int boardNo;
		String title;
		String cont;
		
		boolean chk = true;

		do {
			System.out.println("수정할 게시글 번호를 입력하세요 >> ");
			boardNo = Integer.parseInt(scan.nextLine());
			
			chk = getPost(boardNo);

			if(chk == false) {
				System.out.println(boardNo + "번째 글은 등록되지 않은 글입니다.");
				System.out.println("수정할 자료가 없으니 다시 입력하세요.");
			}
			
		} while(chk == false);
		
		System.out.println("수정할 사항을 입력하세요.");
		
		System.out.print("제목 : ");
		title = scan.nextLine();
		System.out.print("내용 : ");
		cont = scan.nextLine();
		
//		scan.nextLine();	//버퍼 비우기
		
		BoardVO vo = new BoardVO();
		
		vo.setBoard_title(title);
		vo.setBoard_content(cont);
		vo.setBoard_no(boardNo);
		
		int cnt = bdService.updatePost(vo);
		
		if(cnt > 0) {
			System.out.println(boardNo + "번째 글 수정 완료~");
		} else {
			System.out.println(boardNo + "번째 글 수정 실패!!");
		}
		return cnt;
	}


	private boolean getPost(int boardNo) {
		
		boolean chk = false;
		
		chk = bdService.getPost(boardNo);
		
		return chk;
	}

	private void regPost() {

		String title;
		String writer;
		String cont;

		System.out.println();
		System.out.println("글작성을 시작합니다.");

		System.out.print("제목 : ");
		title = scan.nextLine().trim();
		System.out.print("작성자 : ");
		writer = scan.nextLine();
		System.out.print("내용 : ");
		cont = scan.nextLine().trim();

		// 입력받은 정보를 VO객체에 넣는다.
		BoardVO vo = new BoardVO();
		vo.setBoard_writer(writer);
		vo.setBoard_title(title);
		vo.setBoard_content(cont);

		int cnt = bdService.regPost(vo);
		
		if(cnt > 0) {
			System.out.println("새로운 글이 등록되었습니다.");
		} else {
			System.out.println("새로운 글이 등록되지 않았습니다.");
		}
		
	}
	
	public static void main(String[] args) {
		Controller c = new Controller();
		c.start();
	}
}
