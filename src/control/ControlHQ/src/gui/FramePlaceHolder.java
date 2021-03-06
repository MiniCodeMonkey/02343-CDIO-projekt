package gui;

import gui.comm.CommFrame;
import gui.debug.DebugFrame;
import gui.info.MiniInfoFrame;
import gui.manualControl.ControlFrame;
import gui.path.PathToleranceFrame;
import gui.processing.ImageThresholdsFrame;
import gui.processing.ProcessingFrame;
import gui.speed.SpeedFrame;

public class FramePlaceHolder {
	
	private static MainFrame mf = null;
	private static ProcessingFrame pf = null;
	private static CommFrame commf = null;
	private static ControlFrame conf = null;
	private static PathToleranceFrame pathf = null;
	private static SpeedFrame speedf = null;
	private static MiniInfoFrame minInfof = null;
	private static DebugFrame debugf = null;
	private static ImageThresholdsFrame imgThresholdf = null;

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
	public static synchronized void setPathFrame(PathToleranceFrame pathf) {
		FramePlaceHolder.pathf = pathf;
	}
	public static synchronized PathToleranceFrame getPathFrame() {
		return pathf;
	}
	public static void setSpeedFrame(SpeedFrame speedf) {
		FramePlaceHolder.speedf = speedf;
	}
	public static SpeedFrame getSpeedFrame() {
		return speedf;
	}
	public static void setMinInfoFrame(MiniInfoFrame minInfof) {
		FramePlaceHolder.minInfof = minInfof;
	}
	public static MiniInfoFrame getMinInfoFrame() {
		return minInfof;
	}
	public static void setDebugf(DebugFrame debugf) {
		FramePlaceHolder.debugf = debugf;
	}
	public static DebugFrame getDebugf() {
		return debugf;
	}
	public static void setImgThresholdFrame(ImageThresholdsFrame imgThresholdf)
	{
		FramePlaceHolder.imgThresholdf = imgThresholdf;
	}
	public static ImageThresholdsFrame getImgThresholdFrame()
	{
		return imgThresholdf;
	}
	
	
}
