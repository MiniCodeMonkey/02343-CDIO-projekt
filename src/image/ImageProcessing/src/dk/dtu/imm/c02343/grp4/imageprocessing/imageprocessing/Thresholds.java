package dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing;

public class Thresholds {
	private int[] min;
	private int[] max;
	
	/**
	 * Konstrukt�r til standard-v�rdier: Ingen thresholds
	 */
	public Thresholds() {
		// Erkl�r min- og max-arrays med v�rdierne
		min = new int[] {0, 0, 0};
		max = new int[] {255, 255, 255};
	}
	
	/**
	 * Konstrukt�r, som s�tter alle minimum- og maksimum-v�rdier for gr�nserne. V�rdierne er 8-bit - i omr�det 0-255.
	 * @param minR Minimum for R�D
	 * @param minG Minimum for GR�N
	 * @param minB Minimum for BL�
	 * @param maxR Maksimum for R�D
	 * @param maxG Maksimum for GR�N
	 * @param maxB Maksimum for BL�
	 * @throws ColorValueException Hvis en gr�nsev�rdi er uden for det tilladte omr�de; 0-255 (8-bit).
	 */
	public Thresholds(int minR, int minG, int minB, int maxR, int maxG, int maxB) throws ColorValueException {
		// Benyt setThresholds(...) til at s�tte v�rdier.
		setThresholds(minR, minG, minB, maxR, maxG, maxB);
	}
	
	/**
	 * S�tter mindstev�rdierne for farvekomponenterne. V�rdierner er 8-bit - i omr�det 0-255.
	 * @param minR Minimum for R�D
	 * @param minG Minimum for GR�N
	 * @param minB Minimum for BL�
	 * @throws ColorValueException Hvis en gr�nsev�rdi er uden for det tilladte omr�de; 0-255 (8-bit).
	 */
	public void setMin(int minR, int minG, int minB) throws ColorValueException {
		if (minR < 0 || minG < 0 || minB < 0 || minR > 255 || minG > 255 || minB > 255) {
			throw new ColorValueException("Farvev�rdi er uden for det tilladte. Hver farvev�rdi skal v�re i omr�det 0-255. Givne v�rdier: R " + minR + ", G " + minG + ", B " + minB + ".");
		}
		// S�t nye v�rdier i min-array
		min = new int[] {minR, minG, minB};
	}
	
	/**
	 * S�tter maksimumv�rdierne for farvekomponenterne. V�rdierner er 8-bit - i omr�det 0-255.
	 * @param maxR Maksimum for R�D
	 * @param maxG Maksimum for GR�N
	 * @param maxB Maksimum for BL�
	 * @throws ColorValueException Hvis en gr�nsev�rdi er uden for det tilladte omr�de; 0-255 (8-bit).
	 */
	public void setMax(int maxR, int maxG, int maxB) throws ColorValueException {
		// Tjek, om v�rdierne er uden for det tilladte omr�de.
		if (maxR < 0 || maxG < 0 || maxB < 0 || maxR > 255 || maxG > 255 || maxB > 255) {
			throw new ColorValueException("Farvev�rdi er uden for det tilladte. Hver farvev�rdi skal v�re i omr�det 0-255. Givne v�rdier: R " + maxR + ", G " + maxG + ", B " + maxB + ".");
		}
		// S�t nye v�rdier i max-array
		max = new int[] {maxR, maxG, maxB};
	}
	
	/**
	 * S�tter alle minimum- og maksimum-v�rdier for gr�nserne. V�rdierne er 8-bit - i omr�det 0-255.
	 * @param minR Minimum for R�D
	 * @param minG Minimum for GR�N
	 * @param minB Minimum for BL�
	 * @param maxR Maksimum for R�D
	 * @param maxG Maksimum for GR�N
	 * @param maxB Maksimum for BL�
	 * @throws ColorValueException Hvis en gr�nsev�rdi er uden for det tilladte omr�de; 0-255 (8-bit).
	 */
	public void setThresholds(int minR, int minG, int minB, int maxR, int maxG, int maxB) throws ColorValueException {
		setMin(minR, minG, minB);
		setMax(maxR, maxG, maxB);
	}
	
	/**
	 * Henter minimumsgr�nser som int-array
	 * @return Minimumsgr�nser p� formen {R,G,B}
	 */
	public int[] getMin() {
		return min;
	}
	
	/**
	 * Henter maksimumgr�nser som int-array
	 * @return Maksimumgr�nser p� formen {R,G,B}
	 */
	public int[] getMax() {
		return max;
	}
	
	/**
	 * Henter minimumsgr�nse for r�d
	 * @return Minimumsgr�nse for r�d
	 */
	public int getMinR() {
		return min[0];
	}
	
	/**
	 * Henter minimumsgr�nse for gr�n
	 * @return Minimumsgr�nse for gr�n
	 */
	public int getMinG() {
		return min[1];
	}
	
	/**
	 * Henter minimumsgr�nse for bl�
	 * @return Minimumsgr�nse for bl�
	 */
	public int getMinB() {
		return min[2];
	}
	
	/**
	 * Henter maksimumsgr�nse for r�d
	 * @return Maksimumgr�nse for r�d
	 */
	public int getMaxR() {
		return min[0];
	}
	
	/**
	 * Henter maksimumsgr�nse for gr�n
	 * @return Maksimumgr�nse for gr�n
	 */
	public int getMaxG() {
		return min[0];
	}
	
	/**
	 * Henter maksimumsgr�nse for bl�
	 * @return Maksimumgr�nse for bl�
	 */
	public int getMaxB() {
		return min[0];
	}
	
	/**
	 * Tjekker, om givne RGB-v�rdier er inden for gr�nsev�rdierne
	 * @param rgb Integer-array med {R,G,B} v�rdier, som skal unders�ges
	 * @return Sand, hvis v�rdierne er inden for gr�nserne
	 */
	public boolean checkThresholds(int[] rgb) {
		return (min[0] <= rgb[0] && rgb[0] <= max[0] && min[1] <= rgb[1] && rgb[1] <= max[1] && min[2] <= rgb[2] && rgb[2] <= max[2]);   
	}
}
