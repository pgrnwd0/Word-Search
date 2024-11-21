package com.gradescope.wordsearch;

import java.util.Random;

/*
 * Preston Greenwood
 * CSC210 Fall 2024
 * 
 * This class is a Word. It has a string representing
 * the word and a direction denoting how it is placed
 * in a WordGrid. It also stores the word's coordinates
 * in a WordGrid. The X and Y coordinates need to be set
 * prior to adding them to a grid because they are by
 * default set to -1.
 */
public class Word {
	private String direction;
	private String word;
	
	private int xCoord = -1;
	private int yCoord = -1;
	
	public Word(String word) {
		this.word = word.toUpperCase();
		setDirection();
	}
	
	// picks a random orientation
	private void setDirection() {
		Random random = new Random();
		// generate number from 0 to 2
		int randomInt = random.nextInt(3);
		
		// random number decides word orientation
		switch(randomInt) {
		case 0:
			this.direction = "horizontal";
			break;
		case 1:
			direction = "vertical";
			break;
		case 2:
			direction = "diagonal";
			break;
		}
	}
	
	public void setWord(String word) {
		this.word = word;
	}
	
	public void setPosition(int x, int y) {
		xCoord = x;
		yCoord = y;
	}
	
	public int getXCoord() {
		return xCoord;
	}
	
	public int getYCoord() {
		return yCoord;
	}
	
	public void setDirection(String newDirection) {
		direction = newDirection;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public String getWord() {
		return word;
	}
	
	public String toString() {
		return word + " at " + "(" + xCoord + "," + yCoord + ")";
	}
}
