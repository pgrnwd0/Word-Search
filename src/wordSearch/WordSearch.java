package wordSearch;
//package com.gradescope.wordsearch;

import java.io.FileWriter;
import java.io.IOException;

public class WordSearch {

	public static void main(String[] args) throws IOException {
		// call grid constructor
		WordGrid grid = new WordGrid(args[0]);
		FileWriter fileWriter = new FileWriter("output_" + args[0]);
		fileWriter.write(grid.toString());
		fileWriter.close();

	}
	
	
	
	// read file and store words in an ArrayList
	
	// make grid

}
