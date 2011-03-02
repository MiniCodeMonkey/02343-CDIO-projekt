package pathfinding.transferInterfaces;

public class Robot {

	private int x, y, angle;

	public void setXCoordinate(int x) {
		this.x = x;
	}

	public int getXCoordinate() {
		return x;
	}

	public void setYCoordinate(int y) {
		this.y = y;
	}

	public int getYCoordinate() {
		return y;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getAngle() {
		return angle;
	}
}
