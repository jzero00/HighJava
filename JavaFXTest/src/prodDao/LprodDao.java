package prodDao;

import java.util.List;

import prodInfo.LprodVO;

public interface LprodDao {

    /**
     * DB의 lprod테이블의 lprod_nm 값을 List에 담아서 반환하는 메소드
     * 
     * @return LprodVO객체를 담고있는 List 객체
     */
    public List<LprodVO> getLprodNameList();
    
}
