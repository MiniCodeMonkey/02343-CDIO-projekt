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

	
//	@Deprecated
//	@Override
//	public void stateChanged(ChangeEvent evt) {
//				
//		if (evt.getSource() instanceof JSlider) {
//			JSlider slider = (JSlider) evt.getSource();
//			
//			if (imageProcessor == null){
//				JOptionPane.showMessageDialog(frame, "Mangler at initalisere imageProcessor", "Fejl", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			
//			// TODO isValueAdjusting   hvis det kører for tungt!
//			
//			if (slider.getParent().getParent()
//					.equals(frame.robotFrontThresholdPanel)) {
//				imageProcessor.setThresholds(IImageProcessor.ROBOTN,
//						new Thresholds(frame.minRedSlider.getValue(),
//								frame.minGreenSlider.getValue(),
//								frame.minBlueSlider.getValue(),
//								frame.maxRedSlider.getValue(),
//								frame.maxGreenSlider.getValue(),
//								frame.maxBlueSlider.getValue()));
//			} else if (slider.getParent().getParent()
//					.equals(frame.robotBackThresholdPanel)) {
//				imageProcessor.setThresholds(IImageProcessor.ROBOTS,
//						new Thresholds(frame.minRedSlider1.getValue(),
//								frame.minGreenSlider1.getValue(),
//								frame.minBlueSlider1.getValue(),
//								frame.maxRedSlider1.getValue(),
//								frame.maxGreenSlider1.getValue(),
//								frame.maxBlueSlider1.getValue()));
//			} else if (slider.getParent().getParent()
//					.equals(frame.cakesThresholdPanel)) {
//				imageProcessor.setThresholds(IImageProcessor.CAKE,
//						new Thresholds(frame.minRedSlider2.getValue(),
//								frame.minGreenSlider2.getValue(),
//								frame.minBlueSlider2.getValue(),
//								frame.maxRedSlider2.getValue(),
//								frame.maxGreenSlider2.getValue(),
//								frame.maxBlueSlider2.getValue()));
//			} else if (slider.getParent().getParent()
//					.equals(frame.obstacleThresholdPanel)) {
//				imageProcessor.setThresholds(IImageProcessor.OBSTACLE,
//						new Thresholds(frame.minRedSlider3.getValue(),
//								frame.minGreenSlider3.getValue(),
//								frame.minBlueSlider3.getValue(),
//								frame.maxRedSlider3.getValue(),
//								frame.maxGreenSlider3.getValue(),
//								frame.maxBlueSlider3.getValue()));
//			} else if (slider.getParent().equals(frame.bufferzonePanel)) {
//				imageProcessor.setObstacleBufferZone(frame.bufzoneSlider.getValue());
//			}
//			
//			
//		}else if (evt.getSource() instanceof JSpinner){
//			JSpinner spinner = (JSpinner) evt.getSource();
//			frame.time_slice = (Float) spinner.getValue();
//		}
//		
//		
//		
//	}
	
	@Override
	public void stateChanged(ChangeEvent e)
	{
		ImageThresholdsFrame frame = FramePlaceHolder.getImgThresholdFrame();
		Object src = e.getSource();
		
		Component parent = ((Component)src).getParent();
		
		if (parent.equals(frame.getR1FrontTab()))
		{
			System.out.println("Robot1 front");
			
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
			System.out.println("Robot1 back");
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
			System.out.println("Robot2 front");
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
			System.out.println("Robot2 back");
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
