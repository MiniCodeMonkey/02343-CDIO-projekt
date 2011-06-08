package command.test;

import command.Commando;
import command.interfaces.IControl;

public class TestBertaBTConnection {

	public static void main(String[] args) {
		
		try {
			
			System.out.println("BERTA program running");
			
			
			Commando com = new Commando(0);
			IControl[] control = com.getControls();
			
			// using berta's control:
			control[0].move(true);
			wait(8000);
			
			control[0].stop();
			
			
//			control[0].disconnect();	
			
			System.out.println("BERTA program finished");
			
			wait(30000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void wait(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}