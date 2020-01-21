package a_thread;

/**
 * 은행의 입출금을 쓰레드로 처리하는 예제
 * (synchronized을 이용한 동기화 처리)
 *
 */
public class T16_SyncAccountTest {

	public static void main(String[] args) {
		
		SyncAccount sAcc = new SyncAccount();
		sAcc.setBal(10000);	//입금처리
		
		BankThread bth1 = new BankThread(sAcc);
		BankThread bth2 = new BankThread(sAcc);
		
		bth1.start();
		bth2.start();
		
	}
	
}

//	은행의 입출금을 관리하는 클래스
class SyncAccount{
	
	private int balance;	//잔액이 저장될 변수
	
	public synchronized int getBalanece() {
		return balance;
	}
	
	public synchronized void setBal(int balance) {
		this.balance = balance;
	}
	
	//입금처리를 수행하는 메소드
	public synchronized void desposit(int money) {
		balance += money;
	}
	
	//출금처리를 수행하는 메소드(출금 성공 : true, 출금 실패 : false 반환)
	//동기화 영역에서 호출하는 메소드도 동기화 처리를 해주어야 한다.
	synchronized public boolean withdraw(int money) {
		
		if(balance >= money) {	//잔액이 많을 경우...
			
			for(int i = 1; i <= 1000000000; i++) {	}	//시간 때우기
			balance -= money;
			System.out.println("메소드 안에서 balance = " + getBalanece());
			return true;
		} else {
			return false;
		}
	}
}

//은행 업무를 처리하는 쓰레드
class BankThread extends Thread{
	
	private SyncAccount sAcc;
	
	public BankThread(SyncAccount sAcc) {
		this.sAcc = sAcc;
	}
	
	@Override
	public void run() {

		boolean result = sAcc.withdraw(6000);	//6000원 인출
		System.out.println("쓰레드 안에서 result = " + result + ", balance = " + sAcc.getBalanece());
	}
}



