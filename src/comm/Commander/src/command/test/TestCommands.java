package command.test;

import java.io.IOException;

import lejos.nxt.SensorPort;
import lejos.nxt.addon.RCXLightSensor;
import lejos.pc.comm.NXTCommException;

import command.BertaCommando;
import command.interfaces.IControl;

public class TestCommands {

	public static void main(String[] args) {
		BertaCommando bertaCommando = null;
		try {
			bertaCommando = new BertaCommando();
		} catch (NXTCommException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		IControl bertaControl = bertaCommando.getControl();
		
		try {
			
		
			
			
			
			
		} finally {
			bertaCommando.disconnect();
		}
	}
	
	public static void wait(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}