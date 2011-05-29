package dk.dtu.imm.c02343.grp4.dto.impl;

import dk.dtu.imm.c02343.grp4.dto.interfaces.ICake;

/**
 * Repr�senterer kager p� banen
 * @author per
 *
 */
public class Cake implements ICake {
	private int y, x;
	
	/**
	 * Opretter en Cake uden koordinater
	 */
	public Cake() {
		this(-1,-1);
	}
	
	/**
	 * Opretter en Cake med specifikke koordinater
	 * @param y Lodret position, y
	 * @param x Vandret position, x
	 */
	public Cake(int y, int x) {
		setPos(y,x);
	}
	
	/**
	 * S�tter positionen for kagen som enkelte koordinater
	 * @param y Lodret position, y
	 * @param x Vandret position, x
	 */
	public void setPos(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
	/**
	 * S�tter positionen for kagen som integer-array
	 * @param yx Array med position som [y,x]
	 */
	public void setPos(int[] yx) {
		this.y = yx[0];
		this.x = yx[1];
	}
	
	/**
	 * Henter positionen for kagen som integer-array
	 * @return Kagens koordinater som [y,x] int-array
	 */
	public int[] getPos() {
		return new int[] {this.y,this.x};
	}
	
	/**
	 * S�tter y-koordinaten for kagen
	 * @param y Ny y-koordinat
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * S�tter x-koordinaten for kagen
	 * @param x Ny x-koordinat
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Henter y-koordinaten for kagen
	 * @return Kagens x-koordinat
	 */
	public int getY() {
		return this.y;
	}
	
	/**
	 * Henter x-koordinaten for kagen
	 * @return Kagens x-koordinat
	 */
	public int getX() {
		return this.x;
	}
	
	public String toString() {
		return "Cake at (y,x): (" + y + "," + x + ")";
	}
}