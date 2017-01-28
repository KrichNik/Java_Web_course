package practice6.part3;

public class Parking {

	public static final int N = 10;

	private final Car[] parking = new Car[N];

	private int countCars = 0;

	public void park(Car car) {
		if (countCars == N) {
			System.out.println("No free spots!");
			return;
		}
		for (int i = 0; i < parking.length; i++) {
			if (parking[i] == null) {
				parking[i] = car;
				countCars++;
				System.out.println("Car " + car.getId() + " was successfully parked on the spot " + i);
				break;
			}
		}
	}

	public void leave(int indexPlace) {
		if (parking[indexPlace] == null) {
			System.out.println("This spot is free!");
			return;
		}
		Car car = parking[indexPlace];
		parking[indexPlace] = null;
		countCars--;
		System.out.println("Car " + car.getId() + " is leaved");
	}

	public void printInfoAboutParking() {
		System.out.println();
		System.out.println("Info about parking:");
		System.out.println("Parking size: " + N);
		System.out.println("Count of cars on parking: " + countCars);
		System.out.println("Free spots: " + (N - countCars));
		System.out.println();
	}
}
