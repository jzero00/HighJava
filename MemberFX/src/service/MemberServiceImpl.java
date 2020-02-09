package service;

import java.util.List;

import dao.MemberDao;
import dao.MemberDaoImpl;
import vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	//사용할 Dao의 객체 변수를 선언한다
	private MemberDao memDao;
	
	private static MemberServiceImpl service;
	
	public MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(service == null) {
			service = new MemberServiceImpl();
		}
		return service;
	}

	@Override
	public int insertMember(MemberVO mv) {
		return memDao.insertMember(mv);
	}

	@Override
	public boolean getMember(String memId) {
		return memDao.getMember(memId);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		return memDao.getAllMemberList();
	}

	@Override
	public int updateMember(MemberVO mv) {
		return memDao.updateMember(mv);
	}

	@Override
	public int deleteMember(String memId) {
		return memDao.deleteMember(memId);
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		
		return memDao.getSearchMember(mv);
	}

}
