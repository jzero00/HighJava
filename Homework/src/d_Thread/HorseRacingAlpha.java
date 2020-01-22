package d_Thread;

import java.util.ArrayList;
import java.util.List;

public class HorseRacingAlpha {

	public static void main(String[] args) {

		List<Horse2> list = new ArrayList<Horse2>();
		
		list.add(new Horse2("바르샤", new Rank()));
		list.add(new Horse2("레알 마드리드", new Rank()));
		list.add(new Horse2("AT 마드리드", new Rank()));
		list.add(new Horse2("세비야", new Rank()));
		list.add(new Horse2("헤타페", new Rank()));
		list.add(new Horse2("레알 소시에다드", new Rank()));
		list.add(new Horse2("발렌시아", new Rank()));
		list.add(new Horse2("아틀레틱", new Rank()));
		list.add(new Horse2("비야레알", new Rank()));
		list.add(new Horse2("그라나다", new Rank()));

		/** 말 쓰레드 5개 실행*/
		for(Horse2 horse : list) {
			horse.start();
		}
		
//		PrintHorse ph = new PrintHorse(list);

	}

}

class DisplayRun extends Thread{

	private String name;
	private int rank;


}

/*//말이 달리는 장면을 출력하는 쓰레드 클래스
class DiplayRunning extends Thread{

	List<Horse> list;

	public PrintHorse(List<Horse> h1) {
		this.list = h1;
	}

	@Override
	public void run() {

		String[] arr = new String[50];
		int rank = 1;
		boolean running = true;

		while(running) {

			for()
		}
	}
}*/




class Horse2 extends Thread {

	private String name;
	int section = 0;
	Rank rank;
	static int count = 1;
	public boolean goal = false; // 결승지점 통과 여부

	public Horse2() {

	}

	public Horse2(String name, Rank rank) {

		this.name = name;
		this.rank = rank;

	}

	@Override
	public void run() {

		String[] arr = new String[50];
//		System.out.println(name + " 말의 남은 거리 : " + section);


		try {
			while (true) {
				/** 1~4 사이의 난수 발생후 sleep */
				sleep((int) (Math.random() * 401 + 100));
				for(int i = 0; i <= 50; i++) {

					arr[i] = "-";

					if (i == section) {
						System.out.print(">");
					}
					/** 말이 section 1 단위로 이동 */
					section += 1;

					/** 50번째 구간이 결승점이라  50이되면 결승점이기 때문에 rank.finishLine(말의 이름)실행 */
					if (section == 50) {
						this.rank.finishLine(name);
						break;
					}

				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


	}
}

class Rank {
	/** 말의 등수 */
	int rank;

	public Rank() {
		// TODO Auto-generated constructor stub
	}

	public void finishLine(String name) {
		// TODO Auto-generated method stub
		rank = Horse2.count++;
		System.out.println(name + " 말 " + rank + "등으로 결승점 도착");


		if (rank == 1) {
			/** 첫번째 매개변수는 Frame지정인데 없기때문에 기본frame로 띄운다 없으면 null지정 가능. */

		}

	}

}
