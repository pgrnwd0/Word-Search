package wordSearch;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


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
		fillArray();
		
		// get words
		String currentLine = fileScanner.nextLine();
		while (fileScanner.hasNextLine()) {
			currentLine = fileScanner.nextLine();
			wordList.add(new Word(currentLine));
		}
		
		fileScanner.close();
	}
	
	private static void fillArray() {
		for (int i = 0; i < height; i++) {
			grid.add(new ArrayList<Character>());
			for (int j = 0; j < width; j++) {
				grid.get(i).add(' ');
			}
		}
	}
	
	public static int getHeight() {
		return height;
	}
	
	public static int getWidth() {
		return width;
	}
	
	private void addWord(Word word) {
		// copy characters from word into grid
		for (int i = 0; i < word.getWord().length(); i++) {
			grid.get(word.getYCoord()).set(word.getXCoord() + i, word.getWord().charAt(i));
		}
		// FIME: this is just for horizontal words. test functionality and add others
	}
	
	public boolean isValidPosition(Word word) {
		if (word.getDirection().equals("horizontal")) {
			// checks if not enough space to put word there
			if (word.getXCoord() > (width - word.getWord().length())) {
				System.out.println("not enough space");
				return false;
			}
			// makes sure it doesn't clash with other words
			for (int i = 0; i < word.getWord().length(); i++) {
				if (grid.get(word.getYCoord()).get(word.getXCoord() + i) != ' '	&& 
						grid.get(word.getYCoord()).get(word.getXCoord() + i) != word.getWord().charAt(i)) {
					System.out.println("clashes with other words");
					return false;
				}
			}
		}
		// FIXME: fill in the rest
		if (word.getDirection().equals("vertical")) {
			
		}
		
		if (word.getDirection().equals("diagonal")) {
			
		}
		return true;
	}
}
