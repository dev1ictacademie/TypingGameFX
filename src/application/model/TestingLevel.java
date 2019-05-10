package application.model;

import java.util.Arrays;

public class TestingLevel {

	public static void main(String[] args) {


		Level level = new Level(LevelData.levels[0]);

		System.out.println(level.getOutput());

		level.setData(LevelData.levels[1]);
		level.setNumberOfCharstoType(20);

		System.out.println(level.getOutput());

		level.setData(LevelData.levels[2]);
		level.setNumberOfCharstoType(25);

		System.out.println(level.getOutput());

		level.setData(LevelData.levels[3]);
		level.setNumberOfCharstoType(35);

		System.out.println(level.getOutput());

		level.setData(LevelData.levels[4]);
		level.setNumberOfCharstoType(25);

		System.out.println(level.getOutput());

	}

}
