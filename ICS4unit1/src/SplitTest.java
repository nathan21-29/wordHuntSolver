
public class SplitTest {

	public static void main(String[] args) {
		String line = "March break is in three weeks!!";
		String[] words = line.split("e");
		for(String token : words) {
			System.out.println(token);
		}
	}

}
