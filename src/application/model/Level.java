package application.model;

import java.util.Random;

/**
*
* @author devadv
*/
public class Level {

	private int numberOfCharstoType = 15;
	private String[] data;

	public Level(String[] data) {
		this.data = data;
	}
	/*
	 * Random generated output of data
	 */
	public String getOutput() {
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

}
