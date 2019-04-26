package application.model; 

import java.util.Random;

/**
*
* @author devadv
*/
 
public class Level {

	private int numberOfCharstoType = 15;
	private int numberOfWordstoType = 20;
	private String[] data;
	private Random random;

	public Level(String[] data) {
		this.data = data;
		random = new Random();
	}

	/*
	 * Random generated output of data
	 */
	public String getOutput() {
		String s = "";
		if (data[0].length() == 1) {
			s = generateLetters();
		} else {
			s = generateWords();
		}
		return s;
	}
	
	/*
	 * return a String of random letters form data
	 */
	public String generateLetters() {
		StringBuilder outputBuilder = new StringBuilder();
		String[] s = new String[numberOfCharstoType];
		for (int i = 0; i < s.length; i++) {
			s[i] = "";
		}
		Random random = new Random();
		// make a list of 15 characters
		for (int i = 0; i < numberOfCharstoType; i++) {
			// make pairs of 1 to 4 characters
			for (int j = 0; j < random.nextInt(3) + 2; j++) {
				s[i] += data[random.nextInt(data.length)];
			}
			outputBuilder.append(s[i]);
			// put a space after random characters
			if (i < numberOfCharstoType - 1) {
				s[i + 1] = " ";
				outputBuilder.append(s[i + 1]);
				i++;
			}
		}
		return outputBuilder.toString();

	}

	/*
	 * returns a String of words
	 */
	public String generateWords() {
		StringBuilder words = new StringBuilder();
		String[] s = new String[numberOfWordstoType];
		// first word
		s[0] = makeWord();
		// loop from second index of String array
		for (int i = 1; i < numberOfWordstoType; i++) {
			do {
				s[i] = makeWord();
				// compare last word with current word if equals replace it
			} while (s[i - 1].equals(s[i]));
		}
		for (String word : s) {
			words.append(word);
			words.append(" ");
		}
		return words.toString();
	}

	// returns one word
	public String makeWord() {
		String word = data[random.nextInt(data.length)];
		return word;
	}

	public String[] getData() {		
		return data;
	}
	
	public void setData(String[] letters) {
		this.data = letters;
	}
	
	public int getNumberOfCharstoType() {
		return numberOfCharstoType;
	}
	
	public void setNumberOfCharstoType(int numberOfCharstoType) {
		this.numberOfCharstoType = numberOfCharstoType;
	}

	public int getNumberOfWordstoType() {
		return numberOfWordstoType;
	}

	public void setNumberOfWordstoType(int numberOfWordstoType) {
		this.numberOfWordstoType = numberOfWordstoType;
	}

} 