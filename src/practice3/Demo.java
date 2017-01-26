package practice3;


import practice3.part1.Part1;
import practice3.part2.Part2;
import practice3.part3.Part3;
import practice3.part4.Part4;
import practice3.part5.Part5;

public class Demo {
	public static final String EOL = System.lineSeparator();

	public static void main(String[] args) {
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
	}

}
