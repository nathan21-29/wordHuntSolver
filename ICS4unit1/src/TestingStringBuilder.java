
public class TestingStringBuilder {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		String word = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		System.out.println(sb.append("abcdefghijklmnopqrstuvwxyz").capacity());
		
	}

}
