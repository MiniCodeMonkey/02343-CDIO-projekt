package command.interfaces;

import java.io.IOException;

public interface IClawControl {

	/**
	 * Åbner kloen.. <br>
	 * TODO limits
	 * 
	 * @param clawMotor
	 *            - hastighed hvormed kloen åbnes (0-50)
	 * @throws IOException
	 *             hvis forbindelsen blev tabt
	 */
	void openClaw(int clawMotor) throws IOException;

	/**
	 * Lukker kloen.. <br>
	 * TODO limits
	 * 
	 * @param clawMotor
	 *            - hastighed hvormed kloen lukkes (0-50)
	 * @throws IOException
	 *             hvis forbindelsen blev tabt
	 */
	void closeClaw(int clawMotor) throws IOException;
	
	/**
	 * Stopper kloen!<br>
	 * Normalt skal denne metode ikke kaldes, da kloen skulle stoppe af sig selv.
	 * @throws IOException
	 */
	void stopClaw() throws IOException;
	
	boolean isClawMoving();
}
