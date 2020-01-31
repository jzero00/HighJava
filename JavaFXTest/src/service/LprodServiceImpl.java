package service;

import java.util.List;

import prodDao.LprodDao;
import prodInfo.LprodVO;

public class LprodServiceImpl implements LprodService {

    private LprodDao lprodDao;
    @Override
    public List<LprodVO> SelectLprodName(LprodVO vo) {

	return lprodDao.getLprodNameList();
    }

}
