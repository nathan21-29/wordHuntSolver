import java.util.Scanner;
import java.util.StringTokenizer;

public class LongestWord {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Please enter a sentence: ");
		StringTokenizer st = new StringTokenizer(scanner.nextLine(), " ,.!?:()");
		int maxLength = 0;
		String longestWord = "";
		while(st.hasMoreTokens()) {
			String data = st.nextToken();
			if(data.length() > maxLength) {
				maxLength = data.length();
				longestWord = data;
			}
		}
		System.out.println(longestWord);
		
		scanner.close();
	}

}
