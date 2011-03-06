package imageprocessing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.media.jai.Interpolation;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.ScaleDescriptor;

public class ImageProcessor {
	private int newWidth = 4;
	private int newHeight = 3;
	
	public ImageProcessor(int newWidth, int newHeight) {
		this.newWidth = newWidth;
		this.newHeight = newHeight;
	}

	public Image resizeImage(Image inputImage) {
		float scaleX = (float) newWidth / inputImage.getWidth(null);
		float scaleY = (float) newHeight / inputImage.getHeight(null);
		BufferedImage buffImage = toBufferedImage(inputImage, BufferedImage.TYPE_INT_RGB);
		RenderedOp renderedOp = ScaleDescriptor.create(buffImage, new Float(scaleX), new Float(scaleY), new Float(0f), new Float(0f), Interpolation.getInstance(Interpolation.INTERP_BICUBIC), null);
		return renderedOp.getAsBufferedImage();
	}

	public BufferedImage toBufferedImage(final Image image, final int type) {
		if (image instanceof BufferedImage)
			return (BufferedImage) image;
//		if (image instanceof VolatileImage)
//			return ((VolatileImage) image).getSnapshot();
//		loadImage(image);
		final BufferedImage buffImg = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		final Graphics2D g2 = buffImg.createGraphics();
		g2.drawImage(image, null, null);
		g2.dispose();
		return buffImg;
	}
//
//	private void loadImage(final Image image) {
//		class StatusObserver implements ImageObserver {
//			boolean imageLoaded = false;
//
//			public boolean imageUpdate(final Image img, final int infoflags,
//					final int x, final int y, final int width, final int height) {
//				if (infoflags == ALLBITS) {
//					synchronized (this) {
//						imageLoaded = true;
//						notify();
//					}
//					return true;
//				}
//				return false;
//			}
//		}
//		final StatusObserver imageStatus = new StatusObserver();
//		synchronized (imageStatus) {
//			if (image.getWidth(imageStatus) == -1
//					|| image.getHeight(imageStatus) == -1) {
//				while (!imageStatus.imageLoaded) {
//					try {
//						imageStatus.wait();
//					} catch (InterruptedException ex) {
//					}
//				}
//			}
//		}
//	}
}