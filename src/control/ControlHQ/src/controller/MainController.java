package controller;


import command.Commando;

import controller.RobotThread.RobotState;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ILocations;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.IImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.IImageSource;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.WebCam;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Location;

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
	 * @param robot 
	 */
	public void initialize(int robot)
	{
		// Initialize webcam
		IImageSource imageSource = new WebCam();
		imageSource.init();
		
		// Start processing thread
		processingThread = new ProcessingThread(imageSource,robot);
		processingThread.start();
	}
	
	/**Returns an object, containing info regarding robots, cakes and image-processing
	 * @return ILocation - a {@link ILocation} object
	 */
	public ILocations getInformation() {
		return processingThread.getLocations();
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
			processingThread.stopAllThreads();
		
		processingThread = null;
	}
	/**
	 * Get'er til Bertas {@link RobotState}. 
	 * @return {@link RobotState}
	 */
	public RobotState getBertaState(){
		return processingThread.getBertaState();
	}
	/**
	 * Get'er til Bertas {@link RobotState}. 
	 * @return {@link RobotState}
	 */
	public RobotState getPropState(){
		return processingThread.getPropState();
	}
	public boolean isBertaConnected(){
		return processingThread.isBertaConnected();
	}
	public boolean isPropConnected(){
		return processingThread.isPropConnected();
	}
	public boolean isBertaPaused(){
		return processingThread.isBertaPaused();
	}
	public boolean isPropPaused(){
		return processingThread.isPropPaused();
	}
	public void setPauseBerta(boolean paused){
		processingThread.setPauseBerta(paused);
	}
	public void setPauseProp(boolean paused){
		processingThread.setPauseProp(paused);
	}
	public IImageProcessor getImageProcessor(){
		return processingThread.getImageProcessor();
	}
	public boolean isProcessorRunning()
	{
		return (processingThread == null)? false : true;
	}

	public Location getBertaTargetLocation()
	{
		return processingThread.getBertaTargetLocation();
	}
	public Location getPropTargetLocation()
	{
		return processingThread.getPropTargetLocation();
	}
	public double getBertaTargetAngle()
	{
		return processingThread.getBertaTargetAngle();
	}
	public double getPropTargetAngle()
	{
		return processingThread.getPropTargetAngle();
	}
}
