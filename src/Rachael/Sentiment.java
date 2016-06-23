package Rachael;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Sentiment {
	Set<String> happyWords;
	Set<String> sadWords;
	Set<String> negationWords;
	Set<String> neutralWords;
	Set<String> unknownWords;
	
	// return values for moods - consider changing this to an enum
	int HAPPY = 0;
	int SAD = 1;
	int NEUTRAL = 2;
	int UNKNOWN = 3;
	
	public void Sentiment() {
		this.happyWords = new HashSet<String>();
		this.sadWords = new HashSet<String>();
		this.negationWords = new HashSet<String>();
		this.neutralWords = new HashSet<String>();
		this.unknownWords = new HashSet<String>();
		initSets();
	}

	public Sentiment() {
		this.happyWords = new HashSet<String>();
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

	public Set<String> getUnknownWords() {
		return unknownWords;
	}

	public void setUnknownWords(Set<String> unknownWords) {
		this.unknownWords = unknownWords;
	}

	public int getHAPPY() {
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
	}
	
	public int addWord(String[] words) {
		return (this.HAPPY);
	}
	
	// return the mood of an entire string (phrase or sentence)
	// SAD takes priority, then HAPPY, then NEUTRAL
	public int analyzeString(String[] words) {
		boolean negate = false;
		int mood = NEUTRAL;		

		for (String word : words) {
			if (this.happyWords.contains(word)) {
				if (!(mood == SAD)) {
					mood = HAPPY;
				}
			} else if (this.sadWords.contains(word)) {
				mood = SAD;
			} else if (this.negationWords.contains(word)) {
				negate = true;
			} else if (!this.neutralWords.contains(word)) {
				unknownWords.add(word);
			}
		}
		if (mood == HAPPY && negate == true) {
			mood = SAD;
		}
		if (mood == SAD && negate == true) {
			mood = HAPPY;
		}
		return (mood);
	}

	// return the mood of a single word
	public int analyzeWord(String word) {
		if (this.happyWords.contains(word)) {
			return (HAPPY);
		} else if (this.sadWords.contains(word)) {
			return (SAD);
		} else if (this.neutralWords.contains(word)) {
			return (NEUTRAL);
		}
		return (UNKNOWN);
	}

	// removes the word from the unkown word list and
	// adds it to the given list
	public void updateUnknownWord(String word, int newMood) {
		unknownWords.remove(word);
		if (newMood == HAPPY) {
			happyWords.add(word);
		} else if (newMood == SAD) {
			sadWords.add(word);
		} else if (newMood == NEUTRAL) {
			neutralWords.add(word);
		}
	}
	
	public String toString(int mood) {
		if (mood == HAPPY) {
			return ("HAPPY");
		} else if (mood == SAD) {
			return ("SAD");
		} else if (mood == NEUTRAL) {
			return ("NEUTRAL");
		} else if (mood == UNKNOWN) {
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
