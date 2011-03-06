package testimagesource;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import imagesource.*;

/**
 * Testklasse til ImageSource objekter. Benytter lidt sekvens fra givet kode på CampusNet, samt ImagePanel fra http://www.javafaq.nu/java-example-code-750.html (let modificeret)
 * @author PC
 */
public class TestWebCam implements ActionListener {
	IImageSource imageSource;
	ImagePanel panel;
	
	/**
	 * Metoden, der kører test af WebCam
	 */
	public void run() {
		// Opret WebCam, initialisér, hent billede til RenderedImage, og luk WebCam igen.
		imageSource = new WebCam();
		//imageSource = new ImageFile();
		imageSource.init();
		Image img = imageSource.getImage();
		imageSource.close();
		
		// Opret JFrame samt panel til billede
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		panel = new ImagePanel(img);
		panel.setMinimumSize(new Dimension(320,240));
		frame.getContentPane().add(panel);
		
		// Opret panel til knapper
		JPanel panel2 = new JPanel();
		frame.getContentPane().add(panel2);
		
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
		panel2.add(open);
		panel2.add(update);
		panel2.add(close);
		
		frame.setSize(480, 320);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Eksekveret metode til test af WebCam. Opret en instans af egen klasse og kør run() 
	 * @param args Bruges ikke
	 */
	public static void main(String[] args) {
		TestWebCam testWebCam = new TestWebCam();
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
			Image img = imageSource.getImage();
			panel.setImage((Image) img);
			panel.paint(panel.getGraphics());
		} else if (ae.getActionCommand().equals("close")) {
			// Luk ImageSource
			imageSource.close();
		}
	}
}