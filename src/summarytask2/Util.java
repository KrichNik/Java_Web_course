package summarytask2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class allows you to read data from a file.
 *
 * @see Main
 * @author Nikita Datsenko
 *
 */
public class Util {

	private static final String FILE_ENCODING = "UTF-8";

	private String inputFileName;

	public Util(String inputFileName) {
		this.inputFileName = inputFileName;
	}

	public String readFromFile() {
		String content = "";
		try {
			StringBuilder sb = new StringBuilder();
			Scanner scanner = new Scanner(new File(inputFileName), FILE_ENCODING);
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
