package command.impl;

import java.io.IOException;

import lejos.nxt.remote.NXTCommand;
import lejos.nxt.remote.NXTProtocol;
import command.interfaces.IControl;

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
		if(!reverse)
			speed *= -1;
		
		commander.setOutputState(0, (byte) speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte) speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		
//		String print;
//		if (reverse)	print="Backwards";
//		else	print="Forwards";
//		System.out.println("MOVING: "+print);
		
		setMoving(true);
	}


	@Override
	public void left(int turnSpeed) throws IOException {
		if (isMoving())
			return;
		commander.setOutputState(0, (byte)-turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte)turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
//		System.out.println("TURNING: left");
		setMoving(true);
	}

	@Override
	public void right(int turnSpeed) throws IOException {
		if (isMoving())
			return;
		commander.setOutputState(0, (byte)turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte)-turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
//		System.out.println("TURNING: right");
		setMoving(true);
	}

	@Override
	public void stop() throws IOException {
		commander.setOutputState(0, (byte) 0, 0, 0, 0, 0, 0);
		commander.setOutputState(1, (byte) 0, 0, 0, 0, 0, 0);
		commander.setOutputState(2, (byte) 0, 0, 0, 0, 0, 0);
//		System.out.println("STOPPING");
//		lejos.nxt.Sound.playSoundFile("tires.rso");
		setMoving(false);
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
	 * 
	 * @return {@code true} hvis robotten er i bev�gelse (ikke har f�et en {@code stop()} kommando endnu)
	 * <br>
	 * {@code false} hvis robotten st�r stille
	 */
	public boolean isMoving() {
		return inMotion;
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

	@Override
	public void openClaw(int clawMotor) throws IOException {
//		if (isClawMoving())
//			return;
//		commander.setOutputState(1, (byte)clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, CLAW_LIMIT);
		commander.setOutputState(1, (byte)-clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		clawMoving = true;
	}

	@Override
	public void closeClaw(int clawMotor) throws IOException {
//		if (isClawMoving())
//			return;
//		commander.setOutputState(1, (byte)-clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, CLAW_LIMIT);
		commander.setOutputState(1, (byte)clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		clawMoving = true;
	}

	@Override
	public void stopClaw() throws IOException {
		commander.setOutputState(1, (byte) 0, 0, 0, 0, 0, 0);
		clawMoving = false;
	}


	@Override
	public boolean isClawMoving() {
		return clawMoving;
	}	
		
	@Override
	public void move(boolean reverse) throws IOException {
		move(DEFAULT_MOVE_SPEED, reverse);
		
	}


	@Override
	public void left() throws IOException {
		left(DEFAULT_TURN_SPEED);
		
	}


	@Override
	public void right() throws IOException {
		right(DEFAULT_TURN_SPEED);
		
	}


	@Override
	public void openClaw() throws IOException {
		openClaw(DEFAULT_CLAW_SPEED);
		
	}


	@Override
	public void closeClaw() throws IOException {
		closeClaw(DEFAULT_CLAW_SPEED);
		
	}

	@Override
	public void reverse(int speed, int duration) {
		try {
			move(speed, true);
			try {
				Thread.sleep(duration);
			} catch (InterruptedException e) {
				
			}
			stop();
//			
//			for (int i = 0; i <= duration; i += 700)
//			{
//				lejos.nxt.Sound.playTone(600, 700);
//				Thread.sleep(700);
//			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
	}
}
