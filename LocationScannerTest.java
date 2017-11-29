import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class LocationScannerTest {
	
	@Test
	public void testUnexpectedFormatting() throws IOException {
		LocationScanner lc = new LocationScanner(new FileReader("locScanTestDoc.txt"));
		assertEquals("New York City", lc.next());
		assertTrue(lc.hasNext());
		assertEquals("0", lc.next());
		assertTrue(lc.hasNext());
		assertEquals("0", lc.next());
		assertTrue(lc.hasNext());
		assertEquals("Los Angeles", lc.next());
		assertTrue(lc.hasNext());
		assertEquals("0", lc.next());
		assertTrue(lc.hasNext());
		assertEquals("0", lc.next());
		assertFalse(lc.hasNext());
	}

}
