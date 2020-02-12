package inf;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * RMI용 인터페이스
 * (Remote를 상속해야 한다.)
 */
public interface RemoteInterface extends Remote {

    //이 인터페이스에서 선언된 모든 메소드는 RemoteException을 throws해야 한다.
    
    //이곳에서 선언된 메소드의 파라미터 변수는 클라이언트에서 보내오는 데이터가 저장되고,
    //반환값은 클라이언트 쪽으로 보내는 데이터가 된다.
    
    public void doViewMain() throws RemoteException;
    
}
