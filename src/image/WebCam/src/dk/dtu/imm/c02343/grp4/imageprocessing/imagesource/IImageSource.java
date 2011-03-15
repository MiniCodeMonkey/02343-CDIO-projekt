package dk.dtu.imm.c02343.grp4.imageprocessing.imagesource;

import java.awt.image.BufferedImage;

/**
 * Kilde til r� billeder
 * Der er taget udgangspunkt i eksemplet WebCam.java fra Fildeling i 02343 CDIO-Projekt F11 gruppen p� CampusNet
 * @author PC
 *
 */
public interface IImageSource {
	/**
	 * Luk ImageSource objektet korrekt ned
	 */
	public void close();
	
	/**
	 * Initialis�r ImageSource objektet
	 */
	public void init();
	
	/**
	 * Hent et billede fra kilden
	 * @return Det nuv�rende billede fra ImageSource kilden
	 */
	public BufferedImage getImage();
}