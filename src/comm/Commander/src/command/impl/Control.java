package command.impl;

import java.io.IOException;

import command.interfaces.IControl;


import lejos.nxt.remote.NXTCommand;
import lejos.nxt.remote.NXTProtocol;
import lejos.nxt.remote.RemoteBattery;
import lejos.nxt.remote.RemoteMotor;

/**
 * @author Morten Hulvej
 *
 */
public class Control implements IControl{
	
	private NXTCommand commander;
	private boolean inMotion;
	private boolean clawMoving;
	

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
		if (isMoving())
			return;
		commander.setOutputState(0, (byte)-turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte)turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		System.out.println("TURNING: left");
		setMoving(true);
	}

	@Override
	public void right(int turnSpeed) throws IOException {
		if (isMoving())
			return;
		commander.setOutputState(0, (byte)turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte)-turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		System.out.println("TURNING: right");
		setMoving(true);
	}

	@Override
	public void stop() throws IOException {
		commander.setOutputState(0, (byte) 0, 0, 0, 0, 0, 0);
		commander.setOutputState(1, (byte) 0, 0, 0, 0, 0, 0);
		commander.setOutputState(2, (byte) 0, 0, 0, 0, 0, 0);
		setMoving(false);
	}

	@Override
	public void openClaw(int clawMotor) throws IOException {
		if (isClawMoving())
			return;
		commander.setOutputState(1, (byte)clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		setClawMoving(true);
	}

	@Override
	public void closeClaw(int clawMotor) throws IOException {
		if (isClawMoving())
			return;
		commander.setOutputState(1, (byte)-clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		setClawMoving(true);
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
		this.inMotion = isMoving;
	}


	/**
	 * @return the isMoving
	 */
	public boolean isMoving() {
		return inMotion;
	}


	/**
	 * @param clawMoving the clawMoving to set
	 */
	public void setClawMoving(boolean clawMoving) {
		this.clawMoving = clawMoving;
	}


	/**
	 * @return the clawMoving
	 */
	public boolean isClawMoving() {
		return clawMoving;
	}


	@Override
	public int getBatteryLevel() throws IOException {
		int milliVolts = commander.getBatteryLevel();
		
		int result = 9000 - milliVolts;
		result /= 100;
		result = 100 - result;
		return result;
	}


	@Override
	public int getDistanceToNearestObject() {
		System.err.println("Ikke implementeret...");
		return 0;
	}

}
