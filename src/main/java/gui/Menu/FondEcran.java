package main.java.gui.Menu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FondEcran extends JPanel {

	private String s;

	public FondEcran(String image) {
		s = image;
	}

	public void paintComponent(Graphics g) {
		try {
			Image image = ImageIO.read(new File(s));
			this.setBounds(this.getX(), this.getY(), this.getSize().width, this.getSize().height);
			repaint();
			image = scale(image, this.getWidth(), this.getHeight());
			g.drawImage(image, 0, 0, this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Image scale(Image source, int width, int height) {
		/* On crée une nouvelle image aux bonnes dimensions. */
		BufferedImage buf = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		/* On dessine sur le Graphics de l'image bufferisée. */
		Graphics2D g = buf.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(source, 0, 0, width, height, null);
		g.dispose();

		/* On retourne l'image bufferisée, qui est une image. */
		return buf;
	}

}
