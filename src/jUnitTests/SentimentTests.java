package jUnitTests;



import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Rachael.Admin;
import Rachael.Moods;
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
		String[] wordList6 = {"I", "don't", "it", "me", "newword"};
		String[] wordList7 = {"I", "might", "it", "me", "newword"};
		String[] wordList8 = {"I", "LIKE", "it", "me", "HATE"};
		Moods mood;
		Sentiment sentiments = new Sentiment();
		Admin admin = new Admin("Navreet");
		
		mood = sentiments.analyzeString(admin,wordList1);
		System.out.println(mood.toString(mood));
		
		mood = sentiments.analyzeString(admin,wordList2);
		System.out.println(mood.toString(mood));
		
		mood = sentiments.analyzeString(admin,wordList3);
		System.out.println(mood.toString(mood));
		
		mood = sentiments.analyzeString(admin,wordList4);
		System.out.println(mood.toString(mood));
		
		mood = sentiments.analyzeString(admin,wordList5);
		System.out.println(mood.toString(mood));
		
		mood = sentiments.analyzeString(admin,wordList6);
		System.out.println(mood.toString(mood));		
		
		mood = sentiments.analyzeString(admin,wordList7);
		System.out.println(mood.toString(mood));	
		
		mood = sentiments.analyzeString(admin,wordList8);
		System.out.println(mood.toString(mood));	
		
		
		
	}

}
