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
		String[] wordList = {"I", "do", "not", "like", "cookies"};
		int mood;
		Sentiment sentiments = new Sentiment();
		mood = sentiments.analyzeString(wordList);
	}

}
