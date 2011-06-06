package controller;

import java.io.IOException;

import command.interfaces.IControl;
import dk.dtu.imm.c02343.grp4.dto.impl.Cake;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ICake;
import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Location;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Path;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Step;

public class RobotThread extends Thread
{
	public enum RobotState { IDLE, HEADING_FOR_CAKE, POSITIONING, PICKING_UP, HEADING_FOR_DELIVERY, DELIVERING };
	public enum RobotType { MASTER, SLAVE };
	
	private IControl robotControl;
	private RobotState robotState = RobotState.IDLE;
	private RobotType robotType;
	
	private IRobot robotLocation = null;
	private Location targetLocation = null;
	private Path path = null;
	private boolean pathWasUpdated = false;
	
	private static boolean masterIsDefined = false;

	public RobotThread(IControl robotControl)
	{
		this.robotControl = robotControl;
		
		try
		{
			initialize();
			
			while (true)
			{
				if (robotState != RobotState.IDLE)
				{
					navigate();
				}
			}
		}
		catch (Exception e)
		{
			// TODO
			e.printStackTrace();
		}
	}
	
	private void initialize() throws IOException, InterruptedException
	{
		// Initialize master/slave configuration
		if (RobotThread.masterIsDefined)
		{
			RobotThread.masterIsDefined = false;
			
			this.robotType = RobotType.MASTER;
		}
		else
		{
			this.robotType = RobotType.SLAVE;
		}
		
		// Initialize claw
		robotControl.closeClaw();
		Thread.sleep(2000);
		robotControl.stopClaw();
	}

	private void navigate() throws IOException, InterruptedException
	{
		if (pathWasUpdated)
		{
			pathWasUpdated = false;
			
			if (path != null && path.getLength() > 0)
			{
				// Next step which the robot should be heading for
				Step step = path.getStep(0);
				
				// Calculate target angle
				double dy = step.getY() - robotLocation.getY();
				double dx = step.getX() - robotLocation.getX();
				double targetAngle = 0.0;
				if (dy == 0) {
					// Grænsetilfælde: Robot vender mod højre eller venstre
					if (dx > 0) {
						targetAngle = Math.PI/2;
					} else if (dx < 0) {
						targetAngle = -Math.PI/2;
					} else {
						targetAngle = 0;
					}
				} else if (dx == 0) {
					// Grænsetilfælde: Robot vender op eller ned
					if (dy > 0) {
						targetAngle = Math.PI;
					} else {
						targetAngle = 0;
					}
				} else {
					// Generelt
					targetAngle = -Math.atan(dx/dy);
					if (dx > 0 && dy > 0) {
						// 3. kvadrant
						targetAngle = Math.PI+targetAngle;
					} else if (dx < 0 && dy > 0) {
						// 4. kvadrant
						targetAngle = -Math.PI+targetAngle;
					}
				}
				
				// Birds-eye-view distance from robot to target (cake, delivery location, etc.)
				double distanceToTarget = calculateDistance(
						robotLocation.getX(), robotLocation.getY(),
						targetLocation.GetX(), targetLocation.GetY()
				);
				
				// Difference between robot angle and target angle
				double targetAngleDifference = Math.abs(robotLocation.getAngle() - targetAngle);
				
				switch (this.robotState)
				{
					case HEADING_FOR_CAKE:
					{
						// If close enough to the cake
						if (distanceToTarget < 32)
						{
							this.robotState = RobotState.POSITIONING;
						}
						
						break;
					}
					
					case POSITIONING:
					{
						// Is the rotation close enough?
						if (targetAngleDifference <= Math.toRadians(10))
						{
							// Now picking up cake
							this.robotState = RobotState.PICKING_UP;
							
							// Open claw: 3s
							robotControl.openClaw();
							Thread.sleep(3000);
							robotControl.stopClaw();
							
							// Move forward: 1s
							robotControl.move(100, false);
							Thread.sleep(1000);
							robotControl.stop();
							
							// Close claw: 3s
							robotControl.closeClaw();
							Thread.sleep(3000);
							robotControl.stopClaw();
							
							// Move backwards: 1s
							robotControl.move(50, true);
							Thread.sleep(1000);
							robotControl.stop();
							
							// Decide delivery location
							Location deliveryLocations[] = {
									new Location(locations.getTileImage().getHeight() - 1, locations.getTileImage().getWidth() / 2),
									new Location(locations.getTileImage().getHeight() / 2, locations.getTileImage().getWidth() - 1),
									new Location(1, locations.getTileImage().getWidth() / 2),
									new Location(locations.getTileImage().getHeight() / 2, 1)
							};
							
							double bestDistance = Double.MAX_VALUE;
							for (Location deliveryLocation : deliveryLocations)
							{
								double currentDistance = calculateDistance(deliveryLocation.GetY(), robotLocation.getY(), deliveryLocation.GetX(), robotLocation.getX());
								//Math.sqrt(Math.pow(home.getX() - robotLocation.getX(), 2) + Math.pow(home.getY()- robotLocation.getY(), 2));
								
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
							if (robotLocation.getAngle() < targetAngle)
							{
								robotControl.right(20);
							}
							else
							{
								robotControl.left(20);
							}
						}
						
						break;
					}
					
					case HEADING_FOR_DELIVERY:
					{
						// If close enough to the delivery location
						if (distanceToTarget < 32)
						{
							this.robotState = RobotState.POSITIONING;
						}
						
						break;
					}
				}
				
					/*if (distance < 32)
					{	
						// Move forward
						bertaControl.move(50, false);
						System.out.println("FOUND HOME!!!!!");
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						bertaControl.stop();
						
						// Open claw
						bertaControl.openClaw();
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						bertaControl.stopClaw();
						
						// Move forwards
						bertaControl.move(100, false);
						try {
							Thread.sleep(1500);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						System.out.println("Cake delivered");
						bertaControl.stop();
						
						// Move backwards
						bertaControl.reverse(50, 5750);
						bertaControl.stop();
						
						// Close claw
						bertaControl.closeClaw();
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						bertaControl.stopClaw();
						
						// Reset variables
						headingForHome = false;
						return;
					}
				}
				
				// Is current angle as it is supposed to be?
				if (distance >= 40 && !foundCake && Math.abs(robotLocation.getAngle() - targetAngle) > (Math.PI / 180)*45)
				{
					// Rotate
					if (robotLocation.getAngle() < targetAngle)
					{
						bertaControl.right(30);
					}
					else
					{
						bertaControl.left(30);
					}
				}
				else if (foundCake || (distance < 40 && Math.abs(robotLocation.getAngle() - targetAngle) >= (Math.PI / 180)*10))
				{
					// Rotate
					if (robotLocation.getAngle() < targetAngle)
					{
						bertaControl.right(10);
					}
					else
					{
						bertaControl.left(10);
					}
				}
				else
				{
					System.out.println("Full steam ahead! ---- Aye aye captain, full steam ahead. TUUUUUUUT TUUUUUUUT");
					
					// MOVE!
					bertaControl.move(40, false);
				}*/
			}
		}
	}
		
	private double calculateDistance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
	
	public RobotState getRobotState()
	{
		return robotState;
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

	public void setPath(Path newPath)
	{
		this.path = newPath;
		this.pathWasUpdated = true;
	}

	public Path getPath()
	{
		return this.path;
	}
}
