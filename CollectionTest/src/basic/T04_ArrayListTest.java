package basic;

import java.util.ArrayList;
import java.util.Scanner;

public class T04_ArrayListTest {
	
	/*
	 * 문제 1)	5명의 별명을 입력하여 ArrayList에 저장하고 별명의 길이가 제일 긴 별명을 출력하세요
	 * 			(단, 각 별명의 길이는 모두 다르게 입력한다.)
	 * 
	 * 문제 2)	문제1에서 별명의 길이가 같은 것을 여러개 입력했을 때도 처리되도록 하시오.
	 */
	
	public static void main(String[] args) {
		ArrayList<String> nickList = new ArrayList<>();
		Scanner s = new Scanner(System.in);
		
		
		System.out.println("5명의 별명을 차례로 입력하세요");
		for(int i=1; i<=5; i++){
			System.out.print(i + "번째 이름 : ");
			String nick = s.next();
			nickList.add(nick);
		}
		
		String maxNick = nickList.get(0);
		
		for(int i = 0; i < nickList.size(); i++) {
			
			if(maxNick.length() < nickList.get(i).length()){
				maxNick = nickList.get(i);
			}
		}
		System.out.println("제일 긴 별명은 : " + maxNick);
		
		int maxLength = nickList.get(0).length();
		for(int i = 1; i < nickList.size(); i++) {
			if(maxLength < nickList.get(i).length()) {
				maxLength = nickList.get(i).length();
			}
		}
		
		System.out.println("제일 긴 별명들 :");
		for(int i = 0; i < nickList.size(); i++) {
			if(maxLength == nickList.get(i).length()) {
				System.out.println(nickList.get(i));
			}
		}
		
		
	}
	
	
	
	
	
}
