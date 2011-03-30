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
 * Bearbejdning af billeder. Generering af tile map, identifikation af bane-gr�nser, bestemmelse af objekters position og robotters position samt retning
 * @author PC
 *
 */
public class ImageProcessor {
	/**
	 * Tom konstrukt�r. Metoderne bruges statisk
	 */
	public ImageProcessor() {
	}
	
	/**
	 * Metode til at skalere Image-objekter
	 * @param inputImage Billedet, der skal skaleres
	 * @param newWidth Ny bredde i pixels
	 * @param newHeight Ny h�jde i pixels
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
		// Return�r BufferedImage fra RenderedOp
		return renderedOp.getAsBufferedImage();
	}
	
	/**
	 * Metode til at konvertere et Image-objekt til et BufferedImage objekt
	 * @param image Image-objektet, der skal konverteres
	 * @param type Farvemodel for billedet
	 * @return BufferedImage-version af input-billedet
	 */
	public static BufferedImage toBufferedImage(final Image image, final int type) {
		// Hvis input-billedet allerede er et BufferedImage, g�r intet
		if (image instanceof BufferedImage)
			return (BufferedImage) image;
		// Opret BufferedImage og Graphics
		final BufferedImage buffImg = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
		final Graphics2D g2 = buffImg.createGraphics();
		// Tegn billedet til buffImg
		g2.drawImage(image, null, null);
		g2.dispose();
		// Return�r BufferedImage objektet
		return buffImg;
	}
	
	/**
	 * Opretter et todimensionalt integer-tile map ud fra et billede 
	 * @param image Det billede, der skal fortolkes
	 * @return Todimensionalt int-array med integer-repr�sentationer af de identificerede objekter
	 */
	public static int[][] createTileMap(final BufferedImage image) {
		// Hent bredde og h�jde for input-billede
		int width = image.getWidth();
		int height = image.getHeight();
		// Initialis�r output-array
		int[][] output = new int[height][];
		// L�b igennem hver vandret linje
		for(int j = 0; j < height; j++) {
			// Initialis�r linjens tiles
			output[j] = new int[width];
			// L�b igennem hvert punkt p� linjen
			for(int i = 0; i < width; i++) {
				// Hent RGB-v�rdier
				int[] rgb = getRGBvals(image.getRGB(i, j));
				if (rgb[0] > 150 && rgb[1] > 150 && rgb[2] > 150) {
					// Hvid farve: Forhindring
					output[j][i] = 2;
				} else if (rgb[0] < 70 && rgb[1] > 70 && rgb[2] < 90) {
					// Gr�n farve: Robot N
					output[j][i] = 3;
				} else if (rgb[0] < 80 && rgb[1] < 80 && rgb[2] > 110) {
					// Bl� farve: Robot S
					output[j][i] = 4;
				} else if (rgb[0] > 100 && rgb[1] < 25 && rgb[2] < 25) {
					// R�d farve: Kage
					output[j][i] = 1;
				} else {
					// Ikke identificeret objekt
					output[j][i] = 0;
				}
			}
		}
		return output;
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
	 * Finder alle objekter af en bestemt type i tilemap og returnerer disses center-koordinater
	 * @param tilemap Tilemap, der skal unders�ges
	 * @param type Den type objekter der skal findes
	 * @return Liste over objekternes center-koordinater
	 */
	public static ArrayList<ICake> findCakes(int[][] tilemap, int type) {
		// matrix til at holde styr p� behandlede pixels
		int[][] foundmap = new int[tilemap.length][];
		// Initialis�r alle felter i matricen
		for(int i = 0; i < foundmap.length; i++) {
			foundmap[i] = new int[tilemap[i].length];
			java.util.Arrays.fill(foundmap[i], 0);
		}
		// Retur-objekt med liste over positioner for objekter
		ArrayList<ICake> cakes = new ArrayList<ICake>();
		// L�b igennem alle pixels
		for(int y = 0; y < tilemap.length; y++) {
			for(int x = 0; x < tilemap[y].length; x++) {
				// Tjek, om pixlen er af den �nskede type, samt at den ikke er behandlet
				if (tilemap[y][x] == type && foundmap[y][x] == 0) {
					// Liste til de punkter, objektet best�r af
					ArrayList<int[]> returnCoords = new ArrayList<int[]>();
					// Benyt examineTilemap til at finde alle sammenh�ngende punkter af typen fra dette punkt
					examineTilemap(tilemap, new int[] {y, x}, 1, foundmap, returnCoords);
					
					// Beregn gennemsnitspositionen og f�j denne til retur-listen
					int sumX = 0;
					int sumY = 0;
					Iterator<int[]> itr = returnCoords.iterator();
					while(itr.hasNext()) {
						int[] pos = itr.next();
						sumX += pos[0];
						sumY += pos[1];
					}
					if (returnCoords.size() > 0) {
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
	 * @param tilemap Tilemap, der skal unders�ges
	 * @param type1 Typen af forreste farve p� objektet
	 * @param type2 Typen af bageste farve p� objektet
	 * @return Liste over objekter med center-koordinater og vinkel
	 * @throws Exception S�fremt ikke begge farver er repr�senteret
	 */
	public static ArrayList<IRobot> findRobots(int[][] tilemap, int type1, int type2) {
		// matrix til at holde styr p� behandlede pixels
		int[][] foundmap = new int[tilemap.length][];
		// Initialis�r alle felter i matricen
		for(int i = 0; i < foundmap.length; i++) {
			foundmap[i] = new int[tilemap[i].length];
			java.util.Arrays.fill(foundmap[i], 0);
		}
		// Retur-objekt med liste over positioner for objekter
		ArrayList<IRobot> robots = new ArrayList<IRobot>();
		// L�b igennem alle pixels
		for(int y = 0; y < tilemap.length; y++) {
			for(int x = 0; x < tilemap[y].length; x++) {
				// Tjek, om pixlen er af den �nskede type, samt at den ikke er behandlet
				if ((tilemap[y][x] == type1 || tilemap[y][x] == type2) && foundmap[y][x] == 0) {
					// Liste til de punkter, objektet best�r af
					ArrayList<int[]> return1Coords = new ArrayList<int[]>();
					ArrayList<int[]> return2Coords = new ArrayList<int[]>();
					// Benyt examineTilemap til at finde alle sammenh�ngende punkter af typen fra dette punkt
					examineTilemap(tilemap, new int[] {y, x}, 3, 4, foundmap, return1Coords, return2Coords);
					
					// Unders�g, at begge farver er repr�senteret
					if (return1Coords.size() > 0 && return2Coords.size() > 0) {
						// Summ�r hver farve
						int sum1X = 0;
						int sum1Y = 0;
						int sum2X = 0;
						int sum2Y = 0;
						Iterator<int[]> itr1 = return1Coords.iterator();
						Iterator<int[]> itr2 = return2Coords.iterator();
						while(itr1.hasNext()) {
							int[] pos1 = itr1.next();
							sum1X += pos1[0];
							sum1Y += pos1[1];
						}
						while(itr2.hasNext()) {
							int[] pos2 = itr2.next();
							sum2X += pos2[0];
							sum2Y += pos2[1];
						}
						
						// Beregn middel-koordinater for hver af de to farver 
						int[] coordsN = new int[] {sum1Y/return1Coords.size(),sum1X/return1Coords.size()};
						int[] coordsS = new int[] {sum2Y/return2Coords.size(),sum2X/return2Coords.size()};
						
						// Beregn vinklen mellem linjen gennem punkterne og lodret (positiv vinkel MED uret)
						double a = coordsN[1]-coordsS[1];
						double b = coordsN[0]-coordsS[0];
						double c = Math.sqrt(Math.pow(a,2) + Math.pow(b,2));
						double angle = Math.asin(b/c);
						System.out.println("Angle: " + angle + "rad, " + angle*180/Math.PI + "deg");
						
						// F�j robot til retur-liste
						int robotY = (coordsN[0]+coordsS[0])/2;
						int robotX = (coordsN[1]+coordsS[1])/2;
						robots.add(new Robot(robotY,robotX,angle));
					} else {
						System.out.println("Incomplete robot!");
					}
				}
			}
		}
		
		return robots;
	}
	
	/**
	 * Arbejder rekursivt ud fra et punkt og finder alle sammenh�ngendende pixels af en bestemt type
	 * @param tilemap Det tilemap, der skal unders�ges
	 * @param pos Startpositionen
	 * @param type Den v�rdi der s�ges efter i tilemap
	 * @param foundmap Matrix over fundne/behandlede felter i tilemap
	 * @param returnCoords Liste over matchende koordinater, som er i den unders�gte m�ngde
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
		
		// Tjek punktet til h�jre
		if (pos[1]+1 < tilemap[pos[0]].length && foundmap[pos[0]][pos[1]+1] == 0 && tilemap[pos[0]][pos[1]+1] == type) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]][pos[1]+1] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0],pos[1]+1};
			// Tilf�j koordinater til listen
			returnCoords.add(newpos);
			// K�r metoden rekursivt
			examineTilemap(tilemap, newpos, type, foundmap, returnCoords);
		}
		
		// Tjek punktet under
		if (pos[0]+1 < tilemap.length && foundmap[pos[0]+1][pos[1]] == 0 && tilemap[pos[0]+1][pos[1]] == type) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]+1][pos[1]] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0]+1,pos[1]};
			// Tilf�j koordinater til listen
			returnCoords.add(newpos);
			// K�r metoden rekursivt
			examineTilemap(tilemap, newpos, type, foundmap, returnCoords);
		}
		
		// Tjek punktet til venstre
		if (pos[1]-1 >= 0 && foundmap[pos[0]][pos[1]-1] == 0 && tilemap[pos[0]][pos[1]-1] == type) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]][pos[1]-1] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0],pos[1]-1};
			// Tilf�j koordinater til listen
			returnCoords.add(newpos);
			// K�r metoden rekursivt
			examineTilemap(tilemap, newpos, type, foundmap, returnCoords);
		}
		
		// Tjek punktet over
		if (pos[0]-1 >= 0 && foundmap[pos[0]-1][pos[1]] == 0 && tilemap[pos[0]-1][pos[1]] == type) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]-1][pos[1]] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0]-1,pos[1]};
			// Tilf�j koordinater til listen
			returnCoords.add(newpos);
			// K�r metoden rekursivt
			examineTilemap(tilemap, newpos, type, foundmap, returnCoords);
		}
	}
	
	/**
	 * Arbejder rekursivt ud fra et punkt og finder alle sammenh�ngendende pixels af to bestemte typer
	 * @param tilemap Det tilemap, der skal unders�ges
	 * @param pos Startpositionen
	 * @param type1 Den ene v�rdi der s�ges efter i tilemap
	 * @param type2 Den anden v�rdi der s�ges efter i tilemap
	 * @param foundmap Matrix over fundne/behandlede felter i tilemap
	 * @param returnCoords1 Liste over matchende koordinater af type1, som er i den unders�gte m�ngde
	 * @param returnCoords2 Liste over matchende koordinater af type2, som er i den unders�gte m�ngde
	 */
	private static void examineTilemap(int[][] tilemap, int[] pos, int type1, int type2, int[][] foundmap, ArrayList<int[]> returnCoords1, ArrayList<int[]> returnCoords2) {
		// Debug-udskrifter
//		System.out.println("examineTilemap:");
//		System.out.println("\ttilemap.length: " + tilemap.length);
//		System.out.println("\ttilemap[pos[0]].length: " + tilemap[pos[0]].length);
//		System.out.println("\tpos: (" + pos[0] + "," + pos[1] + ")");
//		System.out.println("\ttype1: " + type1);
//		System.out.println("\ttype2: " + type2);
//		System.out.println("\tfoundmap.length: " + foundmap.length);
//		System.out.println("\treturnCoords1.size(): " + returnCoords1.size());
//		System.out.println("\treturnCoords2.size(): " + returnCoords2.size());
		
		// Tjek punktet til h�jre
		if (pos[1]+1 < tilemap[pos[0]].length && foundmap[pos[0]][pos[1]+1] == 0 && (tilemap[pos[0]][pos[1]+1] == type1 || tilemap[pos[0]][pos[1]+1] == type2)) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]][pos[1]+1] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0],pos[1]+1};
			// Tilf�j koordinater til listen
			if (tilemap[newpos[0]][newpos[1]] == type1) {
				returnCoords1.add(newpos);
			} else {
				returnCoords2.add(newpos);
			}
			// K�r metoden rekursivt
			examineTilemap(tilemap, newpos, type1, type2, foundmap, returnCoords1, returnCoords2);
		}
		
		// Tjek punktet under
		if (pos[0]+1 < tilemap.length && foundmap[pos[0]+1][pos[1]] == 0 && (tilemap[pos[0]+1][pos[1]] == type1 || tilemap[pos[0]+1][pos[1]] == type2)) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]+1][pos[1]] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0]+1,pos[1]};
			// Tilf�j koordinater til listen
			if (tilemap[newpos[0]][newpos[1]] == type1) {
				returnCoords1.add(newpos);
			} else {
				returnCoords2.add(newpos);
			}
			// K�r metoden rekursivt
			examineTilemap(tilemap, newpos, type1, type2, foundmap, returnCoords1, returnCoords2);
		}
		
		// Tjek punktet til venstre
		if (pos[1]-1 >= 0 && foundmap[pos[0]][pos[1]-1] == 0 && (tilemap[pos[0]][pos[1]-1] == type1 || tilemap[pos[0]][pos[1]-1] == type2)) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]][pos[1]-1] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0],pos[1]-1};
			// Tilf�j koordinater til listen
			if (tilemap[newpos[0]][newpos[1]] == type1) {
				returnCoords1.add(newpos);
			} else {
				returnCoords2.add(newpos);
			}
			// K�r metoden rekursivt
			examineTilemap(tilemap, newpos, type1, type2, foundmap, returnCoords1, returnCoords2);
		}
		
		// Tjek punktet over
		if (pos[0]-1 >= 0 && foundmap[pos[0]-1][pos[1]] == 0 && (tilemap[pos[0]-1][pos[1]] == type1 || tilemap[pos[0]-1][pos[1]] == type2)) {
			// Mark�r punktet som bes�gt
			foundmap[pos[0]-1][pos[1]] = 1;
			// Beregn n�ste position, der skal unders�ges
			int[] newpos = new int[] {pos[0]-1,pos[1]};
			// Tilf�j koordinater til listen
			if (tilemap[newpos[0]][newpos[1]] == type1) {
				returnCoords1.add(newpos);
			} else {
				returnCoords2.add(newpos);
			}
			// K�r metoden rekursivt
			examineTilemap(tilemap, newpos, type1, type2, foundmap, returnCoords1, returnCoords2);
		}
	}
	
	/**
	 * Finder gr�nserne for banen og returnerer et array med 4 v�rdier; top, venstre, bund og h�jre gr�nse-index
	 * @param tilemap Det tilemap, der skal findes gr�nser p�
	 * @return Array med gr�nserne i r�kkef�lgen [top, venstre, bund, h�jre]
	 */
	public static int[] findBounds(int[][] tilemap) {
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
				if (tilemap[y][x] == 2) {
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
				if (tilemap[y][x] == 2) {
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
				if (tilemap[y][x] == 2) {
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
				if (tilemap[y][x] == 2) {
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
	 * Besk�rer tilemap til givne gr�nser
	 * @param tilemap Tilemap, der skal besk�res
	 * @param bounds Gr�nser, der skal besk�res efter: (top,venstre,bund,h�jre)
	 * @return Besk�ret tilemap
	 */
	public static int[][] cropTilemap(int[][] tilemap, int[] bounds) {
		// Hent gr�nserne ud til enkelte variable
		int ymin = bounds[0];
		int xmin = bounds[1];
		int ymax = bounds[2];
		int xmax = bounds[3];
		
		// Beregn ny h�jde og bredde af tilemap
		int height = ymax-ymin+1;
		int width = xmax-xmin+1; 
		
		// Initialis�r output
		int[][] output = new int[height][];
		
		// Iter�r over den nye h�jde (koordinater i det nye map)
		for(int y = 0; y < height; y++) {
			output[y] = new int[width];
			// Iter�r over den nye bredde
			for(int x = 0; x < width; x++) {
				// Kopi�r v�rdier fra udsnit af oprindeligt tilemap til besk�ret map 
				output[y][x] = tilemap[ymin+y][xmin+x];
			}
		}
		
		return output;
	}
	
	/**
	 * Returnerer en grafik over det tolkede billede til visuel debug
	 * @param tilemap Det tilemap, der skal repr�senteres
	 * @return Billede af tilemap
	 */
	public static BufferedImage createTileImage(ILocations locations) {
		int[][] tilemap = locations.getTilemap();
		BufferedImage tileImage = new BufferedImage(tilemap[0].length, tilemap.length, BufferedImage.TYPE_INT_ARGB);
		System.out.println("Dimensions: " + tileImage.getHeight() + "x" + tileImage.getWidth());
		// Iter�r over alle vandrette linjer
		for(int i = 0; i < tilemap.length; i++) {
			// Iter�r over alle punkter
			for(int j = 0; j < tilemap[i].length; j++) {
				int rgb;
				// S�t RGB-v�rdi til output-billede ud fra tilemap v�rdi. To f�rste hex-v�rdier er alpha-v�rdi: RGB = 0xAARRGGBB.
				switch (tilemap[i][j]) {
					case 1:
						rgb = 0xFFFF0000;
						break;
					case 2:
						rgb = 0xFFFFFFFF;
						break;
					default:
						rgb = 0xFF000000;
				}
				// S�t pixel-v�rdi
				tileImage.setRGB(j, i, rgb);
//				System.out.print(map[i][j]); // Til udskrift af tilemap i console
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
	 * Gennemf�rer fuld analyse af input-billede, og returnerer et Locations objekt
	 */
	public static ILocations examineImage(BufferedImage imageSource, boolean debug) {
		int[][] tilemap = createTileMap(imageSource);
		int[] tileMapBounds = findBounds(tilemap);
		tilemap = cropTilemap(tilemap, tileMapBounds);
		ArrayList<ICake> cakes = findCakes(tilemap, 1);
		ArrayList<IRobot> robots = findRobots(tilemap, 3, 4);
		ILocations locations = new Locations(tilemap,cakes,robots);
		if (debug) {
			locations.setSourceImage(imageSource);
			BufferedImage tileImage = createTileImage(locations);
			locations.setTileImage(tileImage);
		}
		return locations;
	}
}