package dk.dtu.imm.c02343.grp4.pathfinding.test;

import dk.dtu.imm.c02343.grp4.dto.impl.Robot;
import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Path;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.TileMap;
import dk.dtu.imm.c02343.grp4.pathfinding.implementations.PathFinder;

public class AlgorithmTest {

	
	
	public static void main(String[] args)
	{
		IRobot robot = new Robot();
		robot.setAngle(20);
		robot.setPos(10, 5);
		
		TileMap tileMap = new TileMap();
		
		int[][] tiles = new int[][]{ 	 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										 {0,0,1,1,1,1,0,0,0,0,0,0,0,0,0},
										 {0,0,1,1,1,1,0,0,0,0,0,0,0,0,0},
										 {0,0,1,1,1,1,0,0,1,1,1,1,0,0,0},
										 {0,0,1,1,1,1,0,0,1,1,1,1,0,0,0},
										 {0,0,0,0,0,0,0,0,1,1,1,1,0,0,0},
										 {0,0,0,0,0,0,0,0,1,1,1,1,0,0,0},
										 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
										 {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									};
		
		tileMap.setTileMap(tiles);
		
		PathFinder pathFinder = new PathFinder(tileMap, 20, true);
		Path path = pathFinder.findPath(robot, robot.getX(), robot.getY(), 1, 1);
		
		/*if (path == null)
		{
			System.out.println("Could not find path from " + robot.getXCoordinates() + "," + robot.getYCoordinates() + " to 1,1");
		}
		else
		{
			int length = path.getLength();
			System.out.println("Path length: " + length);
			
			for (int i = 0; i < length; i++)
			{
				Step step = path.getStep(i);
				System.out.println("Step: " + step.getX() + ", " + step.getY());
			}
		}*/
	}
}
