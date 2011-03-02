package webcam;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.media.Buffer;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Format;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.control.FormatControl;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.RGBFormat;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class WebCam extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6623282282717492002L;
	protected JPanel visualContainer = null;
	protected static Player player = null;
	protected FormatControl formatControl;
	protected VideoFormat currentFormat = null;

	public WebCam() {
		initialise();
	}

	public void closeWebcam(WebCam webcam) {
		player.stop();
		player.close();
		player.deallocate();
		player = null;
	}

	public RenderedImage getImage() {
		FrameGrabbingControl frameGrabber = (FrameGrabbingControl) player
				.getControl("javax.media.control.FrameGrabbingControl");
		Buffer buf = frameGrabber.grabFrame();
		BufferToImage btoi = new BufferToImage((VideoFormat) buf.getFormat());
		RenderedImage image = (RenderedImage) btoi.createImage(buf);
		return image;
	}

	public void initialise() {
		CaptureDeviceInfo deviceInfo = CaptureDeviceManager.getDevice("vfw:Microsoft WDM Image Capture (Win32):0");
		try {
			player = Manager.createRealizedPlayer(deviceInfo.getLocator());
		} catch (NoPlayerException e) {
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Format[] formats = deviceInfo.getFormats();
		currentFormat = (VideoFormat) formats[3];
		formatControl = (FormatControl) player
				.getControl("javax.media.control.FormatControl");
		formatControl.setFormat(currentFormat);
		player.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	 public static void main(String[] args) throws Exception
	 {
	 WebCam webcam = new WebCam();
			
	 RenderedImage img = webcam.getImage();
	 webcam.closeWebcam(webcam);
			
	 JFrame frame = new JFrame();
	 ImagePanel panel = new ImagePanel((Image) img);
	 panel.setMinimumSize(new Dimension(320,240));
	 frame.getContentPane().add(panel);
	 frame.setSize(480, 320);
	 frame.setVisible(true);
			
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }

}