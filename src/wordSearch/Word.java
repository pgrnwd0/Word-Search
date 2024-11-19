package wordSearch;

import java.util.Random;

public class Word {
	private String direction;
	private String word;
	
	private int xCoord;
	private int yCoord;
	
	public Word(String word) {
		this.word = word;
		setDirection();
	}
	
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
