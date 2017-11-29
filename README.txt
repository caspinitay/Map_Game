=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: caspin
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an approprate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections: I used a linked list to store all the questions and answers (TriviaItems). I
  	 initially planned on using TreeMaps to associate the location with the point on the map, but
  	 I decided instead to create a new class (TriviaItem) and make a linked list of TriviaItem
  	 instances. I decided to use a linked list because it is easy to iterate through. I decided to
  	 build a linked list at the beginning of the game so that the order of the questions would be
  	 pre-determined. Additionally, I wanted the order of the questions to be different each time
  	 the game was played, but the order needed to stay the same during the game. To do this, I used
  	 the Collections.shuffle(linkedList) method. Overall, the implementation was more simple with
  	 linked lists.

  2. I/O: I used IO in two ways in my game. First, in order to create the collection of TriviaItems,
  	 I parse a .txt file with all of the information. I formatted the file such that each row contained
  	 information in the following format: "location_name;x-coordinate;y-coordinate". I then parse this
  	 file and create a TriviaItem from each row of the file. I used IO for this rather than hardcoding
  	 each question so that I could easily organize all of the information. The other use for IO in my
  	 game is for saving the high score. At the beginning of each round, the game parses score.txt to
  	 find the highest score, and displays it to the user in the instructions panel. Then, at the end
  	 of the game, the score is written to score.txt. For efficiency, I re-used the same formatting for
  	 score.txt as locations.txt and I used the same scanners and iterators to parse the files.

  3. Subtyping: I created a subclass of JPanel called MapPanel. This class functioned as the controller
  	 for the game. It used the superclass methods in order to present itself on the main frame, as
  	 well as to control other JComponents (JLabels and JButtons). The main change with this subclass
  	 is the constructor, which handles the file readers, and implements a mouse adapter for handling
  	 user input and the associated logic. MapPanel overrides paintComponent() in order to draw the
  	 custom background and to draw the user's error during particular modes in the game.

  4. JUnit Testing: I used JUnit testing to test the file IO functionality of the game. Since I used
  	 my own format for parsing the files, I tested to make sure that each TriviaItem was created
  	 properly and that the next() method was working as expected. One of the edge cases I accounted for
  	 was reaching the end of the file in order to make sure the hasNext() function worked properly.
  	 I also tested to see what would happen with various errors: trying to parse files that do not
  	 exist in the game directory, handling invalid inputs (negative numbers), and handling invalid
  	 formatting (one number instead of two).


=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  	
  	Game: Top-Level frame, "view" file. Organizes the different parts of the user interface,
  		and calls the "run" method to get the game started.
  	
  	GameMode: Enum that contains all the possible modes of the game: user input mode, transition
  		mode (to run code between questions), and end of game mode.
  		
  	LocationScanner: Parses .txt files that are specially formatted for the game:
		The reader can only handle files with lists of the format
		"[Location Name];x;y\n", where x and y represents the x and y coordinates
		on the map. This scanner decomposes each row of the text file into a location
		name string, an x-coordinate string, and a y-coordinate string. This is also
		used to parse the high scores file.
	
	MapBackground: Separates image I/O functionality from MapPanel to set the map image
		in the background.
	
	MapPanel: This class organizes the main panel of the game, which "listens" for user
		input (mouse clicks), and coordinates the various game objects upon user
		clicks. This class functions as the controller: it handles all of the model files,
		manages the game state, and modifies the displays in the user interface.
	
	Point: Simple Point class used to model user input on the map and locations on the map.
	
	PromptIterator: Uses a LocationScanner to build a linked list of TriviaItems, then shuffles 
		the list, and finally implements an iterator to jump through each TriviaItem to make questions.
		
	TriviaItem: Each TriviaItem object has a string 'locationName' and a Point 'locationPoint', which
		models the location of the TriviaItem on the map. This class models the question and expected
		answer.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
    The major stumbling block was implementing levels. I originally planned on having
  	three levels in my game, each with progressively harder questions. However, I came
  	across a very tricky bug during my implementation. I tried to start a new level when
  	my PromptIterator object returned false for hasNext(), and then create a new 
  	PromptIterator to scan the new file. However, when I tested this out, my new PromptIterator
  	always returned false, even though it continued to return the next questions. Realistically,
  	my biggest limitation with debugging was time.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
	
	Overall, there are parts of my design that I am proud of and parts that I think could be
	better. I think there is a pretty good separation of functionality between the classes.
	Some classes served as models (TriviaItem, Point, LocationScanner), while the controller
	and view were distinct classes (MapPanel and Game, respectively). I also separated functionality
	between the LocationScanner and the Prompt Iterator: the former actually reads the document, while
	the latter formats the data to create TriaItem objects. However, I think this implementation is
	too complicated. I probably could have combined the file parsing and TriviaItem construction into
	one class. I would also refactor the scoring system to take Alaska and Hawaii into consideration,
	since the current scoring system treats them as regular map objects and doesn't take into account
	the different scales on the map.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.

	Background image from http://thefederalistpapers.org/us/quiz-can-you-name-the-state-based-on-its-shape

