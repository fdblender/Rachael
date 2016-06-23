package Rachael;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class RachaelApp {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		initialize();
		StringBuilder output;
		String[] input;
		System.out.println("Hi, What's your name?");
		String name = in.nextLine();
		Diary diary = new Diary(name);
		System.out.println("How are you doing today? you can enter Q to quit at any time");
		Random rnd = new Random();
		while (true) {
			input = in.nextLine().split(" ");
		}
		
		
	}

	private static void initialize() {
		
		Map<String,String> replacementMap = new HashMap<String,String>();
		List<String> hedgeSet = new ArrayList<String>();
		List<String> happyQualifierSet = new ArrayList<String>();
		List<String> sadQualifierSet = new ArrayList<String>();
		List<String> randomQuestionSet = new ArrayList<String>();
		
		replacementMap.put("my", "your");
		replacementMap.put("me", "you");
		replacementMap.put("i", "you");
		replacementMap.put("am", "are");
		replacementMap.put("you", "me");
		replacementMap.put("your", "my");
		
		hedgeSet.add(0,"Please tell me more");
		hedgeSet.add(1,"Many of my patients tell me the same thing");
		hedgeSet.add(2,"It is getting late, maybe we had better quit");
		
		happyQualifierSet.add("Why do you say that");
		happyQualifierSet.add("You seem to think that");
		happyQualifierSet.add("So, you are concerned that");
		
		sadQualifierSet.add("Why do you say that");
		sadQualifierSet.add("You seem to think that");
		sadQualifierSet.add("So, you are concerned that");
		
		randomQuestionSet.add("Why do you say that");
		randomQuestionSet.add("You seem to think that");
		randomQuestionSet.add("So, you are concerned that");
		
		
		
		
	}

}
