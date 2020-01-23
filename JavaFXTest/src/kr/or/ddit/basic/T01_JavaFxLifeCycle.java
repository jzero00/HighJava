package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.stage.Stage;

/*
 *	Stage(무대) → Window창
 *	Scene(장면) → 무대에는 하나의 장면이 배치된다
 *
 *	- javaFx가 실행되는 순서
 *
 *	main()메소드 → launch()메소드 → 해당 객체의 생성자 메소드
 *	→ init()메소드 → start()메소드 → 사용후 종료
 *	→ stop()메소드
 */
public class T01_JavaFxLifeCycle extends Application{

	public T01_JavaFxLifeCycle() {
		
		System.out.println(Thread.currentThread().getName() + " : 생성자 메소드 호출");
		
	}
	
	@Override
	public void init() throws Exception {
		
		System.out.println(Thread.currentThread().getName() + " : init() 메소드 호출");
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		System.out.println(Thread.currentThread().getName() + " : start() 메소드 호출");
		
		primaryStage.show();
		
	}
	
	@Override
	public void stop() throws Exception {
		
		System.out.println(Thread.currentThread().getName() + " : stop() 메소드 호출");
		
	}


	public static void main(String[] args) {
	
		//둘중에 하나를 선택해서 사용
		Application.launch(args);
//		launch(args);
		System.out.println(Thread.currentThread().getName() + " : main() 메소드 호출");
		
	}
	
}
