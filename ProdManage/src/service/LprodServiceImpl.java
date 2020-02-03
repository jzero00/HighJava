package service;

import java.util.List;

import dao.LprodDao;
import dao.LprodDaoImpl;
import vo.LprodVO;

public class LprodServiceImpl implements LprodService {

    private static LprodServiceImpl instance;
    
    private LprodServiceImpl() {}
    
    public static LprodServiceImpl getInstance() {
	if(instance == null) {
	    instance = new LprodServiceImpl();
	}
	return instance;
    }
    LprodDao lprodDao = LprodDaoImpl.getInstance();
    
    @Override
    public List<LprodVO> SelectLprodName() {

	return lprodDao.SelectLprodName();
    }

    @Override
    public List<LprodVO> SelectProdName(String string) {

	return lprodDao.SelectProdName(string);
    }

}
