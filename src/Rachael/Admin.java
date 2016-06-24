package Rachael;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.*;

public class Admin {
	// word, frequency and mood
	Map<String, String> mostUsed = new HashMap<String, String>();

	

	public Admin(String name) {
		File file = new File(name + "Details");
		try {
			if (file.exists()) {
				FileReader fwr;

				fwr = new FileReader(file);
				BufferedReader br = new BufferedReader(fwr);
				br.readLine();
				String detail = br.readLine();
				
				while (detail != null) {
					String nextDetail[] = detail.split(" ");
					mostUsed.put(nextDetail[0], nextDetail[1] + "," + nextDetail[2]);
				}
				br.close();
				fwr.close();

			} else {
				file.createNewFile();

			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}
	}

	public void updateMostUsed(String word, Moods mood) {
		if (mostUsed.containsKey(word)) {

			int freq = Integer.parseInt(mostUsed.get(word).split(",")[0]);
			freq++;
			mostUsed.put(word, freq + "," + mood);

		} else {
			mostUsed.put(word, 1 + "," + mood);
		}
	}

	public void addDetails(String name) {
		try {
			File file = new File(name + "Details");
			FileWriter fr = new FileWriter(file);
			BufferedWriter bwr = new BufferedWriter(fr);
			mostUsed = sortByValue(mostUsed);
			int counter = 0;
			for (String word: mostUsed.keySet()) {	
				if (counter>=10) break;
				String freqMood[] = mostUsed.get(word).split(",");
				bwr.write(word + " " + freqMood[0] + " " + freqMood[1] + "\n");
				counter++;
			}
			bwr.close();
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}
		
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

}
