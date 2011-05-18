package dk.dtu.imm.c02343.grp4.imageprocessing.testimageprocessing;

import java.util.ArrayList;

import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor;

public class TestRobotFinder {
	public static void main(String[] args) {
		ArrayList<IRobot> r;
		int[][] tilemap = new int[][]
		{
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 }
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 }
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 4, 4, 4, 4, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 0, 0, 0, 0 }
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 4, 4, 4, 4, 3, 3, 3, 3 },
			{ 4, 4, 4, 4, 3, 3, 3, 3 },
			{ 4, 4, 4, 4, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 3, 3, 3, 3 },
			{ 4, 4, 4, 4, 3, 3, 3, 3 },
			{ 4, 4, 4, 4, 3, 3, 3, 3 },
			{ 4, 4, 4, 4, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 }
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 3, 3, 3, 3 },
			{ 4, 4, 4, 4, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 4, 4, 4, 4, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 0, 0, 0, 0 },
			{ 4, 4, 4, 4, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
			{ 0, 0, 0, 0, 3, 3, 3, 3 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 3, 3, 3, 3, 4, 4, 4, 4 },
			{ 3, 3, 3, 3, 4, 4, 4, 4 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 4, 4, 4, 4 },
			{ 3, 3, 3, 3, 4, 4, 4, 4 },
			{ 3, 3, 3, 3, 4, 4, 4, 4 },
			{ 3, 3, 3, 3, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 4, 4, 4, 4 },
			{ 3, 3, 3, 3, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 3, 3, 3, 3, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
			{ 0, 0, 0, 0, 4, 4, 4, 4 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
		
		tilemap = new int[][]
		{
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 3, 3, 3, 3, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
			{ 0, 0, 4, 4, 4, 4, 0, 0 },
		};
		r = ImageProcessor.findRobots(tilemap, ImageProcessor.ROBOTN, ImageProcessor.ROBOTS);
		System.out.println("Angle: " + r.get(0) + "\n");
	}
}