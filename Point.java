// This class represents points on the map (model). Will be used for both
// storing the location on the map, as well as calculating how far the user's
// click was from the actual location.
public class Point {
	
	// fields
	private int x;
	private int y;
	
	// Constructor
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	// Get x component
	public int getX() {
		return x;
	}
	
	// Get y component
	public int getY() {
		return y;
	}
	
	// Calculate distance from another point
	public int distanceFrom(Point p1) {
		int dx = p1.getX() - this.x;
		int dy = p1.getY() - this.y;
		return (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
	}
	
	// Override Equals (but getting an error with @Override?)
	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null) {
			return false;
		}
		if (getClass() != o.getClass()) {
			return false;
		}
		Point p1 = (Point) o;
		if (p1.getX() != this.getX()) {
			return false;
		}
		if (p1.getY() != this.getY()) {
			return false;
		}
		return true;
	}

}
