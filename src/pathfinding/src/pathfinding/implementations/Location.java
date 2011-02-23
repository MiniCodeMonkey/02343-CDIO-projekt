package pathfinding.implementations;

import pathfinding.interfaces.ILocation;

public class Location implements ILocation
{
	private int x;
	private int y;

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
}
