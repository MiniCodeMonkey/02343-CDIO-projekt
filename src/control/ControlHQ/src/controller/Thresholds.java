package controller;

public class Thresholds
{
    private static final Thresholds instance = new Thresholds();
    
    // Implement singleton pattern with private constructor
    private Thresholds()
    {
    }
 
    public static Thresholds getInstance()
    {
        return instance;
    }
    
	private int slowSpeed = 4;
	private int mediumSpeed = 12;
	private int highSpeed = 35;
	
	private int minPositionForCake = 18;
	private int maxPositionForCake = 22;
	private int closeEnoughToCake = 30;
	private int closeEnoughToDelivery = 10;
	private int inRangeOfCake = 55;
	
	private double rotationClose = Math.toRadians(4);
	private double rotationFairlyClose = Math.toRadians(20);
	private double rotationKindaClose = Math.toRadians(30);
	private int yieldDistance = 60;
	
	/**
	 * Returns the slow speed set
	 * @return a value between 1 and 100
	 */
	public int getSlowSpeed()
	{
		return slowSpeed;
	}
	
	/**
	 * Sets the slow speed
	 * @param speed a value between 1 and 100
	 */
	public void setSlowSpeed(int speed)
	{
		slowSpeed = speed;
	}
	
	/**
	 * Returns the medium speed
	 * @return a value between 1 and 100
	 */
	public int getMediumSpeed()
	{
		return mediumSpeed;
	}
	
	/**
	 * Sets the medium speed
	 * @param speed speed a value between 1 and 100
	 */
	public void setMediumSpeed(int speed)
	{
		mediumSpeed = speed;
	}
	
	/**
	 * Returns the high speed
	 * @return a value between 1 and 100
	 */
	public int getHighSpeed()
	{
		return highSpeed;
	}
	
	/**
	 * Sets the high speed
	 * @param speed speed a value between 1 and 100
	 */
	public void setHighSpeed(int speed)
	{
		highSpeed = speed;
	}
	
	/**
	 * The distance to be from the cake to initialize pick-up
	 * @return distance in pixels
	 */
	public int getCloseEnoughToCake()
	{
		return closeEnoughToCake;
	}
	
	/**
	 * The distance to be from the cake to initialize pick-up
	 * @param length distance in pixels
	 */
	public void setCloseEnoughToCake(int length)
	{
		closeEnoughToCake = length;
	}
	
	/**
	 * The distance to be from the delivery location to initalize delivery
	 * @return distance in pixels
	 */
	public int getCloseEnoughToDelivery()
	{
		return closeEnoughToDelivery;
	}
	
	/**
	 * The distance to be from the delivery location to initalize delivery
	 * @param length distance in pixels
	 */
	public void setCloseEnoughToDelivery(int length)
	{
		closeEnoughToDelivery = length;
	}
	
	/**
	 * The rotation determined as very small
	 * @return rotation in radians
	 */
	public double getRotationClose()
	{
		return rotationClose;
	}
	
	/**
	 * The rotation determined as very small
	 * @param rotation in radians
	 */
	public void setRotationClose(double rotation)
	{
		rotationClose = rotation;
	}
	
	/**
	 * The rotation determined as fairly small
	 * @return rotation in radians
	 */
	public double getRotationFairlyClose()
	{
		return rotationFairlyClose;
	}
	
	/**
	 * The rotation determined as fairly small
	 * @param rotation in radians
	 */
	public void setRotationFairlyClose(double rotation)
	{
		rotationFairlyClose = rotation;
	}
	
	/**
	 * Returns the distance the master has to be from the slave to make the slave yield
	 * @return distance in pixels
	 */
	public int getYieldDistance()
	{
		return yieldDistance;
	}
	
	/**
	 * Returns the distance the master has to be from the slave to make the slave yield
	 * @param length distance in pixels
	 */
	public void setYieldDistance(int length)
	{
		yieldDistance = length;
	}

	public double getRotationKindaClose()
	{
		return rotationKindaClose;
	}
	
	public void setRotationKindaClose(double rotationKindaClose)
	{
		this.rotationKindaClose = rotationKindaClose;
	}

	public void setMaxPositionForCake(int maxPositionForCake)
	{
		this.maxPositionForCake = maxPositionForCake;
	}

	/**
	 * Returns the max distance where the cake is right beside the claw of the robot
	 * @return
	 */
	public int getMaxPositionForCake()
	{
		return maxPositionForCake;
	}

	public void setMinPositionForCake(int minPositionForCake)
	{
		this.minPositionForCake = minPositionForCake;
	}
	
	/**
	 * Returns the min distance where the cake is right beside the claw of the robot
	 * @return
	 */
	public int getMinPositionForCake()
	{
		return minPositionForCake;
	}
	
	/**
	 * Distance for in range of cake (Robot should move slower when in range of cake)
	 * @return
	 */
	public int getInRangeOfCake()
	{
		return inRangeOfCake;
	}
	
	public void setInRangeOfCake(int inRangeOfCake)
	{
		this.inRangeOfCake = inRangeOfCake;
	}
}
