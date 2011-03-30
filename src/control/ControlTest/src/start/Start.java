package start;

import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import bluetooth.impl.BTConnector;

import gui.BasicControlGui;


public class Start {


	public static void main(String[] args) {
		System.out.println("Basic controls | PC -> NXT");
		BasicControlGui gui;
		
		try {
			gui = new BasicControlGui(BTConnector.getInstance());
			gui.setVisible(true);
			
			while(true){
				Thread.sleep(10000);
				gui.updateReadings();
			}
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
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Fejl", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}
		
		

	}

}