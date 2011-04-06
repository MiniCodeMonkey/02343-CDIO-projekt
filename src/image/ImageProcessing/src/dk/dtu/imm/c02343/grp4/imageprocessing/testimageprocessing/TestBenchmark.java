package dk.dtu.imm.c02343.grp4.imageprocessing.testimageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.IImageSource;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.ImageFile;

/**
 * Program til at måle køretiden for et sæt beregninger
 * @author PC
 *
 */
public class TestBenchmark {

	public static void main(String[] args) {
		BufferedImage srcImage = null;
		try {
			srcImage = ImageIO.read(new File("testimages/test1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		IImageSource imageSource = new ImageFile();
		imageSource.init();
		
		long curTime, endTime;
		curTime = System.currentTimeMillis();
		for (int i = 0; i < 1000; i++) {
			srcImage = imageSource.getImage();
			ImageProcessor.examineImage(srcImage, true);
		}
		endTime = System.currentTimeMillis();
		System.out.println("Runtime: " + (endTime-curTime) + "ms");
	}
}