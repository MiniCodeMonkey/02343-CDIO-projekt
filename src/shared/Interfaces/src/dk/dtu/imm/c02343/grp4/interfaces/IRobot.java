package dk.dtu.imm.c02343.grp4.interfaces;

public interface IRobot {
	/**
	 * S�tter positionen for robotten som enkelte koordinater
	 * @param y Lodret position, y
	 * @param x Vandret position, x
	 */
	public void setPos(int y, int x);
	
	/**
	 * S�tter positionen for robotten som integer-array
	 * @param yx Array med position som [y,x]
	 */
	public void setPos(int[] yx);
	
	/**
	 * Henter positionen for robotten som integer-array
	 * @return Robottens koordinater som [y,x] int-array
	 */
	public int[] getPos();
	
	/**
	 * S�tter y-koordinaten for robotten
	 * @param y Ny y-koordinat
	 */
	public void setY(int y);
	
	/**
	 * S�tter x-koordinaten for robotten
	 * @param x Ny x-koordinat
	 */
	public void setX(int x);
	
	/**
	 * Henter y-koordinaten for robotten
	 * @return Robottens y-koordinat
	 */
	public int getY();
	
	/**
	 * Henter x-koordinaten for robotten
	 * @return Robottens x-koordinat
	 */
	public int getX();
	
	/**
	 * S�tter vinkel for robotten
	 * @param r Vinkel i radianer
	 */
	public void setAngle(double r);
	
	/**
	 * Henter vinkel for robotten
	 * @return Robottens vinkel i radianer
	 */
	public double getAngle();
}
