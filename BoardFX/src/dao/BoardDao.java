package dao;

import java.util.List;

import vo.BoardVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하영 결과를 작성하여 service에 전달하는 DAO의 interface
 * @author PC-05
 *
 */
public interface BoardDao {

	/**
	 * BoardVO에 담겨진 자료를 DB에 register하는 메소드
	 * 
	 * @param bv DB에 insert할 자료가 저장된 BoardVO객체
	 * @return DB 작업이 성공하면 1 이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int regPost(BoardVO bv);
	
	/**
	 * 주어진 회원 ID가 존재하는지 여부를 알아내는 메소드
	 * 
	 * @param board_no 검색할 글번호
	 * @return 해당 글번호가 있으면 true, 없으면 false
	 */
	public boolean getPost(int board_no);
	
	/**
	 * 하나의 BoardVO자료를 이용하여 DB를 update하는 메소드
	 * @param bv update할 회원 정보가 들어있는 BoardVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updatePost(BoardVO bv);
	
	/**
	 * DB의 jdbc_board테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메소드
	 * 
	 * @return MemberVO객체를 담고있는 List 객체
	 */
	public List<BoardVO> getAllPostList();
	
	public int deletePost(int board_no);

	public List<BoardVO> searchPost(BoardVO bv);

}
