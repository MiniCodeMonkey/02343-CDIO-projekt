package dk.dtu.imm.c02343.grp4.imageprocessing.testimageprocessing;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.IImageSource;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.ImageFile;

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
	 * Kører testen
	 */
	public void run() {
		// Opret WebCam, initialisér, hent billede til RenderedImage, og luk WebCam igen.
//		imageSource = new WebCam();
		imageSource = new ImageFile();
		imageSource.init();
		BufferedImage sourceImg = imageSource.getImage();
		imageSource.close();
		
		// Skalér billede
//		sourceImg = (BufferedImage) ImageProcessor.resizeImage((Image) sourceImg, 160, 120);
		
		// Opret tile-image vha. hjælpemetode
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
		
		// Definér knapper
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
		
		// Definér størrelse på vindue
		frame.setSize(sourceImg.getWidth() + 20, sourceImg.getHeight()*2 + 90);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Hjælpemetode til at oprette tilemap og billede ud fra disse informationer
	 * @param sourceImg
	 * @param tileImg
	 */
	private void createTileImage(BufferedImage sourceImg, BufferedImage tileImg) {
		int[][] map = ImageProcessor.createTileMap(sourceImg);
		int[] bounds = ImageProcessor.findBounds(map);
		System.out.println("Boundaries: (top,left,bottom,right): ("+bounds[0]+","+bounds[1]+","+bounds[2]+","+bounds[3]+")");
		map = ImageProcessor.cropTilemap(map, bounds);
		// Iterér over alle vandrette linjer
		for(int i = 0; i < map.length; i++) {
			// Iterér over alle punkter
			for(int j = 0; j < map[i].length; j++) {
				int rgb;
				// Sæt RGB-værdi til output-billede ud fra tilemap værdi. To første hex-værdier er alpha-værdi: RGB = 0xAARRGGBB.
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
				// Sæt pixel-værdi
				tileImg.setRGB(j, i, rgb);
//				System.out.print(map[i][j]); // Til udskrift af tilemap i console
			}
//			System.out.println(); // Til udskrift af tilemap i console
		}
		
		ArrayList<int[]> objects = ImageProcessor.findPickUpObjects(map, 1);
		Iterator<int[]> itr = objects.iterator();
		while(itr.hasNext()) {
			int[] pos = itr.next();
			System.out.println("Object at (" + pos[0] + "," + pos[1] + ").");
			tileImg.setRGB(pos[0], pos[1], 0xFF00FFFF);
			tileImg.setRGB(pos[0]+1, pos[1], 0xFF00FFFF);
			tileImg.setRGB(pos[0]-1, pos[1], 0xFF00FFFF);
			tileImg.setRGB(pos[0], pos[1]+1, 0xFF00FFFF);
			tileImg.setRGB(pos[0], pos[1]-1, 0xFF00FFFF);
		}
		
		try {
			ArrayList<int[]> robots = ImageProcessor.findRobots(map, 3, 4);
			itr = robots.iterator();
			while(itr.hasNext()) {
				int[] pos = itr.next();
				System.out.println("Robot at [(" + pos[0] + "," + pos[1] + ")],[(" + pos[2] + "," + pos[3] + ")]");
				tileImg.setRGB(pos[0],pos[1], 0xFF00FF00);
				tileImg.setRGB(pos[0]+1,pos[1], 0xFF00FF00);
				tileImg.setRGB(pos[0]-1,pos[1], 0xFF00FF00);
				tileImg.setRGB(pos[0],pos[1]+1, 0xFF00FF00);
				tileImg.setRGB(pos[0],pos[1]-1, 0xFF00FF00);
				tileImg.setRGB(pos[2],pos[3], 0xFF0000FF);
				tileImg.setRGB(pos[2]+1,pos[3], 0xFF0000FF);
				tileImg.setRGB(pos[2]-1,pos[3], 0xFF0000FF);
				tileImg.setRGB(pos[2],pos[3]+1, 0xFF0000FF);
				tileImg.setRGB(pos[2],pos[3]-1, 0xFF0000FF);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
/*		int[] bounds = ImageProcessor.findBounds(map);
		System.out.println("Boundaries: (top,left,bottom,right): ("+bounds[0]+","+bounds[1]+","+bounds[2]+","+bounds[3]+")");
		for(int y = bounds[1]; y < bounds[3]; y++) {
			tileImg.setRGB(y, bounds[0], 0xFF00FF00);
			tileImg.setRGB(y, bounds[2], 0xFF00FF00);
		}
		for(int x = bounds[0]; x < bounds[2]; x++) {
			tileImg.setRGB(bounds[1], x, 0xFF00FF00);
			tileImg.setRGB(bounds[3], x, 0xFF00FF00);
		}*/
	}
	
	/**
	 * Eksekveret metode til test af WebCam. Opret en instans af egen klasse og kør run() 
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
			// Initialisér ImageSource
			imageSource.init();
		} else if (ae.getActionCommand().equals("update")) {
			// Hent billede fra ImageSource og vis dette i panel
			BufferedImage sourceImg = imageSource.getImage();
			BufferedImage tileImg = new BufferedImage(sourceImg.getWidth(), sourceImg.getHeight(), BufferedImage.TYPE_INT_ARGB);
			
			// Skalér billede
//			sourceImg = (BufferedImage) ImageProcessor.resizeImage(sourceImg, 160, 120);
			
			// Opret tilemap og billede 
			createTileImage(sourceImg, tileImg);
				
			// Opdatér billeder
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