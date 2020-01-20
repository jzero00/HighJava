package a_CollectionFramework;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelReservation {

	public static void main(String[] args) {
		
		startMenu();
		
	}

	private static void startMenu() {
		boolean flag = true;
		Map<String, Hotel> map = new HashMap<>();
		Scanner s = new Scanner(System.in);
		System.out.println("**************************\n"
				+ "호텔 문을 열었습니다.\n"
				+ "**************************\n\n"
				+ "*******************************************\n");
		do {
			System.out.println("어떤 업무를 하시겠습니까?\n"
					+ "1.체크인  2.체크아웃 3.객실상태 4.업무종료");
			int menu = Integer.parseInt(s.nextLine());
			
			switch(menu) {
			case 1:	checkIn(map);
			break;

			case 2: checkOut(map);
			break;
			
			case 3: roomStatus(map);
			break;
			
			case 4: terminate();
			flag = false;
			break;
			}	
		}while(flag);

	}
	

	private static void checkIn(Map<String, Hotel> map) {
		String room;
		String name;
		Scanner s = new Scanner(System.in);
		System.out.println("어느방에 체크인 하시겠습니까?\n"
				+ "방번호 입력 => ");
		room = s.nextLine();
		System.out.println("누구를 체크인 하시겠습니까?\n"
				+ "이름 입력 => ");
		name = s.nextLine();
		
		if (map.get(room) != null) {
			System.out.println(room + "방에는 이미 사람이 있습니다.");
			return;
		}
		System.out.println("체크인 되었습니다.");		
		Hotel hotel = new Hotel(room, name);
		map.put(room, hotel);
	}
	
	private static void checkOut(Map<String, Hotel> map) {
		
		Scanner s = new Scanner(System.in);
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		String room = s.nextLine();
		if (map.get(room) == null) {
			System.out.println(room + "방에는 체크인한 사람이 없습니다.\r\n");
			return;
		}
		map.remove(room);
		System.out.println("체크아웃 되었습니다.");
		
	}
	
	private static void roomStatus(Map<String, Hotel> map) {
		
		if (map.size() == 0) {
			System.out.println("현재 투숙객이 존재하지 않습니다.\r\n");
			return;
		}
		for (String s : map.keySet()) {
			System.out.println("방번호 : "+ s + ", 투숙객 : "+ map.get(s).getName());
		}
	
	}
	
	private static void terminate() {
		
		System.out.println("**************************\r\n" + 
				"* \r\n" + 
				"* 호텔 문을 닫았습니다.\r\n" + 
				"**************************");
		
	}
}
class Hotel{
	
	private String room; // 방번호
	private String name; // 이름
	
	public Hotel(String room, String name) {
		super();
		this.room = room;
		this.name = name;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}