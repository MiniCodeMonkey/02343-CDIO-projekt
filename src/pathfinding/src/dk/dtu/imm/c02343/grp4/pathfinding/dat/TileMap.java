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
		return (obstacleMap[ty][tx] != 0);//(tileMap[ty][tx] == 2);
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
