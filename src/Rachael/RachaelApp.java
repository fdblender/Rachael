package Rachael;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import FormatNumber.FormatNumberUtil;

public class RachaelApp {
	static Map<String, String> replacementMap = new HashMap<String, String>();
	static List<String> hedgeSet = new ArrayList<String>();
	static List<String> qualifierList = new ArrayList<String>();
	static List<String> happyRandomSet = new ArrayList<String>();
	static List<String> sadRandomSet = new ArrayList<String>();
	static List<String> neutralRandomSet = new ArrayList<String>();
	static Diary diary;
	static Admin admin;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int countHappy =0;
		int countSad =0;
		int counterName =0;
		StringBuilder output;
		String[] input;
		System.out.println(
				"Hi, What's your name? Rachael is expensive. " + "Her time costs $150.00 per 15 minute session.");
		String name = in.nextLine();
		diary = new Diary(name);
		admin = new Admin(name);
		initialize();
		System.out.println("How are you doing today? you can enter Q to " + "quit at any time");
		Random rnd = new Random();
		Sentiment sentiment = new Sentiment();
		int quit = 0;
		double cost = 0;
		long startTime = System.currentTimeMillis();
		while (true) {
			counterName++;
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
				Moods mood = sentiment.analyzeString(admin, input);
				if (mood == Moods.HAPPY) countHappy++;
				else if (mood == Moods.SAD) countSad++;
				//System.out.println(sentiment.getUnknownWords().size());
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
							System.out.println(randomNum);
							String word = sentiment.getUnknownWords().get(randomNum);
							System.out.println(word);
							output = new StringBuilder(
									"What does this '" + word + "' make you? Happy 0, Sad 1 or Nothing 2?");
							System.out.println(output);
							int resp = in.nextInt();
							if (resp == 0)
								sentiment.updateUnknownWord(admin,word, Moods.HAPPY);
							else if (resp == 1)
								sentiment.updateUnknownWord(admin,word, Moods.SAD);
							else
								sentiment.updateUnknownWord(admin,word, Moods.NEUTRAL);
							System.out.println("Ok, Thank you");
							continue;
						}

					} else if (randomNum == 1) {
						
						if (mood == Moods.HAPPY && sentiment.happyWords.size() >= 5) {
							randomNum = rnd.nextInt(happyRandomSet.size());
							output = new StringBuilder(happyRandomSet.get(randomNum));
						} else if (mood == Moods.SAD && sentiment.sadWords.size() >= 5) {
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
			
			if (counterName == 4) {
				System.out.println(diary.getName() + ", " + output.toString().toLowerCase());
				counterName =0;
			} else {
				System.out.println(output);
			}
			
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (countHappy > countSad) 
			System.out.println("I am glad you are doing ok.");
		else 
			System.out.println("You will get better, keep coming for sessions");
		cost = 150.0 + (((totalTime/ (15*60*1000))) * 150);
		System.out.println("Cost for the session: " + FormatNumberUtil.getFormattedCurrency(cost));
		System.out.println("Bye. See you again!!");
		diary.loadFile();
		admin.addDetails(diary.getName());
		in.close();
	}

	private static void initialize() {

		replacementMap.put("my", "your");
		replacementMap.put("me", "you");
		replacementMap.put("i", "you");
		replacementMap.put("am", "are");
		replacementMap.put("are", "am");
		replacementMap.put("you", "i");
		replacementMap.put("your", "my");
		replacementMap.put("we", "you");
		replacementMap.put("here", "there");
		replacementMap.put("there", "here");
		
		
		hedgeSet.add(0, "Please tell me more");
		hedgeSet.add(1, "Many of my patients tell me the same thing");
		hedgeSet.add(2, "It is getting late, maybe we had better quit");
		hedgeSet.add(3, "Excuse me, can you elaborate");
		hedgeSet.add(4, "I appreciat what you are saying, but are you sure");
		hedgeSet.add(5, "Thank you for opening up to me, tell me more");
		hedgeSet.add(6, "You are very thoughtful, please continue");

		happyRandomSet.add("Are you feeling happy");
		happyRandomSet.add("I think you are doing good today");
		happyRandomSet.add("I love to see you so happy");
		happyRandomSet.add("I am glad you are doing good");
		happyRandomSet.add("I am glad you are feeling that way");
		happyRandomSet.add("What's made you so happy?");
		happyRandomSet.add("You sound cheerful, keep the people that make you happy close");
		happyRandomSet.add("Keep smiling, you look so good");
		happyRandomSet.add("You sound content");
		happyRandomSet.add("That's a positive statement");
		happyRandomSet.add("You are very optimistic");

		qualifierList.add("Why do you say, ");
		qualifierList.add("You seem to think that");
		qualifierList.add("So, you are concerned that");
		qualifierList.add("Oh, you feel that");
		qualifierList.add("Really, you think that");
		qualifierList.add("Why do you feel that");
		qualifierList.add("Are you sure,");
		qualifierList.add("You sound little confused when you say,");
		qualifierList.add("You can't say that");
		qualifierList.add("You are crazy if you think that");
		qualifierList.add("Breath Dude!! you can't say,");

		sadRandomSet.add("You sound so depressed, do you need some prosac?");
		sadRandomSet.add("It's alright, just take the next step");
		sadRandomSet.add("Set a positive goal for youself");
		sadRandomSet.add("Don't worry, things happen");
		sadRandomSet.add("There's a silver lining to every cloud");
		sadRandomSet.add("Tomorrow will be better");
		sadRandomSet.add("Too bad, I feel for you");
		sadRandomSet.add("Do you need a hug");
		sadRandomSet.add("You are too depressing, for once in you life cheer up");
		sadRandomSet.add("Go ahead and vent your anger at your spouse");
		sadRandomSet.add("You are venting too much");

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
