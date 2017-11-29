
public class TriviaItem {
	
	// fields
	private String locationName;
	private Point locationPoint;
	
	// Constructor
	public TriviaItem(String s, Point p) {
		this.locationName = s;
		this.locationPoint = p;
	}
	
	// Getter methods
	public String getLocationName() {
		return locationName;
	}
	
	public Point getLocationPoint() {
		return locationPoint;
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
		TriviaItem item1 = (TriviaItem) o;
		if (item1.getLocationName() != this.getLocationName()) {
			return false;
		}
		if (item1.getLocationPoint() != this.getLocationPoint()) {
			return false;
		}
		return true;
	}
}
