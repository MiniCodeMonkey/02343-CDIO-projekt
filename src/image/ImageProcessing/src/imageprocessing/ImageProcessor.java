package imageprocessing;

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
	 */
	public static int[][] findPickObjects(int[][] tilemap, int type) {
		// matrix til at holde styr p� behandlede pixels
		int[][] foundmap = new int[tilemap.length][];
		// ArrayList til at holde firkant-gr�nser for fundne objekter
		/*ArrayList<int[]> objectBounds = new ArrayList<int[]>();*/
		for(int y = 0; y < tilemap.length; y++) {
			foundmap[y] = new int[tilemap[y].length];
			java.util.Arrays.fill(foundmap[y], 0);
			for(int x = 0; x < tilemap.length; x++) {
				// Tjek, om pixlen er af den �nskede type
				if (tilemap[y][x] == type && foundmap[y][x] == 0) {
/*					// F�lgende bruges til at finde ud af, om pixlen allerede er defineret i et objekt
					boolean objectAlreadyFound = false;
					Iterator<int[]> itr = objectBounds.iterator();
					while(itr.hasNext()) {
						int[] bounds = itr.next();
						if (y >= bounds[0] && y <= bounds[2] && x <= bounds[1] && x >= bounds[3]) {
							objectAlreadyFound = true;
							break;
						}
					}
					if (!objectAlreadyFound) {
						
					}*/
				}
			}
		}
		return null;
	}
	
	private void examineTilemap(int[][] tilemap, int[] pos, int type, int[][] foundmap, ArrayList<int[]> returnCoords) {
		if (pos[0]+1 < tilemap.length && tilemap[pos[0]+1][pos[1]] == type) {
			foundmap[pos[0]+1][pos[1]] = 1;
			int[] newpos = new int[] {pos[0]+1,pos[1]};
			returnCoords.add(newpos);
			examineTilemap(tilemap, newpos, type, foundmap, returnCoords);
		}
		if (pos[1]+1 < tilemap[pos[0]].length && tilemap[pos[0]][pos[1]+1] == type) {
			foundmap[pos[0]][pos[1]+1] = 1;
			int[] newpos = new int[] {pos[0],pos[1]+1};
			returnCoords.add(newpos);
			examineTilemap(tilemap, newpos, type, foundmap, returnCoords);
		}
	}
}