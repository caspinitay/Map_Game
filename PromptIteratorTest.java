import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.junit.Test;

public class PromptIteratorTest {
	
	@Test
	public void testIteratorFunction() throws IOException {
		try {
			PromptIterator p = new PromptIterator(new LocationScanner(new FileReader("locScanTestDoc.txt")));
			assertTrue(p.hasNext());
			p.next();
			assertTrue(p.hasNext());
			p.next();
			assertFalse(p.hasNext());
		} catch (IOException e) {
			System.out.println("IO Error " + e.getMessage());
		}
	}
	
	@Test
	public void testIteratorNext() throws IOException {
		try {
			PromptIterator p = new PromptIterator(new LocationScanner(new FileReader("locScanTestDoc.txt")));
			TriviaItem current = p.next();
			assertEquals(new Point(0,0), current.getLocationPoint());
			current = p.next();
			assertEquals(new Point(0,0), current.getLocationPoint());
		} catch (IOException e) {
			System.out.println("IO Error " + e.getMessage());
		}
	}
	
	@Test
	public void testIteratorEnd() throws IOException {
		try {
			PromptIterator p = new PromptIterator(new LocationScanner(new FileReader("locScanTestDoc.txt")));
			p.next();
			p.next();
			assertFalse(p.hasNext());
		} catch (IOException e) {
			System.out.println("IO Error " + e.getMessage());
		}
	}
	
	@Test
	public void testIteratorChange() throws IOException {
		try {
			PromptIterator p = new PromptIterator(new LocationScanner(new FileReader("locScanTestDoc.txt")));
			p.next();
			p.next();
			assertFalse(p.hasNext());
			p = new PromptIterator(new LocationScanner(new FileReader("locations2.txt")));
			assertTrue(p.hasNext());
		} catch (IOException e) {
			System.out.println("IO Error " + e.getMessage());
		}
	}
	
	@Test
	public void testIteratorError() throws IOException {
		try {
			PromptIterator p = new PromptIterator(new LocationScanner(new FileReader("VOID_FILE.txt")));
		} catch (IOException e) {
		}
	}
	
	@Test
	public void testIteratorInvalidInputFormat() throws IOException {
		try {
			PromptIterator p = new PromptIterator(new LocationScanner(new FileReader("locScanTestDoc2.txt")));
		} catch (IOException e) {
			System.out.println("IO Error " + e.getMessage());
		} catch (NoSuchElementException e1) {
			// do nothing to pass test
		}
	}
	
	@Test
	public void testIteratorNegativeInput() throws IOException {
		try {
			PromptIterator p = new PromptIterator(new LocationScanner(new FileReader("locScanTestDoc2.txt")));
			p.next();
		} catch (NoSuchElementException e1) {
			// do nothing to pass test
		}
	}
}
