package d_Thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RunningHorse {

	public static void main(String[] args) {

		List<Horse> list = new ArrayList<Horse>();

		list.add(new Horse("두산"));
		list.add(new Horse("키움"));
		list.add(new Horse(" SK"));
		list.add(new Horse(" LG"));
		list.add(new Horse(" NC"));
		list.add(new Horse(" KT"));
		list.add(new Horse("KIA"));
		list.add(new Horse("삼성"));
		list.add(new Horse("한화"));
		list.add(new Horse("롯데"));

		// 말달리는 메서드
		for (Horse horse : list) {
			horse.start();
		}

		PrintHorse print = new PrintHorse(list);
		print.start();
	}

}

class PrintHorse extends Thread {
	List<Horse> list;

	public PrintHorse(List<Horse> h1) {
		this.list = h1;
	}

	@Override
	public void run() {
		String[] arr = new String[50];
		int rank = 1;
		boolean running = true;
		while (running) {

			for (Horse h2 : list) {
				if (h2.isGoal() == true) {
					System.out.print(h2.getHName() + ": ");
					for (int j = 0; j < 50; j++) {
						arr[j] = "*";
						System.out.print(arr[j]);
					}
					System.out.println();
					continue;
				}

				// 말 달리는거 위치표시
				System.out.print(h2.getHName() + ": ");
				for (int i = 0; i < 50; i++) {
					arr[i] = "-";
					if (h2.getSection() == i) {
						arr[i] = "▶";
					}
				}

				// 말 10마리 출력
				for (int j = 0; j < 50; j++) {
					System.out.print(arr[j]);
				}
				System.out.println();

				// 순위매기기
				if (h2.getSection() >= 50) {
					h2.setRank(rank);
					rank++;
					h2.setGoal(true);
				}

			}

			// 일정 시간마다 출력
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("=========================================================");

			// 말 다 들어오면 경기 끝
			if (rank == 11) {

				System.out.println("=========경기 끝!=======");
				running = false;
			}
		}
		Collections.sort(list);
		for (Horse h2 : list) {
			System.out.print(h2.getRank() + "등  :" + h2.getHName());
			System.out.println();
		}
		System.exit(0);
	}

}

// 말 클래스
class Horse extends Thread implements Comparable<Horse> {

	private String name;
	private int rank;
	private int section = 0;
	public volatile boolean isGoal = false; // 결승지점 통과 여부

	public Horse(String name) {
		super();
		this.name = name;
		this.isGoal = isGoal;
	}

	public String getHName() {
		return name;
	}

	public boolean isGoal() {
		return isGoal;
	}

	public void setGoal(boolean goal) {
		this.isGoal = goal;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getSection() {
		return section;
	}

	@Override
	public void run() {
		int cnt = 0;
		while (true) {
			section += cnt;
			try {
				Thread.sleep(1000 * (int) (Math.random() * 4));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (section == 50) {
				break;
			}
			cnt++;
		}
	}

	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(this.rank, horse.getRank());
	}

}