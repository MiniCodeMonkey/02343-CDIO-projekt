package dk.dtu.imm.c02343.grp4.pathfinding.implementations;

import dk.dtu.imm.c02343.grp4.pathfinding.dat.Robot;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.TileMap;

public class AStarHeuristic
{
	public float getCost(TileMap map, Robot robot, int x, int y, int tx, int ty) {		
		float dx = tx - x;
		float dy = ty - y;
		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		
		return result;
	}
}
