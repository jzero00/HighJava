package service;

import java.util.List;

import dao.BoardDao;
import dao.BoardDaoImpl;
import vo.BoardVO;

public class BoardServiceImpl implements BoardService {

	private static BoardServiceImpl instance;

	private BoardServiceImpl(){}

	public static BoardServiceImpl getInstance(){
		if(instance == null){
			instance = new BoardServiceImpl();
		}
		return instance;
	}

	BoardDao bdDao = BoardDaoImpl.getInstance();
	
	@Override
	public int regPost(BoardVO bv) {
		return bdDao.regPost(bv);
	}

	@Override
	public boolean getPost(int board_no) {
		return bdDao.getPost(board_no);
	}

	@Override
	public int updatePost(BoardVO bv) {
		return bdDao.updatePost(bv);
	}

	@Override
	public List<BoardVO> getAllPostList() {
		return bdDao.getAllPostList();
	}

	@Override
	public int deletePost(int board_no) {
		return bdDao.deletePost(board_no);
	}

	@Override
	public List<BoardVO> searchPost(BoardVO bv) {
		return bdDao.searchPost(bv);
	}

}
