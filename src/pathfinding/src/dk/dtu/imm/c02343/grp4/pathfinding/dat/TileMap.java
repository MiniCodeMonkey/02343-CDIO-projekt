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
		/*
		// Is robot inside bounding box?
		if (obstacleMap[robot.getY()][robot.getX()] > 0)	
		{
			return (obstacleMap[ty][tx] <= 5);
		}
		else
		{
			return (obstacleMap[ty][tx] != 0);
		}
		*/
		
		boolean blocked = true;
		
		try
		{
			// If the robot is the MASTER robot
			if (obstacleMap[robot.getY()][robot.getX()] != -1)
			{
				// Blocked if an obstacle or the SLAVE robot is in the way
				blocked = (obstacleMap[ty][tx] >= 39 || obstacleMap[ty][tx] == -1);
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
		/*for (int y = 0; y < obstacleMap.length; y++)
		{
			for (int x = 0; x < obstacleMap[0].length; x++)
			{
				System.out.print(String.format( "%02d",obstacleMap[y][x])+" ");
			}
			System.out.println();
		}
		
		System.out.println("\n\n\n\n");*/
		
		/*if (obstacleMap[ty][tx] >= 25)
		{
			return 2;
		}
		else */if (obstacleMap[ty][tx] >= 1)
		{
			return 2;
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
