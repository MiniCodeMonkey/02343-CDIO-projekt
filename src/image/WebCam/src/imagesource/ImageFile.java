package imagesource;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * ImageSource-objekt, som returnerer Image-objekter ud fra PNG-billeder i mappen testimages.
 * @author PC
 *
 */
public class ImageFile implements IImageSource {
	private File dir;
	private int index;
	private List<String> files;
	
	/**
	 * Constructor, initialis�rer mappenavn med billeder og variable
	 */
	public ImageFile() {
		this.dir = new File("testimages/");
		this.index = 0;
		files = new ArrayList<String>();
	}
	
	/**
	 * S�ger billedmappe efter PNG-billeder.
	 */
	public void init() {
		// Nulstil index og hent liste over elementer i mappen
		this.index = 0;
		String[] list = dir.list();
		// Tjek alle mappe-elementer, og f�j .png-elementer til
		for(String item : list) {
			if (item.toLowerCase().endsWith(".png")) {
				files.add(dir+"/"+item);
			}
		}
	}
	
	/**
	 * Nulstiller fil-listen
	 */
	public void close() {
		// Nulstil ArrayList
		this.files.clear();
	}

	/**
	 * Henter det n�ste billede p� listen
	 * @return N�ste billede p� listen
	 */
	public Image getImage() {
		// Opret File objekt til billedet, og l�s dette til et Image-objekt
		File file = new File(files.get(index));
		Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Opdat�r t�ller
		index++;
		if (index >= files.size()) index = 0;
		return image;
	}
}