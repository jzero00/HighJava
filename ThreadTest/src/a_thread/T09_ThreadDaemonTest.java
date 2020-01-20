package a_thread;

public class T09_ThreadDaemonTest {

	public static void main(String[] args) {
		
		AutoSaveThread autoSave = new AutoSaveThread();
		
		//데몬 쓰레드로 설정하기 : start()메소드를 호출하기 전에 설정한다.
		autoSave.setDaemon(true);
		autoSave.start();
		
		try {
			for(int i = 1; i <=20; i++) {
				System.out.println("작업 : " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("메인 쓰레드 종료...");
	}
}

/**
 * 자동저장하는 쓰레드
 * @author PC-05
 *
 */
class AutoSaveThread extends Thread{
	
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			save();		//저장 기능 호출
		}
	}
}