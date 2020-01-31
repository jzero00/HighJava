package prodDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import prodInfo.LprodVO;

public class LprodDaoImpl implements LprodDao {

    private SqlMapClient dao;

    private SqlMapClient smc;
    
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    @Override
    public List<LprodVO> getLprodNameList() {

	List<LprodVO> lprodNameList = new ArrayList<LprodVO>();
	
	try {
	    lprodNameList = smc.queryForList("lprod.selectProdName");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return lprodNameList;
    }

}
