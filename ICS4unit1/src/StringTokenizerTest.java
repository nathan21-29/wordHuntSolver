import java.util.StringTokenizer;

public class StringTokenizerTest {

	public static void main(String[] args) {
		String line = "To be or not to be, that is the question.";
		StringTokenizer tokens = new StringTokenizer(line, "sbi", true);
		while(tokens.hasMoreTokens()) {
			System.out.println(tokens.nextToken());
		}
	}

}
