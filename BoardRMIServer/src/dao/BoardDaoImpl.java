package dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import vo.BoardVO;

public class BoardDaoImpl implements BoardDao {

    private static BoardDaoImpl dao;
    private SqlMapClient smc;

    private BoardDaoImpl(){

	try {

	    Charset charset = Charset.forName("UTF-8");
	    Resources.setCharset(charset);
	    Reader rd;

	    rd = Resources.getResourceAsReader("SqlMapConfig.xml");

	    //1-2 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성
	    smc = SqlMapClientBuilder.buildSqlMapClient(rd);
	    rd.close();	//reader객체 닫기

	} catch (IOException e) {
	    System.out.println("SqlMapClient 객체 생성 실패!!");
	    e.printStackTrace();
	}

    }

    public static BoardDaoImpl getInstance() {
	if(dao == null) {
	    dao = new BoardDaoImpl();
	}
	return dao;
    }

    @Override
    public int regPost(BoardVO vo) {

	int cnt = 0;

	try {

	    Object obj = smc.insert("board.regPost", vo);

	    if(obj == null) {
		cnt = 1;
	    } else {
		cnt = 0;
	    }

	} catch (SQLException e) {
	    e.printStackTrace();
	} 
	return cnt;
    }


    @Override
    public int updatePost(BoardVO vo) {

	int cnt = 0;

	try {

	    cnt = smc.update("board.updatePost", vo);

	} catch (SQLException e) {
	    e.printStackTrace();
	} 
	return cnt;
    }


    @Override
    public List<BoardVO> getAllPostList() {

	List<BoardVO> bdList = new ArrayList<BoardVO>();

	try {

	    bdList = smc.queryForList("board.getAllPostList");

	} catch (SQLException e) {
	    e.printStackTrace();
	} 

	return bdList;
    }


    @Override
    public boolean getPost(int board_no) {

	List<BoardVO> searchList = new ArrayList<>();

	boolean chk = false;

	try {

	    searchList = smc.queryForList("board.getPost", board_no);


	    if(searchList != null) {
		chk = true;
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	    searchList = null;
	    chk = false;
	} 
	return chk;
    }


    @Override
    public int deletePost(int board_no) {

	int cnt = 0;

	try {

	    cnt = smc.delete("board.deletePost", board_no);

	} catch (SQLException e) {
	    System.out.println("삭제에 실패하셨습니다.");
	    e.printStackTrace();
	}
	return cnt;
    }


    @Override
    public List<BoardVO> searchPost(BoardVO vo) {

	List<BoardVO> searchPost = new ArrayList<>();

	try {

	    searchPost = smc.queryForList("board.searchPost", vo);

	} catch (SQLException e) {
	    searchPost = null;
	    e.printStackTrace();
	} 
	return searchPost;
    }
}
