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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import vo.ZiptbVO;

public class ZiptbDaoImpl implements ZiptbDao {

    private static ZiptbDaoImpl dao;

    private SqlMapClient smc;

    private Connection conn;
    private Statement stmt;
    private PreparedStatement pstmt;
    private ResultSet rs;

    private ZiptbDaoImpl() {

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

    public static ZiptbDaoImpl getInstance() {

	if(dao == null) {
	    dao = new ZiptbDaoImpl();
	}
	return dao;
    }

    @Override
    public List<ZiptbVO> getDongList() {

	List<ZiptbVO> dongList = new ArrayList<ZiptbVO>();

	try {
	    dongList = smc.queryForList("ziptb.getDong");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return dongList;
    }

    @Override
    public List<ZiptbVO> searchBunji() {
	// TODO Auto-generated method stub
	return null;
    }

}
