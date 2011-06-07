package gui.processing;

import gui.FramePlaceHolder;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.IImageProcessor;
import dk.dtu.imm.c02343.grp4.imageprocessing.imageprocessing.Thresholds;

/**Fælles Listener klasse for alle control i image-processing..
 * @author Morten Hulvej
 *
 */
public class ImageChangeListener implements ChangeListener, ItemListener {

	ProcessingFrame frame = FramePlaceHolder.getProcessingFrame();
	IImageProcessor imageProcessor;
	
	public IImageProcessor getImageProcessor() {
		return imageProcessor;
	}

	public void setImageProcessor(IImageProcessor ip) {
		this.imageProcessor = ip;
	}
	
	@Override
	public void stateChanged(ChangeEvent evt) {
				
		if (evt.getSource() instanceof JSlider) {
			JSlider slider = (JSlider) evt.getSource();
			
			if (imageProcessor == null){
				JOptionPane.showMessageDialog(frame, "Mangler at initalisere imageProcessor", "Fejl", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			// TODO isValueAdjusting   hvis det kører for tungt!
			
			if (slider.getParent().getParent()
					.equals(frame.robotFrontThresholdPanel)) {
				imageProcessor.setThresholds(IImageProcessor.ROBOTN,
						new Thresholds(frame.minRedSlider.getValue(),
								frame.minGreenSlider.getValue(),
								frame.minBlueSlider.getValue(),
								frame.maxRedSlider.getValue(),
								frame.maxGreenSlider.getValue(),
								frame.maxBlueSlider.getValue()));
			} else if (slider.getParent().getParent()
					.equals(frame.robotBackThresholdPanel)) {
				imageProcessor.setThresholds(IImageProcessor.ROBOTS,
						new Thresholds(frame.minRedSlider1.getValue(),
								frame.minGreenSlider1.getValue(),
								frame.minBlueSlider1.getValue(),
								frame.maxRedSlider1.getValue(),
								frame.maxGreenSlider1.getValue(),
								frame.maxBlueSlider1.getValue()));
			} else if (slider.getParent().getParent()
					.equals(frame.cakesThresholdPanel)) {
				imageProcessor.setThresholds(IImageProcessor.CAKE,
						new Thresholds(frame.minRedSlider2.getValue(),
								frame.minGreenSlider2.getValue(),
								frame.minBlueSlider2.getValue(),
								frame.maxRedSlider2.getValue(),
								frame.maxGreenSlider2.getValue(),
								frame.maxBlueSlider2.getValue()));
			} else if (slider.getParent().getParent()
					.equals(frame.obstacleThresholdPanel)) {
				imageProcessor.setThresholds(IImageProcessor.OBSTACLE,
						new Thresholds(frame.minRedSlider3.getValue(),
								frame.minGreenSlider3.getValue(),
								frame.minBlueSlider3.getValue(),
								frame.maxRedSlider3.getValue(),
								frame.maxGreenSlider3.getValue(),
								frame.maxBlueSlider3.getValue()));
			} else if (slider.getParent().equals(frame.bufferzonePanel)) {
				imageProcessor.setObstacleBufferZone(frame.bufzoneSlider.getValue());
			}
			
			
		}else if (evt.getSource() instanceof JSpinner){
			JSpinner spinner = (JSpinner) evt.getSource();
			frame.time_slice = (Float) spinner.getValue();
		}
		
		
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		
		if (e.getStateChange() == ItemEvent.SELECTED)
			frame.webcamFeedPaused = true;
		else if (e.getStateChange() == ItemEvent.DESELECTED){
			frame.webcamFeedPaused = false;
		}
			
		
	}

}
