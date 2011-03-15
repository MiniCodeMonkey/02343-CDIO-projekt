package start;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import gui.BasicControlGui;
import connector.Connector;


public class Start {


	public static void main(String[] args) {
		System.out.println("Basic controls | PC -> NXT");
		
		
		try {
			new BasicControlGui(Connector.getInstance()).setVisible(true);
		} catch (UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		} catch (IllegalAccessException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		} catch (InstantiationException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}
		
		

	}

}
