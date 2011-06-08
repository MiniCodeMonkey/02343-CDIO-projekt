package gui.speed;

import gui.FramePlaceHolder;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Thresholds;

public class SpeedChangeListener implements ChangeListener {

	SpeedFrame frame = FramePlaceHolder.getSpeedf();
	
	@Override
	public void stateChanged(ChangeEvent e) {
		
		if (e.getSource() instanceof JSlider) {
			JSlider slider = (JSlider) e.getSource();
			
			
			if (slider.equals(frame.slowSpeedSlider)){
				Thresholds.getInstance().setSlowSpeed(frame.slowSpeedSlider.getValue());
			} else if (slider.equals(frame.mediumSpeedSlider)){
				Thresholds.getInstance().setMediumSpeed(frame.mediumSpeedSlider.getValue());
			}else if(slider.equals(frame.highSpeedSlider)){
				Thresholds.getInstance().setHighSpeed(frame.highSpeedSlider.getValue());
			}
			
		}

	}

}
