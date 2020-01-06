package a_1_lotto;

import java.util.ArrayList;
import java.util.Scanner;

public class lotto {

	public static void main(String[] args) {

		System.out.println("==========================");
		System.out.println("     Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("1. Lotto 구입\n2. 프로그램 종료");
		System.out.println("==========================");
		System.out.print("메뉴 선택 : ");
		Scanner s = new Scanner(System.in);

		int menu = Integer.parseInt(s.nextLine());
		if (menu == 1) {
			System.out.println("Lotto 구입 시작");
			System.out.println("(1000원에 로또번호 하나입니다.)");
			System.out.println("금액 입력 : ");
			int money = Integer.parseInt(s.nextLine());
			System.out.println("로또 번호는 다음과 같습니다.");
			int cnt = money / 1000;
			
			for (int i = 0; i < cnt; i++) {
				ArrayList<Integer> lotto = new ArrayList<Integer>();
				for(int j = 0; j <= 5; j++) {
					lotto.add((int)(Math.random() * 45) + 1);

					
					
					
					
					if(j==0) {					
						System.out.print("로또번호" + (j+1) + ": ");
					}
					if (j == 5) {
						System.out.print(lotto.get(j));
					}else {
						System.out.print(lotto.get(j) + ", ");
					}
				}
				System.out.println();				
			}


			System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " +
					money%1000 + "원입니다.");
		}
		else if (menu == 2) {
			System.out.println("감사합니다.");
		}
		else {
			System.out.println("다시 입력해주세요.");
		}


	}
	/*
	 *  로또를 구매하는 프로그램 작성하기


	로또번호1 : 2,3,4,5,6,7
	로또번호2 : 20,21,22,23,24,25

	받은 금액은 2500원이고 거스름돈은 500원입니다.

   	 ==========================
         Lotto 프로그램
	--------------------------
	  1. Lotto 구입
	  2. 프로그램 종료
	==========================		 
	메뉴선택 : 2  <-- 입력

	감사합니다
	 */

}
