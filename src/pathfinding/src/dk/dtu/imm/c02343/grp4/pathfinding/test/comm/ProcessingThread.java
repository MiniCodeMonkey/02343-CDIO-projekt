package dk.dtu.imm.c02343.grp4.pathfinding.test.comm;

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
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor2;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.IImageSource;
import dk.dtu.imm.c02343.grp4.imageprocessing.imagesource.WebCam;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Location;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Path;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.Step;
import dk.dtu.imm.c02343.grp4.pathfinding.dat.TileMap;
import dk.dtu.imm.c02343.grp4.pathfinding.implementations.PathFinder;

public class ProcessingThread extends Thread {
	private BertaCommando bertaCommando;
	private IControl bertaControl;
	private IImageSource imageSource;
	private IImageProcessor imageProcessor;
	private TestPathfinding testPathfinding;
	private boolean running;
	private boolean headingForHome = false;
	private boolean foundCake = false;
	private ICake homeCake;
	
	public void setPathfinding(TestPathfinding testPathfinding)
	{
		this.testPathfinding = testPathfinding;
	}
	
	public void run()
	{
		System.out.println("Starting processing thread.");
		imageSource = new WebCam();
		imageSource.init();
		imageProcessor = new ImageProcessor2();
		bertaCommando = new BertaCommando();
		bertaControl = bertaCommando.getControl();
		
		try {
			bertaControl.closeClaw();
			Thread.sleep(2000);
			bertaControl.stopClaw();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		running = true;
		
		while (running)
		{
			BufferedImage image = imageSource.getImage();
			ILocations locations = imageProcessor.examineImage(image, true);
			try {
				calculatePath(locations);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			testPathfinding.updateImages(locations.getSourceImage(), locations.getTileImage());
			
			// Sleep a bit
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		System.out.println("Ending ProcessingThread");
	}

	private void calculatePath(ILocations locations) throws IOException
	{
		bertaControl.stop();
		System.out.println("Calculating path...");
		
		if (locations.getRobots().size() <= 0 || (locations.getCakes().size() <= 0 && !headingForHome))
		{
			System.out.println("Missing cake and/or robot on image. (robots: "+ locations.getRobots().size() +", cakes: "+ locations.getCakes().size() +")");
			
			bertaControl.reverse(100, 600);
			return;
		}
		
		TileMap tileMap = new TileMap();
		tileMap.setTileMap(locations.getTilemap(), locations.getObstaclemap());
		IRobot robot = locations.getRobots().get(0);
		ICake cake;
		
		if (!headingForHome)
		{
			cake = locations.getCakes().get(0);
		}
		else
		{
			cake = homeCake;
		}
		
		PathFinder pathFinder = new PathFinder(tileMap, 1500, true);
		Location targetLocation = new Location(cake.getY(), cake.getX());
		Path path = pathFinder.findPath(robot, targetLocation);
		path = resizePath(path);
		
		System.out.println("Finding path between " + robot.getX() +"," + robot.getY() + " and " + cake.getX() + "," + cake.getY() + ".");
		
		BufferedImage image = locations.getTileImage();
		Graphics g = image.getGraphics();
		
		g.setColor(Color.white);
		
		if (path == null)
		{
			System.out.println("Could not find path between robot and target");
//			JOptionPane.showMessageDialog(null, "Could not find path between " + robot.getX() +"," + robot.getY() + " and " + cake.getX() + "," + cake.getY() + ".");
			bertaControl.stop();
			return;
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
			while (n < path.getLength() && step.getX() == robot.getX() && step.getY() == robot.getY());
			
			System.out.println("step: " + step.getX() + "," + step.getY());
			System.out.println("robot: " + robot.getX() + "," + robot.getY());
			
			double dy = step.getY()-robot.getY();
			double dx = step.getX()-robot.getX();
			double supposedToBeAngle = 0.0;
			if (dy == 0) {
				// Grænsetilfælde: Robot vender mod højre eller venstre
				if (dx > 0) {
					supposedToBeAngle = Math.PI/2;
				} else if (dx < 0) {
					supposedToBeAngle = -Math.PI/2;
				} else {
					supposedToBeAngle = 0;
				}
			} else if (dx == 0) {
				// Grænsetilfælde: Robot vender op eller ned
				if (dy > 0) {
					supposedToBeAngle = Math.PI;
				} else {
					supposedToBeAngle = 0;
				}
			} else {
				// Generelt
				supposedToBeAngle = -Math.atan(dx/dy);
				if (dx > 0 && dy > 0) {
					// 3. kvadrant
					supposedToBeAngle = Math.PI+supposedToBeAngle;
				} else if (dx < 0 && dy > 0) {
					// 4. kvadrant
					supposedToBeAngle = -Math.PI+supposedToBeAngle;
				}
			}
			
			System.out.println("Current robot angle: " + (robot.getAngle()*180/Math.PI) + ", supposed to be angle: " + (supposedToBeAngle*180/Math.PI));
			
			double distance = calculateDistance(robot.getX(), robot.getY(), cake.getX(), cake.getY());
			System.out.println("Distance to target: " + distance);
			
			if (!headingForHome)
			{
				if (distance < 32)
				{
					if (!foundCake)
					{
						foundCake = true;
						//running = false;
						System.out.println("CAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAKE!!!!!!!!!");
					}
					
					if (Math.abs(robot.getAngle() - supposedToBeAngle) <= (Math.PI / 180)*10)
					{
						bertaControl.openClaw();
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						bertaControl.stopClaw();
		
						System.out.println("Moving forward");
						bertaControl.move(40, false);
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						bertaControl.stop();
						
						System.out.println("Closing claw");
						bertaControl.closeClaw();
						
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						bertaControl.stopClaw();
						
						System.out.println("Reversing");
						bertaControl.move(50, true);
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						bertaControl.stop();
						/*System.out.println("Opening claw");
						bertaControl.openClaw();
						
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						bertaControl.stopClaw();
						
						System.out.println("Done");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
						
						ICake homes[] = {
								new Cake(locations.getTileImage().getHeight() - 1, locations.getTileImage().getWidth() / 2),
								new Cake(locations.getTileImage().getHeight() / 2, locations.getTileImage().getWidth() - 1),
								new Cake(1, locations.getTileImage().getWidth() / 2),
								new Cake(locations.getTileImage().getHeight() / 2, 1)
						};
						
						double bestDistance = Double.MAX_VALUE;
						for (ICake home : homes)
						{
							double currentDistance = Math.sqrt(Math.pow(home.getX() - robot.getX(), 2) + Math.pow(home.getY()- robot.getY(), 2));
							
							if (currentDistance < bestDistance)
							{
								bestDistance = currentDistance;
								homeCake = home;
							}
						}
						
						headingForHome = true;
						foundCake = false;
						System.out.println("Heading for home...");
						
						return;
					}
				}
			}
			else
			{
				if (distance < 32)
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
					bertaControl.move(50, true);
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
			if (distance >= 40 && !foundCake && Math.abs(robot.getAngle() - supposedToBeAngle) > (Math.PI / 180)*45)
			{
				// Rotate
				if (robot.getAngle() < supposedToBeAngle)
				{
					bertaControl.right(50);
				}
				else
				{
					bertaControl.left(50);
				}
			}
			else if (foundCake || (distance < 40 && Math.abs(robot.getAngle() - supposedToBeAngle) >= (Math.PI / 180)*10))
			{
				// Rotate
				if (robot.getAngle() < supposedToBeAngle)
				{
					bertaControl.right(25);
				}
				else
				{
					bertaControl.left(25);
				}
			}
			else
			{
				System.out.println("Full steam ahead! ---- Aye aye captain, full steam ahead. TUUUUUUUT TUUUUUUUT");
				
				// MOVE!
				bertaControl.move(40, false);
			}
		}
	}

	private Path resizePath(Path path) {
		if (path == null || path.getLength() <= 0)
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
	
	private double calculateDistance(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt(Math.pow(x2-x1, 2) + Math.pow(y2-y1, 2));
	}
}