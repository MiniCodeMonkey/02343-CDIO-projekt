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

	public enum RobotState
	{
		IDLE, HEADING_FOR_CAKE, POSITIONING, PICKING_UP, HEADING_FOR_DELIVERY, DELIVERING, YIELD_CAKE, YIELD_DELIVERY
	};

	public enum RobotType
	{
		MASTER, SLAVE
	};

	private IControl robotControl;
	private RobotState robotState = RobotState.IDLE;
	private RobotType robotType;

	private boolean running = true;

	private IRobot robotLocation = null;
	private Location targetLocation = null;
	private Rectangle mapSize = new Rectangle();

	private Path path = null;
	private boolean pathWasUpdated = false;

	private List<IRobot> allRobotLocations = null;

	private static boolean masterIsDefined = false;

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

			// setting master/slave configs + claw init
			initialize();

			if (robotType == RobotType.MASTER)
				Thread.currentThread().setName("RobotThread BERTA");
			else
				Thread.currentThread().setName("RobotThread PROP");

			while (running)
			{
				if (robotState != RobotState.IDLE)
				{
					navigate();
				} else
				{

//					robotControl.stop();
//					robotControl.stopClaw();
				}
				// Thread.sleep(50);
			}
			robotControl.stop();
		} catch (Exception e)
		{
			// TODO
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
		} else
		{
			this.robotType = RobotType.SLAVE;
		}

		// Initialize claw
		robotControl.closeClaw();
		Thread.sleep(500);
		robotControl.stopClaw();
	}

	/**
	 * Calculates a new path for the robot and navigates the robot
	 * 
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private void navigate() throws IOException, InterruptedException
	{

		if (pathWasUpdated)
		{
			pathWasUpdated = false;

			if (path != null && path.getLength() > 0)
			{

				Step step;
				double targetAngle;
				int currentStep = 0;

				step = path.getStep(currentStep);

				if (step.getX() == robotLocation.getX() && step.getY() == robotLocation.getY())
				{

					currentStep++;
					try
					{
						step = path.getStep(currentStep);
					} catch (IndexOutOfBoundsException e)
					{

						System.err.println(e.getMessage());
						return;
					}

				}

				//System.out.println();

				// Calculate target angle
				double dy = step.getY() - robotLocation.getY();
				double dx = step.getX() - robotLocation.getX();

				// radianer!
				targetAngle = calculateTargetAngle(dy, dx);

				//System.out.println("dy: " + dy + "| dx: " + dx);

				// Birds-eye-view distance from robot to target (cake, delivery
				// location, etc.)
				double distanceToTarget = calculateDistance(robotLocation.getX(), robotLocation.getY(),
						targetLocation.GetX(), targetLocation.GetY());

				// Difference between robot angle and target angle
				// radianer!!
				double targetAngleDifference = 0;
//				if (robotLocation.getAngle() < 0 && targetAngle < 0)
//					targetAngleDifference = Math.abs(Math.abs(robotLocation.getAngle()) - Math.abs(targetAngle));
//				else if (robotLocation.getAngle() <= 0 && targetAngle >= 0)
//					targetAngleDifference = Math.abs(targetAngle - Math.abs(robotLocation.getAngle()));
//				else if (targetAngle < 0)
//					targetAngleDifference = Math.abs(robotLocation.getAngle() - Math.abs(targetAngle));
				
				targetAngleDifference = Math.abs(Math.abs(robotLocation.getAngle()) - Math.abs(targetAngle));

				System.out.println("Robot angle: " + Math.toDegrees(robotLocation.getAngle()));
				System.out.println("Target angle: " + Math.toDegrees(targetAngle));
				//System.out.println("DistanceToTarget: " + distanceToTarget);
				System.out.println("TargetAngleDiff: " + Math.toDegrees(targetAngleDifference));
				//System.out.println("Targetlocation: " + targetLocation.GetX() + "," + targetLocation.GetY());

				System.out.println(this.robotState);

				// Perform actions according to the robot state
				switch (this.robotState)
				{
				case HEADING_FOR_CAKE:
				{
					//System.out.println("HEADING_FOR_CAKE");
					// If close enough to the cake
					if (distanceToTarget < Thresholds.getInstance().getCloseEnoughToCake())
					{
						//System.out.println("CLOSE ENOUGH TO CAKE");
						this.robotState = RobotState.POSITIONING;
					}
					break;
				}

				case POSITIONING:
				{
					//System.out.println("POSITIONING");
					// Is the rotation close enough?
					if (targetAngleDifference <= Thresholds.getInstance().getRotationClose())
					{
						// Now picking up cake
						this.robotState = RobotState.PICKING_UP;
						//System.out.println("PICKING_UP");

						// Open claw: 3s
						robotControl.move(20, false);
						robotControl.openClaw();
						Thread.sleep(1000);
						robotControl.stopClaw();

						// Move forward: 1s
						robotControl.move(20, false);
						Thread.sleep(1000);
						robotControl.stop();

						// Close claw: 3s
						robotControl.closeClaw();
						Thread.sleep(1000);
						robotControl.stopClaw();

						// Move backwards: 1s
						robotControl.move(50, true);
						Thread.sleep(1000);
						robotControl.stop();

						// Decide delivery location
						Location deliveryLocations[] = {
								new Location((int) mapSize.getHeight() - 1, (int) mapSize.getWidth() / 2),
								new Location((int) mapSize.getHeight() / 2, (int) mapSize.getWidth() - 1),
								new Location(1, (int) mapSize.getWidth() / 2),
								new Location((int) mapSize.getHeight() / 2, 1)
						};

						double bestDistance = Double.MAX_VALUE;
						for (Location deliveryLocation : deliveryLocations)
						{
							double currentDistance = calculateDistance(deliveryLocation.GetX(), deliveryLocation.GetY(), robotLocation.getX(),
									robotLocation.getY());

							if (currentDistance < bestDistance)
							{
								//System.out.println("new BEST distance " + currentDistance);
								bestDistance = currentDistance;
								targetLocation = deliveryLocation;
							}
						}

						// Now heading for delivery
						this.robotState = RobotState.HEADING_FOR_DELIVERY;
					} else
					{
						//System.out.println("POSITIONING -> rotate");
						// Rotate slowly to cake
						if (robotLocation.getAngle() < targetAngle
								&& (targetAngle - robotLocation.getAngle()) < Math.PI)
						{
							robotControl.right(Thresholds.getInstance().getSlowSpeed());
						} else
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
						robotControl.move(30, false);
						Thread.sleep(2000);
						robotControl.stop();

						// Open claw
						robotControl.openClaw();
						Thread.sleep(1000);
						robotControl.stopClaw();

						// Move forwards
						robotControl.move(50, false);
						Thread.sleep(1000);
						//System.out.println("Cake delivered");
						robotControl.stop();

						// Move backwards
						robotControl.move(50, true);
						Thread.sleep(2000);
						robotControl.stop();

						// Close claw
						robotControl.closeClaw();
						Thread.sleep(1000);
						robotControl.stopClaw();

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
				if (this.robotState == RobotState.HEADING_FOR_CAKE
						|| this.robotState == RobotState.HEADING_FOR_DELIVERY)
				{
					// Slaves should yield for the master
					if (this.robotType == RobotType.SLAVE)
					{
						// Is any robots nearby?
						for (IRobot otherRobotLocation : allRobotLocations)
						{
							if (!otherRobotLocation.equals(robotLocation) && otherRobotLocation.getX() >= 0)
							{
								double distance = calculateDistance(robotLocation.getX(), robotLocation.getY(),
										otherRobotLocation.getX(), otherRobotLocation.getY());

								if (distance < Thresholds.getInstance().getYieldDistance())
								{
									robotControl.stop();

									if (this.robotState == RobotState.HEADING_FOR_CAKE)
										this.robotState = RobotState.YIELD_CAKE;

									if (this.robotState == RobotState.HEADING_FOR_DELIVERY)
										this.robotState = RobotState.YIELD_DELIVERY;

									break;
								}
							}
						}
					}

					if (this.robotState == RobotState.HEADING_FOR_CAKE
							|| this.robotState == RobotState.HEADING_FOR_DELIVERY)
					{
						// We are very very close to the correct angle, so drive
						// forward
						if (targetAngleDifference <= Thresholds.getInstance().getRotationClose())
						{
							//System.out.println("very very close");
							robotControl.move(Thresholds.getInstance().getHighSpeed(), false);

							// debug
							Thread.sleep(800);

						} else if (targetAngleDifference <= Thresholds.getInstance().getRotationFairlyClose()) // Do
																												// minor
																												// corrections
						{
							//System.out.println("not very close");
							// Rotate
							if (robotLocation.getAngle() < targetAngle
									&& (targetAngle - robotLocation.getAngle()) < Math.PI)
							{
								robotControl.right(Thresholds.getInstance().getSlowSpeed());
							} else
							{
								robotControl.left(Thresholds.getInstance().getSlowSpeed());
							}
						} else
						// Do major corrections
						{
							//System.out.println("not very AT ALL close");
							// Rotate
							if (robotLocation.getAngle() < targetAngle
									&& (targetAngle - robotLocation.getAngle()) < Math.PI)
							{
								robotControl.right(Thresholds.getInstance().getMediumSpeed());
							} else
							{
								robotControl.left(Thresholds.getInstance().getMediumSpeed());
							}
						}
					}
				}
			}
		}
	}

	private double calculateTargetAngle(double dy, double dx)
	{
		double targetAngle = 0.0;
		if (dy == 0)
		{
			// Grænsetilfælde: Robot vender mod højre eller venstre
			if (dx > 0)
			{
				targetAngle = Math.PI / 2;
			} else if (dx < 0)
			{
				targetAngle = -Math.PI / 2;
			} else
			{
				targetAngle = 0;
			}
		} else if (dx == 0)
		{
			// Grænsetilfælde: Robot vender op eller ned
			if (dy > 0)
			{
				targetAngle = Math.PI;
			} else
			{
				targetAngle = 0;
			}
		} else
		{
			// Generelt
			targetAngle = -Math.atan(dx / dy);

			if (dx > 0 && dy > 0)
			{
				// 3. kvadrant
				targetAngle = Math.PI + targetAngle;
			} else if (dx < 0 && dy > 0)
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

	public RobotState getRobotState()
	{
		return robotState;
	}

	public synchronized void setRobotState(RobotState robotState)
	{
		this.robotState = robotState;
	}

	public void setRobotLocation(IRobot robotLocation)
	{
		this.robotLocation = robotLocation;
	}

	public IRobot getRobotLocation()
	{
		return robotLocation;
	}

	public Location getTargetLocation()
	{
		return targetLocation;
	}

	public void setTargetLocation(Location targetLocation)
	{
		this.targetLocation = targetLocation;
	}

	public void setPath(Path newPath)
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

	public Path getPath()
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
}
