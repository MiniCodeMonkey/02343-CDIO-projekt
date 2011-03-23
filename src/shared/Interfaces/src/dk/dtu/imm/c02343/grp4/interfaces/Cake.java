package dk.dtu.imm.c02343.grp4.interfaces;

/**
 * Repræsenterer kager på banen
 * @author per
 *
 */
public interface Cake {
	/**
	 * Sætter positionen for kagen som enkelte koordinater
	 * @param y Lodret position, y
	 * @param x Vandret position, x
	 */
	public void setPos(int y, int x);
	
	/**
	 * Sætter positionen for kagen som integer-array
	 * @param yx Array med position som [y,x]
	 */
	public void setPos(int[] yx);
	
	/**
	 * Henter positionen for kagen som integer-array
	 * @return Kagens koordinater som [y,x] int-array
	 */
	public int[] getPos();
	
	/**
	 * Sætter y-koordinaten for kagen
	 * @param y Ny y-koordinat
	 */
	public void setY(int y);
	
	/**
	 * Sætter x-koordinaten for kagen
	 * @param x Ny x-koordinat
	 */
	public void setX(int x);
	
	/**
	 * Henter y-koordinaten for kagen
	 * @return Kagens x-koordinat
	 */
	public int getY();
	
	/**
	 * Henter x-koordinaten for kagen
	 * @return Kagens x-koordinat
	 */
	public int getX();
}