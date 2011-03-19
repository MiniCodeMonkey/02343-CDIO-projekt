package dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.media.jai.Interpolation;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.ScaleDescriptor;

/**
 * Klasse med statiske metoder, som bearbejder billeder
 * @author PC
 *
 */
public class ImageProcessor {
	
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
				if (rgb[0] > 150 && rgb[1] > 150 && rgb[2] > 150) {
					// Hvid farve: Forhindring
					output[j][i] = 2;
				} else if (rgb[0] > 100 && rgb[1] < 25 && rgb[2] < 25) {
					// Rød farve: Kage
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
	 */
	public static ArrayList<int[]> findPickUpObjects(int[][] tilemap, int type) {
		// matrix til at holde styr på behandlede pixels
		int[][] foundmap = new int[tilemap.length][];
		// Initialisér alle felter i matricen
		for(int i = 0; i < foundmap.length; i++) {
			foundmap[i] = new int[tilemap[i].length];
			java.util.Arrays.fill(foundmap[i], 0);
		}
		// Retur-objekt med liste over positioner for objekter
		ArrayList<int[]> objectCoords = new ArrayList<int[]>();
		// Løb igennem alle pixels
		for(int y = 0; y < tilemap.length; y++) {
			for(int x = 0; x < tilemap[y].length; x++) {
				// Tjek, om pixlen er af den ønskede type, samt at den ikke er behandlet
				if (tilemap[y][x] == type && foundmap[y][x] == 0) {
					// Liste til de punkter, objektet består af
					ArrayList<int[]> returnCoords = new ArrayList<int[]>();
					// Benyt examineTilemap til at finde alle sammenhængende punkter af typen fra dette punkt
					examineTilemap(tilemap, new int[] {y, x}, 1, foundmap, returnCoords);
					
					// Beregn gennemsnitspositionen og føj denne til retur-listen
					int sumX = 0;
					int sumY = 0;
					Iterator<int[]> itr = returnCoords.iterator();
					while(itr.hasNext()) {
						int[] pos = itr.next();
						sumX += pos[0];
						sumY += pos[1];
					}
					if (returnCoords.size() > 0) {
						objectCoords.add(new int[] {sumY/returnCoords.size(),sumX/returnCoords.size()});
					}
				}
			}
		}
		return objectCoords;
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
				if (tilemap[y][x] == 2) {
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
				if (tilemap[y][x] == 2) {
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
				if (tilemap[y][x] == 2) {
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
				if (tilemap[y][x] == 2) {
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
}