package pathfinding.interfaces;

public interface ILocation
{
	/**
	 * Gets the location on the x axis
	 */
	public int GetX();
	
	/**
	 * Gets the location on the y axis
	 */
	public int GetY();
	
	/**
	 * Sets the location on the x axis
	 * @param x X location
	 */
	public void SetX(int x);
	
	/**
	 * Sets the location on the y axis
	 * @param y Y location
	 */
	public void SetY(int y);
}
