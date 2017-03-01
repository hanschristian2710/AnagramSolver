import java.util.ArrayList;
import java.util.List;

public class AnagramSolver {

	private List<String> dictionary;
	private LetterInventory inputPhrase;
	private List<String> sortedInventory;
	private List<String> anagramSolution = new ArrayList<String>();

	/**
	 * Construct an anagram solver that will use the given word list as its
	 * dictionary. The list must not be modified.
	 * 
	 * @param list
	 */
	public AnagramSolver(List<String> list) {
		dictionary = list;
	}

	/**
	 * Print to System.out all combinations of words from its dictionary that
	 * are anagrams of the String s and that include at most max words (or
	 * unlimited number of words if max is 0). Throw an IllegalArgumentException
	 * if max is less than 0.
	 * 
	 * @param s
	 * @param max
	 */
	public void print(String s, int max) {
		if (max < 0) {
			throw new IllegalArgumentException();
		} else {
			inputPhrase = new LetterInventory(s);
			// Sort the inventory with possible word from the dictionary
			sortInventory();
			if (inputPhrase.isEmpty()) {
				printOut();
			}

			// 1 is the maximum word that can be the anagram
			if (max == 1) {
				for (String w1 : sortedInventory) {
					LetterInventory word1 = new LetterInventory(w1);
					if (word1.equals(inputPhrase)) {
						anagramSolution.add(w1);
						printOut();
						anagramSolution.remove(w1);
					}
				}
			} else { // can be more than one or have no maximum
				String inputPhrase1;
				LetterInventory anagram;
				for (String w2 : sortedInventory) {
					LetterInventory word2 = new LetterInventory(w2);
					if (inputPhrase.subtract(word2) != null) {
						anagramSolution.add(w2);
						anagram = inputPhrase.subtract(word2);
						inputPhrase1 = anagram.toString();

						// When there is no maximum
						if (max == 0) {
							max = sortedInventory.size();
						}

						print(inputPhrase1, max - 1);
						anagramSolution.remove(w2);
						inputPhrase = new LetterInventory(s);
					}
				}
			}
		}
	}

	/**
	 * This method is to print out the anagram associated with the input phrase
	 * given by the user.
	 */
	private void printOut() {
		if (anagramSolution.size() > 0) {
			String str = "["; // initialize with square bracket
			// Add the word associated
			for (String word : anagramSolution) {
				str = str + word + ", ";
			}

			// Remove every comma and space ", " that is leftover
			str = str.substring(0, str.length() - 2) + "]";
			AnagramMain.out.println(str); // print the anagram
		}
	}

	/**
	 * This method is to sort the inventory with the possibility of phrase given
	 * by the user and the word that are listed in the dictionary.
	 */
	private void sortInventory() {
		sortedInventory = new ArrayList<String>();
		for (String w : dictionary) {
			LetterInventory sort = new LetterInventory(w);
			// Add the word that has the subtraction result positive
			if (inputPhrase.subtract(sort) != null) {
				sortedInventory.add(w);
			}
		}
	}
}
