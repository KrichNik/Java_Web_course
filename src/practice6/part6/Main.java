package practice6.part6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try {
			initialize(args);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void initialize(String[] args) throws FileNotFoundException {
		String fileName;
		String taskName;
		if (args.length > 1) {
			fileName = args[1];
			taskName = args[3];
		} else {
			String inputData = args[0];
			String[] inputSplit = inputData.split("\\s");
			fileName = inputSplit[1];
			taskName = inputSplit[3];
		}
		String inputText = getTextFromFile(fileName);
		analyzeText(inputText, taskName);
	}

	public static String getTextFromFile(String fileName) throws FileNotFoundException {
		String content;
		StringBuilder sb = new StringBuilder();
		Scanner scanner = new Scanner(new File(fileName), "UTF-8");
		while (scanner.hasNextLine()) {
			sb.append(scanner.nextLine()).append(System.lineSeparator());
		}
		content = sb.substring(0, sb.length() - System.lineSeparator().length());
		scanner.close();
		return content;
	}

	public static void analyzeText(String text, String taskName) {
		switch (taskName) {

		case "frequency": {
			Analyzer.FREQUENCY.analyze(text);
			break;
		}

		case "length": {
			Analyzer.LENGTH.analyze(text);
			break;
		}

		case "duplicates": {
			Analyzer.DUPLICATES.analyze(text);
			break;
		}

		default:
			throw new IllegalArgumentException("Unknown task!");
		}
	}
}
