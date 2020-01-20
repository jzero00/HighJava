package d_Thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HorseRacingBeta {

	public static void main(String[] args) {

		List<Horse2> list = new ArrayList<Horse2>();

		list.add(new Horse2("1번말"));
		list.add(new Horse2("2번말"));
		list.add(new Horse2("3번말"));
		list.add(new Horse2("4번말"));
		list.add(new Horse2("5번말"));
		list.add(new Horse2("6번말"));
		list.add(new Horse2("7번말"));
		list.add(new Horse2("8번말"));
		list.add(new Horse2("9번말"));
		list.add(new Horse2("10번말"));

		// 말달리는 메서드
		for (Horse2 horse : list) {
			horse.start();
		}
		​
		PrintHorse ph = new PrintHorse(list);
		ph.start();

	}

}

class PrintHorse extends Thread {

	List<Horse2> list;

	public PrintHorse(List<Horse2> h1) {

		this.list = h1;

	}

	@Override
	public void run() {
		String[] arr = new String[50];
		int rnk = 1;
		boolean ing = true;
		while (ing) {
			​
			for (Horse2 h2 : list) {
				if(h2.isGoal()==true) {
					System.out.print(h2.getName() + " : ");
					for (int j = 0; j < 50; j++) {
						arr[j] = "*";
						System.out.print(arr[j]);
					}
					System.out.println();
					continue;
				}

				// 말 달리는거 위치표시
				System.out.print(h2.getName() + " : ");
				for (int i = 0; i < 50; i++) {
					arr[i] = "-";
					if (h2.getLocation() == i) {
						arr[i] = ">";
					}
				}


				// 말 10마리 출력
				for (int j = 0; j < 50; j++) {
					System.out.print(arr[j]);
				}
				System.out.println();
				​
				// 순위매기기
				if (h2.getLocation() >= 50) {
					h2.setRank(rnk);
					rnk++;
					h2.setGoal(true);
				}
				​
			} // end for
			​
			//일정 시간마다 출력
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("=========================================");

			//말 다 들어오면 경기 끝
			if (rnk==11) {

				System.out.println("=========경기 끝!=======");
				ing=false;
			}
		}// end while
		Collections.sort(list);
		for (Horse2 h2 : list) {
			System.out.printf("%3d 등  :  %3s",h2.getRank(),h2.getName());
			System.out.println();
		}
		System.exit(0);
	}
}


class Horse2 extends Thread implements Comparable<Horse2>{

	private String name;
	private int rank = 0;
	private int location = 0;
	public boolean goal = false;	//결승점 통과 여부

	public Horse2(String string) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {

		int cnt = 0;

		while(true) {
			location += cnt;

			try {
				Thread.sleep(1000 * (int)(Math.random() * 4));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(location == 50) {
				break;
			}
			cnt++;
		}
	}

	@Override
	public int compareTo(Horse2 o) {
		// TODO Auto-generated method stub
		return 0;
	}
}