package dao;

import java.util.List;

import vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하영 결과를 작성하여 service에 전달하는 DAO의 interface
 * @author PC-05
 *
 */
public interface MemberDao {
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메소드
	 * 
	 * @param mv DB에 insert할 자료가 저장된 MemberVO객체
	 * @return DB 작업이 성공하면 1 이상의 값이 반환되고 실패하면 0이 반환된다.
	 */
	public int insertMember(MemberVO mv);
	
	/**
	 * 주어진 회원 ID가 존재하는지 여부를 알아내는 메소드
	 * 
	 * @param memId 검색할 회원ID
	 * @return 해당 회원ID가 있으면 true, 없으면 false
	 */
	public boolean getMember(String memId);
	
	/**
	 * DB의 mymember테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메소드
	 * 
	 * @return MemberVO객체를 담고있는 List 객체
	 */
	public List<MemberVO> getAllMemberList();
	
	/**
	 * 하나의 MemberVO자료를 이용하여 DB를 update하는 메소드
	 * @param mv update할 회원 정보가 들어있는 MemberVO객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * 회원 ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메소드
	 * @param memId
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * MemberVO에 담긴 자료를 이용해 회원을 검색하는 메소드
	 * 
	 * @param mv 검색할 자료가 들어있는 MemberVO객체
	 * @return 검색된 결과를 담은 List 객체
	 */
	public List<MemberVO> getSearchMember(MemberVO mv);
}
