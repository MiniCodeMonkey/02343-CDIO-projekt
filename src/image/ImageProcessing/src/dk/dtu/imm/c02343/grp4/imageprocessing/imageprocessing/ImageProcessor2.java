package dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import com.sun.xml.internal.bind.v2.runtime.FilterTransducer;

import dk.dtu.imm.c02343.grp4.dto.impl.Cake;
import dk.dtu.imm.c02343.grp4.dto.impl.Locations;
import dk.dtu.imm.c02343.grp4.dto.impl.Robot;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ICake;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ILocations;
import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;

/**
 * Bearbejdning af billeder. Generering af tile map, identifikation af bane-gr�nser, bestemmelse af objekters position og robotters position samt retning
 * @author PC
 *
 */
public class ImageProcessor2 implements IImageProcessor {
	// Gr�nsev�rdier for forskellige typer objekter
	private Thresholds obstacleThresholds;
	private Thresholds cakeThresholds;
	private Thresholds robot1NThresholds;
	private Thresholds robot1SThresholds;
	private Thresholds robot2NThresholds;
	private Thresholds robot2SThresholds;
	
	// Buffer omkring forhindringer
	private int obstacleBuffer = OBSTACLE_BUFFER;
	
	// Kildebillede
	private BufferedImage sourceImage;
	
	public BufferedImage getSourceImage() {
		return sourceImage;
	}

	public void setSourceImage(BufferedImage sourceImage) {
		this.sourceImage = sourceImage;
	}

	// Debug-billede
	private BufferedImage tileImage;
	
	// Tilemap
	private int[][] tilemap;
	
	// Obstacle map
	private int[][] obstaclemap;
	
	// Processing resolution
	private int resX = RESOLUTION_X;
	private int resY = RESOLUTION_Y;
	
	// Liste af kager
	private ArrayList<ICake> cakes;
	
	// Liste af robotter
	private ArrayList<IRobot> robots;
	
	/**
	 * Konstrukt�r. Initialiserer standard-v�rdier.
	 */
	public ImageProcessor2() {
		obstacleThresholds = OBSTACLE_THRESHOLDS;
		cakeThresholds = CAKE_THRESHOLDS;
		robot1NThresholds = ROBOT1_N_THRESHOLDS;
		robot1SThresholds = ROBOT1_S_THRESHOLDS;
		robot2NThresholds = ROBOT2_N_THRESHOLDS;
		robot2SThresholds = ROBOT2_S_THRESHOLDS;
		sourceImage = new BufferedImage(320, 240, BufferedImage.TYPE_INT_ARGB);
		tileImage = new BufferedImage(320, 240, BufferedImage.TYPE_INT_ARGB);
		cakes = new ArrayList<ICake>();
		robots = new ArrayList<IRobot>();
	}
	
	/**
	 * Opretter et todimensionalt integer-tile map ud fra et billede 
	 * @param image Det billede, der skal fortolkes
	 * @return Todimensionalt int-array med integer-repr�sentationer af de identificerede objekter
	 */
	public void generateTileMap() {
		// Hent bredde og h�jde for input-billede
		int width = sourceImage.getWidth();
		int height = sourceImage.getHeight();
		// Initialis�r tilemap
		tilemap = new int[height][];
		// L�b igennem hver vandret linje
		for(int j = 0; j < height; j++) {
			// Initialis�r linjens tiles
			tilemap[j] = new int[width];
			// L�b igennem hvert punkt p� linjen
			for(int i = 0; i < width; i++) {
				// Hent RGB-v�rdier
				int[] rgb = getRGBvals(sourceImage.getRGB(i, j));
				
				if (obstacleThresholds.checkThresholds(rgb)) {
					// Hvid farve: Forhindring
					tilemap[j][i] = OBSTACLE;
				} else if (cakeThresholds.checkThresholds(rgb)) {
					// R�d farve: Kage
					tilemap[j][i] = CAKE;
				} else if (robot1NThresholds.checkThresholds(rgb)) {
					// Gr�n farve: Robot 1 N
					tilemap[j][i] = ROBOT1N;
				} else if (robot1SThresholds.checkThresholds(rgb)) {
					// Bl� farve: Robot 1 S
					tilemap[j][i] = ROBOT1S;
				} else if (robot2NThresholds.checkThresholds(rgb)) {
					// Orange farve: Robot 2 N
					tilemap[j][i] = ROBOT2N;
				} else if (robot2SThresholds.checkThresholds(rgb)) {
					// Gul farve: Robot 2 S
					tilemap[j][i] = ROBOT2S;
				} else {
					// Ikke identificeret objekt
					tilemap[j][i] = BACKGROUND;
				}
			}
		}
	}
	
	/**
	 * Filtrerer forhindrings-pixels i tilemap, s� enkelte pixels sorteres fra
	 */
	private void filterObstacles() {
		// matrix til at holde styr p� behandlede pixels
		int[][] foundmap = new int[tilemap.length][];
		// Initialis�r alle felter i matricen
		for(int i = 0; i < foundmap.length; i++) {
			foundmap[i] = new int[tilemap[i].length];
			java.util.Arrays.fill(foundmap[i], BACKGROUND);
		}
		
		for (int y = 0; y < tilemap.length; y++) {
			for (int x = 0; x < tilemap[0].length; x++) {
				if (foundmap[y][x] == 0) {
					foundmap[y][x] = 1;
					ArrayList<int[]> coordinates = new ArrayList<int[]>();
					collectRecursion(new int[]{y,x}, OBSTACLE, foundmap, coordinates);
					if (coordinates.size() < MIN_OBJECT_SIZE) {
//						System.out.println("Not enough points for object of type " + type + ". Found " + coordinates.size() + " points.");
						for (int[] coordinate : coordinates) {
							tilemap[coordinate[0]][coordinate[1]] = BACKGROUND;
						}
					}
				}
			}
		}
	}
	
	/**
	 * Genererer forhindrings-map og kage-/objektlister
	 */
	public void processTilemap() {
		// matrix til at holde styr p� behandlede pixels
		int[][] foundmap = new int[tilemap.length][];
		// Initialis�r alle felter i matricen
		for(int i = 0; i < foundmap.length; i++) {
			foundmap[i] = new int[tilemap[i].length];
			java.util.Arrays.fill(foundmap[i], BACKGROUND);
		}
		
		// Initialis�r obstacle-map
		obstaclemap = new int[tilemap.length][];
		for (int i = 0; i < obstaclemap.length; i++) {
			obstaclemap[i] = new int[tilemap[0].length];
			java.util.Arrays.fill(obstaclemap[i], 0);
		}
		
		// Initialis�r kage- og robotlister
		cakes = new ArrayList<ICake>();
		robots = new ArrayList<IRobot>();
		
		int[] robot1Npos = new int[] {-1,-1};
		int[] robot1Spos = new int[] {-1,-1};
		int[] robot2Npos = new int[] {-1,-1};
		int[] robot2Spos = new int[] {-1,-1};
		
		// Iter�r over positioner i tilemap
		for (int y = 0; y < tilemap.length; y = y + resY) {
			for (int x = 0; x < tilemap[y].length; x = x + resX) {
				if (foundmap[y][x] == 0) {
					if (tilemap[y][x] == BACKGROUND) {
						// Mark�r punktet som behandlet
						foundmap[y][x] = 1;
					} else if (tilemap[y][x] == OBSTACLE) {
						// Mark�r punktet som behandlet
						foundmap[y][x] = 1;
						// S�t nuv�rende pixel til bufferzone-v�rdi, hvis der er en obstacle her
						obstaclemap[y][x] = obstacleBuffer;
						// Iter�r i +/- bufferzone pixels omkring pixlen
						for (int dy = -obstacleBuffer; dy < obstacleBuffer; dy++) {
							if (!(y+dy < 0 || y+dy >= obstaclemap.length)) { // Sikrer, at der ikke g�res noget uden for array-gr�nser
								for (int dx = -obstacleBuffer; dx < obstacleBuffer; dx++) {
									if (!(x+dx < 0 || x+dx >= obstaclemap[y+dy].length)) { // Sikrer, at der ikke g�res noget uden for array-gr�nser
										// Udregn "power" ud fra afstanden til den fundne obstacle-pixel. "power" er bufferzone-v�rdien minus afstanden.
										int power = obstacleBuffer-(int)Math.floor(Math.sqrt(dy*dy+dx*dx));
										// Den beregnede power skal kun p�f�res en pixel, hvis den er st�rre end tidligere v�rdi.
										if (power > obstaclemap[y+dy][x+dx]) {
											obstaclemap[y+dy][x+dx] = power;
										}
									}
								}
							}
						}
					} else if (tilemap[y][x] == CAKE) {
						try {
							// Behandl objekt
							int[] pos = collect(y, x, CAKE, foundmap);
							// Opret kage-objekt og f�j til liste
							Cake cake = new Cake(pos[0], pos[1]);
							cakes.add(cake);
//							System.out.println(cake);
						} catch (InsufficientObjectException e) {
//							System.out.println(e.getMessage());
						}
					} else if (tilemap[y][x] == ROBOT1N) {
						try {
							// Behandl objekt
							robot1Npos = collect(y, x, ROBOT1N, foundmap);
//							System.out.println("Robot front at (y,x): (" + robotNpos[0] + "," + robotNpos[1] + ")");
						} catch (InsufficientObjectException e) {
//							System.out.println(e.getMessage());
						}
					} else if (tilemap[y][x] == ROBOT1S) {
						try {
							// Behandl objekt
							robot1Spos = collect(y, x, ROBOT1S, foundmap);
//							System.out.println("Robot rear at (y,x): (" + robotSpos[0] + "," + robotSpos[1] + ")");
						} catch (InsufficientObjectException e) {
//							System.out.println(e.getMessage());
						}
					} else if (tilemap[y][x] == ROBOT2N) {
						try {
							// Behandl objekt
							robot2Npos = collect(y, x, ROBOT2N, foundmap);
//							System.out.println("Robot front at (y,x): (" + robotNpos[0] + "," + robotNpos[1] + ")");
						} catch (InsufficientObjectException e) {
//							System.out.println(e.getMessage());
						}
					} else if (tilemap[y][x] == ROBOT2S) {
						try {
							// Behandl objekt
							robot2Spos = collect(y, x, ROBOT2S, foundmap);
//							System.out.println("Robot rear at (y,x): (" + robotSpos[0] + "," + robotSpos[1] + ")");
						} catch (InsufficientObjectException e) {
//							System.out.println(e.getMessage());
						}
					}
				}
			}
		}
		
		// Opret robot-objekter
		if (robot1Npos[0] > 0 && robot1Npos[1] > 0 && robot1Spos[0] > 0 && robot1Spos[1] > 0) {
			int[] robotPos = new int[] {(robot1Npos[0]+robot1Spos[0])/2, (robot1Npos[1]+robot1Spos[1])/2};
			double robotAngle = calculateAngle(robot1Npos, robot1Spos);
//			System.out.println("Robot pos (y,x,a): (" + robotPos[0] + "," + robotPos[1] + "," + robotAngle + ")");
			robots.add(new Robot(robotPos[0],robotPos[1],robotAngle));
		} else {
			// Robot ikke fundet. Opret dummy objekt.
			robots.add(new Robot(-1,-1,0));
		}
		
		if (robot2Npos[0] > 0 && robot2Npos[1] > 0 && robot2Spos[0] > 0 && robot2Spos[1] > 0) {
			int[] robotPos = new int[] {(robot2Npos[0]+robot2Spos[0])/2, (robot2Npos[1]+robot2Spos[1])/2};
			double robotAngle = calculateAngle(robot2Npos, robot2Spos);
//			System.out.println("Robot pos (y,x,a): (" + robotPos[0] + "," + robotPos[1] + "," + robotAngle + ")");
			robots.add(new Robot(robotPos[0],robotPos[1],robotAngle));
		} else {
			// Robot ikke fundet. Opret dummy objekt.
			robots.add(new Robot(-1,-1,0));
		}
	}
	
	private int[] collect(int y, int x, int type, int[][] foundmap) throws InsufficientObjectException {
		foundmap[y][x] = 1;
		ArrayList<int[]> coordinates = new ArrayList<int[]>();
		collectRecursion(new int[]{y,x}, type, foundmap, coordinates);
		if (coordinates.size() < MIN_OBJECT_SIZE) {
//			System.out.println("Not enough points for object of type " + type + ". Found " + coordinates.size() + " points.");
			throw new InsufficientObjectException("Not enough points for object of type " + type + ". Found " + coordinates.size() + " points.");
		} else if (type == OBSTACLE) {
//			System.out.println("Found " + coordinates.size() + "points.");
		}
		
		int oy = 0;
		int ox = 0;
		for (int[] pos : coordinates) {
			oy += pos[0];
			ox += pos[1];
		}
		oy = oy/coordinates.size();
		ox = ox/coordinates.size();
		
		return new int[]{oy,ox};
	}
	
	private void collectRecursion(int[] pos, int type, int[][] foundmap, ArrayList<int[]> coordinates) {
		// Tjek punktet til h�jre
		if (pos[1]+1 < tilemap[pos[0]].length && foundmap[pos[0]][pos[1]+1] == 0 && tilemap[pos[0]][pos[1]+1] == type) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]][pos[1]+1] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0],pos[1]+1};
			// Tilf�j koordinater til listen
			coordinates.add(newpos);
			// K�r metoden rekursivt
			collectRecursion(newpos, type, foundmap, coordinates);
		}
		
		// Tjek punktet under
		if (pos[0]+1 < tilemap.length && foundmap[pos[0]+1][pos[1]] == 0 && tilemap[pos[0]+1][pos[1]] == type) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]+1][pos[1]] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0]+1,pos[1]};
			// Tilf�j koordinater til listen
			coordinates.add(newpos);
			// K�r metoden rekursivt
			collectRecursion(newpos, type, foundmap, coordinates);
		}
		
		// Tjek punktet til venstre
		if (pos[1]-1 >= 0 && foundmap[pos[0]][pos[1]-1] == 0 && tilemap[pos[0]][pos[1]-1] == type) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]][pos[1]-1] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0],pos[1]-1};
			// Tilf�j koordinater til listen
			coordinates.add(newpos);
			// K�r metoden rekursivt
			collectRecursion(newpos, type, foundmap, coordinates);
		}
		
		// Tjek punktet over
		if (pos[0]-1 >= 0 && foundmap[pos[0]-1][pos[1]] == 0 && tilemap[pos[0]-1][pos[1]] == type) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]-1][pos[1]] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0]-1,pos[1]};
			// Tilf�j koordinater til listen
			coordinates.add(newpos);
			// K�r metoden rekursivt
			collectRecursion(newpos, type, foundmap, coordinates);
		}
	}
	
	private double calculateAngle(int[] to, int[] from) {
		double dy = to[0]-from[0];
		double dx = to[1]-from[1];
		double angle = 0.0;
		if (dy == 0) {
			// Gr�nsetilf�lde: Robot vender mod h�jre eller venstre
			if (dx > 0) {
				angle = Math.PI/2;
			} else if (dx < 0) {
				angle = -Math.PI/2;
			} else {
				angle = 0;
			}
		} else if (dx == 0) {
			// Gr�nsetilf�lde: Robot vender op eller ned
			if (dy > 0) {
				angle = Math.PI;
			} else {
				angle = 0;
			}
		} else {
			// Generelt
			angle = -Math.atan(dx/dy);
			if (dx > 0 && dy > 0) {
				// 3. kvadrant
				angle = Math.PI+angle;
			} else if (dx < 0 && dy > 0) {
				// 4. kvadrant
				angle = -Math.PI+angle;
			}
		}
		return angle;
	}
	
	/**
	 * Finder gr�nserne for banen og returnerer et array med 4 v�rdier; top, venstre, bund og h�jre gr�nse-index
	 * @param tilemap Det tilemap, der skal findes gr�nser p�
	 * @return Array med gr�nserne i r�kkef�lgen [top, venstre, bund, h�jre]
	 */
	private int[] findBounds() {
		// Gr�nser s�ttes umiddelbart til input-tilemap's gr�nser
		int[] bounds = new int[] {0,0,tilemap.length-1,tilemap[0].length-1};
		
		// Bestemmer antallet af obstacle-v�rdier der skal v�re p� en linje, som betegnes som gr�nse
		int bordercount = 5;
		
		// Variable brugt ved bestemmelse af gr�nser
		int y;
		int x;
		int count;
		boolean found;
		
		// Initialis�r variable til f�rste gr�nse: top
		y = bounds[0];
		x = bounds[1];
		count = 0;
		found = false;
		
		// Unders�g gennem y-v�rdierne
		while (!found && y < bounds[2]) {
			count = 0;
			// Iter�r gennem x-v�rdierne
			for (x=bounds[1]; x < bounds[3]; x++) {
				// Hvis positionen er en obstacle, t�l op
				if (tilemap[y][x] == OBSTACLE) {
					count++;
				}
				
				// Hvis der er fundet nok obstacle-positioner, s�ttes found og gr�nse - og for-l�kken termineres
				if (count > bordercount) {
					found = true;
					bounds[0] = y;
					break;
				}
			}
			y++;
		}
		
		// Initialis�r variable til anden gr�nse: venstre
		y = bounds[0];
		x = bounds[1];
		count = 0;
		found = false;
		
		// Unders�g gennem x-v�rdierne 
		while (!found && x < bounds[3]) {
			count = 0;
			// Iter�r gennem y-v�rdierne
			for (y=bounds[0]; y < bounds[2]; y++) {
				// Hvis positionen er en obstacle, t�l op
				if (tilemap[y][x] == OBSTACLE) {
					count++;
				}
				
				// Hvis der er fundet nok obstacle-positioner, s�ttes found og gr�nse - og for-l�kken termineres
				if (count > bordercount) {
					found = true;
					bounds[1] = x;
					break;
				}
			}
			x++;
		}
		
		// Initialis�r variable til tredje gr�nse: bund
		y = bounds[2];
		x = bounds[1];
		count = 0;
		found = false;
		
		// Unders�g gennem y-v�rdierne 
		while (!found && y > bounds[0]) {
			count = 0;
			// Iter�r gennem x-v�rdierne
			for (x=bounds[1]; x < bounds[3]; x++) {
				// Hvis positionen er en obstacle, t�l op
				if (tilemap[y][x] == OBSTACLE) {
					count++;
				}
				
				// Hvis der er fundet nok obstacle-positioner, s�ttes found og gr�nse - og for-l�kken termineres
				if (count > bordercount) {
					found = true;
					bounds[2] = y;
					break;
				}
			}
			y--;
		}
		
		// Initialis�r variable til anden gr�nse: venstre
		y = bounds[0];
		x = bounds[3];
		count = 0;
		found = false;
		
		// Unders�g gennem x-v�rdierne 
		while (!found && x > bounds[1]) {
			count = 0;
			// Iter�r gennem y-v�rdierne
			for (y=bounds[0]; y < bounds[2]; y++) {
				// Hvis positionen er en obstacle, t�l op
				if (tilemap[y][x] == OBSTACLE) {
					count++;
				}
				
				// Hvis der er fundet nok obstacle-positioner, s�ttes found og gr�nse - og for-l�kken termineres
				if (count > bordercount) {
					found = true;
					bounds[3] = x;
					break;
				}
			}
			x--;
		}
		
		return bounds;
	}
	
	/**
	 * Returnerer farve-komponenterne ud fra en RGB-integer
	 * @param rgbval Farven, der skal deles
	 * @return int array med farvekomponenterne i r�kkef�lgen R G B
	 */
	public static int[] getRGBvals(int rgbval) {
		// Initialis�r output-array
		int[] output = new int[3];
		// Beregn RGB-komponenter vha. bit-shifting og bitwise AND
		output[2] = rgbval & 0xFF;
		output[1] = (rgbval >> 8) & 0xFF;
		output[0] = (rgbval >> 16) & 0xFF;
		return output;
	}

	/**
	 * Besk�rer tilemap til givne gr�nser
	 * @param tilemap Tilemap, der skal besk�res
	 * @param bounds Gr�nser, der skal besk�res efter: (top,venstre,bund,h�jre)
	 * @return Besk�ret tilemap
	 */
	public void crop() {
		int[] bounds = findBounds();
		// Hent gr�nserne ud til enkelte variable
		int ymin = bounds[0];
		int xmin = bounds[1];
		int ymax = bounds[2];
		int xmax = bounds[3];
		
		// Beregn ny h�jde og bredde af tilemap
		int height = ymax-ymin+1;
		int width = xmax-xmin+1; 
		
		// Initialis�r output
		int[][] outputTilemap = new int[height][];
		int[][] outputObstaclemap = new int[height][];
		
		// Iter�r over den nye h�jde (koordinater i det nye map)
		for(int y = 0; y < height; y++) {
			outputTilemap[y] = new int[width];
			outputObstaclemap[y] = new int[width];
			// Iter�r over den nye bredde
			for(int x = 0; x < width; x++) {
				// Kopi�r v�rdier fra udsnit af oprindeligt tilemap til besk�ret map 
				outputTilemap[y][x] = tilemap[ymin+y][xmin+x];
				outputObstaclemap[y][x] = tilemap[ymin+y][xmin+x];
			}
		}
		
		tilemap = outputTilemap;
		obstaclemap = outputObstaclemap;
	}
	
	/**
	 * Genererer grafik over det tolkede billede til visuel debug
	 * @return Billede af tilemap
	 */
	public void createTileImage() {
		tileImage = new BufferedImage(tilemap[0].length, tilemap.length, BufferedImage.TYPE_INT_ARGB);
//		System.out.println("Dimensions: " + tileImage.getHeight() + "x" + tileImage.getWidth());
		// Iter�r over alle vandrette linjer
		for(int i = 0; i < tilemap.length; i++) {
			// Iter�r over alle punkter
			for(int j = 0; j < tilemap[i].length; j++) {
				int rgb;
				// S�t RGB-v�rdi til output-billede ud fra tilemap v�rdi. To f�rste hex-v�rdier er alpha-v�rdi: RGB = 0xAARRGGBB.
				switch (tilemap[i][j]) {
					case CAKE:
						rgb = 0xFFFF0000;
						break;
					case OBSTACLE:
						rgb = 0xFFFFFFFF;
						break;
					case ROBOT1N:
						rgb = 0xFF00FF00;
						break;
					case ROBOT1S:
						rgb = 0xFF0000FF;
						break;
					case ROBOT2N:
						rgb = 0xFFFF7700;
						break;
					case ROBOT2S:
						rgb = 0xFFFFFF00;
						break;
					default:
						rgb = 0xFF000000;
				}
				// Hvis pixel er bufferzonegr�nse (v�rdi 1 i obstaclemap), s�t farve
				if (obstaclemap[i][j] == 1) {
					rgb = 0xFFFFFF00;
				}
				// S�t pixel-v�rdi
				tileImage.setRGB(j, i, rgb);
//				System.out.print(tilemap[i][j]); // Til udskrift af tilemap i console
			}
//			System.out.println(); // Til udskrift af tilemap i console
		}
		
		Iterator<ICake> cakeItr = cakes.iterator();
		while(cakeItr.hasNext()) {
			ICake cake = cakeItr.next();
//			System.out.println("Object at (" + cake.getY() + "," + cake.getX() + ").");
			tileImage.setRGB(cake.getX(), cake.getY(), 0xFF00FFFF);
			tileImage.setRGB(cake.getX()+1, cake.getY(), 0xFF00FFFF);
			tileImage.setRGB(cake.getX()-1, cake.getY(), 0xFF00FFFF);
			tileImage.setRGB(cake.getX(), cake.getY()+1, 0xFF00FFFF);
			tileImage.setRGB(cake.getX(), cake.getY()-1, 0xFF00FFFF);
		}
		
		for (IRobot robot : robots) {
//			System.out.println("Robot at (" + robot.getY() + "," + robot.getX() + ") angle: " + robot.getAngle() + "rad = " + robot.getAngle()*180/Math.PI + " deg");
			if (robot.getX() >= 0 && robot.getX() < tileImage.getWidth() && robot.getY() >= 0 && robot.getY() < tileImage.getHeight()) {
				tileImage.setRGB(robot.getX(),robot.getY(), 0xFFFF0000);
				if (robot.getX() < tileImage.getWidth())
					tileImage.setRGB(robot.getX()+1,robot.getY(), 0xFFFF0000);
				if (robot.getX() >= 0)
					tileImage.setRGB(robot.getX()-1,robot.getY(), 0xFFFF0000);
				if (robot.getY() < tileImage.getHeight())
					tileImage.setRGB(robot.getX(),robot.getY()+1, 0xFFFF0000);
				if (robot.getY() >= 0)
					tileImage.setRGB(robot.getX(),robot.getY()-1, 0xFFFF0000);
			}
		}
	}
	
	/**
	 * S�t gr�nsev�rdier for bestemmelse af objekttyper
	 * @param type Talrepr�sentationen p� typen. Kan v�re ImageProcessor.OBSTACLE, ImageProcessor.CAKE, ImageProcessor.ROBOTN eller ImageProcessor.ROBOTS
	 * @param thresholds Thresholds-objekt med de nye gr�nsev�rdier
	 */
	public void setThresholds(int type, Thresholds thresholds) {
		if (type == OBSTACLE) {
			obstacleThresholds = thresholds;
		} else if (type == CAKE) {
			cakeThresholds = thresholds;
		} else if (type == ROBOT1N) {
			robot1NThresholds = thresholds;
		} else if (type == ROBOT1S) {
			robot1SThresholds = thresholds;
		} else if (type == ROBOT2N) {
			robot2NThresholds = thresholds;
		} else if (type == ROBOT2S) {
			robot2SThresholds = thresholds;
		}
	}
	
	/**
	 * Hent gr�nsev�rdier for bestemmelse af objekttyper
	 * @param type Talrepr�sentationen p� typen. Kan v�re ImageProcessor.OBSTACLE, ImageProcessor.CAKE, ImageProcessor.ROBOTN eller ImageProcessor.ROBOTS
	 * @return Thresholds-objekt med de �nskede gr�nsev�rdier
	 */
	public Thresholds getThresholds(int type) {
		if (type == OBSTACLE) {
			return obstacleThresholds;
		} else if (type == CAKE) {
			return cakeThresholds;
		} else if (type == ROBOT1N) {
			return robot1NThresholds;
		} else if (type == ROBOT1S) {
			return robot1SThresholds;
		} else if (type == ROBOT2N) {
			return robot2NThresholds;
		} else if (type == ROBOT2S) {
			return robot2SThresholds;
		}
		return null;
	}
	
	/**
	 * Henter bufferzonen omkring forhindringer
	 * @return Zonebredde
	 */
	public int getObstacleBufferZone() {
		return obstacleBuffer;
	}
	
	/**
	 * S�tter bufferzonen omkring forhindringer
	 * @param bufferZone Ny zonebredde
	 */
	public void setObstacleBufferZone(int bufferZone) {
		if (bufferZone >= 0) {
			obstacleBuffer = bufferZone;
		}
	}
	
	public int getResolutionX() {
		return resX;
	}

	public void setResolutionX(int resolution) {
		this.resX = resolution;
	}

	public int getResolutionY() {
		return resY;
	}

	public void setResolutionY(int resolution) {
		this.resY = resolution;
	}

	/**
	 * Gennemf�rer fuld analyse af input-billede, og returnerer et Locations objekt
	 */
	public ILocations examineImage(BufferedImage imageSource, boolean debug) {
		setSourceImage(imageSource);
		generateTileMap();
		crop();
		filterObstacles();
		processTilemap();
		ILocations locations = new Locations(tilemap, obstaclemap, cakes, robots);
		if (debug) {
			locations.setSourceImage(imageSource);
			createTileImage();
			locations.setTileImage(tileImage);
		}
		return locations;
	}
}