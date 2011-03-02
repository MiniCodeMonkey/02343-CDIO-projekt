package testimagesource;
import java.awt.*;

import javax.swing.*;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = -8300597968476640481L;
	private Image img;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
        this.img = img;
        Dimension size = new Dimension(img.getWidth(null),img.getHeight(null));
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

	public void paintComponent(Graphics g) {
        g.drawImage(img,0,0,null);
    }
	
	public void setImage(Image img) {
		this.img = img;
	}
}

/*   Swing Hacks
 *   Tips and Tools for Killer GUIs
 * By Joshua Marinacci, Chris Adamson
 *   First Edition June 2005
 *   Series: Hacks
 *   ISBN: 0-596-00907-0
 *   http://www.oreilly.com/catalog/swinghks/
 */