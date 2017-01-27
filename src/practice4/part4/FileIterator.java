package practice4.part4;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import practice4.Util;

public class FileIterator implements Iterable<String> {

	private static final String REGEX_FIND_STRING = "[^\\.]+\\.\\s+|[^\\.]+(\\..+\\s+)*";

	private final String[] inputStrings;

	FileIterator(String fileName) {
		String fileData = Util.readFromFile(fileName);
		inputStrings = stringsMatcher(fileData);
	}

	@Override
	public Iterator<String> iterator() {
		return new IteratorImpl();
	}

	private static String[] stringsMatcher(final String input) {
		String[] inputStrings = new String[stringsCounter(input)];
		Pattern p = Pattern.compile(REGEX_FIND_STRING);
		Matcher m = p.matcher(input);
		int i = 0;
		while (m.find()) {
			inputStrings[i++] = m.group().trim();
		}
		return inputStrings;
	}

	private static int stringsCounter(final String input) {
		Pattern p = Pattern.compile(REGEX_FIND_STRING);
		Matcher m = p.matcher(input);
		int count = 0;
		while (m.find()) {
			count++;
		}
		return count;
	}

	public class IteratorImpl implements Iterator<String> {

		private int cursor;

		@Override
		public boolean hasNext() {
			return cursor != inputStrings.length;
		}

		@Override
		public String next() {
			return inputStrings[cursor++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
