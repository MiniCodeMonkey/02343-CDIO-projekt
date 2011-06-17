package command.impl;

import java.io.IOException;
import java.io.Serializable;

import command.interfaces.IControl;

import lejos.nxt.Sound;
import lejos.nxt.remote.NXTCommand;
import lejos.nxt.remote.NXTProtocol;

/**
 * @author Morten Hulvej
 *
 */
public class Control implements IControl, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6101771069854621278L;
	private NXTCommand commander;
	private boolean inForwardMotion = false;
	private boolean inBackwardMotion = false;
	private boolean inLeftMotion = false;
	private boolean inRightMotion = false;
	private boolean stopped = true;
	private boolean clawMoving;

	private static int sleepTime = 50;

	public Control(NXTCommand commander) {
		this.commander = commander;
	}


	@Override
	public void move(int speed, boolean reverse) throws IOException {
		if (isInForwardMotion() && !reverse)
			return;
		if (isInBackwardMotion() && reverse)
			return;
		setInRightMotion(false);
		setInLeftMotion(false);
		setStopped(false);

		if(reverse){
			if(speed>=15)softStartBackwards(speed);
			System.out.println("Moving backwards");
			commander.setOutputState(0, (byte) speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			commander.setOutputState(2, (byte) speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			setInBackwardMotion(true);
		}else{
			if(speed>=15)softStartForwards(speed);
			System.out.println("Moving forwards");
			commander.setOutputState(0, (byte) -speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			commander.setOutputState(2, (byte) -speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			setInForwardMotion(true);
		}




		//		String print;
		//		if (reverse)	print="Backwards";
		//		else	print="Forwards";
		//		System.out.println("MOVING: "+print);

	}

	private void softStartForwards(int speed) throws IOException{
		System.out.println("SoftStart Forwards");
		for(int i =0; i<speed;i++){
			commander.setOutputState(0, (byte) -i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			commander.setOutputState(2, (byte) -i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void softStartBackwards(int speed) throws IOException{
		System.out.println("SoftStart Backwards");
		for(int i =0; i<speed;i++){
			commander.setOutputState(0, (byte) i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			commander.setOutputState(2, (byte) i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void softStop() throws IOException{
		System.out.println("SoftStop");
		int currentSpeed = commander.getOutputState(0).powerSetpoint;
		if(isInBackwardMotion()){
			for(int i=currentSpeed; i==0;i--){
				commander.setOutputState(0, (byte) i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
				commander.setOutputState(1, (byte) i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
				commander.setOutputState(2, (byte) i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);			
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		if(isInForwardMotion()){
			for(int i=currentSpeed; i==0;i--){
				commander.setOutputState(0, (byte) -i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
				commander.setOutputState(1, (byte) -i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
				commander.setOutputState(2, (byte) -i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);			
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}


	@Override
	public void left(int turnSpeed) throws IOException {
		if (isInLeftMotion())
			return;
		setInBackwardMotion(false);
		setInForwardMotion(false);
		setInRightMotion(false);
		setStopped(false);
		System.out.println("moving left");

		commander.setOutputState(0, (byte)turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte)-turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		//		System.out.println("TURNING: left");
		setInLeftMotion(true);
	}

	@Override
	public void right(int turnSpeed) throws IOException {
		if (isInRightMotion())
			return;
		setInBackwardMotion(false);
		setInForwardMotion(false);
		setInLeftMotion(false);
		setStopped(false);
		System.out.println("moving right");

		commander.setOutputState(0, (byte)-turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte)turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		//		System.out.println("TURNING: right");
		setInRightMotion(true);
	}

	@Override
	public void stop() throws IOException {
		if (isStopped())
			return;
		softStop();
//		commander.setOutputState(0, (byte) 0, 0, 0, 0, 0, 0);
//		commander.setOutputState(1, (byte) 0, 0, 0, 0, 0, 0);
//		commander.setOutputState(2, (byte) 0, 0, 0, 0, 0, 0);
		System.out.println("stopping");

		setInBackwardMotion(false);
		setInForwardMotion(false);
		setInLeftMotion(false);
		setInRightMotion(false);

		setStopped(true);
	}



	/**
	 * @return the commander
	 */
	public NXTCommand getCommander() {
		return commander;
	}


	/**
	 * 
	 * @return {@code true} hvis robotten er i bevægelse (ikke har fået en {@code stop()} kommando endnu)
	 * <br>
	 * {@code false} hvis robotten står stille
	 */
	public boolean isInForwardMotion() {
		return inForwardMotion;
	}


	/**
	 * @param inForwardMoving the isMoving to set
	 */
	public void setInForwardMotion(boolean inForwardMoving) {
		this.inForwardMotion = inForwardMoving;
	}


	public boolean isInBackwardMotion() {
		return inBackwardMotion;
	}


	public void setInBackwardMotion(boolean inBackwardMotion) {
		this.inBackwardMotion = inBackwardMotion;
	}


	public boolean isInLeftMotion() {
		return inLeftMotion;
	}


	public void setInLeftMotion(boolean inLeftMotion) {
		this.inLeftMotion = inLeftMotion;
	}


	public boolean isInRightMotion() {
		return inRightMotion;
	}


	public void setInRightMotion(boolean inRightMotion) {
		this.inRightMotion = inRightMotion;
	}


	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}


	public boolean isStopped() {
		return stopped;
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
		//		commander.setOutputState(1, (byte)-clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, CLAW_LIMIT);
		commander.setOutputState(1, (byte)-clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		clawMoving = true;
	}

	@Override
	public void closeClaw(int clawMotor) throws IOException {
		//		if (isClawMoving())
		//			return;
		//		commander.setOutputState(1, (byte)clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, CLAW_LIMIT);
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

	@Deprecated
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
			e.printStackTrace();
			//		} catch (InterruptedException e) {
			//			e.printStackTrace();
		}
	}


	@Override
	public boolean hasCake() {

		Sound.playTone(440, 500);


		return false;
	}


	@Override
	public void disconnect() throws IOException {
		commander.close();
	}

}
