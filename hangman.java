import java.util.Scanner;
import java.util.ArrayList;

public class hangman {
	wordList wl = new wordList();
	String word;
	ArrayList<Character> unused = new ArrayList<Character>();
	ArrayList<Character> used = new ArrayList<Character>();
	ArrayList<Character> hiddenWord = new ArrayList<Character>();
	int tries;
	boolean win = false;
	Scanner scan = new Scanner(System.in);
	boolean winRound = false;

	// Runs the game until the user wins or runs out of tries
	public void game() {
		System.out.println("Welcome to hangman!");
		while (tries < 6) {
			play();
			if (win) {
				printWord();
				System.out.println();
				printLetters();
				System.out.println();
				System.out.println("You win!");
				return;
			}
			if (!winRound) {
				tries++;
			}
		}
		System.out.println("Sorry! You lose! The word was: " + word);

	}

	// Gives updates to the player on which letters have been guessed
	// and how many tries they have left. Also lets the user guess a
	// letter.
	public void play() {
		winRound = false;
		System.out.println("Tries left: " + (6 - tries));
		System.out.print("Your word: ");
		printWord();
		System.out.println();
		printLetters();
		System.out.println();
		System.out.print("Guess a letter: ");
		String guess = scan.next();
		guess = guess.toUpperCase();
		char letter = guess.charAt(0);
		while ((int) letter < 65 || (int) letter > 90) {
			System.out.println("Invalid letter, guess again.");
			System.out.print("Guess a letter: ");
			guess = scan.next();
			guess = guess.toUpperCase();
			letter = guess.charAt(0);
		}
		guess(letter);
		win = gameOver();
	}

	// Takes a letters and checks if the hidden word
	// has that letter
	public void guess(char letter) {
		// Checks to see if the user has guessed this letter
		for (int i = 0; i < used.size(); i++) {
			if (letter == used.get(i)) {
				winRound = true;
				return;
			}
		}

		// Checks if letter exists in hidden word, if it does
		// it reveals that letter in hidden word
		for (int i = 0; i < hiddenWord.size(); i++) {
			if (letter == word.charAt(i)) {
				hiddenWord.set(i, letter);
				winRound = true;
			}
		}
		// Moves the letter from unused to used ArrayList
		for (int i = 0; i < unused.size(); i++) {
			if (letter == unused.get(i)) {
				unused.remove(i);
				used.add(letter);
			}
		}
	}

	// Checks if the player has won
	public boolean gameOver() {
		for (int i = 0; i < hiddenWord.size(); i++) {
			if (hiddenWord.get(i) == '_') {
				return false;
			}
		}
		return true;
	}

	// Prints the word the player is trying to guess
	public void printWord() {
		for (int i = 0; i < hiddenWord.size(); i++) {
			System.out.print(hiddenWord.get(i) + " ");
		}
	}

	// Prints which letters have and haven't been used.
	public void printLetters() {
		System.out.print("Unused Letters: ");
		for (int i = 0; i < unused.size(); i++) {
			System.out.print(unused.get(i) + " ");
		}
		System.out.println();
		System.out.print("Used Letters: ");
		for (int i = 0; i < used.size(); i++) {
			System.out.print(used.get(i) + " ");
		}
	}

	// Clears everything from the past game and sets
	// up ArrayLists.
	public void setup() {
		tries = 0;
		used.clear();
		unused.clear();
		hiddenWord.clear();
		word = wl.getRandomWord();
		word = word.toUpperCase();

		// Adds all capital letters to an ArrayList
		for (int i = 65; i < 91; i++) {
			unused.add((char) (i));
		}
		for (int i = 0; i < word.length(); i++) {
			hiddenWord.add('_');
		}
	}

	// Starts the game and asks the user if they want to play again
	// after winning or losing.
	public hangman() {
		String playAgain = "Y";
		while (playAgain.equals("Y")) {
			setup();
			game();
			System.out.print("Play Again? (Y)es or (N)o: ");
			playAgain = scan.next();
			playAgain = playAgain.toUpperCase();
		}
	}

	public static void main(String[] args) {
		new hangman();
	}
}
