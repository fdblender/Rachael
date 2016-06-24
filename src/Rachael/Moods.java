package Rachael;

public enum Moods {

	HAPPY, SAD, NEUTRAL, UNKNOWN;
	
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

}
