package command.claw;

import java.io.IOException;

import lejos.nxt.Motor;
import lejos.nxt.remote.NXTCommand;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.TachoMotor;

import command.interfaces.IClawControl;

public class ClawDriver implements IClawControl{
	
	private int limit;
	private NXTCommand commander;
	private RemoteMotor motor;

	public ClawDriver(ClawSettingDTO settings) {
		limit = (int) settings.getLimit();
		commander = settings.getCommander();

		System.out.println("CLAWDRIVER: motor "+settings.getClawMotor());
		
		setMotor(settings.getClawMotor());
		
	}

	@Override
	public void openClaw(int clawMotor) throws IOException {
//		if (motor.isMoving())
//			return;
		
		motor.rotateTo(limit,true);
//		commander.setOutputState(1, (byte)clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, (int) limit);
	}

	@Override
	public void closeClaw(int clawMotor) throws IOException {
//		if (motor.isMoving())
//			return;

		motor.rotateTo(limit,true);
//		commander.setOutputState(1, (byte)-clawMotor, NXTProtocol.MOTORON, NXTProtocol.REGULATION_MODE_IDLE, 0, 0, (int) limit);
	}

	@Override
	public void stopClaw() throws IOException {
		motor.stop();
		
	}

	public boolean isClawMoving() {
		return motor.isMoving();
	}

	public void setMotor(RemoteMotor motor) {
		this.motor = motor;
	}

	public TachoMotor getMotor() {
		return motor;
	}
	
}
