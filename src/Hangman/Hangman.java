package Hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Hangman {
	int wins, losses;
	String currentWord = new String("");
	Dictionary dictionary = new Dictionary();
	
	public Hangman() {
		loadWL();
	}
	
	public void loadWL() {
		File file = new File("record.txt");
		try {
			Scanner sc = new Scanner(file);
			String recordString = sc.nextLine();
			String[] recordArray = recordString.split(",");
			this.wins = Integer.parseInt(recordArray[0]);
			this.losses = Integer.parseInt(recordArray[1]);
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public void writeWL() {
		try {
			FileWriter file = new FileWriter("record.txt");
			String recordString = new String(this.wins+","+this.losses);
			file.write(recordString);
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
	public boolean playGame() {
		Scanner in = new Scanner(System.in);
		System.out.println("Hangman? y/n");
		String playing = in.nextLine().toLowerCase();
		if (playing.contains("n")) {
			in.close();
			return false;
		}
		currentWord = this.dictionary.chooseWord().toLowerCase();
		boolean won = false;
		int lettersInWord = currentWord.length();
		char[] wordChars = new char[lettersInWord];
		for (int i=0;i<lettersInWord;i++) {
			wordChars[i] = currentWord.charAt(i);
		}
		char[] lettersGuessed = new char[lettersInWord];
		int guessesLeft = 5;
		
		while (guessesLeft > 0) {
			System.out.printf("%d Letter Word\nYou have %d guesses left.\n", lettersInWord, guessesLeft);
			for (char c: lettersGuessed) {
				System.out.print(c);
			}
			System.out.print("\n");
			String guess = new String("z");
			guess = in.nextLine().toLowerCase();
			char guessLetter = guess.charAt(0);
			boolean foundMatch = false;
			for (int i=0;i<lettersInWord;i++) {
				char letter = currentWord.charAt(i);
				if (guessLetter == letter) {
					lettersGuessed[i] = letter;
					foundMatch = true;
				}
			}
			if (foundMatch) {
				System.out.printf("There is a %s.\n", guess);
			} else {
				System.out.printf("There is no %s.\n", guess);
				guessesLeft--;
			}
			
			int lettersSame = 0;
			for (int i=0;i<lettersInWord;i++) {
				if (lettersGuessed[i]==wordChars[i]) {
					lettersSame++;
				}
			}
			if (lettersSame == lettersInWord) {
				won = true;
				break;
			}
		}
		if (won) {
			this.wins++;
		} else {
			this.losses++;
		}
		in.close();
		this.writeWL();
		return won;
	}
}
