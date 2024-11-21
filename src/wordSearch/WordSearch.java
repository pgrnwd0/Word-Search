package com.gradescope.wordsearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Preston Greenwood
 * CSC 210 Fall 2024
 * 
 * This program reads a file with dimensions and words
 * and creates a words search grid with that data. The
 * program argument is the name of the input file. It
 * will output the word search in a file named:
 * "output_" + input file name
 */

public class WordSearch {

	public static void main(String[] args) throws IOException {
		// args[0] should be the name of input file
		WordGrid grid = readFile(args[0]);
		
		// write to output file
		FileWriter fileWriter = new FileWriter("output_" + args[0]);
		fileWriter.write(grid.toString());
		fileWriter.close();

	}
	
	// reads file, creates a WordGrid and adds words from file to it
	public static WordGrid readFile(String fileName) throws FileNotFoundException {
		ArrayList<String> wordList = new ArrayList<String>();
		File inputFile = new File(fileName);
		Scanner fileScanner = new Scanner(inputFile);
		
		// grid dimensions
		int height = fileScanner.nextInt();
		int width = fileScanner.nextInt();
		int maxLength = Math.max(width , height);
		
		WordGrid grid = new WordGrid(height , width);
		
		// get all words in file
		String currentLine = fileScanner.nextLine();
		while (fileScanner.hasNextLine()) {
			currentLine = fileScanner.nextLine().toLowerCase();
			// checks if word is too big or small
			if (currentLine.length() <= maxLength && currentLine.length() >= 3) {
				wordList.add(currentLine);
			}

		}
		fileScanner.close();
		// give the words to the word grid
		for (String word : wordList) {
			grid.addWord(word);
		}
		
		grid.placeWords();
		return grid;
	}
}
