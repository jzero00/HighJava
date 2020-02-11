package creational.builder;

public class Bottle implements Packing {

    @Override
    public String pack() {
	return "병으로 포장함";
    }
}
