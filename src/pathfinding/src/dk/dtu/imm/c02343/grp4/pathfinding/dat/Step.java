package dk.dtu.imm.c02343.grp4.pathfinding.dat;

/**
 * A single step within the path
 * 
 * @author Kevin Glass
 */
public class Step {
	/** The x coordinate at the given step */
	private int x;
	/** The y coordinate at the given step */
	private int y;
	
	/**
	 * Create a new step
	 * 
	 * @param x The x coordinate of the new step
	 * @param y The y coordinate of the new step
	 */
	public Step(int y, int x) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Get the x coordinate of the new step
	 * 
	 * @return The x coodindate of the new step
	 */
	public int getX() {
		return x;
	}

	/**
	 * Get the y coordinate of the new step
	 * 
	 * @return The y coodindate of the new step
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * @see Object#hashCode()
	 */
	public int hashCode() {
		return x*y;
	}

	/**
	 * @see Object#equals(Object)
	 */
	public boolean equals(Object other) {
		if (other instanceof Step) {
			Step o = (Step) other;
			
			return (o.x == x) && (o.y == y);
		}
		
		return false;
	}
}