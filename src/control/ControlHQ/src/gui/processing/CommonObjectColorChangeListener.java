package gui.processing;

import gui.FramePlaceHolder;

import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.MainController;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.IImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.Thresholds;

public class CommonObjectColorChangeListener implements ChangeListener
{

	@Override
	public void stateChanged(ChangeEvent e)
	{
		System.out.println("Common object stateListener invoked!");
		
		
		if (e.getSource() instanceof JSlider)
		{
			JSlider slider = (JSlider) e.getSource();
			
			MainController.getInstance().getImageProcessor().setObstacleBufferZone(slider.getValue());
			
		}
		if(e.getSource() instanceof JSpinner){
			JSpinner spinner = (JSpinner) e.getSource();
			ImageThresholdsFrame frame = FramePlaceHolder.getImgThresholdFrame();
			Thresholds obThreshold, cakeThreshold;
			
			//OBSTACLES
			if(spinner.equals(frame.getObsMinRed())
			||(spinner.equals(frame.getObsMinGreen()))
			||(spinner.equals(frame.getObsMinBlue()))
			||(spinner.equals(frame.getObsMaxRed()))
			||(spinner.equals(frame.getObsMaxGreen()))
			||(spinner.equals(frame.getObsMaxBlue()))
			){
				
				obThreshold = new Thresholds(
							(Integer)frame.getObsMinRed().getValue(), 
							(Integer)frame.getObsMinGreen().getValue(), 
							(Integer)frame.getObsMinBlue().getValue(), 
							(Integer)frame.getObsMaxRed().getValue(), 
							(Integer)frame.getObsMaxGreen().getValue(), 
							(Integer)frame.getObsMaxBlue().getValue());
				 
				 MainController.getInstance().getImageProcessor().setThresholds(IImageProcessor.OBSTACLE, obThreshold);
				
			}
			//CAKES
			if(spinner.equals(frame.getCakeMinRed())
			||(spinner.equals(frame.getCakeMinGreen()))
			||(spinner.equals(frame.getCakeMinBlue()))
			||(spinner.equals(frame.getCakeMaxRed()))
			||(spinner.equals(frame.getCakeMaxGreen()))
			||(spinner.equals(frame.getCakeMaxBlue()))
			){
				cakeThreshold = new Thresholds(
							(Integer)frame.getCakeMinRed().getValue(), 
							(Integer)frame.getCakeMinGreen().getValue(), 
							(Integer)frame.getCakeMinBlue().getValue(), 
							(Integer)frame.getCakeMaxRed().getValue(), 
							(Integer)frame.getCakeMaxGreen().getValue(), 
							(Integer)frame.getCakeMaxBlue().getValue());
				 
				 MainController.getInstance().getImageProcessor().setThresholds(IImageProcessor.CAKE, cakeThreshold);
				
			}	
		}
	}
}
