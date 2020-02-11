package structural.decorator;

public class RedShapeDecorator extends ShapeDecotator {

    public RedShapeDecorator(Shape decoratedShape) {
	super(decoratedShape);
    }

    @Override
    public void draw() {

	decoratedShape.draw();	//기본 기능
	setRedBorder(decoratedShape);	//빨간색 칠하기
	
    }
    
    private void setRedBorder(Shape decoratedShape) {
	
	System.out.println("경계선 색깔을 빨간색으로 칠하기");
    }

}
