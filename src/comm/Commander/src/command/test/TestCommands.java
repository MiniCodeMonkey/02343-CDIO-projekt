package command.test;

import java.io.IOException;

import command.BertaCommando;
import command.interfaces.IControl;

public class TestCommands {

	public static void main(String[] args) {
		BertaCommando bertaCommando = new BertaCommando();
		IControl bertaControl = bertaCommando.getControl();
		
		try {
			bertaControl.openClaw();
			wait(3000);
			bertaControl.stopClaw();
			wait(1000);
			bertaControl.move(100, false);
			wait(1000);
			bertaControl.stop();
			wait(1000);
			bertaControl.closeClaw();
			wait(3000);
			bertaControl.stopClaw();
			wait(1000);
			bertaControl.move(50, true);
			wait(1000);
			bertaControl.stop();
			wait(1000);
			bertaControl.left(100);
			wait(1000);
			bertaControl.stop();
			wait(1000);
			bertaControl.openClaw();
			wait(3000);
			bertaControl.stopClaw();
			wait(1000);
			bertaControl.closeClaw();
			wait(3000);
			bertaControl.stopClaw();
			wait(1000);
			bertaControl.move(100, false);
			wait(1000);
			bertaControl.stop();
			wait(1000);
			bertaControl.openClaw();
			wait(3000);
			bertaControl.stopClaw();
			wait(1000);
			bertaControl.move(50, true);
			wait(1000);
			bertaControl.stop();
			wait(1000);
			bertaControl.openClaw();
			wait(3000);
			bertaControl.stopClaw();
			wait(1000);
		} catch (IOException e) {
			e.printStackTrace();
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