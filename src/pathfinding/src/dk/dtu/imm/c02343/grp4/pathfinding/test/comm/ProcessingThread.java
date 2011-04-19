package dk.dtu.imm.c02343.grp4.pathfinding.test.comm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JOptionPane;

import command.BertaCommando;
import command.interfaces.IControl;

import dk.dtu.imm.c02343.grp4.dto.interfaces.ICake;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ILocations;
import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.IImageSource;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.WebCam;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Path;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Step;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.TileMap;
import dk.dtu.imm.c02343.grp4.pathfinding.implementations.PathFinder;

public class ProcessingThread extends Thread {
	private BertaCommando bertaCommando;
	private IControl bertaControl;
	private IImageSource imageSource;
	private TestPathfinding testPathfinding;
	private boolean running;
	
	public void setPathfinding(TestPathfinding testPathfinding)
	{
		this.testPathfinding = testPathfinding;
	}
	
	public void run()
	{
		System.out.println("Starting processing thread.");
		imageSource = new WebCam();
		imageSource.init();
		bertaCommando = new BertaCommando();
		bertaControl = bertaCommando.getControl();
		
		running = true;
		
		while (running)
		{
			BufferedImage image = imageSource.getImage();
			ILocations locations = ImageProcessor.examineImage(image, true);
			try {
				calculatePath(locations);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			testPathfinding.updateImages(locations.getSourceImage(), locations.getTileImage());
			
			// Sleep a bit
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void calculatePath(ILocations locations) throws IOException
	{
		System.out.println("Calculating path...");
		
		if (locations.getRobots().size() <= 0 || locations.getCakes().size() <= 0)
		{
			System.out.println("Missing cake and/or robot on image. (robots: "+ locations.getRobots().size() +", cakes: "+ locations.getCakes().size() +")");
			
			return;
		}
		
		TileMap tileMap = new TileMap();
		tileMap.setTileMap(locations.getTilemap(), locations.getObstaclemap());
		IRobot robot = locations.getRobots().get(0);
		ICake cake = locations.getCakes().get(0);
		
		if (robot.getAngle()*180/Math.PI < 0)
		{
			//robot.setAngle(robot.getAngle() + Math.PI * 2);
		}
		
		// ** HOT FIX **
		// (Drop it like it's hot)
		int x = robot.getY();
		int y = robot.getX();
		robot.setPos(y, x);
		
		x = cake.getY();
		y = cake.getX();
		cake.setPos(y, x);
		// ** HOT FIX **
		
		PathFinder pathFinder = new PathFinder(tileMap, 1500, true);
		Path path = pathFinder.findPath(robot, robot.getY(), robot.getX(), cake.getY(), cake.getX());
		path = resizePath(path);
		
		System.out.println("Finding path between " + robot.getX() +"," + robot.getY() + " and " + cake.getX() + "," + cake.getY() + ".");
		
		BufferedImage image = locations.getTileImage();
		Graphics g = image.getGraphics();
		
		g.setColor(Color.white);
		
		if (path == null)
		{
			JOptionPane.showMessageDialog(null, "Could not find path between " + robot.getX() +"," + robot.getY() + " and " + cake.getX() + "," + cake.getY() + ".");
		}
		else
		{
			for (int i = 0; i < path.getLength(); i++)
			{
				Step step = path.getStep(i);
				Step nextStep;
				
				if (i == path.getLength() - 1)
				{
					nextStep = new Step(cake.getY(), cake.getX());
				}
				else
				{
					nextStep = path.getStep(i + 1);
				}
				
				g.drawLine(step.getX(), step.getY(), nextStep.getX(), nextStep.getY());
			}
		}
		
		locations.setTileImage(image);

		if (path != null && path.getLength() > 0)
		{
			Step step;
			
			int n = 0;
			do
			{
				step = path.getStep(n);
				n++;
			}
			while (step.getX() == robot.getX() && step.getY() == robot.getY());
			
			System.out.println("step: " + step.getX() + "," + step.getY());
			System.out.println("robot: " + robot.getX() + "," + robot.getY());
			
			double supposedToBeAngle = 0.0;
			
			if (step.getX() - robot.getX() == 0) {
				if (step.getY() > robot.getY()) {
					supposedToBeAngle = Math.PI;
				}
			} else {
				supposedToBeAngle = Math.atan(
						(step.getY() - robot.getY())
						/
						(step.getX() - robot.getX())
				) + Math.PI / 2;
				
				/*supposedToBeAngle = Math.atan(
						(step.getY() - robot.getY())
						/
						(step.getX() - robot.getX())
				) + Math.PI / 2 - Math.PI;*/
			}
			
			System.out.println("Current robot angle: " + (robot.getAngle()*180/Math.PI) + ", supposed to be angle: " + (supposedToBeAngle*180/Math.PI));
			
			bertaControl.stop();
			
			// Is current angle as it is supposed to be?
/*			if (true)
			{
				bertaControl.right(10);
			}
			else */if (Math.abs(robot.getAngle() - supposedToBeAngle) > ((Math.PI * 2) / 360)*10) // ~10 deg
			{
				// Rotate
//				if (supposedToBeAngle*180/Math.PI > robot.getAngle()*180/Math.PI)
				if (Math.abs(robot.getAngle()-supposedToBeAngle) > 0)
				{
					bertaControl.right(7);
				}
				else
				{
					bertaControl.left(7);
				}
			}
			else
			{
				System.out.println("Full steam ahead! ---- Aye aye captain, full steam ahead. TUUUUUUUT TUUUUUUUT");
				
				// MOVE!
				bertaControl.move(20, false);
			}
		}
	}

	private Path resizePath(Path path) {
		if (path.getLength() <= 0)
			return path;
		
		Path returnPath = new Path();
		
		Step step = path.getStep(0);
		Step lastStep;
		
		returnPath.appendStep(step.getY(), step.getX());
		
		for (int i = 1; i < path.getLength(); i++)
		{
			step = path.getStep(i);
			lastStep = returnPath.getStep(returnPath.getLength() - 1);
			
			if (Math.sqrt(Math.pow(step.getX() - lastStep.getX(), 2) + Math.pow(step.getY() - lastStep.getY(), 2)) > 20)
			{
				returnPath.appendStep(step.getY(), step.getX());
			}
		}
		
		System.out.println("Reduced path from " + path.getLength() + " steps to " + returnPath.getLength());
		
		return returnPath;
	}

	public void stopThread() {
		running = false;
		
		try {
			bertaControl.stop();
		} catch (IOException e) {
			
		}
		bertaCommando.disconnect();
		imageSource.close();
	}
}
