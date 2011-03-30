package dk.dtu.imm.c02343.grp4.dto.impl;

import java.awt.image.BufferedImage;
import java.util.List;

import dk.dtu.imm.c02343.grp4.dto.interfaces.ICake;
import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;

/**
 * Specificerer tilemap over banen med forhindringer, samt en liste af kager og en liste af robotter.
 * Indeholder desuden illustrationer af, hvad der benyttes undervejs.
 * @author per
 */
public class DebugLocations extends Locations {
	/**
	 * Input-billede, som er blevet behandlet
	 */
	private BufferedImage sourceImage;
	/**
	 * Genereret grafik over det tolkede billede
	 */
	private BufferedImage tileImage;
	
	/**
	 * Tom konstruktør
	 */
	public DebugLocations() {
		super();
	}
	
	/**
	 * Sætter tilemap, Cake liste og Robot liste
	 * @param tilemap tilemap til Locations
	 * @param cakes Cake-liste
	 * @param robots Robot-liste
	 */
	public DebugLocations(int[][] tilemap, List<ICake> cakes, List<IRobot> robots) {
		super(tilemap, cakes, robots);
	}
	
	/**
	 * Sætter tilemap, Cake liste og Robot liste
	 * @param tilemap tilemap til Locations
	 * @param cakes Cake-liste
	 * @param robots Robot-liste
	 * @param sourceImage Input-billede, som er blevet behandlet
	 * @param tileImage Genereret grafik over det tolkede billede
	 */
	public DebugLocations(int[][] tilemap, List<ICake> cakes, List<IRobot> robots, BufferedImage sourceImage, BufferedImage tileImage) {
		super(tilemap, cakes, robots);
	}
	
	/**
	 * Sætter sourceImage
	 * @param sourceImage Billede, der skal gemmes
	 */
	public void setSourceImage(BufferedImage sourceImage) {
		this.sourceImage = sourceImage;
	}
	
	/**
	 * Henter sourceImage
	 * @return Det gemte sourceImage
	 */
	public BufferedImage getSourceImage() {
		return this.sourceImage;
	}
	
	/**
	 * Sætter tileImage
	 * @param tileImage Billede, der skal gemmes
	 */
	public void setTileImage(BufferedImage tileImage) {
		this.tileImage = tileImage;
	}
	
	/**
	 * Henter tileImage
	 * @return Det gemte tileImage
	 */
	public BufferedImage getTileImage() {
		return this.tileImage;
	}
}
