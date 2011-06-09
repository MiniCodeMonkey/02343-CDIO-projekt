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
	 * Integer-repræsentation af en forhindring i tilemap
	 */
	public static final int OBSTACLE = 2;
	/**
	 * Integer-repræsentation af en kage i tilemap
	 */
	public static final int CAKE = 1;
	/**
	 * Integer-repræsentation af en robot-front i tilemap
	 */
	public static final int ROBOT1N = 3;
	/**
	 * Integer-repræsentation af en robot-bagdel i tilemap
	 */
	public static final int ROBOT1S = 4;
	/**
	 * Integer-repræsentation af en robot-front i tilemap
	 */
	public static final int ROBOT2N = 5;
	/**
	 * Integer-repræsentation af en robot-bagdel i tilemap
	 */
	public static final int ROBOT2S = 6;
	/**
	 * Integer-repræsentation af baggrund i tilemap
	 */
	public static final int BACKGROUND = 0;
	
	/**
	 * Mindste størrelse på sammenhængende områder i kager og robot-elementer i pixels
	 */
	public static final int MIN_OBJECT_SIZE = 20;
	
	/**
	 * Standard størrelse på forhindrings-sikkerhedszone
	 */
	public static final int OBSTACLE_BUFFER = 30;
	
	/**
	 * Standard grænseværdier for mapping af forhindringer fra billedkilde
	 */
	public static final Thresholds OBSTACLE_THRESHOLDS = new Thresholds(140, 140, 140, 255, 255, 255);
	
	/**
	 * Standard grænseværdier for mapping af kager fra billedkilde
	 */
	public static final Thresholds CAKE_THRESHOLDS = new Thresholds(80, 0, 0, 255, 45, 45);
	
	/**
	 * Standard grænseværdier for mapping af robot1 front fra billedkilde
	 */
	public static final Thresholds ROBOT1_N_THRESHOLDS = new Thresholds(0, 60, 0, 70, 255, 90);
	
	/**
	 * Standard grænseværdier for mapping af robot1 bag fra billedkilde
	 */
	public static final Thresholds ROBOT1_S_THRESHOLDS = new Thresholds(0, 0, 30, 20, 50, 255);
	
	/**
	 * Standard grænseværdier for mapping af robot2 front fra billedkilde
	 */
	public static final Thresholds ROBOT2_N_THRESHOLDS = new Thresholds(150, 60, 0, 220, 90, 40);
	
	/**
	 * Standard grænseværdier for mapping af robot2 bag fra billedkilde
	 */
	public static final Thresholds ROBOT2_S_THRESHOLDS = new Thresholds(170, 170, 0, 230, 230, 90);
	
	/**
	 * Standard værdi for X-opløsningen i behandlingen
	 */
	public static final int RESOLUTION_X = 3;
	
	/**
	 * Standard værdi for Y-opløsningen i behandlingen
	 */
	public static final int RESOLUTION_Y = 3;
	
	/**
	 * Behandler et billede og genererer forhindrings-map samt robot- og kage-positioner
	 * @param sourceImage Billedet, der skal behandles
	 * @param debug Hvidt der skal køres i debug-mode, hvor ekstra billede genereres til illustration af behandlingen
	 * @return ILocations objekt med tilemap, forhindringsmap og robot- og kagepositioner
	 */
	public ILocations examineImage(BufferedImage sourceImage, boolean debug);
	
	public BufferedImage getSourceImage();
	
	public void setSourceImage(BufferedImage sourceImage);
	
	public void setThresholds(int type, Thresholds thresholds);
	
	public Thresholds getThresholds(int type);
	
	public void setObstacleBufferZone(int bufferZone);
	
	public int getObstacleBufferZone();
	
	public void setResolutionX(int resolution);
	
	public void setResolutionY(int resolution);
	
	public int getResolutionX();
	
	public int getResolutionY();
}
