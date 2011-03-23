package dk.dtu.imm.c02343.grp4.interfaces;

/**
 * Repr�senterer kager p� banen
 * @author per
 *
 */
public interface Cake {
	/**
	 * S�tter positionen for kagen som enkelte koordinater
	 * @param y Lodret position, y
	 * @param x Vandret position, x
	 */
	public void setPos(int y, int x);
	
	/**
	 * S�tter positionen for kagen som integer-array
	 * @param yx Array med position som [y,x]
	 */
	public void setPos(int[] yx);
	
	/**
	 * Henter positionen for kagen som integer-array
	 * @return Kagens koordinater som [y,x] int-array
	 */
	public int[] getPos();
	
	/**
	 * S�tter y-koordinaten for kagen
	 * @param y Ny y-koordinat
	 */
	public void setY(int y);
	
	/**
	 * S�tter x-koordinaten for kagen
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