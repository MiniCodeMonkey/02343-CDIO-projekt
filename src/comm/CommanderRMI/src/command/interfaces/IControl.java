package command.interfaces;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import command.BertaCommando;

/**
 * Interface til primitiv styring af robot (the basics)
 * 
 * @author Morten Hulvej
 *
 */
public interface IControl extends Remote {
	
	int CLAW_LIMIT = 175;
	int DEFAULT_CLAW_SPEED = 15;
	
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
	 * S�tter motor A & C til en default fart ({@code DEFAULT_ROBOT_SPEED}) og starter dem. Stopper ikke f�r metoden {@code stop()} k�res
	 * @param reverse - bagl�ns
	 * @throws IOException
	 */
	void move(boolean reverse) throws IOException, RemoteException;
	/**
	 * Drejer sjovt nok til venstre.
	 * @param turnSpeed - hastighed hvormed der drejes (0-50)
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void left(int turnSpeed) throws IOException, RemoteException;
	/**
	 * Drejer sjovt nok til venstre.
	 * <br>hastighed - ({@code DEFAULT_TURN_SPEED})
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void left() throws IOException, RemoteException;
	/**
	 * Drejer sjovt nok til h�jre.
	 * @param turnSpeed - hastighed hvormed der drejes (0-50)
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void right(int turnSpeed) throws IOException, RemoteException;
	/**Drejer sjovt nok til h�jre.
	 * <br>hastighed - ({@code DEFAULT_TURN_SPEED})
	 * @throws IOException
	 */
	void right() throws IOException, RemoteException;
	/**
	 * Stopper ALLE motorer!
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void stop() throws IOException, RemoteException;
	
	/**
	 * @return
	 * @throws IOException 
	 */
	int getBatteryLevel() throws IOException, RemoteException;
	/**
	 * @return
	 */
	int getDistanceToNearestObject() throws RemoteException;
	
	/**
	 * �bner kloen.. <br>
	 * 
	 * @param speed
	 *            - hastighed hvormed kloen �bnes (0-50)
	 * @throws IOException
	 *             hvis forbindelsen blev tabt
	 */
	void openClaw(int speed) throws IOException, RemoteException;
	void openClaw() throws IOException, RemoteException;
	/**
	 * Lukker kloen.. <br>
	 * 
	 * @param speed
	 *            - hastighed hvormed kloen lukkes (0-50)
	 * @throws IOException
	 *             hvis forbindelsen blev tabt
	 */
	void closeClaw(int speed) throws IOException, RemoteException;
	void closeClaw() throws IOException, RemoteException;
	/**
	 * Stopper kloen!<br>
	 * Normalt skal denne metode ikke kaldes, da kloen skulle stoppe af sig selv.
	 * @throws IOException
	 */
	void stopClaw() throws IOException, RemoteException;
	
	boolean isClawMoving() throws RemoteException;
	
	boolean hasCake() throws RemoteException;
	
	void reverse(int speed, int duration) throws RemoteException;
	
	/** Lukker forbindelsen til Robotten. <br>
	 * - g�r det samme som  {@link BertaCommando}{@code .disconnect()}
	 * @throws IOException
	 */
	void disconnect() throws IOException, RemoteException;
	
}
