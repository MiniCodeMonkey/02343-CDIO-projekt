package command.rmi;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ITestControl extends Remote {
	int CLAW_LIMIT = 175;
	int DEFAULT_CLAW_SPEED = 20;
	
	int DEFAULT_MOVE_SPEED = 20;
	int DEFAULT_TURN_SPEED = 15;	
	
	/**
	 * S�tter motor A & C til en bestemt fart og starter dem. Stopper ikke f�r metoden {@code stop()} k�res
	 * @param speed - hastighed (0-100)
	 * @param reverse - bagl�ns
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void move(int speed, boolean reverse) throws IOException, RemoteException;
	
	/**
	 * @return
	 * @throws IOException 
	 */
	int getBatteryLevel() throws IOException;
}