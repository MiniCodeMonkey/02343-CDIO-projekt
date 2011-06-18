package dk.dtu.imm.c02343.grp4.imageprocessing.testimageprocessing;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;

/**
 * Panel til visning af et billede
 * @author PC med grundlag i http://www.javafaq.nu/java-example-code-750.html
 *
 */
public class ImagePanel extends JPanel {
	private static final long serialVersionUID = -8300597968476640481L;
	private BufferedImage img;
	
	/**
	 * Constructor, som tager et filnavn som argument. Denne fil benyttes som billede.
	 * @param img Billedfil, som skal vises
	 */
	@Deprecated
    public ImagePanel(String img) {
//        this(new ImageIcon(img));
    }
    
    /**
     * Constructor, som tager et Image-objekt som argument. Dette Image benyttes som billede.
     * @param img Billedet, som skal vises
     */
    public ImagePanel(BufferedImage img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null),img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    /**
     * Metode til at gentegne panelet
     */
	public void paintComponent(Graphics g) {
        g.drawImage(img,0,0,null);
    }
	
	/**
	 * Metode til at ændre det billede, som skal vises
	 * @param img Det nye billede, der skal vises
	 */
	public void setImage(BufferedImage img) {
		this.img = img;
	}
	
	public BufferedImage getImage() {
		return img;
	}
}

// Oprindelig kilde
/*   Swing Hacks
 *   Tips and Tools for Killer GUIs
 * By Joshua Marinacci, Chris Adamson
 *   First Edition June 2005
 *   Series: Hacks
 *   ISBN: 0-596-00907-0
 *   http://www.oreilly.com/catalog/swinghks/
 */