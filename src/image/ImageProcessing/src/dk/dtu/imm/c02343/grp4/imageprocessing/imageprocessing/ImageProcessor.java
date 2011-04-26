package dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.media.jai.Interpolation;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.ScaleDescriptor;

import dk.dtu.imm.c02343.grp4.dto.impl.Cake;
import dk.dtu.imm.c02343.grp4.dto.impl.Locations;
import dk.dtu.imm.c02343.grp4.dto.impl.Robot;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ICake;
import dk.dtu.imm.c02343.grp4.dto.interfaces.ILocations;
import dk.dtu.imm.c02343.grp4.dto.interfaces.IRobot;

/**
 * Bearbejdning af billeder. Generering af tile map, identifikation af bane-grænser, bestemmelse af objekters position og robotters position samt retning
 * @author PC
 *
 */
public class ImageProcessor {
	/**
	 * Integer-repræsentation af en forhindring i tilemap
	 */
	public static final int OBSTACLE = 2;
	/**
	 * Integer-repræsentation af en kage i tilemap
	 */
	public static final int CAKE = 1;
	/**
	 * Integer-repræsentation af en robot-front i tilemap
	 */
	public static final int ROBOTN = 3;
	/**
	 * Integer-repræsentation af en robot-bagdel i tilemap
	 */
	public static final int ROBOTS = 4;
	/**
	 * Integer-repræsentation af baggrund i tilemap
	 */
	public static final int BACKGROUND = 0;
	
	/**
	 * Mindste størrelse på sammenhængende områder i kager og robot-elementer i pixels
	 */
	public static final int MIN_OBJECT_SIZE = 10;
	
	// Grænseværdier for forskellige typer objekter
	private static Thresholds obstacleThresholds = new Thresholds(150, 150, 150, 255, 255, 255);
	private static Thresholds cakeThresholds = new Thresholds(100, 0, 0, 255, 25, 25);
	private static Thresholds robotNThresholds = new Thresholds(0, 70, 0, 70, 255, 90);
	private static Thresholds robotSThresholds = new Thresholds(0, 0, 50, 20, 50, 255);
	
	// Buffer omkring forhindringer
	private static int obstacleBuffer = 5;
	
	/**
	 * Tom konstruktør. Metoderne bruges statisk
	 */
	public ImageProcessor() {
	}
	
	/**
	 * Metode til at skalere Image-objekter
	 * @param inputImage Billedet, der skal skaleres
	 * @param newWidth Ny bredde i pixels
	 * @param newHeight Ny højde i pixels
	 * @return Skaleret billede
	 */
	public static Image resizeImage(Image inputImage, int newWidth, int newHeight) {
		// Beregn skaleringsfaktorer
		float scaleX = (float) newWidth / inputImage.getWidth(null);
		float scaleY = (float) newHeight / inputImage.getHeight(null);
		// Opret BufferedImage til brug ved skalering
		BufferedImage buffImage = toBufferedImage(inputImage, BufferedImage.TYPE_INT_RGB);
		// Opret RenderedOp objekt med skaleret billede
		RenderedOp renderedOp = ScaleDescriptor.create(buffImage, new Float(scaleX), new Float(scaleY), new Float(0f), new Float(0f), Interpolation.getInstance(Interpolation.INTERP_BICUBIC), null);
		// Returnér BufferedImage fra RenderedOp
		return renderedOp.getAsBufferedImage();
	}
	
	/**
	 * Metode til at konvertere et Image-objekt til et BufferedImage objekt
	 * @param image Image-objektet, der skal konverteres
	 * @param type Farvemodel for billedet
	 * @return BufferedImage-version af input-billedet
	 */
	public static BufferedImage toBufferedImage(final Image image, final int type) {
		// Hvis input-billedet allerede er et BufferedImage, gør intet
		if (image instanceof BufferedImage)
			return (BufferedImage) image;
		// Opret BufferedImage og Graphics
		final BufferedImage buffImg = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		final Graphics2D g2 = buffImg.createGraphics();
		// Tegn billedet til buffImg
		g2.drawImage(image, null, null);
		g2.dispose();
		// Returnér BufferedImage objektet
		return buffImg;
	}
	
	/**
	 * Opretter et todimensionalt integer-tile map ud fra et billede 
	 * @param image Det billede, der skal fortolkes
	 * @return Todimensionalt int-array med integer-repræsentationer af de identificerede objekter
	 */
	public static int[][] createTileMap(final BufferedImage image) {
		// Hent bredde og højde for input-billede
		int width = image.getWidth();
		int height = image.getHeight();
		// Initialisér output-array
		int[][] output = new int[height][];
		// Løb igennem hver vandret linje
		for(int j = 0; j < height; j++) {
			// Initialisér linjens tiles
			output[j] = new int[width];
			// Løb igennem hvert punkt på linjen
			for(int i = 0; i < width; i++) {
				// Hent RGB-værdier
				int[] rgb = getRGBvals(image.getRGB(i, j));
				
				if (obstacleThresholds.checkThresholds(rgb)) {
					// Hvid farve: Forhindring
					output[j][i] = OBSTACLE;
				} else if (cakeThresholds.checkThresholds(rgb)) {
					// Rød farve: Kage
					output[j][i] = CAKE;
				} else if (robotNThresholds.checkThresholds(rgb)) {
					// Grøn farve: Robot N
					output[j][i] = ROBOTN;
				} else if (robotSThresholds.checkThresholds(rgb)) {
					// Blå farve: Robot S
					output[j][i] = ROBOTS;
				} else {
					// Ikke identificeret objekt
					output[j][i] = BACKGROUND;
				}
			}
		}
		return output;
	}
	
	/**
	 * Returnerer farve-komponenterne ud fra en RGB-integer
	 * @param rgbval Farven, der skal deles
	 * @return int array med farvekomponenterne i rækkefølgen R G B
	 */
	public static int[] getRGBvals(int rgbval) {
		// Initialisér output-array
		int[] output = new int[3];
		// Beregn RGB-komponenter vha. bit-shifting og bitwise AND
		output[2] = rgbval & 0xFF;
		output[1] = (rgbval >> 8) & 0xFF;
		output[0] = (rgbval >> 16) & 0xFF;
		return output;
	}
	
	/**
	 * Finder alle objekter af en bestemt type i tilemap og returnerer disses center-koordinater
	 * @param tilemap Tilemap, der skal undersøges
	 * @param type Den type objekter der skal findes
	 * @return Liste over objekternes center-koordinater
	 */
	public static ArrayList<ICake> findCakes(int[][] tilemap, int type) {
		// matrix til at holde styr på behandlede pixels
		int[][] foundmap = new int[tilemap.length][];
		// Initialisér alle felter i matricen
		for(int i = 0; i < foundmap.length; i++) {
			foundmap[i] = new int[tilemap[i].length];
			java.util.Arrays.fill(foundmap[i], BACKGROUND);
		}
		// Retur-objekt med liste over positioner for objekter
		ArrayList<ICake> cakes = new ArrayList<ICake>();
		// Løb igennem alle pixels
		for(int y = 0; y < tilemap.length; y++) {
			for(int x = 0; x < tilemap[y].length; x++) {
				// Tjek, om pixlen er af den ønskede type, samt at den ikke er behandlet
				if (tilemap[y][x] == type && foundmap[y][x] == BACKGROUND) {
					// Liste til de punkter, objektet består af
					ArrayList<int[]> returnCoords = new ArrayList<int[]>();
					// Benyt examineTilemap til at finde alle sammenhængende punkter af typen fra dette punkt
					examineTilemap(tilemap, new int[] {y, x}, type, foundmap, returnCoords);
					
					// Acceptér ikke for små objekter
					if (returnCoords.size() > MIN_OBJECT_SIZE) {
						// Beregn gennemsnitspositionen og føj denne til retur-listen
						int sumX = 0;
						int sumY = 0;
						Iterator<int[]> itr = returnCoords.iterator();
						while(itr.hasNext()) {
							int[] pos = itr.next();
							sumX += pos[0];
							sumY += pos[1];
						}
						int cakeY = sumY/returnCoords.size();
						int cakeX = sumX/returnCoords.size();
						cakes.add(new Cake(cakeY,cakeX));
					}
				}
			}
		}
		return cakes;
	}
	
	/**
	 * Finder alle objekter af to givne typer i tilemap og returnerer disses center-koordinater samt vinkel med lodret
	 * @param tilemap Tilemap, der skal undersøges
	 * @param type1 Typen af forreste farve på objektet
	 * @param type2 Typen af bageste farve på objektet
	 * @return Liste over objekter med center-koordinater og vinkel
	 * @throws Exception Såfremt ikke begge farver er repræsenteret
	 */
	public static ArrayList<IRobot> findRobots(int[][] tilemap, int type1, int type2) {
		ArrayList<IRobot> robots = new ArrayList<IRobot>();
		try {
			int[] posN = findCakes(tilemap, type1).get(0).getPos();
			int[] posS = findCakes(tilemap, type2).get(0).getPos();
			int[] coords = new int[] {(posN[0]+posS[0])/2,(posN[1]+posS[1])/2};
			double a = posN[1]-posS[1];
			double b = posN[0]-posS[0];
			double angle = 0.0;
			double c = Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
			angle = Math.asin(b/c);
			if (a > 0 && b < 0) {
				// 3. kvadrant
				angle = -Math.PI-angle;
			} else if (a > 0 && b >= 0) {
				// 4. kvadrant
				angle = Math.PI-angle;
			}
			robots.add(new Robot(coords[0],coords[1],angle));
		} catch (IndexOutOfBoundsException e) {
			System.out.println("No robot found.");
		}
		return robots;
	}
	
	/**
	 * Arbejder rekursivt ud fra et punkt og finder alle sammenhængendende pixels af en bestemt type
	 * @param tilemap Det tilemap, der skal undersøges
	 * @param pos Startpositionen
	 * @param type Den værdi der søges efter i tilemap
	 * @param foundmap Matrix over fundne/behandlede felter i tilemap
	 * @param returnCoords Liste over matchende koordinater, som er i den undersøgte mængde
	 */
	private static void examineTilemap(int[][] tilemap, int[] pos, int type, int[][] foundmap, ArrayList<int[]> returnCoords) {
		// Debug-udskrifter
//		System.out.println("examineTilemap:");
//		System.out.println("\ttilemap.length: " + tilemap.length);
//		System.out.println("\ttilemap[pos[0]].length: " + tilemap[pos[0]].length);
//		System.out.println("\tpos: (" + pos[0] + "," + pos[1] + ")");
//		System.out.println("\ttype: " + type);
//		System.out.println("\tfoundmap.length: " + foundmap.length);
//		System.out.println("\treturnCoords.size(): " + returnCoords.size());
		
		// Tjek punktet til højre
		if (pos[1]+1 < tilemap[pos[0]].length && foundmap[pos[0]][pos[1]+1] == 0 && tilemap[pos[0]][pos[1]+1] == type) {
			// Markér punktet som besøgt
			foundmap[pos[0]][pos[1]+1] = 1;
			// Beregn næste position, der skal undersøges
			int[] newpos = new int[] {pos[0],pos[1]+1};
			// Tilføj koordinater til listen
			returnCoords.add(newpos);
			// Kør metoden rekursivt
			examineTilemap(tilemap, newpos, type, foundmap, returnCoords);
		}
		
		// Tjek punktet under
		if (pos[0]+1 < tilemap.length && foundmap[pos[0]+1][pos[1]] == 0 && tilemap[pos[0]+1][pos[1]] == type) {
			// Markér punktet som besøgt
			foundmap[pos[0]+1][pos[1]] = 1;
			// Beregn næste position, der skal undersøges
			int[] newpos = new int[] {pos[0]+1,pos[1]};
			// Tilføj koordinater til listen
			returnCoords.add(newpos);
			// Kør metoden rekursivt
			examineTilemap(tilemap, newpos, type, foundmap, returnCoords);
		}
		
		// Tjek punktet til venstre
		if (pos[1]-1 >= 0 && foundmap[pos[0]][pos[1]-1] == 0 && tilemap[pos[0]][pos[1]-1] == type) {
			// Markér punktet som besøgt
			foundmap[pos[0]][pos[1]-1] = 1;
			// Beregn næste position, der skal undersøges
			int[] newpos = new int[] {pos[0],pos[1]-1};
			// Tilføj koordinater til listen
			returnCoords.add(newpos);
			// Kør metoden rekursivt
			examineTilemap(tilemap, newpos, type, foundmap, returnCoords);
		}
		
		// Tjek punktet over
		if (pos[0]-1 >= 0 && foundmap[pos[0]-1][pos[1]] == 0 && tilemap[pos[0]-1][pos[1]] == type) {
			// Markér punktet som besøgt
			foundmap[pos[0]-1][pos[1]] = 1;
			// Beregn næste position, der skal undersøges
			int[] newpos = new int[] {pos[0]-1,pos[1]};
			// Tilføj koordinater til listen
			returnCoords.add(newpos);
			// Kør metoden rekursivt
			examineTilemap(tilemap, newpos, type, foundmap, returnCoords);
		}
	}
	
	/**
	 * Finder grænserne for banen og returnerer et array med 4 værdier; top, venstre, bund og højre grænse-index
	 * @param tilemap Det tilemap, der skal findes grænser på
	 * @return Array med grænserne i rækkefølgen [top, venstre, bund, højre]
	 */
	public static int[] findBounds(int[][] tilemap) {
		// Grænser sættes umiddelbart til input-tilemap's grænser
		int[] bounds = new int[] {0,0,tilemap.length-1,tilemap[0].length-1};
		
		// Bestemmer antallet af obstacle-værdier der skal være på en linje, som betegnes som grænse
		int bordercount = 5;
		
		// Variable brugt ved bestemmelse af grænser
		int y;
		int x;
		int count;
		boolean found;
		
		// Initialisér variable til første grænse: top
		y = bounds[0];
		x = bounds[1];
		count = 0;
		found = false;
		
		// Undersøg gennem y-værdierne
		while (!found && y < bounds[2]) {
			count = 0;
			// Iterér gennem x-værdierne
			for (x=bounds[1]; x < bounds[3]; x++) {
				// Hvis positionen er en obstacle, tæl op
				if (tilemap[y][x] == OBSTACLE) {
					count++;
				}
				
				// Hvis der er fundet nok obstacle-positioner, sættes found og grænse - og for-løkken termineres
				if (count > bordercount) {
					found = true;
					bounds[0] = y;
					break;
				}
			}
			y++;
		}
		
		// Initialisér variable til anden grænse: venstre
		y = bounds[0];
		x = bounds[1];
		count = 0;
		found = false;
		
		// Undersøg gennem x-værdierne 
		while (!found && x < bounds[3]) {
			count = 0;
			// Iterér gennem y-værdierne
			for (y=bounds[0]; y < bounds[2]; y++) {
				// Hvis positionen er en obstacle, tæl op
				if (tilemap[y][x] == OBSTACLE) {
					count++;
				}
				
				// Hvis der er fundet nok obstacle-positioner, sættes found og grænse - og for-løkken termineres
				if (count > bordercount) {
					found = true;
					bounds[1] = x;
					break;
				}
			}
			x++;
		}
		
		// Initialisér variable til tredje grænse: bund
		y = bounds[2];
		x = bounds[1];
		count = 0;
		found = false;
		
		// Undersøg gennem y-værdierne 
		while (!found && y > bounds[0]) {
			count = 0;
			// Iterér gennem x-værdierne
			for (x=bounds[1]; x < bounds[3]; x++) {
				// Hvis positionen er en obstacle, tæl op
				if (tilemap[y][x] == OBSTACLE) {
					count++;
				}
				
				// Hvis der er fundet nok obstacle-positioner, sættes found og grænse - og for-løkken termineres
				if (count > bordercount) {
					found = true;
					bounds[2] = y;
					break;
				}
			}
			y--;
		}
		
		// Initialisér variable til anden grænse: venstre
		y = bounds[0];
		x = bounds[3];
		count = 0;
		found = false;
		
		// Undersøg gennem x-værdierne 
		while (!found && x > bounds[1]) {
			count = 0;
			// Iterér gennem y-værdierne
			for (y=bounds[0]; y < bounds[2]; y++) {
				// Hvis positionen er en obstacle, tæl op
				if (tilemap[y][x] == OBSTACLE) {
					count++;
				}
				
				// Hvis der er fundet nok obstacle-positioner, sættes found og grænse - og for-løkken termineres
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
	 * Beskærer tilemap til givne grænser
	 * @param tilemap Tilemap, der skal beskæres
	 * @param bounds Grænser, der skal beskæres efter: (top,venstre,bund,højre)
	 * @return Beskåret tilemap
	 */
	public static int[][] cropTilemap(int[][] tilemap, int[] bounds) {
		// Hent grænserne ud til enkelte variable
		int ymin = bounds[0];
		int xmin = bounds[1];
		int ymax = bounds[2];
		int xmax = bounds[3];
		
		// Beregn ny højde og bredde af tilemap
		int height = ymax-ymin+1;
		int width = xmax-xmin+1; 
		
		// Initialisér output
		int[][] output = new int[height][];
		
		// Iterér over den nye højde (koordinater i det nye map)
		for(int y = 0; y < height; y++) {
			output[y] = new int[width];
			// Iterér over den nye bredde
			for(int x = 0; x < width; x++) {
				// Kopiér værdier fra udsnit af oprindeligt tilemap til beskåret map 
				output[y][x] = tilemap[ymin+y][xmin+x];
			}
		}
		
		return output;
	}
	
	/**
	 * Returnerer en grafik over det tolkede billede til visuel debug
	 * @param tilemap Det tilemap, der skal repræsenteres
	 * @return Billede af tilemap
	 */
	public static BufferedImage createTileImage(ILocations locations) {
		int[][] tilemap = locations.getTilemap();
		int[][] obstaclemap = locations.getObstaclemap();
		BufferedImage tileImage = new BufferedImage(tilemap[0].length, tilemap.length, BufferedImage.TYPE_INT_ARGB);
		System.out.println("Dimensions: " + tileImage.getHeight() + "x" + tileImage.getWidth());
		// Iterér over alle vandrette linjer
		for(int i = 0; i < tilemap.length; i++) {
			// Iterér over alle punkter
			for(int j = 0; j < tilemap[i].length; j++) {
				int rgb;
				// Sæt RGB-værdi til output-billede ud fra tilemap værdi. To første hex-værdier er alpha-værdi: RGB = 0xAARRGGBB.
				switch (tilemap[i][j]) {
					case CAKE:
						rgb = 0xFFFF0000;
						break;
					case OBSTACLE:
						rgb = 0xFFFFFFFF;
						break;
					case ROBOTN:
						rgb = 0xFF00FF00;
						break;
					case ROBOTS:
						rgb = 0xFF0000FF;
						break;
					default:
						rgb = 0xFF000000;
				}
				// Hvis pixel er bufferzonegrænse (værdi 1 i obstaclemap), sæt farve
				if (obstaclemap[i][j] == 1) {
					rgb = 0xFFFFFF00;
				}
				// Sæt pixel-værdi
				tileImage.setRGB(j, i, rgb);
//				System.out.print(tilemap[i][j]); // Til udskrift af tilemap i console
			}
//			System.out.println(); // Til udskrift af tilemap i console
		}
		
		List<ICake> cakes = locations.getCakes();
		Iterator<ICake> cakeItr = cakes.iterator();
		while(cakeItr.hasNext()) {
			ICake cake = cakeItr.next();
			System.out.println("Object at (" + cake.getY() + "," + cake.getX() + ").");
			tileImage.setRGB(cake.getY(), cake.getX(), 0xFF00FFFF);
			tileImage.setRGB(cake.getY()+1, cake.getX(), 0xFF00FFFF);
			tileImage.setRGB(cake.getY()-1, cake.getX(), 0xFF00FFFF);
			tileImage.setRGB(cake.getY(), cake.getX()+1, 0xFF00FFFF);
			tileImage.setRGB(cake.getY(), cake.getX()-1, 0xFF00FFFF);
		}
		
		List<IRobot> robots = locations.getRobots();
		Iterator<IRobot> robotItr = robots.iterator();
		while(robotItr.hasNext()) {
			IRobot robot = robotItr.next();
			System.out.println("Robot at (" + robot.getY() + "," + robot.getX() + ") angle: " + robot.getAngle() + "rad = " + robot.getAngle()*180/Math.PI + " deg");
			tileImage.setRGB(robot.getY(),robot.getX(), 0xFF00FF00);
			tileImage.setRGB(robot.getY()+1,robot.getX(), 0xFF00FF00);
			tileImage.setRGB(robot.getY()-1,robot.getX(), 0xFF00FF00);
			tileImage.setRGB(robot.getY(),robot.getX()+1, 0xFF00FF00);
			tileImage.setRGB(robot.getY(),robot.getX()-1, 0xFF00FF00);
		}
		
		return tileImage;
	}
	
	/**
	 * Opret et tile map over forhindringer med bufferzone omkring
	 * @param tilemap
	 * @return
	 */
	public static int[][] createObstacleMap(int[][] tilemap) {
		// Initialisér output-map
		int[][] obstaclemap = new int[tilemap.length][];
		for (int i = 0; i < obstaclemap.length; i++) {
			obstaclemap[i] = new int[tilemap[0].length];
			java.util.Arrays.fill(obstaclemap[i], 0);
		}
		
		// Iterér over positioner i tilemap
		for (int y = 0; y < tilemap.length; y++) {
			for (int x = 0; x < tilemap[y].length; x++) {
				if (tilemap[y][x] == OBSTACLE) {
					// Sæt nuværende pixel til bufferzone-værdi, hvis der er en obstacle her
					obstaclemap[y][x] = obstacleBuffer;
					// Iterér i +/- bufferzone pixels omkring pixlen
					for (int dy = -obstacleBuffer; dy < obstacleBuffer; dy++) {
						if (!(y+dy < 0 || y+dy >= obstaclemap.length)) { // Sikrer, at der ikke gøres noget uden for array-grænser
							for (int dx = -obstacleBuffer; dx < obstacleBuffer; dx++) {
								if (!(x+dx < 0 || x+dx >= obstaclemap[y+dy].length)) { // Sikrer, at der ikke gøres noget uden for array-grænser
									// Udregn "power" ud fra afstanden til den fundne obstacle-pixel. "power" er bufferzone-værdien minus afstanden.
									int power = obstacleBuffer-(int)Math.floor(Math.sqrt(dy*dy+dx*dx));
									// Den beregnede power skal kun påføres en pixel, hvis den er større end tidligere værdi.
									if (power > obstaclemap[y+dy][x+dx]) {
										obstaclemap[y+dy][x+dx] = power;
									}
								}
							}
						}
					}
				}
			}
		}
		return obstaclemap;
	}
	
	/**
	 * Sæt grænseværdier for bestemmelse af objekttyper
	 * @param type Talrepræsentationen på typen. Kan være ImageProcessor.OBSTACLE, ImageProcessor.CAKE, ImageProcessor.ROBOTN eller ImageProcessor.ROBOTS
	 * @param thresholds Thresholds-objekt med de nye grænseværdier
	 */
	public static void setThresholds(int type, Thresholds thresholds) {
		if (type == OBSTACLE) {
			obstacleThresholds = thresholds;
		} else if (type == CAKE) {
			cakeThresholds = thresholds;
		} else if (type == ROBOTN) {
			robotNThresholds = thresholds;
		} else if (type == ROBOTS) {
			robotSThresholds = thresholds;
		}
	}
	
	/**
	 * Sætter bufferzonen omkring forhindringer
	 * @param bufferZone Ny zonebredde
	 */
	public static void setObstacleBufferZone(int bufferZone) {
		if (bufferZone >= 0) {
			obstacleBuffer = bufferZone;
		}
	}
	
	/**
	 * Gennemfører fuld analyse af input-billede, og returnerer et Locations objekt
	 */
	public static ILocations examineImage(BufferedImage imageSource, boolean debug) {
		int[][] tilemap = createTileMap(imageSource);
		int[] tileMapBounds = findBounds(tilemap);
		tilemap = cropTilemap(tilemap, tileMapBounds);
		int[][] obstaclemap = createObstacleMap(tilemap);
		ArrayList<ICake> cakes = findCakes(tilemap, 1);
		ArrayList<IRobot> robots = findRobots(tilemap, 3, 4);
		ILocations locations = new Locations(tilemap,obstaclemap,cakes,robots);
		if (debug) {
			locations.setSourceImage(imageSource);
			BufferedImage tileImage = createTileImage(locations);
			locations.setTileImage(tileImage);
		}
		return locations;
	}
}