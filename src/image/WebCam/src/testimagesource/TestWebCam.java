package testimagesource;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.RenderedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import imagesource.*;

public class TestWebCam implements ActionListener {
	IImageSource imageSource;
	ImagePanel panel;
	
	public void run() {
		imageSource = new WebCam();
		imageSource.init();
		RenderedImage img = imageSource.getImage();
		imageSource.close();
		
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		panel = new ImagePanel((Image) img);
		panel.setMinimumSize(new Dimension(320,240));
		frame.getContentPane().add(panel);
		
		JPanel panel2 = new JPanel();
		frame.getContentPane().add(panel2);
		
		JButton open = new JButton("Open");
		JButton update = new JButton("Update");
		JButton close = new JButton("Close");
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
	
	public static void main(String[] args) {
		TestWebCam testWebCam = new TestWebCam();
		testWebCam.run();
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals("open")) {
			imageSource.init();
		} else if (ae.getActionCommand().equals("update")) {
			RenderedImage img = imageSource.getImage();
			panel.setImage((Image) img);
			panel.paint(panel.getGraphics());
		} else if (ae.getActionCommand().equals("close")) {
			imageSource.close();
		}
	}
}