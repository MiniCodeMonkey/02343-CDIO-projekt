package dk.dtu.imm.c02343.grp4.pathfinding.dat;

import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;

public class TileMap
{
	private int width, height;
	
	private int[][] tileMap;
	private boolean[][] visited;

	public void setTileMap(int[][] tileMap)
	{
		height = tileMap.length;
		width = tileMap[0].length;
		visited = new boolean[height][width];
		
		addObstacleWalls();
		
		this.tileMap = tileMap;
	}

	private void addObstacleWalls()
	{
		for (int y = 0; y < height; y++)
		{
			
		}
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
		return (tileMap[ty][tx] == 2);
	}

	public float getCost(IRobot robot, int sy, int sx, int ty, int tx)
	{
		return 1;
	}

	public void pathFinderVisited(int y, int x)
	{
		visited[y][x] = true;
	}
	
}
