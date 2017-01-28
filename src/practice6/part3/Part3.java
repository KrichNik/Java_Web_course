package practice6.part3;

/**
 * Реализовать класс, моделирующий работу n-местной автостоянки. Машина
 * подъезжает к определенному месту и едет вправо, пока не встретится свободное
 * место. Класс должен поддерживать методы, обслуживающие приезд и отъезд
 * машины. Определить метод, который выводит в консоль текущее состояние
 * стоянки.
 *
 * 23.11.16
 *
 * @author Nikita Datsenko
 */
public class Part3 {

	public static void main(String[] args) {
		init();
	}

	static void init() {
		Parking parking = new Parking();
		parking.printInfoAboutParking();

		for (int i = 1; i <= Parking.N; i++) {
			parking.park(new Car());
		}
		parking.leave(3);
		parking.leave(8);

		parking.printInfoAboutParking();
		parking.park(new Car());
		parking.park(new Car());
		parking.printInfoAboutParking();
	}

}
