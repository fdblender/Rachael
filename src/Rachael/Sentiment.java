package Rachael;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Sentiment {
	Set<String> happyWords = new HashSet<String>();
	Set<String> sadWords = new HashSet<String>();
	Set<String> negationWords = new HashSet<String>();
	Set<String> neutralWords = new HashSet<String>();
	List<String> unknownWords = new ArrayList<String>();

	// return values for moods - consider changing this to an enum
	//int HAPPY = 0;
	//int SAD = 1;
	//int NEUTRAL = 2;
	//int UNKNOWN = 3;

	public Sentiment() {
		initSets();
	}

	public Set<String> getHappyWords() {
		return happyWords;
	}

	public void setHappyWords(Set<String> happyWords) {
		this.happyWords = happyWords;
	}

	public Set<String> getSadWords() {
		return sadWords;
	}

	public void setSadWords(Set<String> sadWords) {
		this.sadWords = sadWords;
	}

	public Set<String> getNegations() {
		return negationWords;
	}

	public void setNegations(Set<String> negations) {
		this.negationWords = negations;
	}

	public Set<String> getNeutral() {
		return neutralWords;
	}

	public void setNeutral(Set<String> neutral) {
		this.neutralWords = neutral;
	}

	public List<String> getUnknownWords() {
		return unknownWords;
	}

	public void setUnknownWords(List<String> unknownWords) {
		this.unknownWords = unknownWords;
	}

	/*public int getHAPPY() {
		return HAPPY;
	}

	public void setHAPPY(int hAPPY) {
		HAPPY = hAPPY;
	}

	public int getSAD() {
		return SAD;
	}

	public void setSAD(int sAD) {
		SAD = sAD;
	}

	public int getNEUTRAL() {
		return NEUTRAL;
	}

	public void setNEUTRAL(int nEUTRAL) {
		NEUTRAL = nEUTRAL;
	}*/

	
	// return the mood of an entire string (phrase or sentence)
	// SAD takes priority, then HAPPY, then NEUTRAL
	public Moods analyzeString(String[] words) {
		boolean negate = false;
		Moods mood = Moods.NEUTRAL;
		
		for (String word : words) {
			word = word.toLowerCase();
			if (this.happyWords.contains(word)) {
				if (!(mood == Moods.SAD)) {
					mood = Moods.HAPPY;
				}
			} else if (this.sadWords.contains(word)) {
				mood = Moods.SAD;
			} else if (this.negationWords.contains(word)) {
				negate = true;
			} else if (!this.neutralWords.contains(word)) {
				unknownWords.add(word);
			}
			//System.out.println(word + " mood: " + toString(mood) + "negate: " + negate);
		}
		if (mood == Moods.HAPPY && negate == true) {
			mood = Moods.SAD;

		} else if (mood == Moods.SAD && negate == true) {
			mood = Moods.HAPPY;
		}
		return (mood);
	}

	// return the mood of a single word
	public Moods analyzeWord(String word) {
		word = word.toLowerCase();
		if (this.happyWords.contains(word)) {
			return (Moods.HAPPY);
		} else if (this.sadWords.contains(word)) {
			return (Moods.SAD);
		} else if (this.neutralWords.contains(word)) {
			return (Moods.NEUTRAL);
		}
		return (Moods.UNKNOWN);
	}

	// removes the word from the unknown word list and
	// adds it to the given list
	public void updateUnknownWord(String word, Moods newMood) {
		unknownWords.remove(word);
		if (newMood == Moods.HAPPY) {
			happyWords.add(word);
		} else if (newMood == Moods.SAD) {
			sadWords.add(word);
		} else if (newMood == Moods.NEUTRAL) {
			neutralWords.add(word);
		}
	}

	public String toString(Moods mood) {
		if (mood == Moods.HAPPY) {
			return ("HAPPY");
		} else if (mood == Moods.SAD) {
			return ("SAD");
		} else if (mood == Moods.NEUTRAL) {
			return ("NEUTRAL");
		} else if (mood == Moods.UNKNOWN) {
			return ("UNKNOWN");
		} else {
			return ("ERROR: invalid mood");
		}
	}

	// initialize word sets
	private void initSets() {
		neutralWords.add("I");
		neutralWords.add("me");
		neutralWords.add("you");
		neutralWords.add("it");
		neutralWords.add("he");
		neutralWords.add("she");
		neutralWords.add("my");
		neutralWords.add("mine");
		neutralWords.add("the");
		neutralWords.add("a");
		neutralWords.add("they");
		neutralWords.add("we");
		neutralWords.add("are");
		neutralWords.add("is");
		neutralWords.add("was");
		neutralWords.add("will");
		neutralWords.add("be");

		negationWords.add("no");
		negationWords.add("not");
		negationWords.add("don't");
		negationWords.add("dont");

		happyWords.add("happy");
		happyWords.add("love");
		happyWords.add("enjoy");
		happyWords.add("like");
		happyWords.add("enjoy");
		happyWords.add("excited");
		happyWords.add("estatic");
		happyWords.add("wonderful");
		happyWords.add("good");

		sadWords.add("sad");
		sadWords.add("bad");
		sadWords.add("mad");
		sadWords.add("angry");
		sadWords.add("awful");
		sadWords.add("mad");
		sadWords.add("hate");
		sadWords.add("horrible");

	}

}
