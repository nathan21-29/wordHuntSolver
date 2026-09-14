import java.util.ArrayList;
import java.util.Scanner;

public class ControlMyRectangles {

	public static void main(String[] args) {
//		MyRectangle test = new MyRectangle(3, 2, 4, 5);
//		System.out.println(test);
////		for(int i = 0; i < 10; i++) {
////			MyRectangle temp = new MyRectangle(i, i, i, i);
////			System.out.println(temp);
////			System.out.println(temp.compareTo(test));
////		}
//		System.out.println(test.getWidth());
//		test.setWidth(10);
//		System.out.println(test.getWidth());
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the # of squares to generate: ");
		int rectangleCount = Integer.parseInt(in.nextLine());
		System.out.print("Great. Now enter the maximum side length.");
		int maxLength = Integer.parseInt(in.nextLine());
		
		ArrayList<MyRectangle> squares = new ArrayList <> ();
		for(int i = 0; i < rectangleCount; i++) {
			int dimension = (int) (Math.random() * maxLength) + 1;
			squares.add(i, new MyRectangle(0, 0, dimension, dimension));
		}
		int maxArea;
		do {
			int matchCount = 0;
			System.out.print("\nEnter the area of the square to search for: ");
			maxArea = Integer.parseInt(in.nextLine());
			int maxSide1 = (int) Math.sqrt(maxArea);
			int maxSide2 = (int) Math.ceil(Math.sqrt(maxArea));
			MyRectangle compareSquare = new MyRectangle(0, 0, maxSide1, maxSide1);
			MyRectangle compareSquare2 = new MyRectangle(0, 0, maxSide2, maxSide2);
			for(int i = 0; i < squares.size(); i++) {
				if(squares.get(i).equals(compareSquare) || squares.get(i).equals(compareSquare2)) {
					matchCount++;
					squares.remove(i);
					i--; //everything next moves left one, so increment i to avoid skipping
				}
			}
			System.out.println("--> There are " + matchCount + " squares with an area of " + maxArea + " sq. units.");
		} while(maxArea != -1);
	}	

}
