package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import command.Commando;
import command.exception.MasterRobotNotFound;
import command.interfaces.IControl;
import command.rmi.RmiClient;

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
	
	/**
	 * linker to the control of the robots thru RMI
	 */
	private RmiClient robotsCommando;
	private RobotThread[] robotThreads;
	private IImageSource imageSource;
	private IImageProcessor imageProcessor;
	/**
	 * tells if processing is running
	 * <br>Used to loop to process, and to stop it
	 */
	private boolean running;
	/**
	 * Used to decide which robot(s) to connect
	 */
	private int connetToRobots = -1;
	/**
	 * Set to true to output processing time in console
	 */
	private final boolean showProcessingTime = false;
	
	// bruges af GUI
	/**
	 * indeholder liste af kager og robotter
	 */
	private ILocations locations;
	
	private int robotsCount = 0;
	private int cakesCount = 0;
	
	/**
	 * @param imageSource - the image source to use
	 * @param robots - which robot(s) to start; 1 berta, 2 prop, 0 both
	 */
	public ProcessingThread(IImageSource imageSource, int robots)
	{
		this.imageSource = imageSource;
		this.connetToRobots = robots;
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
	
	/**
	 * @throws ControllerException
	 * @throws MasterRobotNotFound
	 */
	private void initialize() throws ControllerException, MasterRobotNotFound
	{
		// Initialize imageprocessor and comm
		imageProcessor = new ImageProcessor2();

		
		// decide which robot(s) to connect
        switch (connetToRobots)
		{
		case 1:	// berta
			robotsCommando = new RmiClient(0);
			break;
		case 2:	// prop
			robotsCommando = new RmiClient(1);
			break;
		case 0:	// both robots
			robotsCommando = new RmiClient();
			break;
		default:
			System.err.println("Can only initialize robots 0-2");
			break;
		}
        
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
				continue;
			}
			
			robotThreads[robotIndex] = new RobotThread(robotControl);
			robotThreads[robotIndex].start();
			
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
			long time = System.currentTimeMillis();
			BufferedImage image = imageSource.getImage();
			System.out.println("Image fetched in "+(System.currentTimeMillis()-time)+" ms");
			
			// Process the image
			time = System.currentTimeMillis();
			locations = imageProcessor.examineImage(image, true);
			System.out.println("Exsamine image in "+(System.currentTimeMillis()-time)+" ms");
			
			// calculate path
			try
			{
				time = System.currentTimeMillis();
				calculatePaths(locations); // Calculate new paths
				System.out.println("Calculate path in "+(System.currentTimeMillis()-time)+" ms");
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
		
		// Sets robot-is-yielding flag in imageprocessor, if robot 2 is in a YIELD state. (Robot 2 then works as an obstacle)
		if (robotThreads[1] != null && (robotThreads[1].getRobotState() == RobotState.YIELD_CAKE || robotThreads[1].getRobotState() == RobotState.YIELD_DELIVERY))
		{
			imageProcessor.setRobotYield(true);
		}
		else
		{
			imageProcessor.setRobotYield(false);
		}
		
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
			// heading for a cake or is currently positioning
			if (robotThread.getRobotState() == RobotState.HEADING_FOR_DELIVERY || robotThread.getRobotState() == RobotState.HEADING_FOR_CAKE || robotThread.getRobotState() == RobotState.POSITIONING)
			{
				// FIXME WTF is this for?!
//				Location target = robotThread.getTargetLocation();
				
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
	
	/**Returns an object, containing info regarding robots, cakes and image-processing
	 * @return {@link ILocations}
	 */
	public synchronized ILocations getLocations()
	{
		return locations;
	}
	
	/**
	 * Stops and disconnect all robots, their threads and stopping the processing-thread
	 */
	public void stopAllThreads()
	{
		if (robotThreads == null)
		{
			// stopping robots
			for (RobotThread robot : robotThreads)
			{
				if (robot != null)
				{
					System.out.println("STOPPING: " + robot.getName());
					robot.setRunning(false);
				}
				
			}
		}
		
		// disconnect all robots
		robotsCommando.shutdown();
		//
		// stopping processing-thread
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
	
	public boolean isBertaConnected()
	{
		 return (robotsCommando.getControl()[0] == null)? false : true;
	}
	
	public boolean isPropConnected()
	{
		 return (robotsCommando.getControl()[1] == null)? false : true;
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
	public Location getBertaTargetLocation()
	{
		return robotThreads[0].getTargetLocation();
	}
	public Location getPropTargetLocation()
	{
		return robotThreads[1].getTargetLocation();
	}
	public double getBertaTargetAngle()
	{
		return robotThreads[0].getTargetAngle();
	}
	public double getPropTargetAngle()
	{
		return robotThreads[1].getTargetAngle();
	}

	public IImageProcessor getImageProcessor()
	{
		return imageProcessor;
	}
}