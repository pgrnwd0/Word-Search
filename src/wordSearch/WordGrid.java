package wordSearch;
//package com.gradescope.wordsearch;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;


public class WordGrid {
	
	public ArrayList<Word> wordList = new ArrayList<Word>();
	public static ArrayList<ArrayList<Character>> grid = new ArrayList<ArrayList<Character>>();
	private static int height;
	private static int width;
	
	
	public WordGrid(String fileName) throws FileNotFoundException {

		File inputFile = new File(fileName);
		Scanner fileScanner = new Scanner(inputFile);
		
		// get dimensions and fill empty array
		height = fileScanner.nextInt();
		width = fileScanner.nextInt();
		fillEmptyArray();
		
		// get words from file
		String currentLine = fileScanner.nextLine();
		while (fileScanner.hasNextLine()) {
			currentLine = fileScanner.nextLine();
			wordList.add(new Word(currentLine));
		}
		
		fileScanner.close();
		for (Word word : wordList) {
			addWords(word);
		}
		// fill empty spaces with random letters
		fillRandomLetters();
	}
	
	// fills grid with empty chars to correct dimensions
	private static void fillEmptyArray() {
		for (int i = 0; i < height; i++) {
			grid.add(new ArrayList<Character>());
			for (int j = 0; j < width; j++) {
				grid.get(i).add(' ');
			}
		}
	}
	
	// puts random letters in all empty spaces
	private static void fillRandomLetters() {
		Random randomLetter = new Random();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (grid.get(i).get(j).equals(' ')) {
					grid.get(i).set(j, (char)(randomLetter.nextInt(26) + 'A'));
				}
			}
		}
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static int getWidth() {
		return width;
	}
	
	// copies the letters from the words into the grid
	private void addWords(Word word) {
		// find a valid space
		generateCoordinates(word);
		//System.out.println(word.toString()); // for debug. remove before final
		// horizontal
		if (word.getDirection().equals("horizontal")) {
			for (int i = 0; i < word.getWord().length(); i++) {
				grid.get(word.getYCoord()).set(word.getXCoord() + i, word.getWord().charAt(i));
			}
		}
		// vertical
		if (word.getDirection().equals("vertical")) {
			for (int i = 0; i < word.getWord().length(); i++) {
				grid.get(word.getYCoord() + i).set(word.getXCoord(), word.getWord().charAt(i));
			}
		}
		// diagonal
		if (word.getDirection().equals("diagonal")) {
			for (int i = 0; i < word.getWord().length(); i++) {
				grid.get(word.getYCoord() + i).set(word.getXCoord() + i, word.getWord().charAt(i));
			}
		}
	}
	
	// checks if word's coordinates are valid
	public boolean isValidPosition(Word word) {
		if (word.getXCoord() == -1 || word.getYCoord() == -1) return false;
		// checks horizontal words. comments for this branch apply for all three
		if (word.getDirection().equals("horizontal")) {
			// makes sure it doesn't clash with other words
			for (int i = 0; i < word.getWord().length(); i++) {
				if (grid.get(word.getYCoord()).get(word.getXCoord() + i) != ' '	&& 
						grid.get(word.getYCoord()).get(word.getXCoord() + i) != word.getWord().charAt(i)) return false;
			}
		}
		// checks vertical words
		if (word.getDirection().equals("vertical")) {
			for (int i = 0; i < word.getWord().length(); i++) {
				if (grid.get(word.getYCoord() + i).get(word.getXCoord()) != ' '	&& 
						grid.get(word.getYCoord() + i).get(word.getXCoord()) != word.getWord().charAt(i)) return false;
			}
		}
		// checks diagonals
		if (word.getDirection().equals("diagonal")) {
			for (int i = 0; i < word.getWord().length(); i++) {
				if (grid.get(word.getYCoord() + i).get(word.getXCoord() + i) != ' '	&& 
						grid.get(word.getYCoord() + i).get(word.getXCoord() + i) != word.getWord().charAt(i)) return false;
			}
		}
		return true;
	}
	
	 // generates random coordinates until it finds one that is valid
	private void generateCoordinates(Word word) {
		Random random = new Random();
		while (!isValidPosition(word)) {
			// keeps setting random coordinates until it finds a valid one
			if (word.getDirection().equals("horizontal")) {
				word.setPosition(random.nextInt(width - word.getWord().length()), random.nextInt(height));
			}
			if (word.getDirection().equals("vertical")) {
				word.setPosition(random.nextInt(width), random.nextInt(height - word.getWord().length()));
			}
			if (word.getDirection().equals("diagonal")) {
				word.setPosition(random.nextInt(width - word.getWord().length()), random.nextInt(height - word.getWord().length()));
			}
		}
	}
	
	public String toString() {
		String board = "";
		for (int i = 0; i < height; i++) {
			board += grid.get(i).get(0);
			for (int j = 1; j < width; j++) {
				board += " " + grid.get(i).get(j);
			}
			board += "\n";
		}
		return board;
	}
}
