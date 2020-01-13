package set;

import java.util.HashSet;

public class Hash {

	public static void main(String[] args) {
		
		HashSet<Integer> A = new HashSet<Integer>();
		A.add(1);
		A.add(2);
		A.add(3);
		
		HashSet<Integer> B = new HashSet<Integer>();
		B.add(3);
		B.add(4);
		B.add(5);
		
		HashSet<Integer> C = new HashSet<Integer>();
		C.add(1);
		C.add(2);
		
		System.out.println(A.containsAll(B)); // false	//A에 B의 내용이 모두 포함되는지 여부 반환
		System.out.println(A.containsAll(C)); // true
		
		A.addAll(B);	//A에 B를 모두 추가
		System.out.println(A);
		
		B.clear();		//B 안의 모든 내용 삭제
		System.out.println(B);
		
		System.out.println(A.equals(B));	//A와 B가 같은지 여부 반환
		
		System.out.println(A.hashCode());	//A의 Hashcode를 반환
		
		System.out.println(B.isEmpty());	//B의 내용이 있는지 없는지 여부를 반환
		
		System.out.println(A.iterator());

		
		
	}	
}
