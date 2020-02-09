package dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import vo.MemberVO;

public class MemberDaoImpl implements MemberDao {
	
	private static MemberDaoImpl dao;

	private SqlMapClient smc;

	private MemberDaoImpl() {

		Charset charset = Charset.forName("UTF-8");
		Resources.setCharset(charset);
		Reader rd;

		try {
			rd = Resources.getResourceAsReader("SqlMapConfig.xml");

			//1-2 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close();	//reader객체 닫기

		} catch (IOException e) {
			System.out.println("SqlMapClient 객체 생성 실패!!");
			e.printStackTrace();
		}


	}

	public static MemberDaoImpl getInstance() {
		if(dao == null) {
			dao = new MemberDaoImpl();
		}
		return dao;
	}

	@Override
	public int insertMember(MemberVO mv) {

		int cnt = 0;

		Object obj;
		try {
			obj = smc.insert("member.insertMember", mv);
			if(obj == null) {	//성공
				cnt = 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean getMember(String memId) {

		boolean chk = false;

		try {

			int cnt = (int) smc.queryForObject("member.getMember", memId);

			if(cnt > 0) {
				chk = true;
			} 	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chk;
	}

	@Override
	public List<MemberVO> getAllMemberList() {

		List<MemberVO> memList = new ArrayList<MemberVO>();

		try {
			memList = smc.queryForList("member.getMemberAll");

		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return memList;
	}

	@Override
	public int updateMember(MemberVO mv) {

		int cnt = 0;

		try {

			cnt = smc.update("member.updateMember", mv);

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {

		int cnt = 0;

		try {

			cnt = smc.delete("member.deleteMember", memId);

		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public List<MemberVO> getSearchMember(MemberVO mv) {
		List<MemberVO> memList = new ArrayList<>();
		try {
			memList = smc.queryForList("member.getSearchMember", mv);
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		}
		return memList;
	}
}
