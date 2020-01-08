package lecture;

import java.util.ArrayList;
import java.util.Scanner;

public class T03_ArrayListTest {

	public static void main(String[] args) {
		
	/*
	 * 문제)	5명의 사람 이름을 입력하여 ArrayList에 저장하고
	 * 		이중에 '김'씨 성의 이름을 출력하시오
	 * 		(단, 입력은 Scanner를 이용하여 입력받는다.
	 */
		ArrayList<String> nameList = new ArrayList<>();
		Scanner s = new Scanner(System.in);
		
		
		System.out.println("5명의 이름을 차례로 입력하세요");
		for(int i=1; i<=5; i++){
			System.out.print(i + "번째 이름 : ");
			String name = s.next();
			nameList.add(name);
		}
		for(int i=0; i<nameList.size(); i++){
			String name = nameList.get(i);
			if(name.charAt(0)=='김'){
				System.out.println(name);
			}
			
		}
		
	}

}
