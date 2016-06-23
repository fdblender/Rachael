package jUnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Rachael.Sentiment;

public class SentimentTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void AnalyzeTest() {
		String[] wordList1 = {"I", "do", "not", "like", "cookies"};
		String[] wordList2 = {"I", "do", "really", "like", "cookies"};
		String[] wordList3 = {"I", "hate", "happy", "cookies"};
		String[] wordList4 = {"I", "do", "not", "hate", "cookies"};
		String[] wordList5 = {"I", "don't", "like", "cookies"};
		int mood;
		Sentiment sentiments = new Sentiment();
		System.out.println(sentiments.analyzeString(wordList1));
		System.out.println(sentiments.analyzeString(wordList2));
		System.out.println(sentiments.analyzeString(wordList3));
		System.out.println(sentiments.analyzeString(wordList4));
		System.out.println(sentiments.analyzeString(wordList5));
	}

}
