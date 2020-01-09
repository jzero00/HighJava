package a_CollectionTest;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/*
 * 문제 ) Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오.
 * 컴퓨터의 숫자는 난수를 이용하여 구한다.
 * (스타라이크 'S', 볼은 'B'로 출력한다.)
 */
public class T11_BaseBallTest {

	public static void main(String[] args) {
		
		Set<Integer> intRndNo = new HashSet<>();

		Scanner s = new Scanner(System.in);
		int cnt = 1;

		int strk = 0;
		int ball = 0;
		int out	= 0;
		
		while (intRndNo.size() < 3) {
			int num = (int)(Math.random() * 9 + 1);
			intRndNo.add(num);
		}
		
//		Iterator<Integer> it = numSet.iterator;
		
		for(Integer num : intRndNo) {
			System.out.print(num);
		}
		
		do {
			System.out.println("숫자를 입력해주세요 → ");
			int input = Integer.parseInt(s.nextLine());
			int i1 = input/100;
			int i2 = (input/10)%10;
			int i3 = input%10;

			if(intRndNo.contains(i1)) {
				System.out.println(cnt + "번째 만에 성공");
			}
			cnt ++;
		}while(strk != 3);
		
	}
	
}
