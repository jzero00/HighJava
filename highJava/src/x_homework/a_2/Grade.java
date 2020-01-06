package x_homework.a_2;

import java.util.ArrayList;
import java.util.List;

public class Grade {

	public static void main(String[] args) {
		
		List<Student> studentList = new ArrayList<>();
		
		studentList.add(new Student("20200101", "박명수", 80, 90, 85));
		studentList.add(new Student("20200102", "유재석", 70, 80, 90));
		studentList.add(new Student("20200103", "정준하", 60, 70, 50));
		studentList.add(new Student("20200104", "정형돈", 90, 80, 65));
	}

}

class Student implements Comparable<Student>{
	
	private String studentId;
	private String name;
	private int kor;
	private int eng;
	private int math;
	
	public Student(String studentId, String name, int kor, int eng, int math) {
		super();
		this.studentId = studentId;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
	}
	
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	@Override
	public String toString() {
		return "Member [학번 : " + studentId + ", 이름 : " + name + ", 국어점수 : " + kor + ", 영어점수 : " + eng + ", 수학점수 : " + math
				+ "]";
	}

	@Override
	public int compareTo(Student o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
	