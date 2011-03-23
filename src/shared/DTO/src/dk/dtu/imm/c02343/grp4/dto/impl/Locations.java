package dk.dtu.imm.c02343.grp4.dto.impl;

import java.util.List;

import dk.dtu.imm.c02343.grp4.interfaces.ICake;
import dk.dtu.imm.c02343.grp4.interfaces.ILocations;
import dk.dtu.imm.c02343.grp4.interfaces.IRobot;

/**
 * Specificerer tilemap over banen med forhindringer, samt en liste af kager og en liste af robotter
 * @author per
 */
public class Locations implements ILocations {
	private int[][] tileMap;
	private List<ICake> cakes;
	private List<IRobot> robots;
	
	/**
	 * Hent tilemap
	 */
	public int[][] getTileMap() {
		return this.tileMap;
	}
	
	/**
	 * Sæt tilemap
	 */
	public void setTilemap(int[][] tileMap) {
		this.tileMap = tileMap;
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
}
