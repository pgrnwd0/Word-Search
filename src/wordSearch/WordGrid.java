package wordSearch;

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
					grid.get(i).set(j, (char)(randomLetter.nextInt(26) + 'a'));
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
		System.out.println(word.toString()); // for debug. remove before final
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
		// checks horizontal words. comments for this branch apply for all three
		if (word.getDirection().equals("horizontal")) {
			// checks if not enough space to put word there
			if (word.getXCoord() > (width - word.getWord().length())) return false;
			// makes sure it doesn't clash with other words
			for (int i = 0; i < word.getWord().length(); i++) {
				if (grid.get(word.getYCoord()).get(word.getXCoord() + i) != ' '	&& 
						grid.get(word.getYCoord()).get(word.getXCoord() + i) != word.getWord().charAt(i)) return false;
			}
		}
		// checks vertical words
		if (word.getDirection().equals("vertical")) {
			if (word.getYCoord() > (height - word.getWord().length())) return false;
			for (int i = 0; i < word.getWord().length(); i++) {
				if (grid.get(word.getYCoord() + i).get(word.getXCoord()) != ' '	&& 
						grid.get(word.getYCoord() + i).get(word.getXCoord()) != word.getWord().charAt(i)) return false;
			}
		}
		// checks diagonals
		if (word.getDirection().equals("diagonal")) {
			if (word.getXCoord() > (width - word.getWord().length()) || word.getYCoord() > (height - word.getWord().length())) return false;
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
		// set initial random coordinates
		word.setPosition(random.nextInt(width), random.nextInt(height));

		while (!isValidPosition(word)) {
			// keeps setting random coordinates until it finds a valid one
			word.setPosition(random.nextInt(width), random.nextInt(height));
		}
	}
	
	public void printBoard() {
		for (int i = 0; i < height; i++) {
			System.out.println(grid.get(i));
		}
	}
}
