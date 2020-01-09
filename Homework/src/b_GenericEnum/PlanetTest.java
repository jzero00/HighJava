package b_GenericEnum;

/*
	문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
	(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.) 
	
	예) 행성의 반지름(KM):
	수성(2439), 
	금성(6052), 
	지구(6371), 
	화성(3390), 
	목성(69911), 
	토성(58232), 
	천왕성(25362), 
	해왕성(24622)
 */
public class PlanetTest {
	public enum Planet{
		수성(2439), 
		금성(6052), 
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);
		
		private int num;
		
		private Planet(int num) {
			this.num = num;
		}
		
		public int getR(){
			return num;
		}
	}

	private static final double PHI = 3.14;
	
	public static void main(String[] args) {
		Planet p0 = Planet.수성;
		Planet p1 = Planet.금성;
		Planet p2 = Planet.지구;
		Planet p3 = Planet.화성;
		Planet p4 = Planet.목성;
		Planet p5 = Planet.토성;
		Planet p6 = Planet.천왕성;
		Planet p7 = Planet.해왕성;
		
		
		
		System.out.println(p0.ordinal() + 1 + "번째 별 : " + p0.name() + " : " + "표면적 넓이 : " + (p0.getR())*(p0.getR())*4*PHI +"제곱km 입니다.");
		System.out.println(p1.ordinal() + 1 + "번째 별 : " + p1.name() + " : " + "표면적 넓이 : " + (p1.getR())*(p1.getR())*4*PHI +"제곱km 입니다.");
		System.out.println(p2.ordinal() + 1 + "번째 별 : " + p2.name() + " : " + "표면적 넓이 : " + (p2.getR())*(p2.getR())*4*PHI +"제곱km 입니다.");
		System.out.println(p3.ordinal() + 1 + "번째 별 : " + p3.name() + " : " + "표면적 넓이 : " + (p3.getR())*(p3.getR())*4*PHI +"제곱km 입니다.");
		System.out.println(p4.ordinal() + 1 + "번째 별 : " + p4.name() + " : " + "표면적 넓이 : " + (long)(p4.getR())*(p4.getR())*4*PHI +"제곱km 입니다.");
		System.out.println(p5.ordinal() + 1 + "번째 별 : " + p5.name() + " : " + "표면적 넓이 : " + (p5.getR())*(p5.getR())*4*PHI +"제곱km 입니다.");
		System.out.println(p6.ordinal() + 1 + "번째 별 : " + p6.name() + " : " + "표면적 넓이 : " + (long)(p6.getR())*(p6.getR())*4*PHI +"제곱km 입니다.");
		System.out.println(p7.ordinal() + 1 + "번째 별 : " + p7.name() + " : " + "표면적 넓이 : " + (long)(p7.getR())*(p7.getR())*4*PHI +"제곱km 입니다.");


		
	}
}
