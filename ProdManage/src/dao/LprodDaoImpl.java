package dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import service.LprodServiceImpl;
import vo.LprodVO;


public class LprodDaoImpl implements LprodDao {

    private static LprodDaoImpl dao;

    private SqlMapClient smc;

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private LprodDaoImpl() {

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
    
    public static LprodDaoImpl getInstance() {
	if (dao == null) {
	    dao = new LprodDaoImpl();
	}
	return dao;
    }

    @Override
    public List<LprodVO> SelectLprodName() {

	List<LprodVO> lprodNameList = new ArrayList<LprodVO>();

	try {
	    lprodNameList = smc.queryForList("lprod.selectProdName");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return lprodNameList;
    }

    @Override
    public List<LprodVO> SelectProdName(LprodVO vo) {

	List<LprodVO> prodNameList = new ArrayList<LprodVO>();
	
	try {
	    prodNameList = smc.queryForList("lprod.SelectProdName");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return prodNameList;
    }

}
