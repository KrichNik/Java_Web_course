package practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Util {

	private static final String FILE_ENCODING = "UTF-8";

	public static String readFromFile(final String fileName) {
		String content = "";
		try {
			StringBuilder sb = new StringBuilder();
			Scanner scanner = new Scanner(new File(fileName), FILE_ENCODING);
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine()).append(System.lineSeparator());
			}
			content = sb.substring(0, sb.length() - System.lineSeparator().length());
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("file has not been read! " + e.getMessage());
		}
		return content;
	}

}
