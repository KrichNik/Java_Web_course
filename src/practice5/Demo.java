package practice5;

import practice5.part1.Part1;
import practice5.part2.Part2;
import practice5.part3.Part3;
import practice5.part4.Part4;
import practice5.part5.Part5;
import practice5.part6.Part6;

public class Demo {
	public static final String EOL = System.lineSeparator();

	public static void main(String[] args) throws InterruptedException {
		System.out.println("~~~~~~~~~~~~Part1~~~~~~~~~~~~");
		Part1.main(args);

		System.out.println(EOL + "~~~~~~~~~~~~Part2~~~~~~~~~~~~");
		Part2.main(args);

		System.out.println(EOL + "~~~~~~~~~~~~Part3~~~~~~~~~~~~");
		Part3.main(args);

		System.out.println(EOL + "~~~~~~~~~~~~Part4~~~~~~~~~~~~");
		Part4.main(args);

		System.out.println(EOL + "~~~~~~~~~~~~Part5~~~~~~~~~~~~");
		Part5.main(args);

		System.out.println(EOL + "~~~~~~~~~~~~part6~~~~~~~~~~~~");
		Part6.main(args);
	}

}
