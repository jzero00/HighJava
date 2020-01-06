package x_homework.a_2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Grade {

	public static void main(String[] args) {

		List<Student> studentList = new ArrayList<>();

		studentList.add(new Student("20200102", "유재석", 70, 80, 90));
		studentList.add(new Student("20200104", "정형돈", 90, 85, 65));
		studentList.add(new Student("20200101", "박명수", 80, 90, 85));
		studentList.add(new Student("20200103", "정준하", 60, 70, 50));

		System.out.println("정렬 전 : ");
		for(Student student : studentList) {
			System.out.println(student);
		}

		System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");

		Collections.sort(studentList);
		System.out.println("학번순서로 오름차순 정렬 후 : ");
		for(Student student : studentList) {
			System.out.println(student);
		}
		
		System.out.println("☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");
		System.out.println("성적 순서로 내림차순 정렬 후 : ");
		Collections.sort(studentList, new Comparator<Student>() {
			public int compare(Student s1, Student s2) {
				int s1sum = s1.getEng() + s1.getKor() + s1.getMath();
				int s2sum = s2.getEng() + s2.getKor() + s2.getMath();
				
				return Integer.compare(s2sum, s1sum);
			}
		});
		
		for(Student student : studentList) {
			System.out.println(student);
		}
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
		int sum = eng + kor + math;
		return "Student 학번 : " + studentId + ", 이름 : " + name + " 국어점수 : " + kor + ", 영어점수 : " + eng + ", 수학점수 : " + math
				+ " 총점 : " + sum + "점";
	}


	@Override
	public int compareTo(Student student) {

		return student.getStudentId().compareTo(studentId) * -1;
	}


}
class SortGradeDesc implements Comparator<Student>{

	@Override
	public int compare(Student student1, Student student2) {
		int sum1 = student1.getEng() + student1.getKor() + student1.getMath();
		int sum2 = student2.getEng() + student2.getKor() + student2.getMath();
		if (sum1 > sum2) {
			return -1;
		}else if(sum1 == sum2) {
			return 0;				
		}else {
			return 1;
		}
		//			return new Integer(student1.getEng() + student1.getKor() + student1.getMath())
		//					.compareTo(student2.getEng() + student2.getKor() + student2.getMath()) * -1;
	}

}