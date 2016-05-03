import java.util.Scanner;
import java.util.ArrayList;

public class hangmanExtra {
	wordListExtra wl;
	String word;
	ArrayList<Character> unused = new ArrayList<Character>();
	ArrayList<Character> used = new ArrayList<Character>();
	ArrayList<Character> hiddenWord = new ArrayList<Character>();
	int tries = 0;
	final int maxTries;
	int maxTriesTemp;
	boolean win = false;
	Scanner scan = new Scanner(System.in);
	boolean winRound = false;

	public void game() {
		System.out.println("Welcome to hangman!");
		while (tries < maxTriesTemp) {
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

	public void play() {
		winRound = false;
		System.out.println("Tries left: " + (maxTriesTemp - tries));
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

	public void guess(char letter) {
		for (int i = 0; i < used.size(); i++) {
			if (letter == used.get(i)) {
				winRound = true;
				return;
			}
		}

		for (int i = 0; i < hiddenWord.size(); i++) {
			if (letter == word.charAt(i)) {
				hiddenWord.set(i, letter);
				winRound = true;
			}
		}
		for (int i = 0; i < unused.size(); i++) {
			if (letter == unused.get(i)) {
				unused.remove(i);
				used.add(letter);
			}
		}
	}

	public boolean gameOver() {
		for (int i = 0; i < hiddenWord.size(); i++) {
			if (hiddenWord.get(i) == '_') {
				return false;
			}
		}
		return true;
	}

	public void printWord() {
		for (int i = 0; i < hiddenWord.size(); i++) {
			System.out.print(hiddenWord.get(i) + " ");
		}
	}

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

	public void setup() {
		tries = 0;
		maxTriesTemp = maxTries;
		used.clear();
		unused.clear();
		hiddenWord.clear();
		word = wl.getRandomWord();
		word = word.toUpperCase();

		for (int i = 65; i < 91; i++) {
			unused.add((char) (i));
		}
		for (int i = 0; i < word.length(); i++) {
			hiddenWord.add('_');
		}
	}

	public hangmanExtra() throws Exception {
		System.out.print("Would you like to play with a custom wordlist? (Y)es or (N)o: ");
		String s = scan.next();
		s = s.toUpperCase();
		if (s.equals("Y")) {
			System.out.print("Please enter the location of the wordlist (ex.'C:/Users/Dylan/Desktop/wordlist.txt': ");
			String dir = scan.next();
			wl = new wordListExtra(dir);
		} else {
			wl = new wordListExtra();
		}
		System.out.println("Wordlist successfully loaded. Contains " + wl.wordListSize() + " words.");
		System.out.print("How many guesses would you like?: ");
		maxTries = scan.nextInt();
		String playAgain = "Y";
		while (playAgain.equals("Y")) {
			setup();
			game();
			System.out.print("Play Again? (Y)es or (N)o: ");
			playAgain = scan.next();
			playAgain = playAgain.toUpperCase();
		}
	}

	public static void main(String[] args) throws Exception {
		new hangmanExtra();
	}
}
