package control;

import gui.MainFrame;
import gui.comm.CommFrame;
import gui.manualControl.ControlFrame;
import gui.processing.ProcessingFrame;

public class FramePlaceHolder {
	
	private static MainFrame mf;
	private static ProcessingFrame pf;
	private static CommFrame commf;
	private static ControlFrame conf;

	public static synchronized void setMainFrame(MainFrame frame) {
		mf = frame;
	}
	public static synchronized void setProcessingFrame(ProcessingFrame frame) {
		pf = frame;
	}
	public static synchronized void setCommFrame(CommFrame frame) {
		commf = frame;
	}
	public static synchronized void setControlFrame(ControlFrame frame) {
		conf = frame;
	}
	
	public static synchronized MainFrame getMainFrame() {
		return mf;
	}
	public static synchronized ProcessingFrame getProcessingFrame() {
		return pf;
	}
	public static synchronized CommFrame getCommFrame() {
		return commf;
	}
	public static synchronized ControlFrame getControlFrame() {
		return conf;
	}
	
}
