package dk.dtu.imm.c02343.grp4.dto.interfaces;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * Specificerer tilemap over banen med forhindringer, samt en liste af kager og en liste af robotter
 * @author per
 */
public interface ILocations {
	/**
	 * Hent tilemap
	 */
	public int[][] getTilemap();
	
	/**
	 * S�t tilemap
	 */
	public void setTilemap(int[][] tilemap);
	
	/**
	 * Hent liste over kager
	 */
	public List<ICake> getCakes();
	
	/**
	 * S�t liste over kager
	 */
	public void setCakes(List<ICake> cakes);
	
	/**
	 * Hent liste over robotter
	 */
	public List<IRobot> getRobots();
	
	/**
	 * S�t liste over robotter
	 */
	public void setRobots(List<IRobot> robots);
	
	/**
	 * S�tter sourceImage
	 * @param sourceImage Billede, der skal gemmes
	 */
	public void setSourceImage(BufferedImage sourceImage);
	
	/**
	 * Henter sourceImage
	 * @return Det gemte sourceImage
	 */
	public BufferedImage getSourceImage();
	
	/**
	 * S�tter tileImage
	 * @param tileImage Billede, der skal gemmes
	 */
	public void setTileImage(BufferedImage tileImage);
	
	/**
	 * Henter tileImage
	 * @return Det gemte tileImage
	 */
	public BufferedImage getTileImage();
}
