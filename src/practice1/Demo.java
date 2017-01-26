package practice1;

import practice1.part1.Hello;
import practice1.part2.Sum;
import practice1.part3.ParseSum;
import practice1.part4.GratestCommonDivisor;
import practice1.part5.Spreadsheet;

public class Demo {

	public static void main(String... args) {

		System.out.println("Part1:");
		Hello.main();

		System.out.println("Part2:");
		Sum.main(new String[] {"10", "20"});

		System.out.println("Part3:");
		GratestCommonDivisor.main(new String[] {"20", "38"});

		System.out.println("Part4:");
		ParseSum.main(new String[] {"423"});

		System.out.println("Part5:");
		Spreadsheet.main();
	}

}
