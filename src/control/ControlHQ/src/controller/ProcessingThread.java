package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import controller.RobotThread.RobotState;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ICake;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ILocations;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.IImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor2;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.IImageSource;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Location;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Path;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Step;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.TileMap;
import dk.dtu.imm.c02343.grp4.pathfinding.implementations.PathFinder;

public class ProcessingThread extends Thread
{
	// private Commando robotsCommando;
	private RmiClient robotsCommando;
	private RobotThread[] robotThreads;
	private IImageSource imageSource;
	private IImageProcessor imageProcessor;
	private boolean running;
	
	// bruges af GUI
	private ILocations locations;
	
	private int robotsCount = 0;
	private int cakesCount = 0;
	
	public ProcessingThread(IImageSource imageSource)
	{
		this.imageSource = imageSource;
	}
	
	public void run()
	{
		Thread.currentThread().setName("Processing Thread");
		try
		{
			initialize();
			runLoop();
		}
		catch (ControllerException e)
		{
			// TODO: Error message should go to GUI
			e.printStackTrace();
		}
		catch (MasterRobotNotFound e)
		{
			// TODO: Error message should go to GUI
			e.printStackTrace();
		}
	}
	
	private void initialize() throws ControllerException, MasterRobotNotFound
	{
		// Initialize imageprocessor and comm
		imageProcessor = new ImageProcessor2();
		
		robotsCommando = new RmiClient();
		robotsCommando.init();
		
		IControl[] robotControls = robotsCommando.getControl();
		
		// initialize RobotThread[]
		robotThreads = new RobotThread[2];
		
		// Initialize all robots and start threads
		int robotIndex = 0;
		for (IControl robotControl : robotControls)
		{
			if (robotControl == null)
			{
				System.out.println("RobotControl was NULL");
				continue;
			}
			
			System.out.println("STARTING NEW ROBOT THREAD, index:" + robotIndex);
			robotThreads[robotIndex] = new RobotThread(robotControl);
			robotThreads[robotIndex].start();
			System.out.println("\tname: " + robotThreads[robotIndex].getName());
			
			robotIndex++;
		}
		
		running = true;
	}
	
	private void runLoop()
	{
		
		// Main processing loop
		while (running)
		{
			// Get image from camera
			BufferedImage image = imageSource.getImage();
			
			// Process the image
			locations = imageProcessor.examineImage(image, true);
			
			try
			{
				calculatePaths(locations); // Calculate new paths
			}
			catch (ControllerException e)
			{
				System.err.println(e.getMessage());
			}
			
		}
	}
	
	/**
	 * Calculates new paths for all robots
	 * 
	 * @param locations The locations object derived from a processed image
	 * @throws ControllerException
	 */
	private void calculatePaths(ILocations locations) throws ControllerException
	{
		// Save number of robots and cakes
		int c = 0;
		if (locations.getRobots().get(0).isActive())
			c++;
		
		if (locations.getRobots().get(1).isActive())
			c++;
		
		robotsCount = c;
		cakesCount = locations.getCakes().size();
		
		// Is no robots available?
		if (!locations.getRobots().get(0).isActive() && !locations.getRobots().get(1).isActive())
		{
			throw new ControllerException("Could not visually find any robots");
		}
		
		// Create a new tile map from the locations object
		TileMap tileMap = new TileMap();
		tileMap.setTileMap(locations.getTilemap(), locations.getObstaclemap());
		
		// Find a path for each robot
		int robotIndex = 0;
		
		// Loop through all active robots
		for (RobotThread robotThread : robotThreads)
		{
			if (robotThread == null)
				continue;
			
			// Tell the thread the new robot locations
			try
			{
				robotThread.setRobotLocation(locations.getRobots().get(robotIndex));
				robotThread.setAllRobotLocations(locations.getRobots());
				
			}
			catch (IndexOutOfBoundsException e)
			{
				throw new ControllerException("Could not visually find robot " + robotThread.toString());
			}
			
			// If idling, possibly pick a cake to pick up
			if (robotThread.getRobotState() == RobotState.IDLE && cakesCount > 0)
			{
				// Build a list of cakes not in use by other robots
				ArrayList<Location> possibleCakes = new ArrayList<Location>();
				
				// Loop through all cakes
				for (ICake cake : locations.getCakes())
				{
					boolean in_use = false;
					for (RobotThread rThread : robotThreads)
					{
						if (rThread == null)
							continue;
						
						if (rThread.getTargetLocation() != null && rThread.getTargetLocation().GetX() > 0)
						{
							// If the robot has the target location as the
							// current cake
							if (rThread.getTargetLocation().GetX() == cake.getX() && rThread.getTargetLocation().GetY() == cake.getY())
							{
								in_use = true;
								break;
							}
						}
						
					}
					
					// No robot is using the cake
					if (!in_use)
					{
						// Add to not-in-use-list
						possibleCakes.add(new Location(cake.getY(), cake.getX()));
					}
				}
				
				// Possible cakes can still be <= 0 if the existing cake is
				// already have been selected by another robot
				if (possibleCakes.size() > 0)
				{
					// Find closest cake
					double closestDistance = Double.MAX_VALUE;
					Location determinedCakeLocation = null;
					
					// Loop through all cake locations to find the best location
					for (Location cakeLocation : possibleCakes)
					{
						
						double dist = Math.sqrt(Math.pow(robotThread.getRobotLocation().getX() - cakeLocation.GetX(), 2) + Math.pow(robotThread.getRobotLocation().getY() - cakeLocation.GetY(), 2));
						
						// Is this cake closer than the last found closest cake?
						if (dist < closestDistance)
						{
							closestDistance = dist;
							determinedCakeLocation = cakeLocation;
						}
					}
					
					// Set the target to the determined cake's location
					robotThread.setTargetLocation(determinedCakeLocation);
					
					// Set the state of the robot
					robotThread.setRobotState(RobotState.HEADING_FOR_CAKE);
				}
			}
			
			// We only calculate a path if the robot is heading for delivery or
			// heading for a cake
			if (robotThread.getRobotState() == RobotState.HEADING_FOR_DELIVERY || robotThread.getRobotState() == RobotState.HEADING_FOR_CAKE)
			{
				Location target = robotThread.getTargetLocation();
				
				// Find path for robot
				PathFinder pathFinder = new PathFinder(tileMap, 1500, false);
				
				Path newPath = null;
				
				if (robotThread.getRobotLocation().isActive())
					newPath = pathFinder.findPath(robotThread.getRobotLocation(), robotThread.getTargetLocation());
				
				if (newPath == null)
				{
					throw new ControllerException("Could not find path for robot " + robotThread.toString());
				}
				else
				{
					// Update path for robot thread
					robotThread.setPath(newPath);
					robotThread.setMapSize(locations.getTileImage().getHeight(), locations.getTileImage().getWidth());
					
					// Draw the path onto the tile image
					locations.setTileImage(drawPath(locations.getTileImage(), robotThread.getPath()));
				}
				
				robotIndex++;
			}
		}
	}
	
	/**
	 * Draws the specified path onto a sourceImage
	 * @param sourceImage
	 * @param path
	 * @return
	 */
	private BufferedImage drawPath(BufferedImage sourceImage, Path path)
	{
		if (path == null)
		{
			return sourceImage;
		}
		
		Graphics g = sourceImage.getGraphics();
		g.setColor(Color.white);
		
		for (int i = 0; i < path.getLength(); i++)
		{
			Step step = path.getStep(i);
			Step nextStep;
			try
			{
				nextStep = path.getStep(i + 1);
			}
			catch (IndexOutOfBoundsException e)
			{
				// ikke flere steps
				break;
			}
			
			g.drawLine(step.getX(), step.getY(), nextStep.getX(), nextStep.getY());
		}
		
		return sourceImage;
	}
	
	public synchronized ILocations getLocations()
	{
		return locations;
	}
	
	public void stopRobotThreads()
	{
		if (robotThreads == null)
		{
			for (RobotThread robot : robotThreads)
			{
				if (robot != null)
				{
					System.out.println("STOPPING: " + robot.getName());
					robot.setRunning(false);
				}
				
			}
		}
		
		// disconnecting all robots
		robotsCommando.shutdown();
		//
		//
		running = false;
		imageSource.close();
	}
	
	public int getRobotsCount()
	{
		return robotsCount;
	}
	
	public int getCakesCount()
	{
		return cakesCount;
	}
	
	// /**
	// * Get'er til {@link RobotState}.
	// * @return array af {@link RobotState} hvor {@link RobotType} MASTER er
	// det første element
	// */
	// public RobotState[] getRobotStates(){
	// RobotState[] states = null;
	// if(robotThreads[0].getRobotType().equals(RobotThread.RobotType.MASTER)){
	// states[0]=robotThreads[0].getRobotState();
	// }
	// else{
	// states[1]=robotThreads[0].getRobotState();
	// }
	// if(robotThreads[1].getRobotType().equals(RobotThread.RobotType.SLAVE)){
	// states[1]=robotThreads[1].getRobotState();
	// }
	// else{
	// states[0]=robotThreads[1].getRobotState();
	// }
	// return states;
	// }
	/**
	 * Get'er til Bertas {@link RobotState}.
	 * 
	 * @return {@link RobotState}
	 */
	public RobotState getBertaState()
	{
		return robotThreads[0].getRobotState();
	}
	
	/**
	 * Get'er til Props {@link RobotState}.
	 * 
	 * @return {@link RobotState}
	 */
	public RobotState getPropState()
	{
		return robotThreads[1].getRobotState();
	}
	
	/**
	 * get'er til Bertas Position
	 * 
	 * @return int[] på formen yx
	 */
	public int[] getBertaPos()
	{
		return robotThreads[0].getRobotLocation().getPos();
	}
	
	/**
	 * get'er til Props Position
	 * 
	 * @return int[] på formen yx
	 */
	public int[] getPropPos()
	{
		return robotThreads[1].getRobotLocation().getPos();
	}
	
	/**
	 * Get'er til Bertas Vinkel
	 * 
	 * @return vink´len som en double, i radianer
	 */
	public double getBertaAngle()
	{
		return robotThreads[0].getRobotLocation().getAngle();
	}
	
	/**
	 * Get'er til Bertas Vinkel
	 * 
	 * @return vink´len som en double, i radianer
	 */
	public double getPropAngle()
	{
		return robotThreads[1].getRobotLocation().getAngle();
	}
	
	public boolean isBertaConnected()
	{
		// return robotsCommando.isBertaConnected();
		return false;
	}
	
	public boolean isPropConnected()
	{
		// return robotsCommando.isPropConnected();
		return false;
	}
	
	public boolean isBertaPaused()
	{
		return robotThreads[Commando.BERTA].isPaused();
	}
	
	public boolean isPropPaused()
	{
		return robotThreads[Commando.PROP].isPaused();
	}
	
	public void setPauseBerta(boolean paused)
	{
		robotThreads[Commando.BERTA].setPaused(paused);
	}
	
	public void setPauseProp(boolean paused)
	{
		robotThreads[Commando.PROP].setPaused(paused);
	}
}