package pathfinding.test;

import pathfinding.dao.Path;
import pathfinding.dao.Path.Step;
import pathfinding.dao.Robot;
import pathfinding.dao.TileMap;
import pathfinding.implementations.PathFinder;

public class AlgorithmTest {

	
	
	public static void main(String[] args)
	{
		Robot robot = new Robot(12, 10, 0);
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
		Path path = pathFinder.findPath(robot, robot.getXCoordinates(), robot.getYCoordinates(), 1, 1);
		
		if (path == null)
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
		}
	}
}
