package dk.dtu.imm.c02343.grp4.imageprocessing.imagesource;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.media.Buffer;
import javax.media.CannotRealizeException;
import javax.media.CaptureDeviceInfo;
import javax.media.CaptureDeviceManager;
import javax.media.Format;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.control.FormatControl;
import javax.media.control.FrameGrabbingControl;
import javax.media.format.VideoFormat;
import javax.media.util.BufferToImage;

/**
 * ImageSource-objekt, som benytter JMF til at skabe forbindelse til et webcam
 * @author PC
 *
 */
public class WebCam implements IImageSource {
	protected static Player player = null;
	protected FormatControl formatControl;
	protected VideoFormat currentFormat = null;
	
	/**
	 * Initialiserer webcam objektet.
	 */
	public WebCam() {
	}

	/**
	 * Luk WebCam objektet. Deaktiverer Player objektet.
	 */
	public void close() {
		player.stop();
		player.close();
		player.deallocate();
		player = null;
	}

	/**
	 * Initialisér webcam-enhed samt styrende objekter til forbindelsen
	 */
	public void init() {
		// Hent webcam info
		CaptureDeviceInfo deviceInfo = CaptureDeviceManager.getDevice("vfw:Microsoft WDM Image Capture (Win32):0");
		// Opret player med forbindelse til webcam
		try {
			player = Manager.createRealizedPlayer(deviceInfo.getLocator());
		} catch (NoPlayerException e) {
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Hent mulige formater for webcam
		Format[] formats = deviceInfo.getFormats();
		/*
0. javax.media.format.YUVFormat
  YUV Video Format: Size = java.awt.Dimension[width=640,height=480] MaxDataLength = 460800 DataType = class [B yuvType = 2 StrideY = 640 StrideUV = 320 OffsetY = 0 OffsetU = 307200 OffsetV = 384000
1. javax.media.format.RGBFormat
  RGB, 160x120, Length=57600, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=480, Flipped
2. javax.media.format.RGBFormat
  RGB, 176x144, Length=76032, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=528, Flipped
3. javax.media.format.RGBFormat
  RGB, 320x240, Length=230400, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=960, Flipped
4. javax.media.format.RGBFormat
  RGB, 352x288, Length=304128, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=1056, Flipped
5. javax.media.format.RGBFormat
  RGB, 640x480, Length=921600, 24-bit, Masks=3:2:1, PixelStride=3, LineStride=1920, Flipped
6. javax.media.format.YUVFormat
  YUV Video Format: Size = java.awt.Dimension[width=160,height=120] MaxDataLength = 28800 DataType = class [B yuvType = 2 StrideY = 160 StrideUV = 80 OffsetY = 0 OffsetU = 19200 OffsetV = 24000
7. javax.media.format.YUVFormat
  YUV Video Format: Size = java.awt.Dimension[width=176,height=144] MaxDataLength = 38016 DataType = class [B yuvType = 2 StrideY = 176 StrideUV = 88 OffsetY = 0 OffsetU = 25344 OffsetV = 31680
8. javax.media.format.YUVFormat
  YUV Video Format: Size = java.awt.Dimension[width=320,height=240] MaxDataLength = 115200 DataType = class [B yuvType = 2 StrideY = 320 StrideUV = 160 OffsetY = 0 OffsetU = 76800 OffsetV = 96000
9. javax.media.format.YUVFormat
  YUV Video Format: Size = java.awt.Dimension[width=352,height=288] MaxDataLength = 152064 DataType = class [B yuvType = 2 StrideY = 352 StrideUV = 176 OffsetY = 0 OffsetU = 101376 OffsetV = 126720
		 */
		// Sæt format
		currentFormat = (VideoFormat) formats[3];
		formatControl = (FormatControl) player.getControl("javax.media.control.FormatControl");
		formatControl.setFormat(currentFormat);
		// Start player
		player.start();
		// Vent, så playeren får tid til at starte op
		try {
			Thread.sleep(5000);
//			do {
//				Thread.sleep(100);
//			} while (player.getState() == Controller.Unrealized || player.getState() == Controller.Realizing);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hent nuværende billede fra webcam
	 * @returns Det aktuelle billede fra webcam
	 */
	public BufferedImage getImage() {
		// Opret framegrabber objekt fra player
		FrameGrabbingControl frameGrabber = (FrameGrabbingControl) player.getControl("javax.media.control.FrameGrabbingControl");
		// Opret buffer med billede, og konvertér dette til et RenderedImage, som returneres
		Buffer buf = frameGrabber.grabFrame();
		BufferToImage btoi = new BufferToImage((VideoFormat) buf.getFormat());
		RenderedImage image = (RenderedImage) btoi.createImage(buf);
		return (BufferedImage) image;
	}
}