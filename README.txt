=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

============================
=: Implementation Details :=
============================

- Overview of each class
  	
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

========================
=: External Resources :=
========================

	Background image from http://thefederalistpapers.org/us/quiz-can-you-name-the-state-based-on-its-shape

