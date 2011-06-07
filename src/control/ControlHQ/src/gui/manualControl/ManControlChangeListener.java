package gui.manualControl;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ManControlChangeListener implements ItemListener {

	ControlFrame frame;
	
	public ManControlChangeListener(ControlFrame f) {
		frame = f;
	}
	
	@Override
	public void itemStateChanged(ItemEvent evt) {
		if (evt.getStateChange() == ItemEvent.SELECTED){
			frame.enableControls(true);
		} else if (evt.getStateChange() == ItemEvent.DESELECTED){
			frame.enableControls(false);
		}

	}

}
