package a_thread;
/*
 * 	wait()메소드 → 동기화 영역에서 락을 풀고 Wait-Set 영역 (공유 객체별 존재)으로 
 * 				이동시킨다.
 * 	notify() 또는 notifyAll() 메소드 → Wait-Set영역에 있는 쓰레드를 깨워서
 * 									실행될 수 있도록 한다.
 * 			<notify()는 하나, notifyAll()은 Wait-Set에 있는 전부를 깨운다>
 * 	→ wait()와 notify(), notifyAll()메소드는 동기화 영역에서만 실행할 수 있다.
 * 		Object클래스에서 제공하는 메소드이다.
 */

/**
 * wait()과 notify()를 이용한 두 쓰레드가 번갈아가면서 
 * 한번씩 실행하는 예제
 * @author PC-05
 *
 */
public class T19_WiatNotifyTest {

	public static void main(String[] args) {
	
		WorkObject workObj = new WorkObject();
		
		ThreadA thA = new ThreadA(workObj);
		ThreadB thB = new ThreadB(workObj);
		
		thA.start();
		thB.start();
		
	}
	
}

//공통으로 사용할 객체
class WorkObject {
	
	public synchronized void methodA() {
		System.out.println("methodA()메소드 작업중....");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void methodB() {
		System.out.println("methodB()메소드 작업중...");
		
		notify();
		
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

//WorkObject의 methodA()메소드만 호출하는 쓰레드
class ThreadA extends Thread{
	
	private WorkObject workObj;
	
	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			workObj.methodA();
		}
		System.out.println("ThreadA 종료");
	}
}

//WorkObject의 methodB()메소드만 호출하는 쓰레드
class ThreadB extends Thread{
	
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 10; i++) {
			workObj.methodB();
		}
		System.out.println("ThreadB 종료");
	}
}