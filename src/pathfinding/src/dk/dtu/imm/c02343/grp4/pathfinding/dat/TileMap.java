package dk.dtu.imm.c02343.grp4.pathfinding.dat;

import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;

public class TileMap
{
	private int width, height;
	
	private int[][] tileMap;
	private int[][] obstacleMap;
	private boolean[][] visited;

	public void setTileMap(int[][] tileMap, int[][] obstacleMap)
	{
		height = tileMap.length;
		width = tileMap[0].length;
		visited = new boolean[height][width];
		
		this.tileMap = tileMap;
		this.obstacleMap = obstacleMap;
	}
	
	public int[][] getObstacleMap()
	{
		return obstacleMap;
	}
	
	public int[][] getTileMap()
	{
		return tileMap;
	}

	public int getWidthInTiles()
	{
		return width;
	}

	public int getHeightInTiles()
	{
		return height;
	}

	public boolean blocked(IRobot robot, int ty, int tx)
	{		
		boolean blocked = true;
		
		try
		{
			// If the robot is the MASTER robot
			if (obstacleMap[robot.getY()][robot.getX()] != -1)
			{
				// Blocked if an obstacle or the SLAVE robot is in the way
				blocked = (obstacleMap[ty][tx] >= 29 || obstacleMap[ty][tx] == -1);
			}
			else
			{
				// Blocked if an obstacle is in the way
				blocked = (obstacleMap[ty][tx] >= 39);
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			
		}
		return blocked;
	}

	public float getCost(IRobot robot, int sy, int sx, int ty, int tx)
	{
		if (obstacleMap[ty][tx] >= 15)
		{
			return 2;
		}
		else if (obstacleMap[ty][tx] >= 1)
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	public void pathFinderVisited(int y, int x)
	{
		visited[y][x] = true;
	}
	
}
