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
		
		System.out.println(p0.ordinal() + 1 + "번째 별은 " + p0.name() + "이고 " + "표면적의 넓이는 " + (p0.getR())*(p0.getR())*4*PHI +"제곱km 입니다.");


		
	}
}
