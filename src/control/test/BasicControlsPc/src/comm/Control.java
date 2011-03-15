package comm;

import java.io.IOException;

import javax.swing.JOptionPane;

import lejos.nxt.Motor;
import lejos.nxt.Sound;
import lejos.nxt.remote.NXTCommand;
import lejos.nxt.remote.NXTProtocol;

/**
 * @author Morten Hulvej
 *
 */
public class Control implements IControl{
	
	private NXTCommand commander;
	private boolean isMoving;
	

	public Control(NXTCommand commander) {
		this.commander = commander;
		setMoving(false);
	}
	

	@Override
	public void move(int speed, boolean reverse) throws IOException {
		if (isMoving())
			return;
		if(reverse)
			speed *= -1;
		
		commander.setOutputState(0, (byte) speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte) speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		setMoving(true);
	}


	@Override
	public void left(int turnSpeed) throws IOException {
		commander.setOutputState(0, (byte)-turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte)turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		System.out.println("TURNING: left");
	}

	@Override
	public void right(int turnSpeed) throws IOException {
		commander.setOutputState(0, (byte)turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte)-turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		System.out.println("TURNING: right");
	}

	@Override
	public void stop() throws IOException {
		commander.setOutputState(0, (byte) 0, 0, 0, 0, 0, 0);
		commander.setOutputState(1, (byte) 0, 0, 0, 0, 0, 0);
		commander.setOutputState(2, (byte) 0, 0, 0, 0, 0, 0);
		setMoving(false);
	}

	@Override
	public void open(int clawMotor) throws IOException {
		commander.setOutputState(1, (byte)-clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
	}

	@Override
	public void close(int clawMotor) throws IOException {
		commander.setOutputState(1, (byte)clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
	}

	/**
	 * @return the commander
	 */
	public NXTCommand getCommander() {
		return commander;
	}


	/**
	 * @param isMoving the isMoving to set
	 */
	public void setMoving(boolean isMoving) {
		this.isMoving = isMoving;
	}


	/**
	 * @return the isMoving
	 */
	public boolean isMoving() {
		return isMoving;
	}

}
