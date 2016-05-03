import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class wordListExtra {
	Random r = new Random();
	private ArrayList<String> words = new ArrayList<String>();

	public wordListExtra(String filePath) throws Exception {
		File data = new File(filePath);
		Scanner scan = new Scanner(data);
		while (scan.hasNext()) {
			words.add(scan.next());
		}
		scan.close();
	}

	public wordListExtra() {
		words.add("birthday");
		words.add("football");
		words.add("kitchen");
		words.add("television");
	}

	public int wordListSize(){
		return words.size();
	}
	
	public wordListExtra(ArrayList<String> words) {
		this.words = words;
	}

	public String getRandomWord() {
		int x = r.nextInt(words.size());
		return words.get(x);
	}

}
