package command.steering;

import lejos.nxt.Motor;
import lejos.nxt.remote.RemoteMotor;
import lejos.robotics.TachoMotor;

/**Indeholder oplysninger omkring fysiske parametre på BERTA
 * @author Morten Hulvej
 *
 */
public class DriverSettingDTO {

	/* motion */
	
	private float wheelDiameter;
	private float trackWidth;
	private TachoMotor leftMotor, rightMotor;
	private boolean reverse;
	private boolean smoothAcc;
	
	/**
	 * Genererer {@link DriverSettingDTO} med default settings
	 * ; TODO
	 */
	public DriverSettingDTO() {

		/* TODO  
		 * default settings... */
		
		setWheelDiameter(36);
		setTrackWidth(120);
		setLeftMotor(new RemoteMotor(BTConnector.getInstance().getNxtCommand(), Motor.A.getId()));
		setRightMotor(new RemoteMotor(BTConnector.getInstance().getNxtCommand(), Motor.C.getId()));
		reverse = false;
		smoothAcc = false;
		
	}
	
	/**
	 * @return
	 */
	public float getWheelDiameter() {
		return wheelDiameter;
	}
	/**
	 * @param wheelDiameter
	 */
	public void setWheelDiameter(float wheelDiameter) {
		this.wheelDiameter = wheelDiameter;
	}
	/**
	 * @return
	 */
	public float getTrackWidth() {
		return trackWidth;
	}
	/**
	 * @param trackWidth Længde mellem hjul-par
	 */
	public void setTrackWidth(float trackWidth) {
		this.trackWidth = trackWidth;
	}
	/**
	 * @return
	 */
	public TachoMotor getLeftMotor() {
		return leftMotor;
	}
	/**
	 * @param leftMotor 
	 */
	public void setLeftMotor(TachoMotor leftMotor) {
		this.leftMotor = leftMotor;
	}
	/**
	 * @return
	 */
	public TachoMotor getRightMotor() {
		return rightMotor;
	}
	/**
	 * @param rightMotor
	 */
	public void setRightMotor(TachoMotor rightMotor) {
		this.rightMotor = rightMotor;
	}
	/**
	 * @param reverse
	 */
	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}
	/**
	 * @return
	 */
	public boolean isReverse() {
		return reverse;
	}
	/**
	 * @return
	 */
	public boolean isSmoothAcc() {
		return smoothAcc;
	}
	/**
	 * @param smoothAcc
	 */
	public void setSmoothAcc(boolean smoothAcc) {
		this.smoothAcc = smoothAcc;
	}
	
	/* limits */
	
	// ? måske ingen
	
}
