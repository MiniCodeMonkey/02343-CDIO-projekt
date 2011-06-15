package command.claw;

import lejos.nxt.Motor;
import lejos.nxt.remote.NXTCommand;
import lejos.nxt.remote.RemoteMotor;

public class ClawSettingDTO {

	private float limit;
	private NXTCommand commander;
	private RemoteMotor clawMotor;
	
	public ClawSettingDTO(NXTCommand command) {
		setCommander(command);
		setLimit(175);
		setClawMotor(Motor.B);
	}
	public ClawSettingDTO(NXTCommand command,float limit) {
		setCommander(command);
		setLimit(limit);
	}
	public ClawSettingDTO(NXTCommand command,float limit,RemoteMotor clawMotor) {
		setCommander(command);
		setLimit(limit);
		setClawMotor(clawMotor);
	}

	public void setLimit(float limit) {
		this.limit = limit;
	}

	public float getLimit() {
		return limit;
	}
	public void setCommander(NXTCommand commander) {
		this.commander = commander;
	}
	public NXTCommand getCommander() {
		return commander;
	}
	public void setClawMotor(RemoteMotor clawMotor) {
		this.clawMotor = clawMotor;
	}
	public RemoteMotor getClawMotor() {
		return clawMotor;
	}
	
}
