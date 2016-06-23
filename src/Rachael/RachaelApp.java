package Rachael;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class RachaelApp {
	static Map<String, String> replacementMap = new HashMap<String, String>();
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
		System.out.println(
				"Hi, What's your name? Rachael is expensive. " + "Her time costs $150.00 per 15 minute session.");
		String name = in.nextLine();
		Diary diary = new Diary(name);
		System.out.println("How are you doing today? you can enter Q to " + "quit at any time");
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
			if (input[0].equals("Q") && input.length == 1) {
				quit++;
				if (quit == 3) {
					break;
				}
				int rand = rnd.nextInt(diary.getUserInput().size());
				output = new StringBuilder("Don't go yet, you were saying "
						+ "that " + diary.getUserInput().get(rand)
						+ ", do you want to chat more");
			} else {
				//System.out.println("in else");
				int mood = sentiment.analyzeString(input);
				//System.out.println(sentiment.toString(mood));
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
					output = new StringBuilder(qualifierList.get(randomNum)+ " ");
					for (String x : input) {
						if (replacementMap.containsKey(x)) {
							output.append(replacementMap.get(x) + " ");
						} else {
							output.append(x + " ");
						}
					}
					
				} else {
					randomNum = rnd.nextInt(3);
					if (randomNum == 0) {
						//System.out.println("in unknown word");
						if (sentiment.getUnknownWords().size() == 0) {
							randomNum = rnd.nextInt(neutralRandomSet.size());
							output = new StringBuilder(neutralRandomSet.get(randomNum));
						} else {
							randomNum = rnd.nextInt(sentiment.getUnknownWords().size());
							//System.out.println(randomNum);
							String word = sentiment.getUnknownWords().get(randomNum);
							//System.out.println(word);
							output = new StringBuilder(
									"What does this " + word + " make you? Happy 0, Sad 1 or Nothing 2?");
							System.out.println(output);
							
							sentiment.updateUnknownWord(word, in.nextInt());
							System.out.println("Ok, Thank you");
							continue;
						}

					} else if (randomNum == 1) {
						
						if (mood == 0 && sentiment.happyWords.size() >= 10) {
							randomNum = rnd.nextInt(happyRandomSet.size());
							output = new StringBuilder(happyRandomSet.get(randomNum));
						} else if (mood == 1 && sentiment.sadWords.size() >= 10) {
							randomNum = rnd.nextInt(sadRandomSet.size());
							output = new StringBuilder(sadRandomSet.get(randomNum));
						} else {
							randomNum = rnd.nextInt(neutralRandomSet.size());
							output = new StringBuilder(neutralRandomSet.get(randomNum));
						}
					} else {
						randomNum = rnd.nextInt(diary.getUserInput().size());
						output = new StringBuilder("Previously you mentioned " + diary.getUserInput().get(randomNum)
								+ ", do you want talk more on this");
					}

				}
			}
			
			
			System.out.println(output);
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		cost = 150.0 + ((totalTime * 1000 * 60 / 15) - 1) * 150;
		System.out.println("Cost for the session: $" + cost);
		System.out.println("Bye. See you again!!");
		diary.loadFile();
		in.close();
	}

	private static void initialize() {

		replacementMap.put("my", "your");
		replacementMap.put("me", "you");
		replacementMap.put("i", "you");
		replacementMap.put("am", "are");
		replacementMap.put("you", "me");
		replacementMap.put("your", "my");
		replacementMap.put("we", "you");
		replacementMap.put("you", "we");
		replacementMap.put("they", "you");
		replacementMap.put("you", "they");
		replacementMap.put("here", "there");
		replacementMap.put("there", "here");
		replacementMap.put("them", "us");
		replacementMap.put("top", "down");
		replacementMap.put("right", "left");
		replacementMap.put("good", "bad");

		hedgeSet.add(0, "Please tell me more");
		hedgeSet.add(1, "Many of my patients tell me the same thing");
		hedgeSet.add(2, "It is getting late, maybe we had better quit");
		hedgeSet.add(3, "Excuse me");
		hedgeSet.add(4, "Appreciated");
		hedgeSet.add(5, "I thank you for all your help");
		hedgeSet.add(6, "It's pleasure talking to you");

		happyRandomSet.add("Are you feeling happy");
		happyRandomSet.add("I think you are doing good today");
		happyRandomSet.add("I love to have it");
		happyRandomSet.add("Its fun going on a trip");
		happyRandomSet.add("Happy holidays");
		happyRandomSet.add("I enjoyed with my family");
		happyRandomSet.add("Are feeling delightful?");
		happyRandomSet.add("I am honored to have you as my student in the class");
		happyRandomSet.add("I appreciate your help");
		happyRandomSet.add("Always be thankful to people who helped you");
		happyRandomSet.add("You are wonderful student  amongst the class");
		happyRandomSet.add("He is very funny boy I have met");
		happyRandomSet.add("Happiness is a feeling of joy");
		happyRandomSet.add("I like to eat icecream");
		happyRandomSet.add("Crazy, funny time with friends are most memorable moments");

		qualifierList.add("Why do you say that");
		qualifierList.add("You seem to think that");
		qualifierList.add("So, you are concerned that");
		qualifierList.add("Oh, you feel that");
		qualifierList.add("Really, you think that");
		qualifierList.add("Why you feel that");
		qualifierList.add("Are you sure that");
		qualifierList.add("You sound like an idiot saying that");
		qualifierList.add("You can't say that");
		qualifierList.add("You are crazy if you think that");
		qualifierList.add("Breath Dude!! you can't say that");

		sadRandomSet.add("You are an idiot");
		sadRandomSet.add("I consider him to be very bad");
		sadRandomSet.add("Teacher scolds naughty students");
		sadRandomSet.add("You are so roud");
		sadRandomSet.add("Bad thinking will scare you more");
		sadRandomSet.add("Arent you mad on me today");
		sadRandomSet.add("Slapping without a reason is werid");
		sadRandomSet.add("Did you murder the him");
		sadRandomSet.add("Why do you kill them");
		sadRandomSet.add("I am not well");
		sadRandomSet.add("Sorry for coming late");
		sadRandomSet.add("There was a death at my family");
		sadRandomSet.add("I met with an accident last night");
		sadRandomSet.add("I cry when you scold me");
		sadRandomSet.add("Tears flooded in his eyes for sorrow");

		neutralRandomSet.add("Why do you say that");
		neutralRandomSet.add("You seem to think that");
		neutralRandomSet.add("So, you are concerned that");
		neutralRandomSet.add("Why will I do that");
		neutralRandomSet.add("Why dont you tell me that");
		neutralRandomSet.add("How did you ge that");
		neutralRandomSet.add("What did you do for that person");
		neutralRandomSet.add("Can you stop talking tat");
		neutralRandomSet.add("How can you blame me for that");

	}

}
