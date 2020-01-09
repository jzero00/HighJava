package a_CollectionFramework;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HotelBook {

	public static void main(String[] args) {


		startMenu();



	}

	private static void startMenu() {
		boolean flag = true;
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
			case 1:	checkIn();
			break;

			case 2: checkOut();
			break;
			
			case 3: roomStatus();
			break;
			
			case 4: terminate();
			flag = false;
			break;
			}	
		}while(flag);




	}
	



	/*
	 * 어느방에 체크인 하시겠습니까? 방번호 입력 => 101 <-- 입력
	 * 
	 * 누구를 체크인 하시겠습니까? 이름 입력 => 홍길동 <-- 입력 체크인 되었습니다.
	 *******************************************
	 * 
	 * 어떤 업무를 하시겠습니까? 1.체크인 2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	 * 메뉴선택 => 1 <-- 입력
	 * 
	 * 어느방에 체크인 하시겠습니까? 방번호 입력 => 102 <-- 입력
	 * 
	 * 누구를 체크인 하시겠습니까? 이름 입력 => 성춘향 <-- 입력 체크인 되었습니다
	 *******************************************
	 * 
	 * 어떤 업무를 하시겠습니까? 1.체크인 2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	 * 메뉴선택 => 3 <-- 입력
	 * 
	 * 방번호 : 102, 투숙객 : 성춘향 방번호 : 101, 투숙객 : 홍길동
	 *******************************************
	 * 
	 * 어떤 업무를 하시겠습니까? 1.체크인 2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	 * 메뉴선택 => 2 <-- 입력
	 * 
	 * 어느방을 체크아웃 하시겠습니까? 방번호 입력 => 101 <-- 입력 체크아웃 되었습니다.
	 *******************************************
	 * 
	 * 어떤 업무를 하시겠습니까? 1.체크인 2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	 * 메뉴선택 => 1 <-- 입력
	 * 
	 * 어느방에 체크인 하시겠습니까? 방번호 입력 => 102 <-- 입력
	 * 
	 * 누구를 체크인 하시겠습니까? 이름 입력 => 허준 <-- 입력 102방에는 이미 사람이 있습니다.
	 *******************************************
	 * 
	 * 어떤 업무를 하시겠습니까? 1.체크인 2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	 * 메뉴선택 => 2 <-- 입력
	 * 
	 * 어느방을 체크아웃 하시겠습니까? 방번호 입력 => 101 <-- 입력 101방에는 체크인한 사람이 없습니다.
	 *******************************************
	 * 
	 * 어떤 업무를 하시겠습니까? 1.체크인 2.체크아웃 3.객실상태 4.업무종료
	 *******************************************
	 * 메뉴선택 => 3 <-- 입력
	 * 
	 * 방번호 : 102, 투숙객 : 성춘향
	 *******************************************
	 **/


	private static void checkIn() {
		Scanner s = new Scanner(System.in);
		System.out.println("어느방에 체크인 하시겠습니까?\n"
				+ "방번호 입력 => ");
		int roomNo = Integer.parseInt(s.nextLine());
		System.out.println("누구를 체크인 하시겠습니까?\n"
				+ "이름 입력 => ");
		String name = s.nextLine();
		Map<Integer, String> room = new HashMap<Integer, String>();
		if(room.get(roomNo) == null) {
			room.put(roomNo, name);	
			System.out.println("체크인 되었습니다.");
		}else {
			System.out.println(room.get(roomNo) + "방에는 이미 사람이 있습니다.");
		}
	}
	
	private static void checkOut() {
		
		
	}
	
	private static void roomStatus() {
		// TODO Auto-generated method stub
		
	}
	
	private static void terminate() {
		
		System.out.println("**************************\r\n" + 
				"	 * \r\n" + 
				"	 * 호텔 문을 닫았습니다.\r\n" + 
				"	 **************************");
		
	}
}
