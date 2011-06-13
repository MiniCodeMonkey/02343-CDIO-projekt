package comm;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class runtimeTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Desktop.getDesktop().browse(new URI("www.google.dk"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
