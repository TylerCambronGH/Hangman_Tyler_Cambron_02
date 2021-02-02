package Hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {
	List<String> wordList = new ArrayList<String>();
	private SecureRandom random = new SecureRandom();
	
	public Dictionary()  {
		List<String> wL = new ArrayList<String>();
		File file = new File("words.txt");
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				wL.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		wordList = wL;
	}
	
	public void readFile() {
		List<String> wL = new ArrayList<String>();
		File file = new File("words.txt");
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				wL.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		wordList = wL;
		return;
	}
	
	public String chooseWord() {
		String[] words = wordList.toArray(new String[0]);
		int index = random.nextInt(words.length);
		return words[index];
	}
}
