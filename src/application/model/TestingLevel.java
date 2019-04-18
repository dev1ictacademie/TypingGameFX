package application.model;

import java.util.Arrays;

public class TestingLevel {

	public static void main(String[] args) {


		Level level = new Level(LevelData.LEVEL1);
/*
		System.out.println(level.getOutput());

		level.setData(LevelData.LEVEL2);
		level.setNumberOfCharstoType(20);

		System.out.println(level.getOutput());

		level.setData(LevelData.LEVEL3);
		level.setNumberOfCharstoType(25);

		System.out.println(level.getOutput());

		level.setData(LevelData.LEVEL3);
		level.setNumberOfCharstoType(35);

		System.out.println(level.getOutput());

		level.setData(LevelData.LEVEL4);
		level.setNumberOfCharstoType(25);

		System.out.println(level.getOutput());

		level.setData(LevelData.LEVEL5);
		level.setNumberOfCharstoType(25);

		System.out.println(level.getOutput());
*/
		level.setData(LevelData.LEVEL6);
		level.setNumberOfWordstoType(8);

		System.out.println( " Level 6 " + level.getOutput());



	}

}
