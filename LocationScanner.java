import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.NoSuchElementException;

// This class parses .txt files that are specially formatted for the game:
// The reader can only handle files with lists of the format
// "[Location Name];x;y\n", where x and y represents the x and y coordinates
// on the map. This scanner decomposes each row of the text file into a location
// name string, an x-coordinate string, and a y-coordinate string.
public class LocationScanner implements Iterator<String> {
	private Reader r;
	private int c;
	
	// Constructor
	public LocationScanner(Reader in) throws IOException {
		this.r = in;
		try {
			c = r.read();
		} catch (IOException e) {
			c = -1;
		}
	}

	// Helper method
	public void skip() {
		try {
			while (c == ';' || c == '\n') {
				c = r.read();
			}
		} catch (IOException e) {
			throw new NoSuchElementException();
		}
	}
	
	@Override
	public boolean hasNext() {
		return (c != -1);
	}

	@Override
	public String next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		String answer = "";
		try {
			while (c != '\n' && c != ';' && c != -1) {
				answer = answer + (char) c;
				c = r.read();
			}
		} catch (IOException e) {
			throw new NoSuchElementException();
		}
		skip();
		return answer;
	}

}
