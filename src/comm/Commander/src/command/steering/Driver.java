package command.steering;

import lejos.robotics.navigation.TachoPilot;

public class Driver extends TachoPilot{

	
	public Driver(DriverSettingDTO settings) {
		super(settings.getWheelDiameter(),settings.getTrackWidth(),settings.getLeftMotor(),settings.getRightMotor(),settings.isReverse());
	}
	
}
