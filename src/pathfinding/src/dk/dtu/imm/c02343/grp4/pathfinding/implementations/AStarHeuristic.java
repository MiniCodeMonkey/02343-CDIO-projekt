package dk.dtu.imm.c02343.grp4.pathfinding.implementations;

import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.TileMap;

public class AStarHeuristic
{
	public float getCost(TileMap map, IRobot robot, int y, int x, int ty, int tx) {		
		float dx = tx - x;
		float dy = ty - y;
		
		float result = (float) (Math.sqrt((dx*dx)+(dy*dy)));
		
		return result;
	}
}
