package controller;

import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.IImageSource;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.WebCam;

public class MainController
{
	private ProcessingThread processingThread = null;
	
	// Implement singleton pattern
	private static MainController instance = new MainController();
	public static MainController getInstance()
	{
		return instance;
	}
	
	// Keep the constructor private
	private MainController()
	{
	}
	
	/**
	 * Initialize the webcam and processor
	 */
	public void initialize()
	{
		// Initialize webcam
		IImageSource imageSource = new WebCam();
		imageSource.init();
		
		// Start processing thread
		processingThread = new ProcessingThread(imageSource);
		processingThread.start();
	}
	
	/**
	 * Returns the current number of robots detected by the image processor
	 * @return number of robots
	 */
	public int currentRobotsCount()
	{
		return processingThread.getRobotsCount();
	}
	
	/**
	 * Returns the current number of cakes detected by the image processor
	 * @return number of cakes
	 */
	public int currentCakesCount()
	{
		return processingThread.getCakesCount();
	}
	
	/**
	 * Stops the controller and destroys all threads
	 */
	public void stop()
	{
		if (processingThread != null)
			processingThread.stopThread();
	}
}
