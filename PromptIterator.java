import java.util.*;

// This class uses a LocationScanner to construct a LinkedList of the game's
// questions (locations), then iterates through the list to present the
// questions to the Top-Level
public class PromptIterator implements Iterator<TriviaItem> {
	
	// fields
	LinkedList<TriviaItem> locationBank;
	int currentIndex;
	int bankSize;
	
	public PromptIterator(LocationScanner lc) {
		locationBank = new LinkedList<TriviaItem>();
		// Construct locationBank
		while (lc.hasNext()) {
			String locationName = lc.next();
			Point locationPoint = new Point(Integer.parseInt(lc.next()), Integer.parseInt(lc.next()));
			locationBank.add(new TriviaItem(locationName, locationPoint));
		}
		Collections.shuffle(locationBank);
		currentIndex = 0;
		bankSize = locationBank.size();
	}
	
	@Override
	public boolean hasNext() {
		return (currentIndex < bankSize);
	}

	@Override
	public TriviaItem next() {
		try {
			TriviaItem answer = locationBank.get(currentIndex);
			currentIndex = currentIndex + 1;
			return answer;
		} catch (IndexOutOfBoundsException e) {
			System.out.println("END OF GAME");
		}
		return null;
	}

}
