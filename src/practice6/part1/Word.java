package practice6.part1;

import java.io.Serializable;
import java.util.Comparator;

public class Word {

	private String word;

	private int frequency;

	public Word(String word) {
		this.word = word;
		frequency = 1;
	}

	public int getFrequency() {
		return frequency;
	}

	public String getWord() {
		return word;
	}

	public void incrFrequency() {
		frequency++;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Word otherWord = (Word) obj;
		if (otherWord.word == null) {
			return false;
		}
		if (!word.equals(otherWord.word)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + frequency;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	static class WordComparator implements Comparator<Word>, Serializable {

		private static final long serialVersionUID = 1L;

		@Override
		public int compare(Word o1, Word o2) {
			if (o1 == null || o2 == null) {
				return -1;
			}
			if (o1.frequency > o2.frequency) {
				return -1;
			} else if (o1.frequency < o2.frequency) {
				return 1;
			} else {
				return o1.getWord().compareTo(o2.getWord());
			}
		}

	}
}
