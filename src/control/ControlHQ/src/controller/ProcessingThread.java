package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import command.Commando;
import command.exception.MasterRobotNotFound;
import command.interfaces.IControl;
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
	private Commando robotsCommando;
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
		} catch (MasterRobotNotFound e) {
			// TODO: Error message should go to GUI
			e.printStackTrace();
		}
	}

	private void initialize() throws ControllerException, MasterRobotNotFound
	{
		// Initialize imageprocessor and comm
		imageProcessor = new ImageProcessor2();
		robotsCommando = new Commando(0);
		IControl[] robotControls = robotsCommando.getControls();
		
		// initialize RobotThread[]
		robotThreads = new RobotThread[2];

		// Initialize all robots and start threads
		int robotIndex = 0;
		for (IControl robotControl : robotControls)
		{
			if (robotControl == null){
				System.out.println("RobotControl was NULL");
				continue;
			}
			
			System.out.println("STARTING NEW ROBOT THREAD, index:" + robotIndex);
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
			BufferedImage image = imageSource.getImage();

			// Process the image
			locations = imageProcessor.examineImage(image, true);
			
			try {
				calculatePaths(locations);
			} catch (ControllerException e) {
				System.err.println(e.getMessage());
			}
				
			
		}
	}

	private void calculatePaths(ILocations locations) throws ControllerException
	{
		// Save number of robots and cakes
		robotsCount = locations.getRobots().size();	// TODO FIX
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
		
		
		
		for (RobotThread robotThread : robotThreads)
		{
			
			Location target = null;
			
			if (robotThread == null){
				continue;
			}
			
			if (cakesCount <= 0 && robotThread.getRobotState() != RobotState.HEADING_FOR_DELIVERY &&
					robotThread.getRobotState() != RobotState.DELIVERING){
				continue;
			}
			
			if (robotThread.getRobotState() == RobotState.PICKING_UP || robotThread.getRobotState() == RobotState.DELIVERING)
				continue;
			
			
			
			
			if (robotThread.getRobotState() == RobotState.HEADING_FOR_DELIVERY)
			{
				target = robotThread.getTargetLocation();
				
			}
			else {
				// TODO FIX tager den nærmeste kage + flere robotter
				ICake currentCake = locations.getCakes().get(0);
				target = new Location(currentCake.getY(), currentCake.getX());
			}
			
			
			robotThread.setTargetLocation(target);
			
			// Get physical robot location
			try
			{
				robotThread.setRobotLocation(locations.getRobots().get(robotIndex));
				robotThread.setAllRobotLocations(locations.getRobots());
				
			}
			catch (IndexOutOfBoundsException e)
			{
				throw new ControllerException("Could not visually find robot " + robotThread.toString());
			}

			// Find path for robot
			PathFinder pathFinder = new PathFinder(tileMap, 1500, false);

			Path newPath = pathFinder.findPath(robotThread.getRobotLocation(), robotThread.getTargetLocation());

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
			
			
			
			// starting robot cycle
			if (robotThread.getRobotState() == RobotState.IDLE)
				robotThread.setRobotState(RobotState.HEADING_FOR_CAKE);
				
			
			
			
			

			robotIndex++;
		}
	}

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
			try {
				nextStep = path.getStep(i + 1);
			} catch (IndexOutOfBoundsException e) {
				// ikke flere steps
				break;
			}

			g.drawLine(step.getX(), step.getY(), nextStep.getX(), nextStep.getY());
		}

		return sourceImage;
	}

	public synchronized ILocations getLocations() {
		return locations;
	}

	public void stopRobotThreads()
	{
		

		// FIXME: Not fully implemented
		
		// TODO stop robot threads;
		for (RobotThread robot : robotThreads) {
			if (robot != null){
				System.out.println("STOPPING: "+robot.getName());
				robot.setRobotState(RobotState.IDLE);
				robot.setRunning(false);
			}
			
		}
		
		
		// disconnecting all robots
//		robotsCommando.disconnect();
//
		imageSource.close();
//		
//		running = false;
	}

	public int getRobotsCount()
	{
		return robotsCount;
	}

	public int getCakesCount()
	{
		return cakesCount;
	}
//	/**
//	 * Get'er til {@link RobotState}. 
//	 * @return array af {@link RobotState} hvor {@link RobotType} MASTER er det første element
//	 */
//	public RobotState[] getRobotStates(){
//		RobotState[] states = null;
//		if(robotThreads[0].getRobotType().equals(RobotThread.RobotType.MASTER)){
//			states[0]=robotThreads[0].getRobotState();
//		}
//		else{
//			states[1]=robotThreads[0].getRobotState();
//		}
//		if(robotThreads[1].getRobotType().equals(RobotThread.RobotType.SLAVE)){
//			states[1]=robotThreads[1].getRobotState();
//		}
//		else{
//			states[0]=robotThreads[1].getRobotState();
//		}
//		return states;
//	}
	/**
	 * Get'er til Bertas {@link RobotState}. 
	 * @return {@link RobotState}
	 */
	public RobotState getBertaState(){
		return robotThreads[0].getRobotState();
	}
	/**
	 * Get'er til Props {@link RobotState}. 
	 * @return {@link RobotState}
	 */
	public RobotState getPropState(){
		return robotThreads[1].getRobotState();
	}
	/**get'er til Bertas Position
	 * @return int[] på formen yx
	 */	
	public int[] getBertaPos(){
		return robotThreads[0].getRobotLocation().getPos();
	}
	/**get'er til Props Position
	 * @return int[] på formen yx
	 */
	public int[] getPropPos(){
		return robotThreads[1].getRobotLocation().getPos();	
	}
	/**
	 * Get'er til Bertas Vinkel
	 * @return vink´len som en double, i radianer
	 */
	public double getBertaAngle(){
		return robotThreads[0].getRobotLocation().getAngle();
	}
	/**
	 * Get'er til Bertas Vinkel
	 * @return vink´len som en double, i radianer
	 */
	public double getPropAngle(){
		return robotThreads[1].getRobotLocation().getAngle();
	}
	public boolean isBertaConnected(){
		return robotsCommando.isBertaConnected();
	}
	public boolean isPropConnected(){
		return robotsCommando.isPropConnected();
	}
}