package dk.dtu.imm.c02343.grp4.interfaces;

import java.util.List;

/**
 * Specificerer tilemap over banen med forhindringer, samt en liste af kager og en liste af robotter
 * @author per
 */
public interface ILocations {
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
	public List<ICake> getCakes();
	
	/**
	 * Sæt liste over kager
	 */
	public void setCakes(List<ICake> cakes);
	
	/**
	 * Hent liste over robotter
	 */
	public List<IRobot> getRobots();
	
	/**
	 * Sæt liste over robotter
	 */
	public void setRobots(List<IRobot> robots);
}
