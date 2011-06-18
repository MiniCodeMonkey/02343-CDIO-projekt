package controller;

import java.awt.Rectangle;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import command.BertaCommando;
import command.interfaces.IControl;

import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor2;
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
		START, IDLE, HEADING_FOR_CAKE, POSITIONING, PICKING_UP, HEADING_FOR_DELIVERY, DELIVERING, YIELD_CAKE, YIELD_DELIVERY
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
	private RobotState robotState = RobotState.START;
	
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
	 * Set to true to output processing time in console
	 */
	private final boolean showProcessingTime = false;
	
	/**
	 * Constructs a new robot thread for a new robot
	 * @param robotControl The control object to physically control the robot
	 * @param type is MASTER or SLAVE
	 */
	public RobotThread(IControl robotControl, RobotType type)
	{
		this.robotControl = robotControl;
		robotType = type;
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
				targetAngle = calculateTargetAngle(dy, dx);
				
				// Birds-eye-view distance from robot to target (cake, delivery, location, etc.)
				double distanceToTarget = calculateDistance(robotLocation.getX(), robotLocation.getY(), targetLocation.GetX(), targetLocation.GetY());
				
				// FIXME  maybe delete  - used in turnTo() insted
				double targetAngleDifference = calculateAngleDifference(robotLocation.getAngle(), targetAngle);

				
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
						
//						turnTo(targetAngle);
						
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
							robotControl.move(30, false);
							Thread.sleep(320);
							robotControl.stop();
							
							// Close claw
							robotControl.closeClaw();
							Thread.sleep(1000);
							robotControl.stopClaw();
							
							// Move backwards
							robotControl.move(30, true);
							Thread.sleep(320);
							robotControl.stop();
							
							// what droppoint to deliver to
							chooseDropPoint();
							
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
							
							turnTo(this.targetLocation.getTargetAngle());
							
							// Move forwards
							robotControl.move(Thresholds.getInstance().getHighSpeed(), false);
							
							// Open claw
							robotControl.openClaw();
							Thread.sleep(1000);
							robotControl.stopClaw();
							
							// Stop after 2s
							robotControl.stop();
							Thread.sleep(200);
							
							// Move backwards
							robotControl.move(Thresholds.getInstance().getHighSpeed(), true);
							
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
				if (this.robotState == RobotState.YIELD_CAKE || this.robotState == RobotState.YIELD_DELIVERY)
				{
					for (IRobot otherRobotLocation : allRobotLocations)
					{
						if (!otherRobotLocation.equals(robotLocation) && otherRobotLocation.isActive())
						{
							double distance = calculateDistance(robotLocation.getX(), robotLocation.getY(), otherRobotLocation.getX(), otherRobotLocation.getY());
							
							if (distance > Thresholds.getInstance().getYieldDistance())
							{									
								robotControl.stop();
								
								System.out.println("NOT yielding! Distance is " + distance + " | Robotthread: " + this);
								
								// TODO hvad hvis Prop er i PICK_UP || DELIVERY
								
								if (this.robotState == RobotState.YIELD_CAKE)
									this.robotState = RobotState.HEADING_FOR_CAKE;
								
								if (this.robotState == RobotState.YIELD_DELIVERY)
									this.robotState = RobotState.HEADING_FOR_DELIVERY;
								
								break;
							}
						}
						
					}
				}
				
				// If we are heading somewhere
				if (this.robotState == RobotState.HEADING_FOR_CAKE || this.robotState == RobotState.HEADING_FOR_DELIVERY)
				{
					// Slaves should yield for the master
					if (this.robotType == RobotType.SLAVE && !robotControl.isBerta())
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
									
									System.out.println("Yielding! Distance is " + distance + " | Robotthread: " + this);
									
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
					
					if (this.robotState == RobotState.HEADING_FOR_CAKE || this.robotState == RobotState.HEADING_FOR_DELIVERY /*|| this.robotState == RobotState.HEADING_FOR_HOME*/)
					{
						
//						turnTo(targetAngle);
												
//						 We are very very close to the correct angle, so drive forward
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
		else
			robotControl.stop();
	}

	private void chooseDropPoint()
	{
		//							int dropDistance = 5/ImageProcessor2.outputScale;
		int dropDistance = 0;

		// Decide delivery location | FIXME: if obstacles is in the way
		Location deliveryLocations[] = {

				// right side
				new Location((int) mapSize.getHeight() / 2 - 30, ImageProcessor2.stageBounds[3] - dropDistance, Math.toRadians(90)),
				new Location((int) mapSize.getHeight() / 2, ImageProcessor2.stageBounds[3] - dropDistance, Math.toRadians(90)),
				new Location((int) mapSize.getHeight() / 2 + 30, ImageProcessor2.stageBounds[3] - dropDistance, Math.toRadians(90)),

				// left side
				new Location((int) mapSize.getHeight() / 2 - 30, ImageProcessor2.stageBounds[1] + dropDistance, Math.toRadians(-90)),
				new Location((int) mapSize.getHeight() / 2, ImageProcessor2.stageBounds[1] + dropDistance, Math.toRadians(-90)),
				new Location((int) mapSize.getHeight() / 2 + 30, ImageProcessor2.stageBounds[1] + dropDistance, Math.toRadians(-90)),

				// lower long side
				new Location(ImageProcessor2.stageBounds[2] - dropDistance,(int) mapSize.getWidth() / 2 - 60, Math.toRadians(180)),
				new Location(ImageProcessor2.stageBounds[2] - dropDistance,(int) mapSize.getWidth() / 2 - 30, Math.toRadians(180)),
				new Location(ImageProcessor2.stageBounds[2] - dropDistance,(int) mapSize.getWidth() / 2, Math.toRadians(180)),
				new Location(ImageProcessor2.stageBounds[2] - dropDistance,(int) mapSize.getWidth() / 2 + 30, Math.toRadians(180)),
				new Location(ImageProcessor2.stageBounds[2] - dropDistance,(int) mapSize.getWidth() / 2 + 60, Math.toRadians(180)),

				// upper long side
				new Location(ImageProcessor2.stageBounds[0] + dropDistance,(int) mapSize.getWidth() / 2 - 60, Math.toRadians(0)),
				new Location(ImageProcessor2.stageBounds[0] + dropDistance,(int) mapSize.getWidth() / 2 - 30, Math.toRadians(0)),
				new Location(ImageProcessor2.stageBounds[0] + dropDistance,(int) mapSize.getWidth() / 2, Math.toRadians(0)),
				new Location(ImageProcessor2.stageBounds[0] + dropDistance,(int) mapSize.getWidth() / 2 + 30, Math.toRadians(0)),
				new Location(ImageProcessor2.stageBounds[0] + dropDistance,(int) mapSize.getWidth() / 2 + 60, Math.toRadians(0))
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
	}
	
	/**
	 * Calculates the delta angle from the robot's current angle and
	 * the current target's angle
	 * @param robotAngle
	 * @param targetAngle
	 * @return Delta angle
	 */
	private double calculateAngleDifference(double robotAngle, double targetAngle)
	{
		double difference;
//		difference = robotAngle - targetAngle;
		
		// Old way to figure out delta angle
		if (targetAngle < 0 && robotAngle < 0 || targetAngle >= 0 && robotAngle >= 0)
		{
			difference = Math.abs(robotAngle - targetAngle);
		}
		else if (robotLocation.getAngle() >= 0)
		{
			difference = Math.abs(robotAngle - targetAngle);
		}
		else
		{
			difference = Math.abs(robotAngle + targetAngle);
		}
		
		return difference;
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
	
	public IControl getRobotControl()
	{
		return robotControl;
	}

	/**
	 * Turns the robot to the specified angle
	 * @param targetAngle The specified angle that the robot should turn to
	 * @throws RemoteException
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public void turnTo(double targetAngle) throws RemoteException, IOException, InterruptedException
	{
//		While the robot does not have the correct angle... ((robotLocation.getAngle() - targetAngle) > Thresholds.getInstance().getRotationClose())
//		while (
//				!((robotLocation.getAngle() == targetAngle) ||
//			(((robotLocation.getAngle() - targetAngle) < Thresholds.getInstance().getRotationClose()))
//		   ))
//		{
//
//			//....turn right, or....
//			if((robotLocation.getAngle() - targetAngle) < 0 )
//			{
//				robotControl.right(Thresholds.getInstance().getMediumSpeed());
//			}
//			//....turn left
//			else
//			{
//				robotControl.left(Thresholds.getInstance().getMediumSpeed());
//			}
//		}
		
		double targetAngleDifference;
		
		do
		{
			// Calculate target angle diference
			targetAngleDifference = calculateAngleDifference(robotLocation.getAngle(), targetAngle);
			
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
		while (targetAngleDifference <= Thresholds.getInstance().getRotationClose());
	}
}
