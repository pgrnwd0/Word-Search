package wordSearch;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class WordSearchTest {

	@Test
	void testDimensions() throws FileNotFoundException {
		WordGrid testGrid = new WordGrid("input.txt");
		assertEquals(20 , testGrid.getHeight());
		assertEquals(20 , testGrid.getWidth());
	}
	
	@Test
	void testWordDirection() {
		Word word = new Word("hello");
		// print out random direction. Should change every time
		System.out.println(word.getDirection());
		word.setDirection("horizontal");
		assertEquals("horizontal" , word.getDirection());
		assertEquals("hello" , word.getWord());
	}
	
	@Test
	void testIsValidPos() throws FileNotFoundException {
		WordGrid testGrid = new WordGrid("input.txt");
		testGrid.wordList.get(0).setPosition(0, 0);
		testGrid.wordList.get(0).setWord("amazing");
		testGrid.wordList.get(0).setDirection("horizontal");
		assertTrue(testGrid.isValidPosition(testGrid.wordList.get(0)));
		// test max length
		testGrid.wordList.get(0).setWord("aaaaaaaaaaaaaaaaaaaa");
		assertTrue(testGrid.isValidPosition(testGrid.wordList.get(0)));
		testGrid.wordList.get(0).setWord("aaaaaaaaaaaaaaaaaaaaa");
		assertFalse(testGrid.isValidPosition(testGrid.wordList.get(0)));
	}

}
