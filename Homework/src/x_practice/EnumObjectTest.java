package x_practice;

enum Currency{
	DOLLLAR(1100), EURO(1500), YEN(1000), YUAN(150);
	int value; 
	private Currency(int value){
		this.value = value;
	}
	public int getValue(){
		return value;
	}
}

public class EnumObjectTest {
	
	public static void main(String[] args) {
		Currency curCurrency = Currency.EURO;
		switch(curCurrency){
		case DOLLLAR :
			System.out.println("달러의 환율은 " + curCurrency.getValue() + "원 입니다.");
			break;
		case EURO :
			System.out.println("유로화의 환율은 " + curCurrency.getValue() + "원 입니다.");
			break;
		case YEN:
			System.out.println("옌의 환율은 " + curCurrency.getValue() + "원 입니다.");
			break;
		case YUAN:
			System.out.println("위안의 환율은 " + curCurrency.getValue() + "원 입니다.");
			break;
		}
	}
}

