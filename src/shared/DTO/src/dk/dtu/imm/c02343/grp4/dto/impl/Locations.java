package dk.dtu.imm.c02343.grp4.dto.impl;

import java.awt.image.BufferedImage;
import java.util.List;

import dk.dtu.imm.c02343.grp4.interfaces.ICake;
import dk.dtu.imm.c02343.grp4.interfaces.ILocations;
import dk.dtu.imm.c02343.grp4.interfaces.IRobot;

/**
 * Specificerer tilemap over banen med forhindringer, samt en liste af kager og en liste af robotter
 * @author per
 */
public class Locations implements ILocations {
	private int[][] tilemap;
	private List<ICake> cakes;
	private List<IRobot> robots;
	private BufferedImage sourceImage;
	private BufferedImage tileImage;
	
	/**
	 * Tom konstruktør
	 */
	public Locations() {
	}
	
	/**
	 * Sætter tilemap, Cake liste og Robot liste
	 * @param tilemap tilemap til Locations
	 * @param cakes Cake-liste
	 * @param robots Robot-liste
	 */
	public Locations(int[][] tilemap, List<ICake> cakes, List<IRobot> robots) {
		setTilemap(tilemap);
		setCakes(cakes);
		setRobots(robots);
	}
	
	/**
	 * Hent tilemap
	 */
	public int[][] getTilemap() {
		return this.tilemap;
	}
	
	/**
	 * Sæt tilemap
	 */
	public void setTilemap(int[][] tilemap) {
		this.tilemap = tilemap;
	}
	
	/**
	 * Hent liste over kager
	 */
	public List<ICake> getCakes() {
		return this.cakes;
	}
	
	/**
	 * Sæt liste over kager
	 */
	public void setCakes(List<ICake> cakes) {
		this.cakes = cakes;
	}
	
	/**
	 * Hent liste over robotter
	 */
	public List<IRobot> getRobots() {
		return this.robots;
	}
	
	/**
	 * Sæt liste over robotter
	 */
	public void setRobots(List<IRobot> robots) {
		this.robots = robots;
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
