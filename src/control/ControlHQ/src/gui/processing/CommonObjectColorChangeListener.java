package gui.processing;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.MainController;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.IImageProcessor;

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
		

	}

}
