package pathfinding.implementations;

import pathfinding.interfaces.ILocation;
import pathfinding.interfaces.ITileMap;

public class TileMap implements ITileMap
{
	public int GetHorizontalTiles()
	{
		return 0;
	}

	public int GetVerticalTiles()
	{
		return 0;
	}

	public void Visit(ILocation location)
	{
		
	}

	public boolean CanGoTo(ILocation location)
	{
		return false;
	}

	public float GetCost(ILocation fromLocation, ILocation toLocation)
	{
		return 0;
	}
}
