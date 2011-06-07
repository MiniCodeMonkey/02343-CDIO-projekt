package controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import command.BertaCommando;
import command.interfaces.IControl;

import dk.dtu.imm.c02343.grp4.dto.impl.Cake;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ICake;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ILocations;
import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.IImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor2;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.IImageSource;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Path;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Step;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.TileMap;
import dk.dtu.imm.c02343.grp4.pathfinding.implementations.PathFinder;

public class ProcessingThread extends Thread
{
	private RobotThread[] robotThreads;
	private IImageSource imageSource;
	private IImageProcessor imageProcessor;
	private boolean running;
	
	private int robotsCount = 0;
	private int cakesCount = 0;
	
	public ProcessingThread(IImageSource imageSource)
	{
		this.imageSource = imageSource;
	}
	
	public void run()
	{
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
	}

	private void initialize() throws ControllerException
	{
		// Initialize imageprocessor and comm
		imageProcessor = new ImageProcessor2();
		BertaCommando bertaCommando = new BertaCommando();
		IControl[] robotControls = bertaCommando.getControls();
		
		// Initialize all robots and start threads
		int robotIndex = 0;
		for (IControl robotControl : robotControls)
		{
			robotThreads[robotIndex] = new RobotThread(robotControl);
			robotThreads[robotIndex].start();
			
			robotIndex++;
		}
		
		running = true;
	}
	
	private void runLoop() throws ControllerException
	{
		// Main processing loop
		while (running)
		{
			// Get image from camera
			BufferedImage image = imageSource.getImage();
			
			// Process the image
			ILocations locations = imageProcessor.examineImage(image, true);
			
			calculatePaths(locations);
		}
	}

	private void calculatePaths(ILocations locations) throws ControllerException
	{
		// Save number of robots and cakes
		robotsCount = locations.getRobots().size();
		cakesCount = locations.getCakes().size();
		
		// Is no robots available?
		if (locations.getRobots().size() <= 0)
		{
			return;
		}
		
		// Is no cakes available?
		if (locations.getCakes().size() <= 0)
		{
			return;
		}
		
		// Create a new tile map from the locations object
		TileMap tileMap = new TileMap();
		tileMap.setTileMap(locations.getTilemap(), locations.getObstaclemap());
		
		// Find a path for each robot
		int robotIndex = 0;
		for (RobotThread robotThread : robotThreads)
		{
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
			
			// Note: We used to resize path here
			// TODO: Generate path with less steps directly in pathfinder class instead
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
			Step nextStep = path.getStep(i + 1);
			
			g.drawLine(step.getX(), step.getY(), nextStep.getX(), nextStep.getY());
		}
		
		return sourceImage;
	}

	public void stopThread()
	{
		running = false;
		
		// FIXME: Not fully implemented
		
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
}