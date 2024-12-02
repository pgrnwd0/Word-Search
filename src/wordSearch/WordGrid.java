package com.gradescope.wordsearch;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

/*
 * Preston Greenwood
 * CSC 210 Fall 2024
 * 
 * This class is a word grid. It has a 2d array of
 * characters and a list of words. The dimensions 
 * are arguments in the constructor and the words
 * that are to be added are added by calling the
 * addWord() method with a Word object as an
 * argument. The words are placed in the grid with
 * the placeWords() method 
 */

public class WordGrid {
	
	private ArrayList<Word> wordList = new ArrayList<Word>();
	private ArrayList<ArrayList<Character>> grid = new ArrayList<ArrayList<Character>>();
	private int height;
	private int width;
	
	
	public WordGrid(int width, int height) throws FileNotFoundException {
		this.height = height;
		this.width = width;
		fillEmptyArray();	
	}
	
	// fills grid with empty chars to correct dimensions
	private void fillEmptyArray() {
		for (int i = 0; i < height; i++) {
			grid.add(new ArrayList<Character>());
			for (int j = 0; j < width; j++) {
				grid.get(i).add(' ');
			}
		}
	}
	
	// adds a word to the word list
	public void addWord(String wordString) {
		wordList.add(new Word(wordString));
	}
	
	// puts all of the words in wordList into the grid
	public void placeWords() {
		for (Word word : wordList) {
			copyWordsToGrid(word);
		}
		fillRandomLetters();
	}
	
	// puts random letters in all empty spaces
	private void fillRandomLetters() {
		Random randomLetter = new Random();
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (grid.get(i).get(j).equals(' ')) {
					grid.get(i).set(j, (char)(randomLetter.nextInt(26) + 'A'));
				}
			}
		}
	}
	
	// copies the letters from the words into the grid
	private void copyWordsToGrid(Word word) {
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
				if (width - word.getWord().length() == 0) word.setPosition(0,random.nextInt(height));
				else word.setPosition(random.nextInt(width - word.getWord().length()), random.nextInt(height));
			}
			if (word.getDirection().equals("vertical")) {
				if (height - word.getWord().length() == 0) word.setPosition(random.nextInt(width),0);
				else word.setPosition(random.nextInt(width), random.nextInt(height - word.getWord().length()));
			}
			if (word.getDirection().equals("diagonal")) {
				if (height - word.getWord().length() == 0 || width - word.getWord().length() == 0) word.setPosition(0,0);
				else word.setPosition(random.nextInt(width - word.getWord().length()), random.nextInt(height - word.getWord().length()));
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public String toString() {
		String board = "   ";
		for (int j = 0; j < width; j++) {
			board += (char)(j + 'a');
			board += ' ';
		}
		board += "\n";
		for (int i = 0; i < height; i++) {
			if (i < 10) {
				board += "0" + String.valueOf(i) + " ";
			}
			else board += String.valueOf(i) + " ";
			board += grid.get(i).get(0);
			for (int j = 1; j < width; j++) {
				board += " " + grid.get(i).get(j);
			}
			board += "\n";
		}
		return board;
	}
}
