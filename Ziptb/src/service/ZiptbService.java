package service;

import java.util.List;

import vo.ZiptbVO;

public interface ZiptbService {

    public List<ZiptbVO> searchDong(ZiptbVO vo);
    
    public List<ZiptbVO> searchBunji(ZiptbVO vo);
    
}
