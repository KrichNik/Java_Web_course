package practice6.part3;

public class Car {

	private static int count;

	private int id;

	public Car() {
		this.id = counter();
	}

	public int getId() {
		return id;
	}

	private static int counter() {
		return count++;
	}
}
