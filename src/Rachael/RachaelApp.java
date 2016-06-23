package Rachael;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


public class RachaelApp {
	static Map<String,String> replacementMap = new HashMap<String,String>();
	static List<String> hedgeSet = new ArrayList<String>();
	static List<String> qualifierList = new ArrayList<String>();
	static List<String> happyRandomSet = new ArrayList<String>();
	static List<String> sadRandomSet = new ArrayList<String>();
	static List<String> neutralRandomSet = new ArrayList<String>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		initialize();
		StringBuilder output;
		String[] input;
		System.out.println("Hi, What's your name? Rachael is expensive. "
				+ "Her time costs $150.00 per 15 minute session.");
		String name = in.nextLine();
		Diary diary = new Diary(name);
		System.out.println("How are you doing today? you can enter Q to "
				+ "quit at any time");
		Random rnd = new Random();
		Sentiment sentiment = new Sentiment();
		int quit = 0;
		double cost = 0;
		long startTime = System.currentTimeMillis();
		while (true) {
			String response = in.nextLine();
			List<String> userInput = diary.getUserInput();
			userInput.add(response);
			diary.setUserInput(userInput);
			input = response.split(" ");
			int mood = sentiment.analyzeString(input);
			int randomNum = rnd.nextInt(3);
			if (randomNum == 0) {
				randomNum = rnd.nextInt(hedgeSet.size());
				output = new StringBuilder(hedgeSet.get(randomNum));
				if (randomNum == 2) {
					System.out.println(output);
					break;
				}
			} else if (randomNum == 1) {
				randomNum = rnd.nextInt(qualifierList.size());
				output = new StringBuilder(qualifierList.get(randomNum));
				for (String x: input) {
					if (replacementMap.containsKey(x)) {
						output.append(replacementMap.get(x) + " ");
					} else {
						output.append(x + " ");
					}
				}
			} else {
				randomNum = rnd.nextInt(3);
				if (randomNum == 0) {
					if (sentiment.unkownWords.size()== 0) {
						randomNum = rnd.nextInt(neutralRandomSet.size());
						output = new StringBuilder(neutralRandomSet.get(randomNum));
					} else {
						randomNum = rnd.nextInt(sentiment.unkownWords.size());
						String word = unkownWords.get(randomNum);
						output = new StringBuilder("What does this " + word + " make you? Happy, Sad or Nothing?");
						sentiment.updateMoodLists(word, in.next());
					}
 					
				} else if (randomNum ==1)  {
					if (mood ==1 && sentiment.happyWords.size() >= 10) {
						randomNum = rnd.nextInt(happyRandomSet.size());
						output = new StringBuilder(happyRandomSet.get(randomNum));
					} else if (mood == 0 && sentiment.sadWords.size() >= 10) {
						randomNum = rnd.nextInt(sadRandomSet.size());
						output = new StringBuilder(sadRandomSet.get(randomNum));
					} else {
						randomNum = rnd.nextInt(sadRandomSet.size());
						output = new StringBuilder(neutralRandomSet.get(randomNum));
					}
				} else {
					randomNum = rnd.nextInt(diary.getUserInput().size());
					output = new StringBuilder("Previously you mentioned " 
					+ diary.getUserInput().get(randomNum) + ", do you want talk more on this");
				}
				
			}
			if (input[0].equals("Q") && input.length ==1) {
				quit++;
				if (quit == 3) {
					break;
				}
				randomNum = rnd.nextInt(diary.getUserInput().size());
				output = new StringBuilder("Don't go yet, you were saying that " 
				+ diary.getUserInput().get(randomNum) + ", do you want to chat more");
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		cost = 150.0 + ;
		System.out.println("Cost for the session: " + );
		System.out.println("Bye. See you again!!");
		diary.loadFile();
		in.close();
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
