package command.test;

import command.Commando;
import command.interfaces.IControl;

public class TestCommands {

	public static void main(String[] args) {
		
		try {
			
			Commando com = new Commando(1);
			IControl[] control = com.getControls();
			
			// using berta's control:
			control[1].move(false);
			wait(500);
			
			control[1].move(true);
			wait(500);
			control[1].stop();
			
			control[1].disconnect();
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
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