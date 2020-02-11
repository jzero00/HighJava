package structural.decorator;

public abstract class ShapeDecotator implements Shape {

    protected Shape decoratedShape;
    
    public ShapeDecotator(Shape decoratedShape) {
	this.decoratedShape = decoratedShape;
    }
    
}
