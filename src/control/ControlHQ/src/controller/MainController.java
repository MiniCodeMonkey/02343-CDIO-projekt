package controller;

import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.IImageSource;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.WebCam;

public class MainController
{
	private ProcessingThread processingThread = null;
	
	public MainController()
	{
		// Initialize webcam
		IImageSource imageSource = new WebCam();
		imageSource.init();
		
		// Start processing thread
		processingThread = new ProcessingThread(imageSource);
		processingThread.start();
	}
	
	public int currentRobotsCount()
	{
		return processingThread.getRobotsCount();
	}
	
	public int currentCakesCount()
	{
		return processingThread.getCakesCount();
	}
}
