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
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Path;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Step;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.TileMap;
import dk.dtu.imm.c02343.grp4.pathfinding.implementations.PathFinder;

public class ProcessingThread extends Thread {
	BertaCommando bertaCommando;
	IControl bertaControl;
	IImageSource imageSource;
	
	boolean running;
	
	public void run(TestPathfinding testPathfinding)
	{
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
				Thread.sleep(200);
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
			Step step = path.getStep(0);
			
			// Is current angle as it is supposed to be?
			double supposedToBeAngle = Math.atan2(step.getX() - robot.getX(), step.getY() - robot.getY());
			
			System.out.println("Current robot angle: " + robot.getAngle() + ", supposed to be angle: " + supposedToBeAngle);
			
			bertaControl.stop();
			if (Math.abs(robot.getAngle() - supposedToBeAngle) > 10.0)
			{
				// Rotate
				if (supposedToBeAngle > robot.getAngle())
				{
					System.out.println("Rotating right");
					bertaControl.right(20);
				}
				else
				{
					System.out.println("Rotating left");
					bertaControl.left(20);
				}
			}
			else
			{
				System.out.println("Forward we go!");
				
				// MOVE!
				bertaControl.move(20, false);
			}
		}
	}

	public void stopThread() {
		running = false;
		
		bertaCommando.disconnect();
		imageSource.close();
	}
}
