package wordSearch;

import java.util.Scanner;
import java.util.ArrayList;

public class FindWords {
	
	public static ArrayList<String> readFile(Scanner reader) {
		ArrayList<String> result = new ArrayList<String>();
		
		while (reader.hasNext()) {
			String line = reader.nextLine();
			result.add(line);
		}
		return result;
	}
	
	public static boolean dimensionsMatch(int width, int height, ArrayList<String> grid) {
		System.out.println(grid.size() + " " + height);
		if (grid.size() != height) return false;
		for (String s : grid) {
			System.out.println(s.split(" ").length + " " + width);
			if (s.split(" ").length != width) return false;
		}
		return true;
	}
	
	public static String removeSpaces(String s) {
		String result = "";
		for (char c: s.toCharArray()) {
			if (c != ' ') result += c;
		}
		return result;
	}
	
	public static String getDiagonal(int x, int y, ArrayList<String> grid) {
		String newString = "";
		for (int i = x; i < grid.size(); i++) {
			if (y < grid.get(i).length()) newString += grid.get(i).charAt(y);
			y+=2;
		}
		return newString;
	}
	
	public static boolean findDiagonal(String word, ArrayList<String> grid) {
		for (int i = 0; i < grid.size(); i++) {
			for (int j = 0; j < grid.get(i).length(); j++) {
				String diagonal = getDiagonal(i, j, grid);	
				if (removeSpaces(diagonal).indexOf(word.toUpperCase()) > -1) return true;
			}
		}
		return false;	
	}
	
	public static boolean findVertical(String word, ArrayList<String> grid) {
		for (int j = 0; j < grid.get(0).length(); j++) {
			String column = "";
			for (int i = 0; i < grid.size(); i++) {
				column += grid.get(i).charAt(j);	
			}
			if (removeSpaces(column).indexOf(word.toUpperCase()) > -1) return true;
		}
		return false;	
	}
	
	public static boolean findHorizontal(String word, ArrayList<String> grid) {
		for (String s : grid) {
			if (removeSpaces(s).indexOf(word.toUpperCase()) > -1) return true;
		}
		return false;	
	}
	
	public static boolean findWords(ArrayList<String> words, ArrayList<String> grid) {
		ArrayList<String> found = new ArrayList<>();
		for (String w : words) {
			if (findHorizontal(w, grid)) found.add(w);
			if (findVertical(w, grid)) found.add(w);
			if (findDiagonal(w, grid)) found.add(w);
		}
		System.out.println(words);
		System.out.println(found);
		if (found.size() == words.size()) return true;
		return false;
	}

}
