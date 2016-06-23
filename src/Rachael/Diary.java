package Rachael;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Diary {
	
	private String name;
	private List<String> userInput = new ArrayList<String>();
	private File userSession;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getUserInput() {
		return userInput;
	}

	public void setUserInput(List<String> userInput) {
		this.userInput = userInput;
	}

	public File getUserSession() {
		return userSession;
	}

	public void setUserSession(File userSession) {
		this.userSession = userSession;
	}

	public Diary (String name) {
		this.name = name;
		File file =  new File("Names.txt");
		try {
			FileReader fwr = new FileReader(file);
			BufferedReader br = new BufferedReader(fwr);
			String nameRead = br.readLine();
			boolean found = false;
			while (nameRead != null) {
				if (nameRead.equals(name)) {
					found = true;
					userSession = new File (name+"Sesion");
					loadList();
				}
				nameRead = br.readLine();
			}
			if (!found) {
				userSession = new File (name+"Sesion");
				userSession.createNewFile();
			}
			br.close();
			fwr.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO Exception");
		} 
	}

	public void loadList() {
		FileReader fwr;
		try {
			fwr = new FileReader(userSession);
			BufferedReader br = new BufferedReader(fwr);
			String input = br.readLine();
			
			while (input != null) {
				userInput.add(input);
				input = br.readLine();
			}
			br.close();
			fwr.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
		
		
	}
	
	public void loadFile() {
		try {
			FileWriter fwr = new FileWriter(userSession, true);
			BufferedWriter bwr = new BufferedWriter(fwr);
			for (String userInp: userInput) {
				bwr.write(userInp + "\n");
			}
			bwr.close();
			fwr.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}
		
	}

}
