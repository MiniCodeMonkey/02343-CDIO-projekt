package controller;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.List;

import command.BertaCommando;
import command.interfaces.IControl;

import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Location;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Path;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Step;

public class RobotThread extends Thread
{
	/**
	 * The different states to robot can be in
	 */
	public enum RobotState
	{
		IDLE, HEADING_FOR_CAKE, POSITIONING, PICKING_UP, HEADING_FOR_DELIVERY, DELIVERING, YIELD_CAKE, YIELD_DELIVERY
	};
	
	/**
	 * Robot type can be master or slave
	 */
	public enum RobotType
	{
		MASTER, SLAVE
	};
	
	/**
	 * Control object used to send commands to the physical robot
	 */
	private IControl robotControl;
	
	/**
	 * The current robot state
	 */
	private RobotState robotState = RobotState.IDLE;
	
	/**
	 * The robot's type (Master or Slave)
	 */
	private RobotType robotType;
	
	/**
	 * Should the main thread loop be running?
	 */
	private boolean running = true;
	
	/**
	 * Should the robot's navigation be paused?
	 */
	private boolean paused = false;
	
	/**
	 * The IRobot object for the threads's robot, containing location information and more
	 */
	private IRobot robotLocation = null;
	
	/**
	 * The target location is for a cake or delivery location
	 */
	private Location targetLocation = null;
	
	/**
	 * The angle to target (supposed-to-be angle)
	 */
	private double targetAngle = 0;
	
	/**
	 * The map's current size
	 */
	private Rectangle mapSize = new Rectangle();
	
	/**
	 * The current path for the robot
	 */
	private Path path = null;
	
	/**
	 * Is set to true each time the path is updated from the image processor and pathfinder
	 */
	private boolean pathWasUpdated = false;
	
	/**
	 * A list of all robot locations, including the current robot's location
	 */
	private List<IRobot> allRobotLocations = null;
	
	/**
	 * Static attribute defining if a master has been initialized before
	 */
	private static boolean masterIsDefined = false;
	
	/**
	 * Set to true to output processing time in console
	 */
	private final boolean showProcessingTime = false;
	
	/**
	 * Constructs a new robot thread for a new robot
	 * @param robotControl The control object to physically control the robot
	 */
	public RobotThread(IControl robotControl)
	{
		this.robotControl = robotControl;
		
	}
	
	/**
	 * Starts this robot as a new Thread via the Runnable interface
	 */
	@Override
	public void run()
	{
		try
		{	
			// Initialize the robot's inital values and motor config
			initialize();
			
			// Main robot thread loop
			while (running)
			{
				if (paused || robotState == RobotState.IDLE) // Is the robot cycle paused or is the robot idling?
				{
					robotControl.stop();
					robotControl.stopClaw();
					Thread.sleep(1000);
				}
				else
				{
					if (showProcessingTime)
					{
						long beforeNavigate = System.currentTimeMillis();
						navigate();
						System.out.println("navigated in " + (System.currentTimeMillis() - beforeNavigate) + " ms");
					}
					else
					{
						navigate();
					}
					
					// Free some CPU
					Thread.sleep(20);
				}
			}
			robotControl.stop();
		}
		catch (Exception e)
		{
			// TODO: Show error in GUI
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Initialize the robot
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void initialize() throws IOException, InterruptedException
	{
		// Initialize master/slave configuration
		if (!RobotThread.masterIsDefined)
		{
			RobotThread.masterIsDefined = true;
			
			this.robotType = RobotType.MASTER;
		}
		else
		{
			this.robotType = RobotType.SLAVE;
		}
		
		// Initialize claw
		robotControl.closeClaw();
		Thread.sleep(400);
		robotControl.stopClaw();
		
		// Give a name for the thread
		if (robotType == RobotType.MASTER)
			Thread.currentThread().setName("RobotThread BERTA");
		else
			Thread.currentThread().setName("RobotThread PROP");
	}
	
	/**
	 * Calculates a new path for the robot and navigates the robot
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void navigate() throws IOException, InterruptedException
	{
		if (pathWasUpdated) // Did we receive an update path from the processing thread?
		{
			pathWasUpdated = false;
			
			// Is the path valid?
			if (path != null && path.getLength() > 0)
			{
				// Find the next step in the path
				int currentStep = 0;
				Step step = path.getStep(currentStep);
				if (step.getX() == robotLocation.getX() && step.getY() == robotLocation.getY())
				{
					currentStep++;
					try
					{
						step = path.getStep(currentStep);
					}
					catch (IndexOutOfBoundsException e)
					{
						
						System.err.println(e.getMessage());
						return;
					}
				}
				
				// Calculate target angle
				double dy = step.getY() - robotLocation.getY();
				double dx = step.getX() - robotLocation.getX();
				double targetAngle = calculateTargetAngle(dy, dx);
				
				// Birds-eye-view distance from robot to target (cake, delivery, location, etc.)
				double distanceToTarget = calculateDistance(robotLocation.getX(), robotLocation.getY(), targetLocation.GetX(), targetLocation.GetY());
				
				double targetAngleDifference = 0;
				if (targetAngle < 0 && robotLocation.getAngle() < 0 || targetAngle >= 0 && robotLocation.getAngle() >= 0)
				{
					targetAngleDifference = Math.abs(robotLocation.getAngle() - targetAngle);
				}
				else if (robotLocation.getAngle() >= 0)
				{
					targetAngleDifference = Math.abs(robotLocation.getAngle() - targetAngle);
				}
				else
				{
					targetAngleDifference = Math.abs(robotLocation.getAngle() + targetAngle);
				}
				
				// Perform actions according to the robot state
				switch (this.robotState)
				{
					case HEADING_FOR_CAKE:
					{
						// If close enough to the cake
						if (distanceToTarget < Thresholds.getInstance().getCloseEnoughToCake())
						{
							robotControl.stop();
							// System.out.println("CLOSE ENOUGH TO CAKE");
							this.robotState = RobotState.POSITIONING;
							
						}
						break;
					}
						
					case POSITIONING:
					{
						// Is the rotation close enough?
						if (targetAngleDifference <= Thresholds.getInstance().getRotationClose())
						{
							robotControl.stop();
							// Now picking up cake
							this.robotState = RobotState.PICKING_UP;
							
							// Open claw
							robotControl.openClaw();
							Thread.sleep(1000);
							robotControl.stopClaw();
							
							// Move forward
							robotControl.move(20, false);
							Thread.sleep(1800);
							robotControl.stop();
							
							// Close claw
							robotControl.closeClaw();
							Thread.sleep(1000);
							robotControl.stopClaw();
							robotControl.stop();
							
							// Move backwards
							robotControl.move(20, true);
							Thread.sleep(2000);
							robotControl.stop();
							
							
							int dropDistance = 20;
							
							// Decide delivery location | FIXME: if obstacles is in the way
							Location deliveryLocations[] = {
									
									// right side
									new Location((int) mapSize.getHeight() / 2 - 30, (int) mapSize.getWidth() - dropDistance),
									new Location((int) mapSize.getHeight() / 2, (int) mapSize.getWidth() - dropDistance),
									new Location((int) mapSize.getHeight() / 2 + 30, (int) mapSize.getWidth() - dropDistance),
									
									// left side
									new Location((int) mapSize.getHeight() / 2 - 30, dropDistance),
									new Location((int) mapSize.getHeight() / 2, dropDistance),
									new Location((int) mapSize.getHeight() / 2 + 30, dropDistance),
									
									// lower long side
									new Location((int)mapSize.getHeight() - dropDistance,(int) mapSize.getWidth() / 2 - 60),
									new Location((int)mapSize.getHeight() - dropDistance,(int) mapSize.getWidth() / 2 - 30),
									new Location((int)mapSize.getHeight() - dropDistance,(int) mapSize.getWidth() / 2),
									new Location((int)mapSize.getHeight() - dropDistance,(int) mapSize.getWidth() / 2 + 30),
									new Location((int)mapSize.getHeight() - dropDistance,(int) mapSize.getWidth() / 2 + 60),
									
									// upper long side
									new Location(dropDistance,(int) mapSize.getWidth() / 2 - 60),
									new Location(dropDistance,(int) mapSize.getWidth() / 2 - 30),
									new Location(dropDistance,(int) mapSize.getWidth() / 2),
									new Location(dropDistance,(int) mapSize.getWidth() / 2 + 30),
									new Location(dropDistance,(int) mapSize.getWidth() / 2 + 60)
							};
							
							double bestDistance = Double.MAX_VALUE;
							for (Location deliveryLocation : deliveryLocations)
							{
								double currentDistance = calculateDistance(deliveryLocation.GetX(), deliveryLocation.GetY(), robotLocation.getX(), robotLocation.getY());
								
								if (currentDistance < bestDistance)
								{
									bestDistance = currentDistance;
									targetLocation = deliveryLocation;
								}
							}
							
							// Now heading for delivery
							this.robotState = RobotState.HEADING_FOR_DELIVERY;
						}
						else
						{
							// Rotate slowly to cake
							if (robotLocation.getAngle() < targetAngle && (targetAngle - robotLocation.getAngle()) < Math.PI)
							{
								robotControl.right(Thresholds.getInstance().getSlowSpeed());
							}
							else
							{
								robotControl.left(Thresholds.getInstance().getSlowSpeed());
							}
						}
						
						break;
					}
						
					case HEADING_FOR_DELIVERY:
					{
						// If close enough to the delivery location
						if (distanceToTarget < Thresholds.getInstance().getCloseEnoughToDelivery())
						{
							this.robotState = RobotState.DELIVERING;
							
							// Move forward
//							robotControl.move(30, false);
//							
//							// Open claw
//							robotControl.openClaw();
//							Thread.sleep(1000);
//							robotControl.stopClaw();
//							
//							// Move forwards
//							Thread.sleep(2000);
//							// System.out.println("Cake delivered");
//							robotControl.stop();
//							Thread.sleep(200);
//							
//							// Move backwards
//							robotControl.move(50, true);
//							Thread.sleep(2000);
//							robotControl.stop();
//							
//							// Close claw
//							robotControl.closeClaw();
//							Thread.sleep(1000);
//							robotControl.stopClaw();
							
							// NY afleverings procedure
							
							robotControl.move(50, false);
							
							// Open claw
							robotControl.openClaw();
							Thread.sleep(1000);
							robotControl.stopClaw();
							
							// Move forwards
							Thread.sleep(2000);
							// System.out.println("Cake delivered");
							robotControl.stop();
							Thread.sleep(200);
							
							// Move backwards
							robotControl.move(50, true);
							Thread.sleep(2000);
							
							// Close claw
							robotControl.closeClaw();
							Thread.sleep(1000);
							robotControl.stopClaw();
							robotControl.stop();
							
							
							// robot becomes idle (job done)
							this.robotState = RobotState.IDLE;
						}
						break;
					}
				}
				
				// Reset yield status
				if (this.robotState == RobotState.YIELD_CAKE)
					this.robotState = RobotState.HEADING_FOR_CAKE;
				
				if (this.robotState == RobotState.YIELD_DELIVERY)
					this.robotState = RobotState.HEADING_FOR_DELIVERY;
				
				// If we are heading somewhere
				if (this.robotState == RobotState.HEADING_FOR_CAKE || this.robotState == RobotState.HEADING_FOR_DELIVERY)
				{
					// Slaves should yield for the master
					if (this.robotType == RobotType.SLAVE)
					{
						// Is any robots nearby?
						for (IRobot otherRobotLocation : allRobotLocations)
						{
							if (!otherRobotLocation.equals(robotLocation) && otherRobotLocation.isActive())
							{
								double distance = calculateDistance(robotLocation.getX(), robotLocation.getY(), otherRobotLocation.getX(), otherRobotLocation.getY());
								
								if (distance < Thresholds.getInstance().getYieldDistance())
								{									
									robotControl.stop();
									
									// TODO hvad hvis Prop er i PICK_UP || DELIVERY
									
									if (this.robotState == RobotState.HEADING_FOR_CAKE)
										this.robotState = RobotState.YIELD_CAKE;
									
									if (this.robotState == RobotState.HEADING_FOR_DELIVERY)
										this.robotState = RobotState.YIELD_DELIVERY;
									
									break;
								}
							}
						}
					}
					
					if (this.robotState == RobotState.HEADING_FOR_CAKE || this.robotState == RobotState.HEADING_FOR_DELIVERY)
					{
						// We are very very close to the correct angle, so drive forward
						if (targetAngleDifference <= Thresholds.getInstance().getRotationClose())
						{
							robotControl.move(Thresholds.getInstance().getHighSpeed(), false);
							
						}
						else if (targetAngleDifference <= Thresholds.getInstance().getRotationFairlyClose()) // Do minor corrections
						{
							// Rotate
							if (robotLocation.getAngle() < targetAngle && (targetAngle - robotLocation.getAngle()) < Math.PI)
							{
								robotControl.right(Thresholds.getInstance().getSlowSpeed());
							}
							else
							{
								robotControl.left(Thresholds.getInstance().getSlowSpeed());
							}
						}
						else // Do major corrections
						{
							// Rotate
							if (robotLocation.getAngle() < targetAngle && (targetAngle - robotLocation.getAngle()) < Math.PI)
							{
								robotControl.right(Thresholds.getInstance().getMediumSpeed());
							}
							else
							{
								robotControl.left(Thresholds.getInstance().getMediumSpeed());
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Calculates the target angle from dy and dx
	 * @param dy
	 * @param dx
	 * @return
	 */
	private double calculateTargetAngle(double dy, double dx)
	{
		double targetAngle = 0.0;
		if (dy == 0)
		{
			// Grænsetilfælde: Robot vender mod højre eller venstre
			if (dx > 0)
			{
				targetAngle = Math.PI / 2;
			}
			else if (dx < 0)
			{
				targetAngle = -Math.PI / 2;
			}
			else
			{
				targetAngle = 0;
			}
		}
		else if (dx == 0)
		{
			// Grænsetilfælde: Robot vender op eller ned
			if (dy > 0)
			{
				targetAngle = Math.PI;
			}
			else
			{
				targetAngle = 0;
			}
		}
		else
		{
			// Generelt
			targetAngle = -Math.atan(dx / dy);
			
			if (dx > 0 && dy > 0)
			{
				// 3. kvadrant
				targetAngle = Math.PI + targetAngle;
			}
			else if (dx < 0 && dy > 0)
			{
				// 4. kvadrant
				targetAngle = -Math.PI + targetAngle;
			}
		}
		
		return targetAngle;
	}
	
	private double calculateDistance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
	}
	
	public synchronized RobotState getRobotState()
	{
		return robotState;
	}
	
	public synchronized void setRobotState(RobotState robotState)
	{
		this.robotState = robotState;
	}
	
	public synchronized void setRobotLocation(IRobot robotLocation)
	{
		this.robotLocation = robotLocation;
	}
	
	public synchronized IRobot getRobotLocation()
	{
		return robotLocation;
	}
	
	public synchronized Location getTargetLocation()
	{
		return targetLocation;
	}
	
	public synchronized void setTargetLocation(Location targetLocation)
	{
		this.targetLocation = targetLocation;
	}
	
	public synchronized double getTargetAngle()
	{
		return targetAngle;
	}

	public synchronized void setPath(Path newPath)
	{
		this.path = newPath;
		this.pathWasUpdated = true;
	}
	
	public RobotType getRobotType()
	{
		return robotType;
	}
	
	public void setRobotType(RobotType robotType)
	{
		this.robotType = robotType;
	}
	
	public synchronized Path getPath()
	{
		return this.path;
	}
	
	public void setMapSize(int height, int width)
	{
		mapSize.setSize(width, height);
	}
	
	public void setAllRobotLocations(List<IRobot> robots)
	{
		allRobotLocations = robots;
	}
	
	public void setRunning(boolean running)
	{
		this.running = running;
	}
	
	public boolean isPaused()
	{
		return paused;
	}
	
	public void setPaused(boolean paused)
	{
		this.paused = paused;
	}
}
