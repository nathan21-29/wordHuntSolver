
public class RecursionTesting {

	public static void main(String[] args) {
		fun2(4321);
	}

	public static void fun2(int k) {
		System.out.println(k);
		if(k > 0)
			fun2(k / 10);
		if(k > 9)
			fun2(k % 10);
	}
}
