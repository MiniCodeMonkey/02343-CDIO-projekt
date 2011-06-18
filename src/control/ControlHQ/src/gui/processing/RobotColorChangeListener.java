package gui.processing;

import gui.FramePlaceHolder;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.MainController;


import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.IImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.Thresholds;

/**Fælles Listener klasse for alle control i image-processing..
 * @author Morten Hulvej
 *
 */
public class RobotColorChangeListener implements ChangeListener {

	
	@Override
	public void stateChanged(ChangeEvent e)
	{
		ImageThresholdsFrame frame = FramePlaceHolder.getImgThresholdFrame();
		Object src = e.getSource();
		
		Component parent = ((Component)src).getParent();
		
		if (parent.equals(frame.getR1FrontTab()))
		{
			
			Thresholds t = new Thresholds(
					(Integer)frame.r1MinRed.getValue(), 
					(Integer)frame.r1MinGreen.getValue(), 
					(Integer)frame.r1MinBlue.getValue(),
					(Integer)frame.r1MaxRed.getValue(), 
					(Integer)frame.r1MaxGreen.getValue(), 
					(Integer)frame.r1MaxBlue.getValue());
			
			MainController.getInstance().getImageProcessor().setThresholds(IImageProcessor.ROBOT1N, t);
			
		}
		else if (parent.equals(frame.getR1BackTab()))
		{
			Thresholds t = new Thresholds(
					(Integer)frame.r1MinRed1.getValue(), 
					(Integer)frame.r1MinGreen1.getValue(), 
					(Integer)frame.r1MinBlue1.getValue(),
					(Integer)frame.r1MaxRed1.getValue(), 
					(Integer)frame.r1MaxGreen1.getValue(), 
					(Integer)frame.r1MaxBlue1.getValue());
			
			MainController.getInstance().getImageProcessor().setThresholds(IImageProcessor.ROBOT1S, t);
		}
		else if (parent.equals(frame.getR2FrontTab()))
		{
			Thresholds t = new Thresholds(
					(Integer)frame.r2MinRed.getValue(), 
					(Integer)frame.r2MinGreen.getValue(), 
					(Integer)frame.r2MinBlue.getValue(),
					(Integer)frame.r2MaxRed.getValue(), 
					(Integer)frame.r2MaxGreen.getValue(), 
					(Integer)frame.r2MaxBlue.getValue());
			
			MainController.getInstance().getImageProcessor().setThresholds(IImageProcessor.ROBOT2N, t);
		}
		else if (parent.equals(frame.getR2BackTab()))
		{
			Thresholds t = new Thresholds(
					(Integer)frame.r2MinRed1.getValue(), 
					(Integer)frame.r2MinGreen1.getValue(), 
					(Integer)frame.r2MinBlue1.getValue(),
					(Integer)frame.r2MaxRed1.getValue(), 
					(Integer)frame.r2MaxGreen1.getValue(), 
					(Integer)frame.r2MaxBlue1.getValue());
			
			MainController.getInstance().getImageProcessor().setThresholds(IImageProcessor.ROBOT2S, t);
		}
		
		
		
		
		
		
	
	}

}
