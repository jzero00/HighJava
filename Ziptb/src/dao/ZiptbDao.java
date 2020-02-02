package dao;

import java.util.List;

import vo.ZiptbVO;

public interface ZiptbDao {
/**
 * DB에서 동이름을 ZiptbVO객체에 담아 ObservableList로 반환
 * @return
 */
    public List<ZiptbVO> searchDong(ZiptbVO vo);
    
    public List<ZiptbVO> searchBunji(ZiptbVO vo);
    
}
