package service;

import java.util.List;

import dao.ZiptbDao;
import dao.ZiptbDaoImpl;
import vo.ZiptbVO;

public class ZiptbServiceImpl implements ZiptbService {
    
    private static ZiptbServiceImpl instance;
    
    private ZiptbServiceImpl() {}
    
    public static ZiptbServiceImpl getInstance() {
	if(instance == null){
	    instance = new ZiptbServiceImpl();
	}
	return instance;
    }
    ZiptbDao zipDao = ZiptbDaoImpl.getInstance();
    
    @Override
    public List<ZiptbVO> getDongList() {

	return zipDao.getDongList();
	
    }

    @Override
    public List<ZiptbVO> searchBunji() {
	// TODO Auto-generated method stub
	return null;
    }

}
