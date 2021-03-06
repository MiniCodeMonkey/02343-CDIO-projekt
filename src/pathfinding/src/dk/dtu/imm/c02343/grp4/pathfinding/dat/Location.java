package dk.dtu.imm.c02343.grp4.pathfinding.dat;

public class Location
{
	private int x;
	private int y;
	private double targetAngle;
	
	/**
	 * Construct a new location
	 * @param y
	 * @param x
	 */
	public Location(int y, int x)
	{
		this.y = y;
		this.x = x;
	}
	
	public Location(int y, int x, double targetAngle)
	{
		this.y = y;
		this.x = x;
		this.targetAngle = targetAngle;
	}

	/**
	 * Gets the location on the x axis
	 */
	public int GetX()
	{
		return x;
	}
	
	/**
	 * Gets the location on the y axis
	 */
	public int GetY()
	{
		return y;
	}
	
	/**
	 * Sets the location on the x axis
	 * @param x X location
	 */
	public void SetX(int x)
	{
		this.x = x;
	}
	
	/**
	 * Sets the location on the y axis
	 * @param y Y location
	 */
	public void SetY(int y)
	{
		this.y = y;
	}

	public void setTargetAngle(double targetAngle) {
		this.targetAngle = targetAngle;
	}

	public double getTargetAngle() {
		return targetAngle;
	}
}
