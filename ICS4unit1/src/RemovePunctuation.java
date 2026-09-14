
public class RemovePunctuation {
	public static void main(String[] args) {
		System.out.println(rev("this;is the;order"));
	}
	public static String rev(String phrase) {
		if(phrase.indexOf(" ") == -1 && phrase.indexOf(";") == -1) {
			return phrase;
		}
		int point = Math.max(phrase.lastIndexOf(" "), phrase.lastIndexOf(";"));
		return phrase.substring(point + 1) + ", " + rev(phrase.substring(0, point));
	}
}
