package gui.image;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.ImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.Thresholds;

/**Fælles Listener klasse for alle control i image-processing..
 * @author Morten Hulvej
 *
 */
public class ImageChangeListener implements ChangeListener, ItemListener {

	ImageFrame frame;
	
	public ImageChangeListener(ImageFrame f) {
		frame = f;
	}
	
	@Override
	public void stateChanged(ChangeEvent evt) {
		
		if (evt.getSource() instanceof JSlider) {
			JSlider slider = (JSlider) evt.getSource();
			
			// TODO isValueAdjusting   hvis det kører for tungt!
			
			if (slider.getParent().getParent()
					.equals(frame.robotFrontThresholdPanel)) {
				ImageProcessor.setThresholds(ImageProcessor.ROBOTN,
						new Thresholds(frame.minRedSlider.getValue(),
								frame.minGreenSlider.getValue(),
								frame.minBlueSlider.getValue(),
								frame.maxRedSlider.getValue(),
								frame.maxGreenSlider.getValue(),
								frame.maxBlueSlider.getValue()));
			} else if (slider.getParent().getParent()
					.equals(frame.robotBackThresholdPanel)) {
				ImageProcessor.setThresholds(ImageProcessor.ROBOTS,
						new Thresholds(frame.minRedSlider1.getValue(),
								frame.minGreenSlider1.getValue(),
								frame.minBlueSlider1.getValue(),
								frame.maxRedSlider1.getValue(),
								frame.maxGreenSlider1.getValue(),
								frame.maxBlueSlider1.getValue()));
			} else if (slider.getParent().getParent()
					.equals(frame.cakesThresholdPanel)) {
				ImageProcessor.setThresholds(ImageProcessor.CAKE,
						new Thresholds(frame.minRedSlider2.getValue(),
								frame.minGreenSlider2.getValue(),
								frame.minBlueSlider2.getValue(),
								frame.maxRedSlider2.getValue(),
								frame.maxGreenSlider2.getValue(),
								frame.maxBlueSlider2.getValue()));
			} else if (slider.getParent().getParent()
					.equals(frame.obstacleThresholdPanel)) {
				ImageProcessor.setThresholds(ImageProcessor.OBSTACLE,
						new Thresholds(frame.minRedSlider3.getValue(),
								frame.minGreenSlider3.getValue(),
								frame.minBlueSlider3.getValue(),
								frame.maxRedSlider3.getValue(),
								frame.maxGreenSlider3.getValue(),
								frame.maxBlueSlider3.getValue()));
			} else if (slider.getParent().equals(frame.bufferzonePanel)) {
				ImageProcessor.setObstacleBufferZone(frame.bufzoneSlider.getValue());
			}
			
			
		}else if (evt.getSource() instanceof JSpinner){
			JSpinner spinner = (JSpinner) evt.getSource();
			frame.time_slice = (Float) spinner.getValue();
		}
		
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if (e.getStateChange() == e.SELECTED)
			frame.webcamFeedPaused = true;
		else if (e.getStateChange() == e.DESELECTED){
			frame.webcamFeedPaused = false;
		}
			
		
	}

}
