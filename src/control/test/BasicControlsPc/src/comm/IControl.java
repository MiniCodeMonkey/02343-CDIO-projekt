package comm;

import java.io.IOException;

/**
 * Midlertidig interface til styring af robot (the basics)
 * 
 * @author Morten Hulvej
 *
 */
public interface IControl {
	/**
	 * S�tter motor A & C til en bestemt fart og starter dem. Stopper ikke f�r metoden {@code stop()} k�res
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
	 * Drejer sjovt nok til h�jre.
	 * @param turnSpeed - hastighed hvormed der drejes (0-50)
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void right(int turnSpeed) throws IOException;
	/**
	 * Stopper ALLE motorer!
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void stop() throws IOException;
	/**�bner kloen.. IKKE TESTET!
	 * <br>
	 * TODO limits
	 * @param clawMotor - hastighed hvormed kloen �bnes (0-50)
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void open(int clawMotor) throws IOException;
	/**Lukker kloen.. IKKE TESTET!
	 * <br>
	 * TODO limits
	 * @param clawMotor - hastighed hvormed kloen lukkes (0-50)
	 * @throws IOException hvis forbindelsen blev tabt
	 */
	void close(int clawMotor) throws IOException;
	
	
}
