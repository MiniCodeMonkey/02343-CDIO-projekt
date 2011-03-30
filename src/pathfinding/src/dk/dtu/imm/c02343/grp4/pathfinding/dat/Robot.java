package dk.dtu.imm.c02343.grp4.pathfinding.dat;

public class Robot {

	private int x, y, angle;

	public Robot(int x, int y, int angle)
	{
		this.x = x;
		this.y = y;
		setAngle(angle);
	}
	
	public int getXCoordinates()
	{
		return x;
	}
	
	public int getYCoordinates()
	{
		return y;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getAngle() {
		return angle;
	}
}
