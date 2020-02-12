package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import vo.BoardVO;

/**
 * Service 객체는 DAO에 설정된 메소드를 원하는 작업에 맞게 호출하여 결과를 받아오고, 받아온 자료를 Controller에게 보내주는 역할을 수행한다.
 * 보통 DAO의 메소드와 같은 구조를 갖는다.
 * 
 * @author PC-05
 *
 */
public interface BoardService extends Remote {

	public int regPost(BoardVO bv) throws RemoteException;
	
	public boolean getPost(int board_no) throws RemoteException;
	
	public int updatePost(BoardVO bv) throws RemoteException;
	
	public List<BoardVO> getAllPostList() throws RemoteException;
	
	public int deletePost(int board_no) throws RemoteException;

	public List<BoardVO> searchPost(BoardVO bv) throws RemoteException;
}
