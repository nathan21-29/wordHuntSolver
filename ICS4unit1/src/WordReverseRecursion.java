import java.util.Scanner;

public class WordReverseRecursion {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter a word: ");
		String word = scanner.nextLine().trim();
		System.out.println(wordReverse(word));
		scanner.close();

	}
	
	public static String wordReverse(String word) {
		if (word.length() == 1) {
			return  word;
		}
		return word.charAt(word.length() - 1) + "*" + wordReverse(word.substring(0, word.length() - 1));
	}

}
