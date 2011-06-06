package exceptions;

import javax.swing.JOptionPane;

public class ErrorMessage {

	public ErrorMessage(String errMessage) {
		JOptionPane.showMessageDialog(null, errMessage, "Fejl", JOptionPane.ERROR_MESSAGE);
	}
}
