import java.util.Scanner;
import java.util.StringTokenizer;

public class PhraseTokenizer {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter a phrase: ");
		StringTokenizer st = new StringTokenizer(scanner.nextLine(), " ");
		int count = 0;
		while(st.hasMoreTokens()) {
			count++;
			System.out.println(st.nextToken());
		}
//		System.out.println(st.countTokens());
//		for(int i = 0; i < st.countTokens() + i; i++) {
//			count++;
//			System.out.println(st.nextToken());
//		}
		System.out.println("This phrase has " + count + " words");
		scanner.close();
	}

}
