
public class MyRectangle {
	private static int numRectangles = 0;
	
	private int left;
	private int bottom;
	private int width;
	private int height;
	
	public MyRectangle(int left, int bottom, int width, int height) {
		this.left = left;
		this.bottom = bottom;
		this.width = Math.max(width, 0);
		this.height = Math.max(height, 0);
		numRectangles++;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public static int getNumRectangles() {
		return numRectangles;
	}
	
	public String toString() {
		return String.format("Total number of rectangles: %d%nBase:(%d,%d) w:%d h:%d", 
				numRectangles, left, bottom, width, height);
	}
	
	public int area() {
		return width * height;
	}
	
	public int compareTo(MyRectangle rect2) {
		int area1 = this.area();
		int area2 = rect2.area();
		if(area1 == area2)
			return 0;
		else if(area2 > area1)
			return -1;
		else //area1 > area2
			return 1;
	}
	
	public boolean equals(Object obj) {
		MyRectangle compareSquare = (MyRectangle)obj;
		return(this.width == compareSquare.width && this.height == compareSquare.height);
	}
}

