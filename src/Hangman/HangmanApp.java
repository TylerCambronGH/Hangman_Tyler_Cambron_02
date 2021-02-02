package Hangman;

public class HangmanApp {
	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		boolean result = hangman.playGame();
		if (result) {
			System.out.println("You win");
		} else {
			System.out.println("You lose");
		}
	}
}