import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

// This class organizes the main panel of the game, which "listens" for user
// input (mouse clicks), and coordinates the various game objects upon user
// clicks. It is essentially the controller.
public class MapPanel extends JPanel {
	
	// court size fields
	public static final int COURT_WIDTH = 1000;
	public static final int COURT_HEIGHT = 700;
	
	// labels, buttons and backgrounds
	private final MapBackground background;
	private JLabel question;
	private JButton nextButton;
	private JLabel scoreLabel;
	
	// Game state
	private int score = 0;
	
	// IO fields
	private PromptIterator p;
	private Writer out;
	
	// Other fields
	private int qNum = 0;
	private TriviaItem currentQ;
	private int answerX;
	private int answerY;
	private GameMode mode = GameMode.USER_INPUT_MODE;
	
	// Constructor
	public MapPanel(MapBackground mapBackground, JLabel question, 
			PromptIterator p, JButton nextButton, JLabel scoreLabel) {
		
		this.background = mapBackground;
		this.p = p;
		this.question = question;
		this.nextButton = nextButton;
		
		// Set current question
		currentQ = p.next();
		question.setText(currentQ.getLocationName());
				
		// Next Button
		nextButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (mode == GameMode.TRANSITION_MODE) {
					nextQuestion();
					nextButton.setVisible(false);
					mode = GameMode.USER_INPUT_MODE;
				}
			}
		}); 
		nextButton.setVisible(false);
		
		// Mouse Adapter
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				if (mode == GameMode.USER_INPUT_MODE) {
					
					// calculate distance
					int x1 = currentQ.getLocationPoint().getX();
					int y1 = currentQ.getLocationPoint().getY();
					answerX = e.getX();
					answerY = e.getY();
					int dx = answerX - x1;
					int dy = answerY - y1;
					int distance = (int) Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
					
					// calculate distance in miles, send to display
					int miles = distance * (713/232);
					question.setText("You clicked " + Integer.toString(miles) + " miles away.");
					
					// calculate score, update label
					score = score + calculateScore(distance);
					scoreLabel.setText("Score: " + Integer.toString(score));
					
					// Show next button, Update Game Mode
					nextButton.setVisible(true);
					mode = GameMode.TRANSITION_MODE;
					
					// Check for end of game
					if (!p.hasNext()) {
						String text = question.getText();
						question.setText(text + " End of game! Your final score is " + score);
						nextButton.setVisible(false);
						mode = GameMode.END_OF_GAME_MODE;
						
						// Write score to a text file
						try {
							out = new BufferedWriter(new FileWriter("scores.txt", true));
							out.write("Score;" + Integer.toString(score) + ";0\n");
							out.close();
						} catch (IOException e2) {
							System.out.println(e2.getMessage());
						}
					}
				}
			}
		});
	}
	
	// Transitions to next question, resets mode to USER_INPUT_MODE
	public void nextQuestion() {
		if (p.hasNext()) {
			qNum = qNum + 1;
			currentQ = p.next();
			question.setText(currentQ.getLocationName());
		}
	}
	
	// Score calculator
	private int calculateScore(int distance) {
		if (distance <= 5) {
			return 500;
		}
		if (distance < 200) {
			return (200 - distance);
		}
		return 0;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		background.draw(g);
		if (mode == GameMode.TRANSITION_MODE || mode == GameMode.END_OF_GAME_MODE) {
			g.drawLine(currentQ.getLocationPoint().getX(), currentQ.getLocationPoint().getY(), answerX, answerY);
			g.drawOval(currentQ.getLocationPoint().getX(), currentQ.getLocationPoint().getY(), 5, 5);
		} else {
			g.dispose();
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}

}
