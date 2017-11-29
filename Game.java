import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class Game implements Runnable {
	
	public int highScore = 0;
	
	@Override
	public void run() {
		// Top-Level Frame
		final JFrame frame = new JFrame("GeoMaster");
		frame.setLocation(300, 300);
		
		try {
			// Prompt Iterator
			LocationScanner lc = new LocationScanner(new FileReader("locations.txt"));
			PromptIterator pi = new PromptIterator(lc);
			
			// Calculate high score
			LocationScanner scoreScanner = new LocationScanner(new FileReader("scores.txt"));
			PromptIterator scoreIterator = new PromptIterator(scoreScanner);
			while (scoreIterator.hasNext()) {
				TriviaItem scoreItem = scoreIterator.next();
				int thisScore = scoreItem.getLocationPoint().getX();
				if (thisScore > highScore) {
					highScore = thisScore;
				}
			}
			
			// Question Panel
			JLabel question = new JLabel("Question");
			JPanel questionPanel = new JPanel();
			questionPanel.add(question, BorderLayout.NORTH);
			frame.add(questionPanel, BorderLayout.SOUTH);
			
			// Next Button - calls MapPanel's nextQuestion() method
			JButton nextButton = new JButton("Next");
			questionPanel.add(nextButton, BorderLayout.SOUTH);
			
			// Score Label
			JLabel scoreLabel = new JLabel("Score: 0");
			
			// Score Panel
			JPanel scorePanel = new JPanel();
			scorePanel.add(scoreLabel);
			frame.add(scorePanel, BorderLayout.EAST);
			
			// Map Panel
			MapPanel mapPanel = new MapPanel(new MapBackground("USMap.png"), 
					question, pi, nextButton, scoreLabel);
			frame.add(mapPanel, BorderLayout.CENTER);
			
			// Put the frame on the screen
			frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			
			// Instructions Frame
			JOptionPane.showMessageDialog(frame, "Welcome to GeoMaster! This is a game about testing \n"
					+ "your US Geography knowledge. Each round, the name of a US landmark will \n"
					+ "appear at the bottom of the screen. This will either be a city, man-made \n"
					+ "structure, or natural wonder found in the US. Click on the map where you think \n"
					+ "this landmark is located, and the game will read out how far away you clicked! \n"
					+ "If you click within 15 miles of the actual location, you get 500 points! If you \n"
					+ "click within 600 miles, your score will range between 0 and 200, based on the \n"
					+ "exact distance you clicked. Good Luck! \n \n"
					+ "Current high score: " + Integer.toString(highScore));
			
		} catch (IOException e) {
			System.out.println("IOException " + e.getMessage());
		}
	}
	

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}

}
