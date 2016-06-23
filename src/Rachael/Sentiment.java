package Rachael;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Sentiment {	
	Set<String> happyWords;
	Set<String> sadWords;	
	Set<String> negations;
	Set<String> neutral;
	boolean HAPPY = true;
	boolean SAD = false;

	public Sentiment() {
		this.happyWords = new HashSet<String>();
		initSets();
	}
	
	public boolean addWord (String[] words ) {
		return(this.HAPPY);
	}
	
	public boolean analyzeWord (String[] words ) {
		return(this.HAPPY);
	}
	
	private void initSets() {
		
	}

}
