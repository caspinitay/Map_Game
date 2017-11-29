import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class MapBackground extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4225487013911296527L;
	
	// Fields
	private static BufferedImage mapImage;
	
	// Constructor
	public MapBackground(String fileName) {
		try {
			mapImage = ImageIO.read(new File(fileName));
		} catch (IOException e) {
			System.out.println("Error with Image File");
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(mapImage, 0, 0, mapImage.getWidth(), mapImage.getHeight(), null);
	}

}
