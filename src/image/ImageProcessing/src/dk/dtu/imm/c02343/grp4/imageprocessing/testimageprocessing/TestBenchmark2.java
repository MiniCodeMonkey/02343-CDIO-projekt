package dk.dtu.imm.c02343.grp4.imageprocessing.testimageprocessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.IImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor2;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.IImageSource;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.ImageFile;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.WebCam;

/**
 * Program til at måle køretiden for et sæt beregninger
 * @author PC
 *
 */
public class TestBenchmark2 {

	public static void main(String[] args) {
		int count = 10;
		IImageProcessor ip = new ImageProcessor2();
		BufferedImage srcImage = null;
		try {
			srcImage = ImageIO.read(new File("testimages/test1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		IImageSource imageSource = new ImageFile();
//		IImageSource imageSource = new WebCam();
		imageSource.init();
		
		long curTime, endTime;
		curTime = System.currentTimeMillis();
		for (int i = 0; i < count; i++) {
//			srcImage = imageSource.getImage();
			ip.examineImage(srcImage, true);
		}
		endTime = System.currentTimeMillis();
		long runtime = endTime-curTime;
		System.out.println("Runtime: " + runtime + "ms");
		System.out.println("average: " + runtime/count + "ms");
		imageSource.close();
	}
}