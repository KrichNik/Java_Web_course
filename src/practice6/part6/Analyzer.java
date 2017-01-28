package practice6.part6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public enum Analyzer {

	FREQUENCY {

		@Override
		public void analyze(String text) {
			System.out.println("Part1(frequency):" + LS);
			wordCount(text);
			System.out.println();
		}

	},

	LENGTH {

		@Override
		public void analyze(String text) {
			System.out.println("Part2(length):" + LS);
			findWordWithMaxLength(text);
			System.out.println();
		}

	},

	DUPLICATES {

		@Override
		public void analyze(String text) {
			System.out.println("Part3(duplicates):" + LS);
			findDuplicates(text);
			System.out.println();
		}

	};

	private static final List<String> WORD_LIST = new ArrayList<>();

	private static final String LS = System.lineSeparator();

	private static final Comparator<String> SORT_STRING_BY_LENGTH = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			if (o1 == null || o2 == null) {
				return -1;
			}
			if (o1.length() < o2.length()) {
				return 1;
			}
			if (o1.length() > o2.length()) {
				return -1;
			}
			return o1.compareTo(o2);
		}
	};

    protected abstract void analyze(String text);

    protected void wordCount(String text) {
        Map<String, Integer> mapWord = new HashMap<>();
        splitText(text);
        for (String word : WORD_LIST) {
            if (mapWord.containsKey(word)) {
                int value = mapWord.get(word);
                mapWord.remove(word);
                mapWord.put(word, ++value);
            } else {
                mapWord.put(word, 1);
            }
        }
        findWordMaxCount(mapWord);
    }

    protected void findWordWithMaxLength(String text) {
        List<String> listWordWithMaxLength = new ArrayList<>();
        splitText(text);
        for (String word : WORD_LIST) {
            if (!listWordWithMaxLength.contains(word)) {
                listWordWithMaxLength.add(word);
            }
        }
        Collections.sort(listWordWithMaxLength, SORT_STRING_BY_LENGTH);
        String word;
        for (int i = 0; i < 3; i++) {
            word = listWordWithMaxLength.get(i);
            System.out.println(word + " ==> " + word.length());
        }
    }

    protected void findDuplicates(String text) {
        splitText(text);
        Set<String> duplicates = getDuplicatesSet();
        for (String word : duplicates) {
            printUpperReverse(word);
        }
    }

	private void splitText(String text) {
		String[] textSplit = text.split("\\W");
		for (String word : textSplit) {
			word = word.trim();
			if (word.equals("")) {
				continue;
			}
			WORD_LIST.add(word);
		}
	}

	private void findWordMaxCount(Map<String, Integer> mapWord) {
		Map<String, Integer> map3Word = new TreeMap<>(Collections.reverseOrder());
		for (int i = 0; i < 3; i++) {
			int maxWordCount = Integer.MIN_VALUE;
			String keyWord = null;
			for (Map.Entry<String, Integer> pair : mapWord.entrySet()) {
				if (pair.getValue() > maxWordCount) {
					maxWordCount = pair.getValue();
					keyWord = pair.getKey();
				}
			}
			mapWord.remove(keyWord);
			if (keyWord != null) {
				map3Word.put(keyWord, maxWordCount);
			}
		}
		printMap(map3Word);
	}

	private void printMap(Map<String, Integer> mapWord) {
		for (Map.Entry<String, Integer> pair : mapWord.entrySet()) {
			System.out.println(pair.getKey() + " ==> " + pair.getValue());
		}
	}

	private Set<String> getDuplicatesSet() {
		int counter = 0;
		Set<String> duplicates = new HashSet<>();
		for (int i = 0; i < WORD_LIST.size(); i++) {
			String currentWord = WORD_LIST.get(i);
			for (int j = i + 1; j < WORD_LIST.size(); j++) {
				if (currentWord.equals(WORD_LIST.get(j)) && duplicates.add(WORD_LIST.get(j))) {
					counter++;
				}
				if (counter == 3) {
					return duplicates;
				}
			}
		}
		System.out.println("There was not found 3 duplicates!");
		return duplicates;
	}

	private void printUpperReverse(String word) {
		String reverse = new StringBuilder(word).reverse().toString();
		String upper = reverse.toUpperCase();
		System.out.println(upper);
	}
}
