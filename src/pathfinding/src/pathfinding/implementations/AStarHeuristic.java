package pathfinding.implementations;

import pathfinding.dao.Robot;
import pathfinding.dao.TileMap;

public class AStarHeuristic
{
	public float getCost(TileMap map, Robot robot, int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		
		return result;
	}
}
