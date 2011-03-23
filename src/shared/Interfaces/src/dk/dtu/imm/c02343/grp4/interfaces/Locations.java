package dk.dtu.imm.c02343.grp4.interfaces;

import java.util.List;

/**
 * Specificerer tilemap over banen med forhindringer, samt en liste af kager og en liste af robotter
 * @author per
 */
public interface Locations {
	/**
	 * Hent tilemap
	 */
	public int[][] getTileMap();
	
	/**
	 * Sæt tilemap
	 */
	public void setTilemap(int[][] tileMap);
	
	/**
	 * Hent liste over kager
	 */
	public List<Cake> getCakes();
	
	/**
	 * Sæt liste over kager
	 */
	public void setCakes(List<Cake> cakes);
	
	/**
	 * Hent liste over robotter
	 */
	public List<Robot> getRobots();
	
	/**
	 * Sæt liste over robotter
	 */
	public void setRoots(List<Robot> robots);
}
