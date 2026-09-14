import java.util.Scanner;

public class StringBuilderShift {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter a word: ");
		String word = scanner.nextLine().trim();
		System.out.print("How many letters would you like to shift the word? ");
		int shift = Integer.parseInt(scanner.nextLine().trim()) % word.length();
		System.out.print("In what direction would you like to shift the word? ");
		int direction = Integer.parseInt(scanner.nextLine());
		StringBuilder sb = new StringBuilder(word);
//		if(direction == 0) {
//			sb.append(sb.substring(0, shift));
//			sb.delete(0, shift);
//		}
//		else { //direction == 1
//			int cutoff = sb.length() - shift;
//			sb.append(sb.substring(0, cutoff));
//			sb.delete(0, cutoff);
//		}
		if(direction == 0) {
			for(int i = 0; i < shift; i++) {
				sb.append(sb.charAt(0));
				sb.delete(0, 1);
			}
		}
		else { //direction == 1
			for(int i = 0; i < shift; i++) {
				sb.insert(0, sb.charAt(sb.length() - 1));
				sb.delete(sb.length() - 1, sb.length());
			}
		}
		System.out.println(sb);
		
		scanner.close();
	}

}
