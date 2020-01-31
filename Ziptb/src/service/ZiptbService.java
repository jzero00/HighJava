package service;

import java.util.List;

import vo.ZiptbVO;

public interface ZiptbService {

    public List<ZiptbVO> getDongList();
    
    public List<ZiptbVO> searchBunji();
    
}
