package dk.dtu.imm.c02343.grp4.imageprocessing.testimageprocessing;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dk.dtu.imm.c02343.grp4.dto.interfaces.ICake;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ILocations;
import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor2;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.IImageSource;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.ImageFile;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.WebCam;

/**
 * Program til test af ImageProcessor funktionerne samt ImageSource input
 * @author PC
 *
 */
public class TestImageProcessor2 implements ActionListener {
	ImageProcessor2 imageProcessor;
	IImageSource imageSource;
	ImagePanel panel1, panel2;
	
	/**
	 * Kører testen
	 */
	public void run() {
		// Opret WebCam, initialisér, hent billede til RenderedImage, og luk WebCam igen.
		imageSource = new WebCam();
//		imageSource = new ImageFile();
		imageProcessor = new ImageProcessor2();
//		try {
			imageSource.init();
//		} catch (Exception e) {
//			imageSource = new ImageFile();
//			imageSource.init();
//		}
		BufferedImage sourceImg = imageSource.getImage();
		imageSource.close();
		
		// Opret tile-image vha. hjælpemetode
		ILocations locations = imageProcessor.examineImage(sourceImg, true);
		BufferedImage tileImg = locations.getTileImage();
		
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
	 * Eksekveret metode til test af WebCam. Opret en instans af egen klasse og kør run() 
	 * @param args Bruges ikke
	 */
	public static void main(String[] args) {
		TestImageProcessor2 testWebCam = new TestImageProcessor2();
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
			int count = 1;
			while(count > 0) {
				// Hent billede fra ImageSource og vis dette i panel
				BufferedImage sourceImg = imageSource.getImage();
				ILocations locations = imageProcessor.examineImage(sourceImg, true);
					
				// Opdatér billeder
				panel1.setImage((Image) sourceImg);
				panel1.paint(panel1.getGraphics());
				panel2.removeAll();
				panel2.setSize(locations.getTileImage().getWidth(), locations.getTileImage().getHeight());
				panel2.setImage((Image) locations.getTileImage());
				panel2.paint(panel2.getGraphics());
				count--;
			}
		} else if (ae.getActionCommand().equals("close")) {
			// Luk ImageSource
			imageSource.close();
		}
	}
}