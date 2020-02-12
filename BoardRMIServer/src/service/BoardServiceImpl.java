package service;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import dao.BoardDao;
import dao.BoardDaoImpl;
import vo.BoardVO;

public class BoardServiceImpl extends UnicastRemoteObject implements BoardService {

	private static BoardServiceImpl instance;

	private BoardServiceImpl() throws RemoteException{}

	public static BoardServiceImpl getInstance() throws RemoteException{
		if(instance == null){
			instance = new BoardServiceImpl();
		}
		return instance;
	}

	BoardDao boardDao = BoardDaoImpl.getInstance();
	
	@Override
	public int regPost(BoardVO bv) {
		return boardDao.regPost(bv);
	}

	@Override
	public boolean getPost(int board_no) {
		return boardDao.getPost(board_no);
	}

	@Override
	public int updatePost(BoardVO bv) {
		return boardDao.updatePost(bv);
	}

	@Override
	public List<BoardVO> getAllPostList() {
		return boardDao.getAllPostList();
	}

	@Override
	public int deletePost(int board_no) {
		return boardDao.deletePost(board_no);
	}

	@Override
	public List<BoardVO> searchPost(BoardVO bv) {
		return boardDao.searchPost(bv);
	}

}
