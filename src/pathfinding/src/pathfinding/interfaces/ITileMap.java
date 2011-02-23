package pathfinding.interfaces;

public interface ITileMap
{
	/**
	 * Gets number of tiles on the horizontal axis
	 * @return Number of tiles on the horizontal axis
	 */
	public int GetHorizontalTiles();
	
	/**
	 * Gets number of tiles on the vertical axis
	 * @return Number of tiles on the vertical axis
	 */
	public int GetVerticalTiles();
	
	/**
	 * Tells the tile map that the given location has been visited
	 * on route calculation
	 * @param location Visited location
	 */
	public void Visit(ILocation location);
	
	/**
	 * Determines if the robot can go to the given location
	 * @param location Requested location
	 * @return Boolean determining if it is possible to go to the location
	 */
	public boolean CanGoTo(ILocation location);
	
	/**
	 * Calculate the length of the path between the location
	 * @param fromLocation The start location
	 * @param toLocation The end location
	 * @return Floating number determining the distance between the locations
	 */
	public float GetCost(ILocation fromLocation, ILocation toLocation);
}
