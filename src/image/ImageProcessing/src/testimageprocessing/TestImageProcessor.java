package testimageprocessing;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import imageprocessing.*;
import imagesource.*;

/**
 * Program til test af ImageProcessor funktionerne samt ImageSource input
 * @author PC
 *
 */
public class TestImageProcessor implements ActionListener {
	ImageProcessor imageProcessor;
	IImageSource imageSource;
	ImagePanel panel1, panel2;
	
	/**
	 * K�rer testen
	 */
	public void run() {
		// Opret WebCam, initialis�r, hent billede til RenderedImage, og luk WebCam igen.
//		imageSource = new WebCam();
		imageSource = new ImageFile();
		imageSource.init();
		BufferedImage sourceImg = imageSource.getImage();
		imageSource.close();
		
		// Skal�r billede
//		sourceImg = (BufferedImage) ImageProcessor.resizeImage((Image) sourceImg, 160, 120);
		
		// Opret tile-image vha. hj�lpemetode
		BufferedImage tileImg = new BufferedImage(sourceImg.getWidth(), sourceImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
		createTileImage(sourceImg, tileImg);
		
		// Opret JFrame samt panel til input-billede
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		panel1 = new ImagePanel(sourceImg);
		panel1.setMinimumSize(new Dimension(sourceImg.getWidth(),sourceImg.getHeight()));
		frame.getContentPane().add(panel1);
		
		// Opret JFrame samt panel til tile-billede
		panel2 = new ImagePanel(tileImg);
		panel2.setMinimumSize(new Dimension(tileImg.getWidth(),tileImg.getHeight()));
		frame.getContentPane().add(panel2);
		
		// Opret panel til knapper
		JPanel buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel);
		
		// Defin�r knapper
		JButton open = new JButton("Connect");
		JButton update = new JButton("Update image");
		JButton close = new JButton("Disconnect");
		open.setActionCommand("open");
		update.setActionCommand("update");
		close.setActionCommand("close");
		open.addActionListener(this);
		update.addActionListener(this);
		close.addActionListener(this);
		buttonPanel.add(open);
		buttonPanel.add(update);
		buttonPanel.add(close);
		
		// Defin�r st�rrelse p� vindue
		frame.setSize(sourceImg.getWidth() + 20, sourceImg.getHeight()*2 + 90);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Hj�lpemetode til at oprette tilemap og billede ud fra disse informationer
	 * @param sourceImg
	 * @param tileImg
	 */
	private void createTileImage(BufferedImage sourceImg, BufferedImage tileImg) {
		int[][] map = ImageProcessor.createTileMap(sourceImg);
		System.out.println();
		// Iter�r over alle vandrette linjer
		for(int i = 0; i < map.length; i++) {
			// Iter�r over alle punkter
			for(int j = 0; j < map[i].length; j++) {
				int rgb;
				// S�t RGB-v�rdi til output-billede ud fra tilemap v�rdi. To f�rste hex-v�rdier er alpha-v�rdi: RGB = 0xAARRGGBB.
				switch (map[i][j]) {
					case 1:
						rgb = 0xFFFF0000;
						break;
					case 2:
						rgb = 0xFFFFFFFF;
						break;
					default:
						rgb = 0xFF000000;
				}
				// S�t pixel-v�rdi
				tileImg.setRGB(j, i, rgb);
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
	
	/**
	 * Eksekveret metode til test af WebCam. Opret en instans af egen klasse og k�r run() 
	 * @param args Bruges ikke
	 */
	public static void main(String[] args) {
		TestImageProcessor testWebCam = new TestImageProcessor();
		testWebCam.run();
	}
	
	/**
	 * ActionPerformed for knapperne 
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("open")) {
			// Initialis�r ImageSource
			imageSource.init();
		} else if (ae.getActionCommand().equals("update")) {
			// Hent billede fra ImageSource og vis dette i panel
			BufferedImage sourceImg = imageSource.getImage();
			BufferedImage tileImg = new BufferedImage(sourceImg.getWidth(), sourceImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
//			img = ImageProcessor.resizeImage(img, 160, 120);
			// Opret tilemap og billede 
			createTileImage(sourceImg, tileImg);
			// Opdat�r billeder
			panel1.setImage((Image) sourceImg);
			panel1.paint(panel1.getGraphics());
			panel2.setImage((Image) tileImg);
			panel2.paint(panel2.getGraphics());
		} else if (ae.getActionCommand().equals("close")) {
			// Luk ImageSource
			imageSource.close();
		}
	}
}