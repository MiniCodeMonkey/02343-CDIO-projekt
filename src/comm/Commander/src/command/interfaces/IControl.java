package command.interfaces;

import java.io.IOException;

/**
 * Interface til primitiv styring af robot (the basics)
 * 
 * @author Morten Hulvej
 *
 */
public interface IControl {
	/**
	 * Sætter motor A & C til en bestemt fart og starter dem. Stopper ikke før metoden {@code stop()} køres
	 * @param speed - hastighed (0-100)
	 * @param reverse - til debug..
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void move(int speed, boolean reverse) throws IOException;
	/**
	 * Drejer sjovt nok til venstre.
	 * @param turnSpeed - hastighed hvormed der drejes (0-50)
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void left(int turnSpeed) throws IOException;
	/**
	 * Drejer sjovt nok til højre.
	 * @param turnSpeed - hastighed hvormed der drejes (0-50)
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void right(int turnSpeed) throws IOException;
	/**
	 * Stopper ALLE motorer!
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void stop() throws IOException;
	
	/**
	 * @return
	 * @throws IOException 
	 */
	int getBatteryLevel() throws IOException;
	/**
	 * @return
	 */
	int getDistanceToNearestObject();
	
	
}
