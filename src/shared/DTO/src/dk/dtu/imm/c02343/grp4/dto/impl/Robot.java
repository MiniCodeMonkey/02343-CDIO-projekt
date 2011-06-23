package dk.dtu.imm.c02343.grp4.dto.impl;

/**
 * Repræsenterer robotter på banen
 * @author per
 *
 */
public class Robot implements dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot {
	private int x, y;
	private double angle;
	
	/**
	 * Opretter en Robot uden koordinater
	 */
	public Robot() {
		this(-1,-1,0);
	}
	
	/**
	 * Opretter en Robot med specifikke koordinater
	 * @param y Lodret position, y
	 * @param x Vandret position, x
	 * @param r Vinkel i radianer
	 */
	public Robot(int y, int x, double r) {
		setPos(y,x);
		setAngle(r);
	}
	
	/**
	 * Sætter positionen for robotten som enkelte koordinater
	 * @param y Lodret position, y
	 * @param x Vandret position, x
	 */
	public void setPos(int y, int x) {
		this.y = y;
		this.x = x;
	}

	/**
	 * Sætter positionen for robotten som integer-array
	 * @param yx Array med position som [y,x]
	 */
	public void setPos(int[] yx) {
		this.y = yx[0];
		this.x = yx[1];
	}

	/**
	 * Henter positionen for robotten som integer-array
	 * @return Robottens koordinater som [y,x] int-array
	 */
	public int[] getPos() {
		return new int[] {this.y,this.x};
	}

	/**
	 * Sætter y-koordinaten for robotten
	 * @param y Ny y-koordinat
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Sætter x-koordinaten for robotten
	 * @param x Ny x-koordinat
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Henter y-koordinaten for robotten
	 * @return Robottens y-koordinat
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Henter x-koordinaten for robotten
	 * @return Robottens x-koordinat
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Sætter vinkel for robotten
	 * @param r Vinkel i radianer
	 */
	public void setAngle(double r) {
		this.angle = r;
	}

	/**
	 * Henter vinkel for robotten
	 * @return Robottens vinkel i radianer
	 */
	public double getAngle() {
		return this.angle;
	}
	
	public String toString() {
		String out = "";
		
		out += "Position (y,x): (" + this.y + "," + this.x + ")\n";
		out += "Angle    (deg): " + this.angle*180/Math.PI;
		
		return out;
	}

	@Override
	public boolean isActive() {
		
		return (x == -1 && y == -1)? false: true;
	}
	
	@Override public boolean equals(Object otherObject)
	{
		Robot otherRobot = (Robot)otherObject;
		
		return (this.getX() == otherRobot.getX() && this.getY() == otherRobot.getY());
	}
}