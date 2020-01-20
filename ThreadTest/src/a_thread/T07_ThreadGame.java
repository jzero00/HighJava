package a_thread;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오
 * 
 * 컴퓨터의 가위 바위 보는 난수를 이용하여 구하고
 * 
 * 사용자의 가위 바위 보는 showInputDialog()메소드를 이용하여 입력받는다.
 * 
 * 입력 시간은 5초로 제한하고 카운트다운을 진행한다.
 * 	5초동안 입력이 없으면 게임을 진것으로 처리한다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 
 * 결과 예시)
 * == 결과 ==
 * 컴퓨터 	: 가위
 * 당  신 	: 바위
 * 결  과	: 당신이 이겼습니다.
 */

public class T07_ThreadGame {
	
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		
		Thread th2 = new CountDown5();
		Thread th1 = new GameResult();
		
		th2.start();
		th1.start();

	}
}
class CountDown5 extends Thread{
	
	public void run() {
		for(int i = 5; i >= 1; i--) {
			
			if(T07_ThreadGame.inputCheck == true) {
				return;	//run()메소드가 종료되면 쓰레드도 끝난다.
			}
			
			System.out.println(i);

			try {
				Thread.sleep(1000);	//1초 동안 잠시 멈춘다.
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//10초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("5초가 지났습니다. You의 패배");
		System.exit(0);
	}
}

class GameResult extends Thread {
	
	@Override
	public void run() {
		String user = JOptionPane.showInputDialog("가위, 바위, 보를 입력하세요.");

		//입력이 완료되면 inputCheck변수를 true로 변경한다.
		T07_ThreadGame.inputCheck = true;

		int com = (int)(Math.random() * 3);
		String rst = null;
		
		if(user == "가위") {
			if(com == 0) {
				rst = "비겼습니다.";
			}
			else if (com == 1) {
				rst = "졌습니다.";
			} else {
				rst = "이겼습니다.";
			}
		} else if (user == "바위") {
			if(com == 0) {
				rst = "졌습니다.";
			}
			else if (com == 1) {
				rst = "비겼습니다.";
			} else {
				rst = "이겼습니다.";
			}
		} else if (user == "보") {
			if(com == 0) {
				rst = "이겼습니다.";
			}
			else if (com == 1) {
				rst = "졌습니다.";
			} else {
				rst = "비겼습니다.";
			}
		}
		
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터 : ");
		System.out.println("당  신 : ");
		System.out.println("You 가" + rst);
	}
}


