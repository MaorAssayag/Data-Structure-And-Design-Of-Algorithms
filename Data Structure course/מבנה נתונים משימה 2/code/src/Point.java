
//////////////// DON'T CHANGE THIS FILE ////////////////

public class Point {

	public Point(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
	}

	public Point(int x, int y) {
		this.x = x;
		this.y = y;
		this.name = "";
	}
	
	public Point(Point other){
		this.x=other.x;
		this.y=other.y;
		this.name=other.name;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String toString() {
		return name+"(" + x + ", " + y + ")";
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean equals(Object other)
	{
		return this.toString().equals(other.toString());
	}
	
	private final int x;
	private final int y;
	
	private final String name;

}
