public class LetterInventory {

	// Array of 26, one for each letter
	private int[] letterCounters = new int[26];
	private int size;

	/**
	 * Construct an inventory (a count) of the alphabetic letters in the given
	 * string, ignoring the case of letters and ignoring any non-alphabetic
	 * characters.
	 * 
	 * @param data
	 */
	public LetterInventory(String data) {
		char[] charArray = data.trim().toCharArray();
		int indexNum;
		for (char ch : charArray) {
			indexNum = countIndexNum(ch);
			// only receive letters alphabet, since the return index
			// number is only between 0 and 25 and it is based on ASCII
			if (indexNum >= 0 && indexNum <= 25) {
				letterCounters[indexNum]++;
				size++;
			}
		}
	}

	/**
	 * Method to return the index number of the character passed
	 * 
	 * @param c
	 * @return index number
	 */
	private int countIndexNum(char c) {
		// set the character based on lower-case ASCII, to find the distance
		c = Character.toLowerCase(c);
		// ASCII Code for 'a' is 97, find the distance from letter 'a'
		int indexNum = c - 'a';
		return indexNum;
	}

	/**
	 * Return a count of how many of this letter are in the inventory. Letter
	 * might be lower case or upper case (your method shouldn't care). If a non
	 * alphabetic character is passed, your method should throw an
	 * IllegalArgumentException.
	 * 
	 * @param letter
	 */
	public int get(char letter) {
		if (!(Character.isAlphabetic(letter))) {
			throw new IllegalArgumentException();
		} else {
			int index = countIndexNum(letter);
			return letterCounters[index];
		}
	}

	/**
	 * Set the count for the given letter to the given value. Letter might be
	 * lower case or upper case. If a non alphabetic character is passed or if
	 * value is negative, your method should throw an IllegalArgumentException
	 * 
	 * @param letter
	 * @param value
	 */
	public void set(char letter, int value) {
		if ((!(Character.isAlphabetic(letter))) || value < 0) {
			throw new IllegalArgumentException();
		} else {
			// get the index of the character
			int index = countIndexNum(letter);
			// Update size = current size - count of that index + given value
			size = size - letterCounters[index] + value;
			// Set the count of the character at that index to the value
			letterCounters[index] = value;
		}

	}

	/**
	 * Return the sum of all of the counts in this inventory. This operation
	 * should be �fast� in that it should store the size rather than having to
	 * compute it each time this method is called.
	 * 
	 * @return size
	 */
	public int size() {
		return size;
	}

	/**
	 * Return true if this inventory is empty (all counts are 0). This operation
	 * should be fast in that it should not need to examine each of the 26
	 * counts when it is called.
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Return a String representation of the inventory with the letters all in
	 * lower case and in sorted order and surrounded by square brackets. The
	 * number of occurrences of each letter should match its count in the
	 * inventory. For example, an inventory of 4 a's, 1 b, 1 l and 1 m would be
	 * represented as '[aaaablm]'.
	 */
	public String toString() {
		// initialize String with open square bracket
		String str = "[";
		char charLetter = 'a'; // initialize with 'a'
		for (int i : letterCounters) {
			for (int j = 0; j < i; j++) {
				str = str + charLetter;
			}
			// Get the character that is one away from the current character
			charLetter = (char) (charLetter + 1);
		}
		str = str + "]"; // close square bracket at the end
		return str;
	}

	/**
	 * Construct and returns a new LetterInventory object that represents the
	 * sum of this letter inventory and the other given LetterInventory. The
	 * counts for each letter should be added together. The two LetterInventory
	 * objects being added together (this and other) should not be changed by
	 * this method
	 * 
	 * @param other
	 * @return result
	 */
	public LetterInventory add(LetterInventory other) {
		LetterInventory result = new LetterInventory("");
		int[] countFirst = this.letterCounters;
		int[] countSecond = other.letterCounters;
		for (int i = 0; i < result.letterCounters.length; i++) {
			result.letterCounters[i] = countFirst[i] + countSecond[i];
		}
		result.size = this.size + other.size;
		return result;
	}

	/**
	 * Construct and returns a new LetterInventory object that represents the
	 * result of subtracting the other inventory from this inventory (i.e.,
	 * subtracting the counts in the other inventory from this object's counts).
	 * If any resulting count would be negative, your method should return null.
	 * The two LetterInventory objects being subtracted (this and other) should
	 * not be changed by this method
	 * 
	 * @param other
	 * @return result
	 */
	public LetterInventory subtract(LetterInventory other) {
		LetterInventory result = new LetterInventory("");
		int[] first = this.letterCounters;
		int[] second = other.letterCounters;
		for (int i = 0; i < result.letterCounters.length; i++) {
			result.letterCounters[i] = first[i] - second[i];
			// If the result negative
			if (result.letterCounters[i] < 0) {
				return null;
			}
		}
		result.size = this.size - other.size;
		return result;
	}

	/**
	 * This method is to check if the given object is an instance of the letter
	 * inventory
	 */
	public boolean equals(Object o) {
		if (!(o instanceof LetterInventory)) {
			return false;
		} else {
			LetterInventory that = (LetterInventory) o;
			return this.toString().equals(that.toString());
		}
	}
}
