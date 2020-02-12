package server;

import java.io.FileOutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import inf.RemoteInterface;

/*
 * RMI용 서비스를 제공하는 객체는 RMI용 인터페이스를 구현하고,
 * UnicastRemoteObject객체를 상속해서 작성한다.
 */
public class RemoteServer extends  UnicastRemoteObject implements RemoteInterface{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    //RMI용 인터페이스를 구현한 객체와 생성자도 RemoteException을 throws하도록 작성한다.

    protected RemoteServer() throws RemoteException {
	super();
	// TODO Auto-generated constructor stub
    }

    @Override
    public void doViewMain() throws RemoteException {

	try {

	    //구현한 RMI용 객체를 클라이언트에서 사용할 수 있도록 RMI서버에서 등록한다.

	    //1. RMI용 인터페이스를 구현한 객체 생성
	    RemoteInterface inf = new RemoteServer();

	    //2. 구현한 객체를 클라이언트에서 찾을 수 있도록
	    //Registry객체를 생성해서 등록한다.

	    //포트번호를 지정하여 Registry 객체생성(기본 포트값 : 1099)
	    Registry reg = LocateRegistry.createRegistry(8888);

	    //Registry서버에 제공하는 객체 등록하기
	    //형식) Registry객체 변수/rebind("객체의 Alias",등록할 객체변수);
	    reg.rebind("server", inf);	//bind되지 않았을때 bind시켜주는 역할을 함

	    System.out.println("서버가 준비되었습니다.");

	} catch (RemoteException e) {
	    e.printStackTrace();
	}
	
	
	
    }
}
