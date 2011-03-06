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
	 * Constructor, initialisérer mappenavn med billeder og variable
	 */
	public ImageFile() {
		this.dir = new File("testimages/");
		this.index = 0;
		files = new ArrayList<String>();
	}
	
	/**
	 * Søger billedmappe efter PNG-billeder.
	 */
	public void init() {
		// Nulstil index og hent liste over elementer i mappen
		this.index = 0;
		String[] list = dir.list();
		// Tjek alle mappe-elementer, og føj .png-elementer til
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
	 * Henter det næste billede på listen
	 * @return Næste billede på listen
	 */
	public Image getImage() {
		// Opret File objekt til billedet, og læs dette til et Image-objekt
		File file = new File(files.get(index));
		Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Opdatér tæller
		index++;
		if (index >= files.size()) index = 0;
		return image;
	}
}