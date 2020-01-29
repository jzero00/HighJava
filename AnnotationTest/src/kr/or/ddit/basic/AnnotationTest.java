package kr.or.ddit.basic;

import java.lang.reflect.Method;

public class AnnotationTest {

	public static void main(String[] args) {

		System.out.println("id = " + PrintAnnotation.id);

		//reflection 기능을 이용한 메소드 실행
		Method[] declaredMethods = Service.class.getDeclaredMethods();

		for(Method m : declaredMethods) {

			System.out.println(m.getName());	//메소드명 출력

			//PrintAnnotation의 count값 만큼....
			for(int i = 0; i < m.getDeclaredAnnotation(PrintAnnotation.class).count(); i++) {

				//PrintAnnotation의 value값 출력
				System.out.print(m.getDeclaredAnnotation(PrintAnnotation.class).value());
			}
			System.out.println();	//줄바꿈 처리

			Class<Service> clazz = Service.class;
			try {
				Service service = (Service) clazz.newInstance();
				m.invoke(service);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

/*
 * 	Java Reflection에 대하여...
 * 
 * 	1. 리플렉션은 클래스 또는 멤버변수, 메소드, 생성자에 대한 정보를 가져오거나 수정할 수 있다.
 * 	2. Reflection API는 java.lang.reflection 패키지와 java.lang.Class를
 * 	통해 제공된다.
 * 	3. Java.lang.Class의 주요 메소드
 * 	- getName(), getSuperclass(), getInterfaces(), getModifiers()
 * 	4. java.lang.reflect 패키지의 주요 클래스
 * 	- Field, Method, Constructor, Modifier 등.
 * 	5. Reflection API를 이용하면 클래스의 private 메소드나 변수에 접근 가능하다.
 * 	(보안위협)
 * 	6. Reflection API의 기능은 뛰어나지만, 약간의 오버헤드가 발생한다.(느린 수행 속도,
 * 	보안 취약성, 권한 문제등 ) 그러므로, 가급적 마지막 수단으로 사용하도록 고려되어야 한다.
 */
