package command.impl;

import java.io.IOException;

import lejos.nxt.Sound;
import lejos.nxt.remote.NXTCommand;

public class ControlSensor extends Control {

	public ControlSensor(NXTCommand commander) {
		super(commander);
	}
	
	@Override
	public boolean hasCake() {
		
		try {
			int milliVolts = getCommander().getBatteryLevel();
			System.out.println(milliVolts);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
