/**
 * 
 */
package dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing;

import java.awt.image.BufferedImage;

import dk.dtu.imm.c02343.grp4.dto.interfaces.ILocations;

/**
 * @author per
 *
 */
/**
 * @author per
 *
 */
public interface IImageProcessor {
	/**
	 * Integer-repr�sentation af en forhindring i tilemap
	 */
	public static final int OBSTACLE = 2;
	/**
	 * Integer-repr�sentation af en kage i tilemap
	 */
	public static final int CAKE = 1;
	/**
	 * Integer-repr�sentation af en robot-front i tilemap
	 */
	public static final int ROBOTN = 3;
	/**
	 * Integer-repr�sentation af en robot-bagdel i tilemap
	 */
	public static final int ROBOTS = 4;
	/**
	 * Integer-repr�sentation af baggrund i tilemap
	 */
	public static final int BACKGROUND = 0;
	
	/**
	 * Mindste st�rrelse p� sammenh�ngende omr�der i kager og robot-elementer i pixels
	 */
	public static final int MIN_OBJECT_SIZE = 15;
	
	/**
	 * Standard st�rrelse p� forhindrings-sikkerhedszone
	 */
	public static final int OBSTACLE_BUFFER = 20;
	
	/**
	 * Standard gr�nsev�rdier for mapping af forhindringer fra billedkilde
	 */
	public static final Thresholds OBSTACLE_THRESHOLDS = new Thresholds(140, 140, 140, 255, 255, 255);
	
	/**
	 * Standard gr�nsev�rdier for mapping af forhindringer fra billedkilde
	 */
	public static final Thresholds CAKE_THRESHOLDS = new Thresholds(70, 0, 0, 255, 25, 25);
	
	/**
	 * Standard gr�nsev�rdier for mapping af forhindringer fra billedkilde
	 */
	public static final Thresholds ROBOT_N_THRESHOLDS = new Thresholds(0, 60, 0, 70, 255, 90);
	
	/**
	 * Standard gr�nsev�rdier for mapping af forhindringer fra billedkilde
	 */
	public static final Thresholds ROBOT_S_THRESHOLDS = new Thresholds(0, 0, 30, 20, 50, 255);
	
	/**
	 * Behandler et billede og genererer forhindrings-map samt robot- og kage-positioner
	 * @param sourceImage Billedet, der skal behandles
	 * @param debug Hvidt der skal k�res i debug-mode, hvor ekstra billede genereres til illustration af behandlingen
	 * @return ILocations objekt med tilemap, forhindringsmap og robot- og kagepositioner
	 */
	public ILocations examineImage(BufferedImage sourceImage, boolean debug);
	
	public BufferedImage getSourceImage();
	
	public void setSourceImage(BufferedImage sourceImage);
	
	public void setThresholds(int type, Thresholds thresholds);
	
	public void setObstacleBufferZone(int bufferZone);
}
