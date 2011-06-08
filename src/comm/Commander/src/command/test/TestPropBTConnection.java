package command.test;

import java.io.IOException;

import command.Commando;
import command.interfaces.IControl;

public class TestPropBTConnection {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("PROP program running");
		
		Commando com = new Commando(1);
		IControl[] control = com.getControls();
		
		
//		control[1].move(true);
//		wait(2000);
		int rounds = 0;
		
			control[1].left();
			wait(5000);
			control[1].right();
			wait(5000);
		
		
		control[1].stop();
		
		System.out.println("PROP program finished");
//		wait(30000);
		
		control[1].disconnect();
	}
	public static void wait(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
