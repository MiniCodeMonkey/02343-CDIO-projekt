package command.impl;

import java.io.IOException;
import java.io.Serializable;

import bluetooth.constants.Constants;

import command.interfaces.IControl;

import lejos.nxt.remote.NXTCommand;
import lejos.nxt.remote.NXTProtocol;
import lejos.nxt.Sound;
import lejos.nxt.SoundSensor;

/**
 * @author Morten Hulvej & Terkel Brix & Per Clausen
 */
public class Control implements IControl, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6101771069854621278L;
	private NXTCommand commander;
	private boolean inLeftMotion = false;
	private boolean inRightMotion = false;
	private boolean clawMoving;

	public static final int STATE_BARCKWARDS_DEC = -3;
	public static final int STATE_BARCKWARDS_STEADY = -2;
	public static final int STATE_BARCKWARDS_ACC = -1;
	public static final int STATE_STOPPED = 0;
	public static final int STATE_FORWARD_ACC = 1;
	public static final int STATE_FORWARD_STEADY = 2;
	public static final int STATE_FORWARD_DEC = 3;
	private int state = STATE_STOPPED;
	private static int STEPSIZE_ACC =3;
	private static int STEPSIZE_DEC =5;
	private static int SOFTSTART_THRESHOLD = 15;
	private static int SOFTSTOP_THRESHOLD =15;
	private static int sleepTime = 10;


	public Control(NXTCommand commander) {
		this.commander = commander;
	}


	@Override
	public void move(int speed, boolean reverse) throws IOException {
		System.out.println("MOOOOOOOOOOOOOOOOOVEEEEEEEEEEEEEEEEEEEEEEEEEEE!");
		if (isInForwardMotion() && !reverse)
		{
			System.out.println("RETURNING !reverse");
			return;
		}
			
		if (isInBackwardMotion() && reverse)
		{
			System.out.println("RETURNING reverse");
			return;
		}
			
		setInRightMotion(false);
		setInLeftMotion(false);

		if(reverse){
			System.out.println("Moving backwards");
			if(speed>=SOFTSTART_THRESHOLD)softStartBackwards(speed);
			commander.setOutputState(0, (byte) speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			commander.setOutputState(2, (byte) speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			System.out.println("PowerSetPoint: "+commander.getOutputState(0).powerSetpoint);
			state=STATE_BARCKWARDS_STEADY;
		}else{
			System.out.println("Moving forwards");
			if(speed>=SOFTSTART_THRESHOLD)softStartForwards(speed);
			commander.setOutputState(0, (byte) -speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			commander.setOutputState(2, (byte) -speed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			state=STATE_FORWARD_STEADY;
			System.out.println("PowerSetPoint: "+commander.getOutputState(0).powerSetpoint);
		}

	}

	
	/**
	 * Metode til langsom at accelerer FREMAD op til speed.
	 * @param speed 
	 * @throws IOException
	 */
	private void softStartForwards(int speed) throws IOException{
		System.out.println("SoftStart Forwards");
		state=STATE_FORWARD_ACC;
		for(int i =0; i<speed;i=i+STEPSIZE_ACC){
			if(state!=STATE_FORWARD_ACC)return;
			commander.setOutputState(0, (byte) -i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			commander.setOutputState(2, (byte) -i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metode til at langomst at accelerer BAGUD op til speed.
	 * @param speed
	 * @throws IOException
	 */
	private void softStartBackwards(int speed) throws IOException{
		System.out.println("SoftStart Backwards");
		state=STATE_BARCKWARDS_ACC;
		for(int i =0; i<speed;i=i+STEPSIZE_ACC){
			if(state!=STATE_BARCKWARDS_ACC)return;

			commander.setOutputState(0, (byte) i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			commander.setOutputState(2, (byte) i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Laver et "blødt" stop, udfra motor 0's powerSetPoint. Decellerer til 0.
	 * @throws IOException
	 */
	private void softStop() throws IOException{
		System.out.println("SoftStop");
		int currentSpeed = commander.getOutputState(0).powerSetpoint;
		if(isInBackwardMotion()){ //Movinbackwards
			state=STATE_BARCKWARDS_DEC;
			for(int i = currentSpeed ; i>0 ; i=i-STEPSIZE_DEC){
				if(state!=STATE_BARCKWARDS_DEC)return;
				commander.setOutputState(0, (byte) i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
				commander.setOutputState(2, (byte) i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);			
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}else if(isInForwardMotion()){ //MovingForwards
			state=STATE_FORWARD_DEC;
			for(int i=currentSpeed; i>0;i=i-STEPSIZE_DEC){
				if(state!=STATE_FORWARD_DEC)return;
				commander.setOutputState(0, (byte) -i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
				commander.setOutputState(2, (byte) -i, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);			
				System.out.println("PowerSetPoint: "+commander.getOutputState(0).powerSetpoint);
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
		setInRightMotion(false);
		System.out.println("moving left");

		commander.setOutputState(0, (byte)turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte)-turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		//		System.out.println("TURNING: left");
		setInLeftMotion(true);
		state = STATE_STOPPED;
	}

	@Override
	public void right(int turnSpeed) throws IOException {
		if (isInRightMotion())
			return;
		setInLeftMotion(false);
		System.out.println("moving right");

		commander.setOutputState(0, (byte)-turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		commander.setOutputState(2, (byte)turnSpeed, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, 0);
		//		System.out.println("TURNING: right");
		setInRightMotion(true);
		state = STATE_STOPPED;
	}

	@Override
	public void stop() throws IOException {
		if (isStopped() && !isInLeftMotion() && !isInRightMotion())
			return;
		if(!(isInLeftMotion() || isInRightMotion()) && commander.getOutputState(0).powerSetpoint >= SOFTSTOP_THRESHOLD) softStop();
		state=STATE_STOPPED;
		commander.setOutputState(0, (byte) 0, 0, 0, 0, 0, 0);
		commander.setOutputState(1, (byte) 0, 0, 0, 0, 0, 0);
		commander.setOutputState(2, (byte) 0, 0, 0, 0, 0, 0);
		System.out.println("stopping");
		setInLeftMotion(false);
		setInRightMotion(false);
		
		//commander.playTone(400, 1000);
		commander.playSoundFile("d_.wav", false);
		

	}

	/**
	 * @return the commander
	 */
	public NXTCommand getCommander() {
		return commander;
	}

	/**
	 * @return {@code true} hvis robotten er i en fremadrettet bevægelse<br>
	 * altså i STATES større end 0(og ikke har fået en {@code stop()} kommando endnu)
	 * <br>
	 * {@code false} hvis robotten står stille
	 */
	public boolean isInForwardMotion() {
		return state>0;
	}


	/**
	 * @return {@code true} hvis robotten er i en bagudrette bevægelse<br>
	 * altså i STATES mindre end 0(og ikke har fået en {@code stop()} kommando endnu)
	 * <br>
	 * {@code false} hvis robotten står stille
	 */
	public boolean isInBackwardMotion() {
		return state<0;
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


	
	/**
	 * @return {@code true} hvis robotten står stille<br>
	 * altså i STATES lig 0.
	 * <br>
	 * {@code false} hvis robotten  ikke står stille (altså er i fremad- eller bagudrettetbevægelse)
	 */
	public boolean isStopped() {
		return state==0;
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
		return false;
	}

	@Override
	public void disconnect() throws IOException {
		commander.close();
	}


	@Override
	public boolean isBerta(){
		
		try {
			System.out.println(commander.getDeviceInfo().bluetoothAddress);
			if (commander.getDeviceInfo().bluetoothAddress.equalsIgnoreCase(bluetooth.constants.Constants.BertaAdrReturnAdr) )
					return true;
			else
				return false;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

}