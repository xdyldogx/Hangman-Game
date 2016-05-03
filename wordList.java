import java.util.ArrayList;
import java.util.Random;

public class wordList {
	Random r = new Random();
	private ArrayList<String> words = new ArrayList<String>();

	public wordList() {
		words.add("birthday");
		words.add("football");
		words.add("kitchen");
		words.add("television");
	}

	public wordList(ArrayList<String> words) {
		this.words = words;
	}

	public int wordListSize(){
		return words.size();
	}
	
	public String getRandomWord() {
		int x = r.nextInt(words.size());
		return words.get(x);
	}

}
