package pathfinding.test;

import java.util.ArrayList;

import pathfinding.dao.Robot;
import pathfinding.dao.TileMap;

public class AlgorithmTest {

	
	
	public static void main(String[] args)
	{
		Robot robot = new Robot(12,9,120);
		TileMap tileMap = new TileMap();
		
		ArrayList<Robot> robotList = new ArrayList<Robot>();
		robotList.add(robot);
		
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
		
	}
}
