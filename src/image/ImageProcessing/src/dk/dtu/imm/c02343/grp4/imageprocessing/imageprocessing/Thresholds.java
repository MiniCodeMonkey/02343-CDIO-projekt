package dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing;

public class Thresholds {
	private int[] min;
	private int[] max;
	
	/**
	 * Konstruktør til standard-værdier: Ingen thresholds
	 */
	public Thresholds() {
		// Erklær min- og max-arrays med værdierne
		min = new int[] {0, 0, 0};
		max = new int[] {255, 255, 255};
	}
	
	/**
	 * Konstruktør, som sætter alle minimum- og maksimum-værdier for grænserne. Værdierne er 8-bit - i området 0-255.
	 * @param minR Minimum for RØD
	 * @param minG Minimum for GRØN
	 * @param minB Minimum for BLÅ
	 * @param maxR Maksimum for RØD
	 * @param maxG Maksimum for GRØN
	 * @param maxB Maksimum for BLÅ
	 * @throws ColorValueException Hvis en grænseværdi er uden for det tilladte område; 0-255 (8-bit).
	 */
	public Thresholds(int minR, int minG, int minB, int maxR, int maxG, int maxB) throws ColorValueException {
		// Benyt setThresholds(...) til at sætte værdier.
		setThresholds(minR, minG, minB, maxR, maxG, maxB);
	}
	
	/**
	 * Sætter mindsteværdierne for farvekomponenterne. Værdierner er 8-bit - i området 0-255.
	 * @param minR Minimum for RØD
	 * @param minG Minimum for GRØN
	 * @param minB Minimum for BLÅ
	 * @throws ColorValueException Hvis en grænseværdi er uden for det tilladte område; 0-255 (8-bit).
	 */
	public void setMin(int minR, int minG, int minB) throws ColorValueException {
		if (minR < 0 || minG < 0 || minB < 0 || minR > 255 || minG > 255 || minB > 255) {
			throw new ColorValueException("Farveværdi er uden for det tilladte. Hver farveværdi skal være i området 0-255. Givne værdier: R " + minR + ", G " + minG + ", B " + minB + ".");
		}
		// Sæt nye værdier i min-array
		min = new int[] {minR, minG, minB};
	}
	
	/**
	 * Sætter maksimumværdierne for farvekomponenterne. Værdierner er 8-bit - i området 0-255.
	 * @param maxR Maksimum for RØD
	 * @param maxG Maksimum for GRØN
	 * @param maxB Maksimum for BLÅ
	 * @throws ColorValueException Hvis en grænseværdi er uden for det tilladte område; 0-255 (8-bit).
	 */
	public void setMax(int maxR, int maxG, int maxB) throws ColorValueException {
		// Tjek, om værdierne er uden for det tilladte område.
		if (maxR < 0 || maxG < 0 || maxB < 0 || maxR > 255 || maxG > 255 || maxB > 255) {
			throw new ColorValueException("Farveværdi er uden for det tilladte. Hver farveværdi skal være i området 0-255. Givne værdier: R " + maxR + ", G " + maxG + ", B " + maxB + ".");
		}
		// Sæt nye værdier i max-array
		max = new int[] {maxR, maxG, maxB};
	}
	
	/**
	 * Sætter alle minimum- og maksimum-værdier for grænserne. Værdierne er 8-bit - i området 0-255.
	 * @param minR Minimum for RØD
	 * @param minG Minimum for GRØN
	 * @param minB Minimum for BLÅ
	 * @param maxR Maksimum for RØD
	 * @param maxG Maksimum for GRØN
	 * @param maxB Maksimum for BLÅ
	 * @throws ColorValueException Hvis en grænseværdi er uden for det tilladte område; 0-255 (8-bit).
	 */
	public void setThresholds(int minR, int minG, int minB, int maxR, int maxG, int maxB) throws ColorValueException {
		setMin(minR, minG, minB);
		setMax(maxR, maxG, maxB);
	}
	
	/**
	 * Henter minimumsgrænser som int-array
	 * @return Minimumsgrænser på formen {R,G,B}
	 */
	public int[] getMin() {
		return min;
	}
	
	/**
	 * Henter maksimumgrænser som int-array
	 * @return Maksimumgrænser på formen {R,G,B}
	 */
	public int[] getMax() {
		return max;
	}
	
	/**
	 * Henter minimumsgrænse for rød
	 * @return Minimumsgrænse for rød
	 */
	public int getMinR() {
		return min[0];
	}
	
	/**
	 * Henter minimumsgrænse for grøn
	 * @return Minimumsgrænse for grøn
	 */
	public int getMinG() {
		return min[1];
	}
	
	/**
	 * Henter minimumsgrænse for blå
	 * @return Minimumsgrænse for blå
	 */
	public int getMinB() {
		return min[2];
	}
	
	/**
	 * Henter maksimumsgrænse for rød
	 * @return Maksimumgrænse for rød
	 */
	public int getMaxR() {
		return min[0];
	}
	
	/**
	 * Henter maksimumsgrænse for grøn
	 * @return Maksimumgrænse for grøn
	 */
	public int getMaxG() {
		return min[0];
	}
	
	/**
	 * Henter maksimumsgrænse for blå
	 * @return Maksimumgrænse for blå
	 */
	public int getMaxB() {
		return min[0];
	}
	
	/**
	 * Tjekker, om givne RGB-værdier er inden for grænseværdierne
	 * @param rgb Integer-array med {R,G,B} værdier, som skal undersøges
	 * @return Sand, hvis værdierne er inden for grænserne
	 */
	public boolean checkThresholds(int[] rgb) {
		return (min[0] <= rgb[0] && rgb[0] <= max[0] && min[1] <= rgb[1] && rgb[1] <= max[1] && min[2] <= rgb[2] && rgb[2] <= max[2]);   
	}
}
