package pathfinding.dao;

public class TileMap
{
	private int width, height;
	
	private int[][] tileMap;
	private boolean[][] visited;

	public void setTileMap(int[][] tileMap)
	{
		width = tileMap.length;
		height = tileMap[0].length;
		visited = new boolean[width][height];
		
		this.tileMap = tileMap;
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

	public boolean blocked(Robot robot, int tx, int ty)
	{
		return (tileMap[tx][ty] != 0);
	}

	public float getCost(Robot robot, int sx, int sy, int tx, int ty)
	{
		return 1;
	}

	public void pathFinderVisited(int x, int y)
	{
		visited[x][y] = true;
	}
	
}
