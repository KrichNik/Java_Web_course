package practice6.part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import practice6.part1.Word.WordComparator;

public class WordContainer {

	private final List<Word> wordList = new ArrayList<>();

	private final WordComparator comparator = new WordComparator();

	public WordContainer() {
	}

	public void addWord(Word word) {
		Word curWord = null;
		int index = 0;
		if ((index = wordList.indexOf(word)) != -1) {
			curWord = wordList.get(index);
			curWord.incrFrequency();
		} else {
			wordList.add(word);
		}
	}

	public void sort() {
		Collections.sort(wordList, comparator);
	}

	public void printWords() {
		for (Word word : wordList) {
			System.out.println(word.getWord() + " : " + word.getFrequency());
		}
		System.out.println();
	}
}
