package service;

import java.util.List;

import vo.LprodVO;


public interface LprodService {

    /**
     * LprodVO에 담겨진 자료를 DB에서 Select하는 메소드
     * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다
     */
    public List<LprodVO> SelectLprodName();
    
    public List<LprodVO> SelectProdName(LprodVO vo);
    
}
