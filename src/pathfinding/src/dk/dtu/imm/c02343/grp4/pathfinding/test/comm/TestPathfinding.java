package dk.dtu.imm.c02343.grp4.pathfinding.test.comm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.rmi.CORBA.Tie;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Program til test af ImageProcessor funktionerne samt ImageSource input
 * @author PC
 *
 */
public class TestPathfinding implements ActionListener {
	ImagePanel panel1, panel2;
	ProcessingThread processingThread;
	
	/**
	 * Kører testen
	 */
	public void run() {		
		// Opret JFrame samt panel til input-billede
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		panel1 = new ImagePanel("");
		frame.getContentPane().add(panel1);
		
		// Opret JFrame samt panel til tile-billede
		panel2 = new ImagePanel("");
		frame.getContentPane().add(panel2);
		
		// Opret panel til knapper
		JPanel buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel);
		
		// Definér knapper
		JButton connect = new JButton("Connect");
		connect.setActionCommand("connect");
		connect.addActionListener(this);
		buttonPanel.add(connect);
		
		JButton disconnect = new JButton("Disconnect");
		disconnect.setActionCommand("disconnect");
		disconnect.addActionListener(this);
		buttonPanel.add(disconnect);
		
		// Definér størrelse på vindue
		frame.setSize(500, 500);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Eksekveret metode til test af WebCam. Opret en instans af egen klasse og kør run() 
	 * @param args Bruges ikke
	 */
	public static void main(String[] args) {
		TestPathfinding testPathfinding = new TestPathfinding();
		testPathfinding.run();
	}
	
	/**
	 * ActionPerformed for knapperne 
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("connect")) {
			processingThread = new ProcessingThread();
			processingThread.start();
			
		} else if (ae.getActionCommand().equals("disconnect")) {
			processingThread.stopThread();
		}
	}

	public void updateImages(BufferedImage sourceImage, BufferedImage tileImage) {
		panel1.setImage(sourceImage);
		panel2.setImage(tileImage);
	}
}